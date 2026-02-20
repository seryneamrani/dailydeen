package com.dailydeen.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u00100\u001a\u000201J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u000b032\u0006\u00105\u001a\u000206J\u0012\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u000208H\u0002J\u000e\u0010:\u001a\u0002012\u0006\u0010;\u001a\u000204J\b\u0010<\u001a\u000201H\u0002J\b\u0010=\u001a\u000201H\u0002J\u000e\u0010>\u001a\u0002012\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u000201H\u0082@\u00a2\u0006\u0002\u0010BJ\u000e\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020\u0018J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0018J\u000e\u0010H\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0018J\u000e\u0010I\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0018J\u000e\u0010J\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0018J\u0016\u0010K\u001a\u00020F2\u0006\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u000eJ\u000e\u0010N\u001a\u0002012\u0006\u0010O\u001a\u00020\u0012R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0010R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\r\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0010R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00180\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0010R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0010R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0010\u00a8\u0006P"}, d2 = {"Lcom/dailydeen/ui/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/dailydeen/data/repository/DailyDeenRepository;", "dataStoreManager", "Lcom/dailydeen/data/local/DataStoreManager;", "(Lcom/dailydeen/data/repository/DailyDeenRepository;Lcom/dailydeen/data/local/DataStoreManager;)V", "_userProgress", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dailydeen/data/local/entity/UserProgress;", "_weeklyProgress", "", "challengesCompletedToday", "Lkotlinx/coroutines/flow/StateFlow;", "", "getChallengesCompletedToday", "()Lkotlinx/coroutines/flow/StateFlow;", "dailyChallenges", "Lcom/dailydeen/data/local/entity/Challenge;", "getDailyChallenges", "dailyQuiz", "Lcom/dailydeen/data/local/entity/QuizQuestion;", "getDailyQuiz", "isDarkMode", "", "latestHadith", "Lcom/dailydeen/data/local/entity/Hadith;", "getLatestHadith", "notifAdhkar", "getNotifAdhkar", "notifChallenges", "getNotifChallenges", "notifEnabled", "getNotifEnabled", "notifHour", "getNotifHour", "notifMinute", "getNotifMinute", "notifQuiz", "getNotifQuiz", "quizzesCompletedToday", "getQuizzesCompletedToday", "starsToday", "getStarsToday", "userProgress", "getUserProgress", "weeklyProgress", "getWeeklyProgress", "completeQuiz", "", "getAdhkars", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dailydeen/data/local/entity/Adhkar;", "category", "", "getStartOfDay", "", "timestamp", "incrementAdhkar", "adhkar", "loadTodayProgress", "loadWeeklyProgress", "rescheduleReminders", "context", "Landroid/content/Context;", "resetIfNewDay", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDarkMode", "enabled", "setNotifAdhkar", "Lkotlinx/coroutines/Job;", "v", "setNotifChallenges", "setNotifEnabled", "setNotifQuiz", "setNotifTime", "hour", "minute", "toggleChallenge", "challenge", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.dailydeen.data.repository.DailyDeenRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final com.dailydeen.data.local.DataStoreManager dataStoreManager = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dailydeen.data.local.entity.Challenge>> dailyChallenges = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.dailydeen.data.local.entity.Hadith> latestHadith = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dailydeen.data.local.entity.QuizQuestion>> dailyQuiz = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.dailydeen.data.local.entity.UserProgress>> _weeklyProgress = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dailydeen.data.local.entity.UserProgress>> weeklyProgress = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isDarkMode = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dailydeen.data.local.entity.UserProgress> _userProgress = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.dailydeen.data.local.entity.UserProgress> userProgress = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> starsToday = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> challengesCompletedToday = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> quizzesCompletedToday = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> notifEnabled = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> notifAdhkar = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> notifChallenges = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> notifQuiz = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> notifHour = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> notifMinute = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.repository.DailyDeenRepository repository, @org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.DataStoreManager dataStoreManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dailydeen.data.local.entity.Challenge>> getDailyChallenges() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.dailydeen.data.local.entity.Hadith> getLatestHadith() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dailydeen.data.local.entity.QuizQuestion>> getDailyQuiz() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dailydeen.data.local.entity.UserProgress>> getWeeklyProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isDarkMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.dailydeen.data.local.entity.UserProgress> getUserProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getStarsToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getChallengesCompletedToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getQuizzesCompletedToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getNotifEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getNotifAdhkar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getNotifChallenges() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getNotifQuiz() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getNotifHour() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getNotifMinute() {
        return null;
    }
    
    private final java.lang.Object resetIfNewDay(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void loadWeeklyProgress() {
    }
    
    private final void loadTodayProgress() {
    }
    
    private final long getStartOfDay(long timestamp) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.dailydeen.data.local.entity.Adhkar>> getAdhkars(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
        return null;
    }
    
    public final void incrementAdhkar(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Adhkar adhkar) {
    }
    
    public final void toggleChallenge(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.entity.Challenge challenge) {
    }
    
    public final void setDarkMode(boolean enabled) {
    }
    
    public final void completeQuiz() {
    }
    
    public final void rescheduleReminders(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job setNotifEnabled(boolean v) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job setNotifAdhkar(boolean v) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job setNotifChallenges(boolean v) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job setNotifQuiz(boolean v) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job setNotifTime(int hour, int minute) {
        return null;
    }
}