package com.dailydeen.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0014\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\rH\'J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n0\t2\u0006\u0010\u0010\u001a\u00020\u0011H\'J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n0\tH\'J\u0010\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\tH\'J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010&J\u0016\u0010\'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010)J\u0018\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\t2\u0006\u0010\u0018\u001a\u00020\u0011H\'J\u0016\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010-\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010.\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010 \u00a8\u0006/"}, d2 = {"Lcom/dailydeen/data/local/dao/DailyDeenDao;", "", "countAdhkars", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countChallenges", "countHadiths", "countQuizQuestions", "getAdhkarsByCategory", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/dailydeen/data/local/entity/Adhkar;", "category", "", "getDailyChallenges", "Lcom/dailydeen/data/local/entity/Challenge;", "startOfDay", "", "getDailyQuiz", "Lcom/dailydeen/data/local/entity/QuizQuestion;", "getLatestHadith", "Lcom/dailydeen/data/local/entity/Hadith;", "getProgressByDate", "Lcom/dailydeen/data/local/entity/UserProgress;", "date", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAdhkar", "", "adhkar", "(Lcom/dailydeen/data/local/entity/Adhkar;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertChallenge", "challenge", "(Lcom/dailydeen/data/local/entity/Challenge;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertHadith", "hadith", "(Lcom/dailydeen/data/local/entity/Hadith;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertProgress", "progress", "(Lcom/dailydeen/data/local/entity/UserProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertQuizQuestion", "question", "(Lcom/dailydeen/data/local/entity/QuizQuestion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeProgressByDate", "resetChallengesForNewDay", "todayStart", "updateAdhkar", "updateChallenge", "app_debug"})
@androidx.room.Dao
public abstract interface DailyDeenDao {
    
    @androidx.room.Query(value = "SELECT * FROM challenges WHERE date >= :startOfDay")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.Challenge>> getDailyChallenges(long startOfDay);
    
    @androidx.room.Query(value = "UPDATE challenges SET isCompleted = 0, date = :todayStart")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object resetChallengesForNewDay(long todayStart, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertChallenge(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Challenge challenge, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateChallenge(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Challenge challenge, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM adhkars WHERE category = :category")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.Adhkar>> getAdhkarsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAdhkar(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Adhkar adhkar, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateAdhkar(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Adhkar adhkar, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM hadiths ORDER BY date DESC LIMIT 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.dailydeen.data.local.entity.Hadith> getLatestHadith();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertHadith(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Hadith hadith, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM quiz_questions LIMIT 10")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.QuizQuestion>> getDailyQuiz();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertQuizQuestion(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.QuizQuestion question, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_progress WHERE date = :date")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProgressByDate(long date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dailydeen.data.local.entity.UserProgress> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertProgress(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.UserProgress progress, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_progress WHERE date = :date")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.dailydeen.data.local.entity.UserProgress> observeProgressByDate(long date);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM challenges")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object countChallenges(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM adhkars")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object countAdhkars(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM hadiths")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object countHadiths(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM quiz_questions")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object countQuizQuestions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}