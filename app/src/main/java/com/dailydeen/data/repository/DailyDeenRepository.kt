package com.dailydeen.data.repository

import com.dailydeen.data.local.dao.DailyDeenDao
import com.dailydeen.data.local.entity.*
import kotlinx.coroutines.flow.Flow
import java.util.*

class DailyDeenRepository(private val dao: DailyDeenDao) {

    fun getDailyChallenges(): Flow<List<Challenge>> {
        val startOfDay = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        return dao.getDailyChallenges(startOfDay)
    }

    suspend fun updateChallenge(challenge: Challenge) = dao.updateChallenge(challenge)

    fun getAdhkars(category: String): Flow<List<Adhkar>> = dao.getAdhkarsByCategory(category)

    suspend fun updateAdhkar(adhkar: Adhkar) = dao.updateAdhkar(adhkar)

    fun getLatestHadith(): Flow<Hadith?> = dao.getLatestHadith()

    fun getDailyQuiz(): Flow<List<QuizQuestion>> = dao.getDailyQuiz()

    suspend fun getProgress(date: Long): UserProgress? = dao.getProgressByDate(date)

    suspend fun saveProgress(progress: UserProgress) = dao.insertProgress(progress)

    suspend fun resetChallengesForNewDay(todayStart: Long) {
        dao.resetChallengesForNewDay(todayStart)
    }


    suspend fun updateStreakIfNeeded(todayStart: Long) {
        val today = dao.getProgressByDate(todayStart) ?: return

        // Si journée pas validée -> ne touche pas au streak
        if (today.starsEarned < 3) return

        val yesterdayStart = todayStart - 24L * 60L * 60L * 1000L
        val yesterday = dao.getProgressByDate(yesterdayStart)

        val newStreak = if (yesterday != null && yesterday.starsEarned >= 3) {
            (yesterday.streakCount + 1).coerceAtLeast(1)
        } else {
            1
        }

        // Évite de réécrire si déjà correct
        if (today.streakCount == newStreak) return

        dao.insertProgress(today.copy(streakCount = newStreak))
    }

}
