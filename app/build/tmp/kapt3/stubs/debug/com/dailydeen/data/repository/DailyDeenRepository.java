package com.dailydeen.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006J\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u0006J\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0006J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/dailydeen/data/repository/DailyDeenRepository;", "", "dao", "Lcom/dailydeen/data/local/dao/DailyDeenDao;", "(Lcom/dailydeen/data/local/dao/DailyDeenDao;)V", "getAdhkars", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/dailydeen/data/local/entity/Adhkar;", "category", "", "getDailyChallenges", "Lcom/dailydeen/data/local/entity/Challenge;", "getDailyQuiz", "Lcom/dailydeen/data/local/entity/QuizQuestion;", "getLatestHadith", "Lcom/dailydeen/data/local/entity/Hadith;", "getProgress", "Lcom/dailydeen/data/local/entity/UserProgress;", "date", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetChallengesForNewDay", "", "todayStart", "saveProgress", "progress", "(Lcom/dailydeen/data/local/entity/UserProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAdhkar", "adhkar", "(Lcom/dailydeen/data/local/entity/Adhkar;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateChallenge", "challenge", "(Lcom/dailydeen/data/local/entity/Challenge;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStreakIfNeeded", "app_debug"})
public final class DailyDeenRepository {
    @org.jetbrains.annotations.NotNull
    private final com.dailydeen.data.local.dao.DailyDeenDao dao = null;
    
    public DailyDeenRepository(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.dao.DailyDeenDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.Challenge>> getDailyChallenges() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateChallenge(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Challenge challenge, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.Adhkar>> getAdhkars(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateAdhkar(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Adhkar adhkar, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.dailydeen.data.local.entity.Hadith> getLatestHadith() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.QuizQuestion>> getDailyQuiz() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getProgress(long date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dailydeen.data.local.entity.UserProgress> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveProgress(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.UserProgress progress, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object resetChallengesForNewDay(long todayStart, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateStreakIfNeeded(long todayStart, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}