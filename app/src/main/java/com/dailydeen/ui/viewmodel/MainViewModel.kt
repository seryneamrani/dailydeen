package com.dailydeen.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dailydeen.data.local.DataStoreManager
import com.dailydeen.data.local.entity.Challenge
import com.dailydeen.data.local.entity.UserProgress
import com.dailydeen.data.repository.DailyDeenRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import android.content.Context
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.first
import com.dailydeen.worker.DailyReminderWorker
class MainViewModel(
    private val repository: DailyDeenRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val dailyChallenges: StateFlow<List<Challenge>> = repository.getDailyChallenges()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val latestHadith = repository.getLatestHadith()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val dailyQuiz = repository.getDailyQuiz()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _weeklyProgress = MutableStateFlow<List<UserProgress>>(emptyList())
    val weeklyProgress: StateFlow<List<UserProgress>> = _weeklyProgress.asStateFlow()

    val isDarkMode = dataStoreManager.isDarkMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private val _userProgress = MutableStateFlow<UserProgress?>(null)
    val userProgress: StateFlow<UserProgress?> = _userProgress.asStateFlow()

    val starsToday: StateFlow<Int> = userProgress
        .map { it?.starsEarned ?: 0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val challengesCompletedToday: StateFlow<Int> = userProgress
        .map { it?.challengesCompleted ?: 0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val quizzesCompletedToday: StateFlow<Int> = userProgress
        .map { it?.quizzesCompleted ?: 0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val notifEnabled = dataStoreManager.notifEnabled.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)
    val notifAdhkar = dataStoreManager.notifAdhkar.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)
    val notifChallenges = dataStoreManager.notifChallenges.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)
    val notifQuiz = dataStoreManager.notifQuiz.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)
    val notifHour = dataStoreManager.notifHour.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 20)
    val notifMinute = dataStoreManager.notifMinute.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)



    init {
        viewModelScope.launch {
            resetIfNewDay()
            loadTodayProgress()
            loadWeeklyProgress()
        }
    }

    private suspend fun resetIfNewDay() {
        val todayStart = getStartOfDay()
        val lastReset = dataStoreManager.lastResetDay.first()

        if (lastReset == todayStart) return

        // 1) Reset challenges + remettre la date à aujourd'hui (sinon page vide demain)
        repository.resetChallengesForNewDay(todayStart)

        // 2) Créer/sauver progress du jour si absent (sinon pas persisté)
        val existing = repository.getProgress(todayStart)
        if (existing == null) {
            repository.saveProgress(UserProgress(date = todayStart))
        }

        // 3) Marquer reset fait
        dataStoreManager.setLastResetDay(todayStart)
    }

    private fun loadWeeklyProgress() {
        viewModelScope.launch {
            val last7Days = mutableListOf<UserProgress>()
            val calendar = Calendar.getInstance()
            for (i in 0..6) {
                val date = getStartOfDay(calendar.timeInMillis)
                val progress = repository.getProgress(date) ?: UserProgress(date = date)
                last7Days.add(progress)
                calendar.add(Calendar.DAY_OF_YEAR, -1)
            }
            _weeklyProgress.value = last7Days.reversed()
        }
    }

    private fun loadTodayProgress() {
        viewModelScope.launch {
            val today = getStartOfDay()
            val progress = repository.getProgress(today) ?: UserProgress(date = today)
            _userProgress.value = progress
        }
    }

    private fun getStartOfDay(timestamp: Long = System.currentTimeMillis()): Long {
        return Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    fun getAdhkars(category: String) = repository.getAdhkars(category)

    fun incrementAdhkar(adhkar: com.dailydeen.data.local.entity.Adhkar) {
        if (adhkar.currentCount < adhkar.targetCount) {
            viewModelScope.launch {
                repository.updateAdhkar(adhkar.copy(currentCount = adhkar.currentCount + 1))
            }
        }
    }

    fun toggleChallenge(challenge: Challenge) {
        viewModelScope.launch {

            val updatedChallenge = challenge.copy(isCompleted = !challenge.isCompleted)
            repository.updateChallenge(updatedChallenge)

            val today = getStartOfDay()
            val currentProgress = _userProgress.value ?: UserProgress(date = today)

            val newChallengesCount = if (updatedChallenge.isCompleted) {
                currentProgress.challengesCompleted + 1
            } else {
                (currentProgress.challengesCompleted - 1).coerceAtLeast(0)
            }

            val newStars = if (updatedChallenge.isCompleted) {
                currentProgress.starsEarned + 1
            } else {
                (currentProgress.starsEarned - 1).coerceAtLeast(0)
            }

            val updatedProgress = currentProgress.copy(
                challengesCompleted = newChallengesCount,
                starsEarned = newStars
            )

            repository.saveProgress(updatedProgress)
            _userProgress.value = updatedProgress
            repository.updateStreakIfNeeded(updatedProgress.date)
            _userProgress.value = repository.getProgress(updatedProgress.date) ?: updatedProgress
            loadWeeklyProgress()

        }
    }




    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setDarkMode(enabled)
        }

    }

    fun completeQuiz() {
        viewModelScope.launch {

            val today = getStartOfDay()
            val currentProgress = _userProgress.value ?: UserProgress(date = today)
            if (currentProgress.quizzesCompleted >= 1) return@launch


            val updatedProgress = currentProgress.copy(
                quizzesCompleted = currentProgress.quizzesCompleted + 1,
                starsEarned = currentProgress.starsEarned + 2
            )

            repository.saveProgress(updatedProgress)
            _userProgress.value = updatedProgress

            repository.updateStreakIfNeeded(updatedProgress.date)
            _userProgress.value = repository.getProgress(updatedProgress.date) ?: updatedProgress
            loadWeeklyProgress()

        }
    }
    fun rescheduleReminders(context: Context) {
        viewModelScope.launch {
            val enabled = dataStoreManager.notifEnabled.first()
            val hour = dataStoreManager.notifHour.first()
            val minute = dataStoreManager.notifMinute.first()

            val workManager = WorkManager.getInstance(context)

            workManager.cancelUniqueWork("daily_reminder_once")

            if (!enabled) return@launch

            val now = Calendar.getInstance()
            val next = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                if (before(now)) add(Calendar.DAY_OF_YEAR, 1)
            }

            val delayMs = (next.timeInMillis - now.timeInMillis).coerceAtLeast(0L)

            val request = OneTimeWorkRequestBuilder<DailyReminderWorker>()
                .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
                .build()

            workManager.enqueueUniqueWork(
                "daily_reminder_once",
                ExistingWorkPolicy.REPLACE,
                request
            )
        }
    }





    fun setNotifEnabled(v: Boolean) = viewModelScope.launch { dataStoreManager.setNotifEnabled(v) }
    fun setNotifAdhkar(v: Boolean) = viewModelScope.launch { dataStoreManager.setNotifAdhkar(v) }
    fun setNotifChallenges(v: Boolean) = viewModelScope.launch { dataStoreManager.setNotifChallenges(v) }
    fun setNotifQuiz(v: Boolean) = viewModelScope.launch { dataStoreManager.setNotifQuiz(v) }
    fun setNotifTime(hour: Int, minute: Int) = viewModelScope.launch { dataStoreManager.setNotifTime(hour, minute) }


}
