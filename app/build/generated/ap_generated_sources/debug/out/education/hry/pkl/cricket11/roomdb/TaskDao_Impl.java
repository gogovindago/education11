package education.hry.pkl.cricket11.roomdb;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfTask;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Task`(`id`,`task`,`desc`,`finish_by`,`finished`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getId());
        if (value.getTask() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTask());
        }
        if (value.getDesc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDesc());
        }
        if (value.getFinishBy() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFinishBy());
        }
        final int _tmp;
        _tmp = value.isFinished() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Task` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Task` SET `id` = ?,`task` = ?,`desc` = ?,`finish_by` = ?,`finished` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getId());
        if (value.getTask() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTask());
        }
        if (value.getDesc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDesc());
        }
        if (value.getFinishBy() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFinishBy());
        }
        final int _tmp;
        _tmp = value.isFinished() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public void insert(Task task) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTask.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Task task) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Task task) {
    __db.beginTransaction();
    try {
      __updateAdapterOfTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Task> getAll() {
    final String _sql = "SELECT * FROM task";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTask = _cursor.getColumnIndexOrThrow("task");
      final int _cursorIndexOfDesc = _cursor.getColumnIndexOrThrow("desc");
      final int _cursorIndexOfFinishBy = _cursor.getColumnIndexOrThrow("finish_by");
      final int _cursorIndexOfFinished = _cursor.getColumnIndexOrThrow("finished");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Task _item;
        _item = new Task();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTask;
        _tmpTask = _cursor.getString(_cursorIndexOfTask);
        _item.setTask(_tmpTask);
        final String _tmpDesc;
        _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
        _item.setDesc(_tmpDesc);
        final String _tmpFinishBy;
        _tmpFinishBy = _cursor.getString(_cursorIndexOfFinishBy);
        _item.setFinishBy(_tmpFinishBy);
        final boolean _tmpFinished;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFinished);
        _tmpFinished = _tmp != 0;
        _item.setFinished(_tmpFinished);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
