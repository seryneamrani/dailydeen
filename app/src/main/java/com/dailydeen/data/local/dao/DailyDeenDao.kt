package com.dailydeen.data.local.dao

import androidx.room.*
import com.dailydeen.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyDeenDao {
    // Challenges
    @Query("SELECT * FROM challenges WHERE date >= :startOfDay")
    fun getDailyChallenges(startOfDay: Long): Flow<List<Challenge>>

    @Query("UPDATE challenges SET isCompleted = 0, date = :todayStart")
    suspend fun resetChallengesForNewDay(todayStart: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenge(challenge: Challenge)

    @Update
    suspend fun updateChallenge(challenge: Challenge)

    // Adhkars
    @Query("SELECT * FROM adhkars WHERE category = :category")
    fun getAdhkarsByCategory(category: String): Flow<List<Adhkar>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdhkar(adhkar: Adhkar)

    @Update
    suspend fun updateAdhkar(adhkar: Adhkar)

    // Hadith
    @Query("SELECT * FROM hadiths ORDER BY date DESC LIMIT 1")
    fun getLatestHadith(): Flow<Hadith?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHadith(hadith: Hadith)

    // Quiz
    @Query("SELECT * FROM quiz_questions LIMIT 10")
    fun getDailyQuiz(): Flow<List<QuizQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestion(question: QuizQuestion)

    // Progress
    @Query("SELECT * FROM user_progress WHERE date = :date")
    suspend fun getProgressByDate(date: Long): UserProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserProgress)

    @Query("SELECT * FROM user_progress WHERE date = :date")
    fun observeProgressByDate(date: Long): Flow<UserProgress?>



    // Counts (to avoid reseeding every launch)
    @Query("SELECT COUNT(*) FROM challenges")
    suspend fun countChallenges(): Int

    @Query("SELECT COUNT(*) FROM adhkars")
    suspend fun countAdhkars(): Int

    @Query("SELECT COUNT(*) FROM hadiths")
    suspend fun countHadiths(): Int

    @Query("SELECT COUNT(*) FROM quiz_questions")
    suspend fun countQuizQuestions(): Int

}
