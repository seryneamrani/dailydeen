package com.dailydeen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001b"}, d2 = {"Lcom/dailydeen/DailyDeenApp;", "Landroid/app/Application;", "()V", "applicationScope", "Lkotlinx/coroutines/CoroutineScope;", "dataStoreManager", "Lcom/dailydeen/data/local/DataStoreManager;", "getDataStoreManager", "()Lcom/dailydeen/data/local/DataStoreManager;", "setDataStoreManager", "(Lcom/dailydeen/data/local/DataStoreManager;)V", "database", "Lcom/dailydeen/data/local/DailyDeenDatabase;", "getDatabase", "()Lcom/dailydeen/data/local/DailyDeenDatabase;", "setDatabase", "(Lcom/dailydeen/data/local/DailyDeenDatabase;)V", "repository", "Lcom/dailydeen/data/repository/DailyDeenRepository;", "getRepository", "()Lcom/dailydeen/data/repository/DailyDeenRepository;", "setRepository", "(Lcom/dailydeen/data/repository/DailyDeenRepository;)V", "onCreate", "", "scheduleDailyReminders", "seedDatabase", "app_debug"})
public final class DailyDeenApp extends android.app.Application {
    public com.dailydeen.data.local.DailyDeenDatabase database;
    public com.dailydeen.data.repository.DailyDeenRepository repository;
    public com.dailydeen.data.local.DataStoreManager dataStoreManager;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope applicationScope = null;
    
    public DailyDeenApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dailydeen.data.local.DailyDeenDatabase getDatabase() {
        return null;
    }
    
    public final void setDatabase(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.DailyDeenDatabase p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dailydeen.data.repository.DailyDeenRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.repository.DailyDeenRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dailydeen.data.local.DataStoreManager getDataStoreManager() {
        return null;
    }
    
    public final void setDataStoreManager(@org.jetbrains.annotations.NotNull
    com.dailydeen.data.local.DataStoreManager p0) {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void scheduleDailyReminders() {
    }
    
    private final void seedDatabase() {
    }
}