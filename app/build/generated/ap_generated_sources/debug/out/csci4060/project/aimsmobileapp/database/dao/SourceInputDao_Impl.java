package csci4060.project.aimsmobileapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.aimsmobileapp.database.entity.SourceInput;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SourceInputDao_Impl implements SourceInputDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SourceInput> __insertionAdapterOfSourceInput;

  private final EntityDeletionOrUpdateAdapter<SourceInput> __updateAdapterOfSourceInput;

  private final SharedSQLiteStatement __preparedStmtOfSetProduct_type;

  private final SharedSQLiteStatement __preparedStmtOfSetStart_date;

  private final SharedSQLiteStatement __preparedStmtOfSetStart_time;

  private final SharedSQLiteStatement __preparedStmtOfSetEnd_date;

  private final SharedSQLiteStatement __preparedStmtOfSetEnd_time;

  private final SharedSQLiteStatement __preparedStmtOfSetTrailer_gross_quantity;

  private final SharedSQLiteStatement __preparedStmtOfSetTrailer_net_quantity;

  private final SharedSQLiteStatement __preparedStmtOfSetStart_meter_reading;

  private final SharedSQLiteStatement __preparedStmtOfSetEnd_meter_reading;

  private final SharedSQLiteStatement __preparedStmtOfSetPickup_gross_quantity;

  private final SharedSQLiteStatement __preparedStmtOfSetPickup_net_quantity;

  private final SharedSQLiteStatement __preparedStmtOfSetBol_number;

  private final SharedSQLiteStatement __preparedStmtOfSetPickup_ratio;

  public SourceInputDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSourceInput = new EntityInsertionAdapter<SourceInput>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `SourceInput` (`trip_id`,`sequence_id`,`product_type`,`start_date`,`start_time`,`end_date`,`end_time`,`trailer_gross_quantity`,`trailer_net_quantity`,`start_meter_reading`,`end_meter_reading`,`pickup_gross_quantity`,`pickup_net_quantity`,`bol_number`,`pickup_ratio`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SourceInput value) {
        stmt.bindLong(1, value.getTrip_id());
        stmt.bindLong(2, value.getSequence_id());
        if (value.getProduct_type() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getProduct_type());
        }
        if (value.getStart_date() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStart_date());
        }
        if (value.getStart_time() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStart_time());
        }
        if (value.getEnd_date() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEnd_date());
        }
        if (value.getEnd_time() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEnd_time());
        }
        stmt.bindDouble(8, value.getTrailer_gross_quantity());
        stmt.bindDouble(9, value.getTrailer_net_quantity());
        stmt.bindDouble(10, value.getStart_meter_reading());
        stmt.bindDouble(11, value.getEnd_meter_reading());
        stmt.bindDouble(12, value.getPickup_gross_quantity());
        stmt.bindDouble(13, value.getPickup_net_quantity());
        stmt.bindLong(14, value.getBol_number());
        stmt.bindDouble(15, value.getPickup_ratio());
      }
    };
    this.__updateAdapterOfSourceInput = new EntityDeletionOrUpdateAdapter<SourceInput>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `SourceInput` SET `trip_id` = ?,`sequence_id` = ?,`product_type` = ?,`start_date` = ?,`start_time` = ?,`end_date` = ?,`end_time` = ?,`trailer_gross_quantity` = ?,`trailer_net_quantity` = ?,`start_meter_reading` = ?,`end_meter_reading` = ?,`pickup_gross_quantity` = ?,`pickup_net_quantity` = ?,`bol_number` = ?,`pickup_ratio` = ? WHERE `trip_id` = ? AND `sequence_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SourceInput value) {
        stmt.bindLong(1, value.getTrip_id());
        stmt.bindLong(2, value.getSequence_id());
        if (value.getProduct_type() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getProduct_type());
        }
        if (value.getStart_date() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStart_date());
        }
        if (value.getStart_time() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStart_time());
        }
        if (value.getEnd_date() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEnd_date());
        }
        if (value.getEnd_time() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEnd_time());
        }
        stmt.bindDouble(8, value.getTrailer_gross_quantity());
        stmt.bindDouble(9, value.getTrailer_net_quantity());
        stmt.bindDouble(10, value.getStart_meter_reading());
        stmt.bindDouble(11, value.getEnd_meter_reading());
        stmt.bindDouble(12, value.getPickup_gross_quantity());
        stmt.bindDouble(13, value.getPickup_net_quantity());
        stmt.bindLong(14, value.getBol_number());
        stmt.bindDouble(15, value.getPickup_ratio());
        stmt.bindLong(16, value.getTrip_id());
        stmt.bindLong(17, value.getSequence_id());
      }
    };
    this.__preparedStmtOfSetProduct_type = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET product_type = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStart_date = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET start_date = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStart_time = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET start_time = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetEnd_date = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET end_date = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetEnd_time = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET end_time = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetTrailer_gross_quantity = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET trailer_gross_quantity = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetTrailer_net_quantity = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET trailer_net_quantity = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStart_meter_reading = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET start_meter_reading = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetEnd_meter_reading = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET end_meter_reading = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetPickup_gross_quantity = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET pickup_gross_quantity = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetPickup_net_quantity = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET pickup_net_quantity = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetBol_number = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET bol_number = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetPickup_ratio = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SourceInput SET pickup_ratio = ? WHERE trip_id = ? AND sequence_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void addSourceInput(final SourceInput sourceInput) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSourceInput.insert(sourceInput);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateSourceInput(final SourceInput sourceInput) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSourceInput.handle(sourceInput);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setProduct_type(final String product_type, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetProduct_type.acquire();
    int _argIndex = 1;
    if (product_type == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, product_type);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetProduct_type.release(_stmt);
    }
  }

  @Override
  public void setStart_date(final String start_date, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetStart_date.acquire();
    int _argIndex = 1;
    if (start_date == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, start_date);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetStart_date.release(_stmt);
    }
  }

  @Override
  public void setStart_time(final String start_time, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetStart_time.acquire();
    int _argIndex = 1;
    if (start_time == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, start_time);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetStart_time.release(_stmt);
    }
  }

  @Override
  public void setEnd_date(final String end_date, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetEnd_date.acquire();
    int _argIndex = 1;
    if (end_date == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, end_date);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetEnd_date.release(_stmt);
    }
  }

  @Override
  public void setEnd_time(final String end_time, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetEnd_time.acquire();
    int _argIndex = 1;
    if (end_time == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, end_time);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetEnd_time.release(_stmt);
    }
  }

  @Override
  public void setTrailer_gross_quantity(final double trailer_gross_quantity, final int trip_id,
      final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetTrailer_gross_quantity.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, trailer_gross_quantity);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetTrailer_gross_quantity.release(_stmt);
    }
  }

  @Override
  public void setTrailer_net_quantity(final double trailer_net_quantity, final int trip_id,
      final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetTrailer_net_quantity.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, trailer_net_quantity);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetTrailer_net_quantity.release(_stmt);
    }
  }

  @Override
  public void setStart_meter_reading(final double start_meter_reading, final int trip_id,
      final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetStart_meter_reading.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, start_meter_reading);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetStart_meter_reading.release(_stmt);
    }
  }

  @Override
  public void setEnd_meter_reading(final double end_meter_reading, final int trip_id,
      final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetEnd_meter_reading.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, end_meter_reading);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetEnd_meter_reading.release(_stmt);
    }
  }

  @Override
  public void setPickup_gross_quantity(final double pickup_gross_quantity, final int trip_id,
      final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetPickup_gross_quantity.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, pickup_gross_quantity);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetPickup_gross_quantity.release(_stmt);
    }
  }

  @Override
  public void setPickup_net_quantity(final double pickup_net_quantity, final int trip_id,
      final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetPickup_net_quantity.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, pickup_net_quantity);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetPickup_net_quantity.release(_stmt);
    }
  }

  @Override
  public void setBol_number(final int bol_number, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetBol_number.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, bol_number);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetBol_number.release(_stmt);
    }
  }

  @Override
  public void setPickup_ratio(final double pickup_ratio, final int trip_id, final int sequence_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetPickup_ratio.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, pickup_ratio);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trip_id);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, sequence_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetPickup_ratio.release(_stmt);
    }
  }

  @Override
  public String getProduct_type(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT product_type FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
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
      _statement.release();
    }
  }

  @Override
  public String getStart_date(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT start_date FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
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
      _statement.release();
    }
  }

  @Override
  public String getStart_time(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT start_time FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
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
      _statement.release();
    }
  }

  @Override
  public String getEnd_date(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT end_date FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
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
      _statement.release();
    }
  }

  @Override
  public String getEnd_time(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT end_time FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
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
      _statement.release();
    }
  }

  @Override
  public double getTrailer_gross_quantity(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT trailer_gross_quantity FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getTrailer_net_quantity(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT trailer_net_quantity FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getStart_meter_reading(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT start_meter_reading FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getEnd_meter_reading(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT end_meter_reading FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getPickup_gross_quantity(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT pickup_gross_quantity FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getPickup_net_quantity(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT pickup_net_quantity FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getBol_number(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT bol_number FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getPickup_ratio(final int trip_id, final int sequence_id) {
    final String _sql = "SELECT pickup_ratio FROM SourceInput WHERE trip_id = ? AND sequence_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, trip_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sequence_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}