package csci4060.project.aimsmobileapp.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.aimsmobileapp.database.entity.Driver;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DriverDao_Impl implements DriverDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Driver> __insertionAdapterOfDriver;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDriver;

  private final SharedSQLiteStatement __preparedStmtOfSetDriverName;

  public DriverDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDriver = new EntityInsertionAdapter<Driver>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Driver` (`driver_code`,`driver_name`,`truck_id`,`truck_code`,`truck_description`,`trailer_id`,`trailer_code`,`trailer_description`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Driver value) {
        if (value.getDriver_code() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDriver_code());
        }
        if (value.getDriver_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDriver_name());
        }
        stmt.bindLong(3, value.getTruck_id());
        if (value.getTruck_code() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTruck_code());
        }
        if (value.getTruck_description() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTruck_description());
        }
        stmt.bindLong(6, value.getTrailer_id());
        if (value.getTrailer_code() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTrailer_code());
        }
        if (value.getTrailer_description() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTrailer_description());
        }
      }
    };
    this.__preparedStmtOfDeleteDriver = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Driver WHERE driver_code = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetDriverName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Driver SET driver_name = ? WHERE driver_code = ?";
        return _query;
      }
    };
  }

  @Override
  public void addDriver(final Driver driver) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDriver.insert(driver);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDriver(final String driver_code) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDriver.acquire();
    int _argIndex = 1;
    if (driver_code == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, driver_code);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteDriver.release(_stmt);
    }
  }

  @Override
  public void setDriverName(final String driver_code, final String name) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetDriverName.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    if (driver_code == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, driver_code);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetDriverName.release(_stmt);
    }
  }

  @Override
  public LiveData<String> getDriverName(final String driver_code) {
    final String _sql = "SELECT driver_name FROM Driver WHERE driver_code = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (driver_code == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, driver_code);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"Driver"}, false, new Callable<String>() {
      @Override
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if(_cursor.moveToFirst()) {
            _result = _cursor.getString(0);
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
}
