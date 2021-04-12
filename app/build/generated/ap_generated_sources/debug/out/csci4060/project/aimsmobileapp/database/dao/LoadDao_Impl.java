package csci4060.project.aimsmobileapp.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.aimsmobileapp.database.entity.Load;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LoadDao_Impl implements LoadDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Load> __insertionAdapterOfLoad;

  private final EntityDeletionOrUpdateAdapter<Load> __deletionAdapterOfLoad;

  private final EntityDeletionOrUpdateAdapter<Load> __updateAdapterOfLoad;

  public LoadDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoad = new EntityInsertionAdapter<Load>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Load` (`sequence_number`,`waypoint_description`,`latitude`,`longitude`,`trip_id`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Load value) {
        stmt.bindLong(1, value.getSequence_number());
        if (value.getWaypoint_description() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getWaypoint_description());
        }
        stmt.bindDouble(3, value.getLatitude());
        stmt.bindDouble(4, value.getLongitude());
        stmt.bindLong(5, value.getTrip_id());
      }
    };
    this.__deletionAdapterOfLoad = new EntityDeletionOrUpdateAdapter<Load>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Load` WHERE `sequence_number` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Load value) {
        stmt.bindLong(1, value.getSequence_number());
      }
    };
    this.__updateAdapterOfLoad = new EntityDeletionOrUpdateAdapter<Load>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Load` SET `sequence_number` = ?,`waypoint_description` = ?,`latitude` = ?,`longitude` = ?,`trip_id` = ? WHERE `sequence_number` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Load value) {
        stmt.bindLong(1, value.getSequence_number());
        if (value.getWaypoint_description() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getWaypoint_description());
        }
        stmt.bindDouble(3, value.getLatitude());
        stmt.bindDouble(4, value.getLongitude());
        stmt.bindLong(5, value.getTrip_id());
        stmt.bindLong(6, value.getSequence_number());
      }
    };
  }

  @Override
  public void addLoad(final Load load) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLoad.insert(load);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteLoad(final Load load) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfLoad.handle(load);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLoad(final Load load) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLoad.handle(load);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<Integer> getTripId(final int sequence_number) {
    final String _sql = "SELECT trip_id FROM Load WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_number);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Load"}, false, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getInt(0);
            }
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
  public LiveData<List<Load>> getLoads(final int trip_id) {
    final String _sql = "SELECT * FROM Load WHERE trip_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Load"}, false, new Callable<List<Load>>() {
      @Override
      public List<Load> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSequenceNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sequence_number");
          final int _cursorIndexOfWaypointDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "waypoint_description");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_id");
          final List<Load> _result = new ArrayList<Load>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Load _item;
            final int _tmpSequence_number;
            _tmpSequence_number = _cursor.getInt(_cursorIndexOfSequenceNumber);
            final String _tmpWaypoint_description;
            _tmpWaypoint_description = _cursor.getString(_cursorIndexOfWaypointDescription);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final int _tmpTrip_id;
            _tmpTrip_id = _cursor.getInt(_cursorIndexOfTripId);
            _item = new Load(_tmpSequence_number,_tmpWaypoint_description,_tmpLatitude,_tmpLongitude,_tmpTrip_id);
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
