package com.dailydeen.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        // Existing keys
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val USER_NAME = stringPreferencesKey("user_name")

        // New notification settings
        val KEY_NOTIF_ENABLED = booleanPreferencesKey("notif_enabled")
        val KEY_NOTIF_ADHKAR = booleanPreferencesKey("notif_adhkar")
        val KEY_NOTIF_CHALLENGES = booleanPreferencesKey("notif_challenges")
        val KEY_NOTIF_QUIZ = booleanPreferencesKey("notif_quiz")
        val KEY_NOTIF_HOUR = intPreferencesKey("notif_hour")      // 0..23
        val KEY_NOTIF_MINUTE = intPreferencesKey("notif_minute")  // 0..59

        val KEY_LAST_RESET_DAY = longPreferencesKey("last_reset_day")

    }

    // ---- Existing flows ----
    val isDarkMode: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[DARK_MODE] ?: false
    }

    // Keep for backward-compat (your old "Daily Reminders" switch)
    val notificationsEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[NOTIFICATIONS_ENABLED] ?: true
    }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_MODE] = enabled
        }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    // ---- New flows for configurable reminders ----
    val notifEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_NOTIF_ENABLED] ?: true
    }

    val notifAdhkar: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_NOTIF_ADHKAR] ?: true
    }

    val notifChallenges: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_NOTIF_CHALLENGES] ?: true
    }

    val notifQuiz: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_NOTIF_QUIZ] ?: true
    }

    val notifHour: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[KEY_NOTIF_HOUR] ?: 20
    }

    val notifMinute: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[KEY_NOTIF_MINUTE] ?: 0
    }

    val lastResetDay = context.dataStore.data.map { prefs ->
        prefs[KEY_LAST_RESET_DAY] ?: 0L
    }

    suspend fun setLastResetDay(dayStart: Long) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LAST_RESET_DAY] = dayStart
        }
    }


    suspend fun setNotifEnabled(v: Boolean) {
        context.dataStore.edit { prefs -> prefs[KEY_NOTIF_ENABLED] = v }
    }

    suspend fun setNotifAdhkar(v: Boolean) {
        context.dataStore.edit { prefs -> prefs[KEY_NOTIF_ADHKAR] = v }
    }

    suspend fun setNotifChallenges(v: Boolean) {
        context.dataStore.edit { prefs -> prefs[KEY_NOTIF_CHALLENGES] = v }
    }

    suspend fun setNotifQuiz(v: Boolean) {
        context.dataStore.edit { prefs -> prefs[KEY_NOTIF_QUIZ] = v }
    }



    suspend fun setNotifTime(hour: Int, minute: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_NOTIF_HOUR] = hour.coerceIn(0, 23)
            prefs[KEY_NOTIF_MINUTE] = minute.coerceIn(0, 59)
        }
    }
}
