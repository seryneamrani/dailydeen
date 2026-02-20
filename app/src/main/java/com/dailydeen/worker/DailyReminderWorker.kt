package com.dailydeen.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.dailydeen.data.local.DataStoreManager
import com.dailydeen.ui.MainActivity
import kotlinx.coroutines.flow.first
import java.util.Calendar
import java.util.concurrent.TimeUnit

class DailyReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val ds = DataStoreManager(this.applicationContext)

        val enabled = ds.notifEnabled.first()
        if (!enabled) return Result.success()

        val adhkar = ds.notifAdhkar.first()
        val challenges = ds.notifChallenges.first()
        val quiz = ds.notifQuiz.first()

        if (!adhkar && !challenges && !quiz) return Result.success()

        val message = when {
            quiz && !adhkar && !challenges -> "Quiz time! 🧠 Test your knowledge today."
            adhkar && challenges && !quiz -> "Adhkars + Challenges ✅ Keep your daily sunnahs going!"
            adhkar && !challenges && !quiz -> "Time for your adhkars 🤲"
            challenges && !adhkar && !quiz -> "Complete your sunnah challenges ✅"
            else -> {
                val parts = buildList {
                    if (challenges) add("challenges")
                    if (adhkar) add("adhkars")
                    if (quiz) add("quiz")
                }
                "Don't forget your ${parts.joinToString(" + ")} today!"
            }
        }

        showNotification(message)

        // ✅ Replanifie pour demain à l'heure choisie
        scheduleNext()

        return Result.success()
    }

    private fun showNotification(message: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "daily_deen_reminders"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Deen Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // ✅ Click notif opens MainActivity (Home)
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        )

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Daily Deen Reminder")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent) // ✅ IMPORTANT: use the pendingIntent
            .build()

        notificationManager.notify(1, notification)
    }

    private suspend fun scheduleNext() {
        val ds = DataStoreManager(this.applicationContext)

        val enabled = ds.notifEnabled.first()
        if (!enabled) return

        val hour = ds.notifHour.first()
        val minute = ds.notifMinute.first()

        val workManager = WorkManager.getInstance(this.applicationContext)

        val now = Calendar.getInstance()
        val next = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1) // demain
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
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
