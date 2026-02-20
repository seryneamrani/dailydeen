package com.dailydeen.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/dailydeen/data/local/DailyDeenDatabase;", "Landroidx/room/RoomDatabase;", "()V", "dailyDeenDao", "Lcom/dailydeen/data/local/dao/DailyDeenDao;", "app_debug"})
@androidx.room.Database(entities = {com.dailydeen.data.local.entity.Challenge.class, com.dailydeen.data.local.entity.Adhkar.class, com.dailydeen.data.local.entity.Hadith.class, com.dailydeen.data.local.entity.QuizQuestion.class, com.dailydeen.data.local.entity.UserProgress.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.dailydeen.data.local.Converters.class})
public abstract class DailyDeenDatabase extends androidx.room.RoomDatabase {
    
    public DailyDeenDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.dailydeen.data.local.dao.DailyDeenDao dailyDeenDao();
}