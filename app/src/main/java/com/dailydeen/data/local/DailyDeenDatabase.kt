package com.dailydeen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dailydeen.data.local.dao.DailyDeenDao
import com.dailydeen.data.local.entity.*

@Database(
    entities = [Challenge::class, Adhkar::class, Hadith::class, QuizQuestion::class, UserProgress::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DailyDeenDatabase : RoomDatabase() {
    abstract fun dailyDeenDao(): DailyDeenDao
}

// Converters for List<String> used in QuizQuestion
class Converters {
    @androidx.room.TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @androidx.room.TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
}
