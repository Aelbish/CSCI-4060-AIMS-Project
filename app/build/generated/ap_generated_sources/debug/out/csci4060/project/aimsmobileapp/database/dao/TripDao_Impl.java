package csci4060.project.aimsmobileapp.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.aimsmobileapp.database.entity.Trip;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDao_Impl implements TripDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Trip> __insertionAdapterOfTrip;

  private final EntityDeletionOrUpdateAdapter<Trip> __deletionAdapterOfTrip;

  private final EntityDeletionOrUpdateAdapter<Trip> __updateAdapterOfTrip;

  private final SharedSQLiteStatement __preparedStmtOfUpdateIsSelected;

  public TripDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrip = new EntityInsertionAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Trip` (`trip_id`,`trip_name`,`trip_date`,`is_selected`,`driver_code`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getTrip_id());
        if (value.getTrip_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTrip_name());
        }
        if (value.getTrip_date() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTrip_date());
        }
        stmt.bindLong(4, value.getIs_selected());
        if (value.getDriver_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDriver_code());
        }
      }
    };
    this.__deletionAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Trip` WHERE `trip_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getTrip_id());
      }
    };
    this.__updateAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Trip` SET `trip_id` = ?,`trip_name` = ?,`trip_date` = ?,`is_selected` = ?,`driver_code` = ? WHERE `trip_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getTrip_id());
        if (value.getTrip_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTrip_name());
        }
        if (value.getTrip_date() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTrip_date());
        }
        stmt.bindLong(4, value.getIs_selected());
        if (value.getDriver_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDriver_code());
        }
        stmt.bindLong(6, value.getTrip_id());
      }
    };
    this.__preparedStmtOfUpdateIsSelected = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE trip SET is_selected = 1 WHERE trip_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void addTrip(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrip.insert(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTrip(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTrip.handle(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTrip(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTrip.handle(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateIsSelected(final int trip_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateIsSelected.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, trip_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateIsSelected.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Trip>> getAllTrips() {
    final String _sql = "SELECT * FROM trip";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"trip"}, false, new Callable<List<Trip>>() {
      @Override
      public List<Trip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_id");
          final int _cursorIndexOfTripName = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_name");
          final int _cursorIndexOfTripDate = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_date");
          final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "is_selected");
          final int _cursorIndexOfDriverCode = CursorUtil.getColumnIndexOrThrow(_cursor, "driver_code");
          final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Trip _item;
            final int _tmpTrip_id;
            _tmpTrip_id = _cursor.getInt(_cursorIndexOfTripId);
            final String _tmpTrip_name;
            _tmpTrip_name = _cursor.getString(_cursorIndexOfTripName);
            final String _tmpTrip_date;
            _tmpTrip_date = _cursor.getString(_cursorIndexOfTripDate);
            final String _tmpDriver_code;
            _tmpDriver_code = _cursor.getString(_cursorIndexOfDriverCode);
            _item = new Trip(_tmpTrip_id,_tmpTrip_name,_tmpTrip_date,_tmpDriver_code);
            final int _tmpIs_selected;
            _tmpIs_selected = _cursor.getInt(_cursorIndexOfIsSelected);
            _item.setIs_selected(_tmpIs_selected);
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
}
