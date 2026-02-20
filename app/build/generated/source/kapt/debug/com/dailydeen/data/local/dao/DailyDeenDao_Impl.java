package com.dailydeen.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.dailydeen.data.local.Converters;
import com.dailydeen.data.local.entity.Adhkar;
import com.dailydeen.data.local.entity.Challenge;
import com.dailydeen.data.local.entity.Hadith;
import com.dailydeen.data.local.entity.QuizQuestion;
import com.dailydeen.data.local.entity.UserProgress;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DailyDeenDao_Impl implements DailyDeenDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Challenge> __insertionAdapterOfChallenge;

  private final EntityInsertionAdapter<Adhkar> __insertionAdapterOfAdhkar;

  private final EntityInsertionAdapter<Hadith> __insertionAdapterOfHadith;

  private final EntityInsertionAdapter<QuizQuestion> __insertionAdapterOfQuizQuestion;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<UserProgress> __insertionAdapterOfUserProgress;

  private final EntityDeletionOrUpdateAdapter<Challenge> __updateAdapterOfChallenge;

  private final EntityDeletionOrUpdateAdapter<Adhkar> __updateAdapterOfAdhkar;

  private final SharedSQLiteStatement __preparedStmtOfResetChallengesForNewDay;

  public DailyDeenDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChallenge = new EntityInsertionAdapter<Challenge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `challenges` (`id`,`title`,`description`,`isCompleted`,`date`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final Challenge entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getDate());
      }
    };
    this.__insertionAdapterOfAdhkar = new EntityInsertionAdapter<Adhkar>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `adhkars` (`id`,`content`,`translation`,`targetCount`,`currentCount`,`category`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final Adhkar entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        if (entity.getTranslation() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTranslation());
        }
        statement.bindLong(4, entity.getTargetCount());
        statement.bindLong(5, entity.getCurrentCount());
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
      }
    };
    this.__insertionAdapterOfHadith = new EntityInsertionAdapter<Hadith>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `hadiths` (`id`,`content`,`source`,`date`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final Hadith entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        if (entity.getSource() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSource());
        }
        statement.bindLong(4, entity.getDate());
      }
    };
    this.__insertionAdapterOfQuizQuestion = new EntityInsertionAdapter<QuizQuestion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `quiz_questions` (`id`,`question`,`options`,`correctAnswerIndex`,`explanation`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final QuizQuestion entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getQuestion() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getQuestion());
        }
        final String _tmp = __converters.fromList(entity.getOptions());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        statement.bindLong(4, entity.getCorrectAnswerIndex());
        if (entity.getExplanation() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExplanation());
        }
      }
    };
    this.__insertionAdapterOfUserProgress = new EntityInsertionAdapter<UserProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_progress` (`date`,`starsEarned`,`challengesCompleted`,`quizzesCompleted`,`streakCount`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final UserProgress entity) {
        statement.bindLong(1, entity.getDate());
        statement.bindLong(2, entity.getStarsEarned());
        statement.bindLong(3, entity.getChallengesCompleted());
        statement.bindLong(4, entity.getQuizzesCompleted());
        statement.bindLong(5, entity.getStreakCount());
      }
    };
    this.__updateAdapterOfChallenge = new EntityDeletionOrUpdateAdapter<Challenge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `challenges` SET `id` = ?,`title` = ?,`description` = ?,`isCompleted` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final Challenge entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getDate());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__updateAdapterOfAdhkar = new EntityDeletionOrUpdateAdapter<Adhkar>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `adhkars` SET `id` = ?,`content` = ?,`translation` = ?,`targetCount` = ?,`currentCount` = ?,`category` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final Adhkar entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        if (entity.getTranslation() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTranslation());
        }
        statement.bindLong(4, entity.getTargetCount());
        statement.bindLong(5, entity.getCurrentCount());
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfResetChallengesForNewDay = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE challenges SET isCompleted = 0, date = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertChallenge(final Challenge challenge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChallenge.insert(challenge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAdhkar(final Adhkar adhkar, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAdhkar.insert(adhkar);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertHadith(final Hadith hadith, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHadith.insert(hadith);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertQuizQuestion(final QuizQuestion question,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQuizQuestion.insert(question);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertProgress(final UserProgress progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserProgress.insert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateChallenge(final Challenge challenge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfChallenge.handle(challenge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAdhkar(final Adhkar adhkar, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAdhkar.handle(adhkar);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object resetChallengesForNewDay(final long todayStart,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfResetChallengesForNewDay.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, todayStart);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfResetChallengesForNewDay.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Challenge>> getDailyChallenges(final long startOfDay) {
    final String _sql = "SELECT * FROM challenges WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"challenges"}, new Callable<List<Challenge>>() {
      @Override
      @NonNull
      public List<Challenge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Challenge> _result = new ArrayList<Challenge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Challenge _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Challenge(_tmpId,_tmpTitle,_tmpDescription,_tmpIsCompleted,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Adhkar>> getAdhkarsByCategory(final String category) {
    final String _sql = "SELECT * FROM adhkars WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"adhkars"}, new Callable<List<Adhkar>>() {
      @Override
      @NonNull
      public List<Adhkar> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "translation");
          final int _cursorIndexOfTargetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCount");
          final int _cursorIndexOfCurrentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<Adhkar> _result = new ArrayList<Adhkar>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Adhkar _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpTranslation;
            if (_cursor.isNull(_cursorIndexOfTranslation)) {
              _tmpTranslation = null;
            } else {
              _tmpTranslation = _cursor.getString(_cursorIndexOfTranslation);
            }
            final int _tmpTargetCount;
            _tmpTargetCount = _cursor.getInt(_cursorIndexOfTargetCount);
            final int _tmpCurrentCount;
            _tmpCurrentCount = _cursor.getInt(_cursorIndexOfCurrentCount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item = new Adhkar(_tmpId,_tmpContent,_tmpTranslation,_tmpTargetCount,_tmpCurrentCount,_tmpCategory);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Hadith> getLatestHadith() {
    final String _sql = "SELECT * FROM hadiths ORDER BY date DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"hadiths"}, new Callable<Hadith>() {
      @Override
      @Nullable
      public Hadith call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final Hadith _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _result = new Hadith(_tmpId,_tmpContent,_tmpSource,_tmpDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<QuizQuestion>> getDailyQuiz() {
    final String _sql = "SELECT * FROM quiz_questions LIMIT 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"quiz_questions"}, new Callable<List<QuizQuestion>>() {
      @Override
      @NonNull
      public List<QuizQuestion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfOptions = CursorUtil.getColumnIndexOrThrow(_cursor, "options");
          final int _cursorIndexOfCorrectAnswerIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "correctAnswerIndex");
          final int _cursorIndexOfExplanation = CursorUtil.getColumnIndexOrThrow(_cursor, "explanation");
          final List<QuizQuestion> _result = new ArrayList<QuizQuestion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QuizQuestion _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final List<String> _tmpOptions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfOptions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfOptions);
            }
            _tmpOptions = __converters.fromString(_tmp);
            final int _tmpCorrectAnswerIndex;
            _tmpCorrectAnswerIndex = _cursor.getInt(_cursorIndexOfCorrectAnswerIndex);
            final String _tmpExplanation;
            if (_cursor.isNull(_cursorIndexOfExplanation)) {
              _tmpExplanation = null;
            } else {
              _tmpExplanation = _cursor.getString(_cursorIndexOfExplanation);
            }
            _item = new QuizQuestion(_tmpId,_tmpQuestion,_tmpOptions,_tmpCorrectAnswerIndex,_tmpExplanation);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getProgressByDate(final long date,
      final Continuation<? super UserProgress> $completion) {
    final String _sql = "SELECT * FROM user_progress WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProgress>() {
      @Override
      @Nullable
      public UserProgress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfStarsEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "starsEarned");
          final int _cursorIndexOfChallengesCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "challengesCompleted");
          final int _cursorIndexOfQuizzesCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "quizzesCompleted");
          final int _cursorIndexOfStreakCount = CursorUtil.getColumnIndexOrThrow(_cursor, "streakCount");
          final UserProgress _result;
          if (_cursor.moveToFirst()) {
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpStarsEarned;
            _tmpStarsEarned = _cursor.getInt(_cursorIndexOfStarsEarned);
            final int _tmpChallengesCompleted;
            _tmpChallengesCompleted = _cursor.getInt(_cursorIndexOfChallengesCompleted);
            final int _tmpQuizzesCompleted;
            _tmpQuizzesCompleted = _cursor.getInt(_cursorIndexOfQuizzesCompleted);
            final int _tmpStreakCount;
            _tmpStreakCount = _cursor.getInt(_cursorIndexOfStreakCount);
            _result = new UserProgress(_tmpDate,_tmpStarsEarned,_tmpChallengesCompleted,_tmpQuizzesCompleted,_tmpStreakCount);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserProgress> observeProgressByDate(final long date) {
    final String _sql = "SELECT * FROM user_progress WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_progress"}, new Callable<UserProgress>() {
      @Override
      @Nullable
      public UserProgress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfStarsEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "starsEarned");
          final int _cursorIndexOfChallengesCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "challengesCompleted");
          final int _cursorIndexOfQuizzesCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "quizzesCompleted");
          final int _cursorIndexOfStreakCount = CursorUtil.getColumnIndexOrThrow(_cursor, "streakCount");
          final UserProgress _result;
          if (_cursor.moveToFirst()) {
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpStarsEarned;
            _tmpStarsEarned = _cursor.getInt(_cursorIndexOfStarsEarned);
            final int _tmpChallengesCompleted;
            _tmpChallengesCompleted = _cursor.getInt(_cursorIndexOfChallengesCompleted);
            final int _tmpQuizzesCompleted;
            _tmpQuizzesCompleted = _cursor.getInt(_cursorIndexOfQuizzesCompleted);
            final int _tmpStreakCount;
            _tmpStreakCount = _cursor.getInt(_cursorIndexOfStreakCount);
            _result = new UserProgress(_tmpDate,_tmpStarsEarned,_tmpChallengesCompleted,_tmpQuizzesCompleted,_tmpStreakCount);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object countChallenges(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM challenges";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countAdhkars(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM adhkars";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countHadiths(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM hadiths";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countQuizQuestions(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM quiz_questions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
