package com.dailydeen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class Challenge(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val date: Long = System.currentTimeMillis()
)

@Entity(tableName = "adhkars")
data class Adhkar(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val translation: String,
    val targetCount: Int,
    val currentCount: Int = 0,
    val category: String // "morning", "evening", "general"
)

@Entity(tableName = "hadiths")
data class Hadith(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val source: String,
    val date: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey val date: Long, // Use start of day timestamp
    val starsEarned: Int = 0,
    val challengesCompleted: Int = 0,
    val quizzesCompleted: Int = 0,
    val streakCount: Int = 0
)
