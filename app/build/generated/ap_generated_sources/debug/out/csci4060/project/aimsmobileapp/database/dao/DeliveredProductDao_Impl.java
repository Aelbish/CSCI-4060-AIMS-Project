package csci4060.project.aimsmobileapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.aimsmobileapp.database.entity.DeliveredProduct;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DeliveredProductDao_Impl implements DeliveredProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DeliveredProduct> __insertionAdapterOfDeliveredProduct;

  private final SharedSQLiteStatement __preparedStmtOfSetBol_number;

  private final SharedSQLiteStatement __preparedStmtOfSetFuel_product;

  private final SharedSQLiteStatement __preparedStmtOfSetStart_load;

  private final SharedSQLiteStatement __preparedStmtOfSetEnd_load;

  private final SharedSQLiteStatement __preparedStmtOfSetGross_picked_up;

  private final SharedSQLiteStatement __preparedStmtOfSetNet_picked_up;

  private final SharedSQLiteStatement __preparedStmtOfSetProduct_name;

  private final SharedSQLiteStatement __preparedStmtOfSetGross_delivered;

  private final SharedSQLiteStatement __preparedStmtOfSetNet_delivered;

  private final SharedSQLiteStatement __preparedStmtOfSetTicket_num;

  private final SharedSQLiteStatement __preparedStmtOfSetStart_truck_meter;

  private final SharedSQLiteStatement __preparedStmtOfSetEnd_truck_meter;

  private final SharedSQLiteStatement __preparedStmtOfSetStart_stick_meter;

  private final SharedSQLiteStatement __preparedStmtOfSetEnd_stick_meter;

  private final SharedSQLiteStatement __preparedStmtOfSetDelivery_comments;

  public DeliveredProductDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDeliveredProduct = new EntityInsertionAdapter<DeliveredProduct>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `DeliveredProduct` (`sequence_number`,`bol_number`,`fuel_product`,`start_load`,`end_load`,`gross_picked_up`,`net_picked_up`,`product_name`,`gross_delivered`,`net_delivered`,`ticket_num`,`start_truck_meter`,`end_truck_meter`,`start_stick_meter`,`end_stick_meter`,`delivery_comments`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DeliveredProduct value) {
        stmt.bindLong(1, value.getSequence_number());
        stmt.bindLong(2, value.getBol_number());
        if (value.getFuel_product() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFuel_product());
        }
        stmt.bindLong(4, value.getStart_load());
        stmt.bindLong(5, value.getEnd_load());
        stmt.bindDouble(6, value.getGross_picked_up());
        stmt.bindDouble(7, value.getNet_picked_up());
        if (value.getProduct_name() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getProduct_name());
        }
        stmt.bindDouble(9, value.getGross_delivered());
        stmt.bindDouble(10, value.getNet_delivered());
        stmt.bindLong(11, value.getTicket_num());
        stmt.bindDouble(12, value.getStart_truck_meter());
        stmt.bindDouble(13, value.getEnd_truck_meter());
        stmt.bindDouble(14, value.getStart_stick_meter());
        stmt.bindDouble(15, value.getEnd_stick_meter());
        if (value.getDelivery_comments() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getDelivery_comments());
        }
      }
    };
    this.__preparedStmtOfSetBol_number = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET bol_number = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetFuel_product = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET fuel_product = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStart_load = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET start_load = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetEnd_load = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET end_load = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetGross_picked_up = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET gross_picked_up = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetNet_picked_up = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET net_picked_up = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetProduct_name = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET product_name = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetGross_delivered = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET gross_delivered = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetNet_delivered = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET net_delivered = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetTicket_num = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET ticket_num = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStart_truck_meter = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET start_truck_meter = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetEnd_truck_meter = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET end_truck_meter = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStart_stick_meter = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET start_stick_meter = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetEnd_stick_meter = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET end_stick_meter = ? WHERE sequence_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetDelivery_comments = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE deliveredproduct SET delivery_comments = ? WHERE sequence_number = ?";
        return _query;
      }
    };
  }

  @Override
  public void addDeliveredProduct(final DeliveredProduct deliveredProduct) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDeliveredProduct.insert(deliveredProduct);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setBol_number(final int bol_number, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetBol_number.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, bol_number);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
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
  public void setFuel_product(final String fuel_product, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetFuel_product.acquire();
    int _argIndex = 1;
    if (fuel_product == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, fuel_product);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetFuel_product.release(_stmt);
    }
  }

  @Override
  public void setStart_load(final long start_load, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetStart_load.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, start_load);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetStart_load.release(_stmt);
    }
  }

  @Override
  public void setEnd_load(final long end_load, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetEnd_load.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, end_load);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetEnd_load.release(_stmt);
    }
  }

  @Override
  public void setGross_picked_up(final double gross_picked_up, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetGross_picked_up.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, gross_picked_up);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetGross_picked_up.release(_stmt);
    }
  }

  @Override
  public void setNet_picked_up(final double net_picked_up, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetNet_picked_up.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, net_picked_up);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetNet_picked_up.release(_stmt);
    }
  }

  @Override
  public void setProduct_name(final String product_name, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetProduct_name.acquire();
    int _argIndex = 1;
    if (product_name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, product_name);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetProduct_name.release(_stmt);
    }
  }

  @Override
  public void setGross_delivered(final double gross_delivered, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetGross_delivered.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, gross_delivered);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetGross_delivered.release(_stmt);
    }
  }

  @Override
  public void setNet_delivered(final double net_delivered, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetNet_delivered.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, net_delivered);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetNet_delivered.release(_stmt);
    }
  }

  @Override
  public void setTicket_num(final int ticket_num, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetTicket_num.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, ticket_num);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetTicket_num.release(_stmt);
    }
  }

  @Override
  public void setStart_truck_meter(final double start_truck_meter, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetStart_truck_meter.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, start_truck_meter);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetStart_truck_meter.release(_stmt);
    }
  }

  @Override
  public void setEnd_truck_meter(final double end_truck_meter, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetEnd_truck_meter.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, end_truck_meter);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetEnd_truck_meter.release(_stmt);
    }
  }

  @Override
  public void setStart_stick_meter(final double start_stick_meter, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetStart_stick_meter.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, start_stick_meter);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetStart_stick_meter.release(_stmt);
    }
  }

  @Override
  public void setEnd_stick_meter(final double end_stick_meter, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetEnd_stick_meter.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, end_stick_meter);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetEnd_stick_meter.release(_stmt);
    }
  }

  @Override
  public void setDelivery_comments(final String delivery_comments, final int sequence_num) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetDelivery_comments.acquire();
    int _argIndex = 1;
    if (delivery_comments == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, delivery_comments);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sequence_num);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetDelivery_comments.release(_stmt);
    }
  }

  @Override
  public int getBol_number(final int sequence_num) {
    final String _sql = "SELECT bol_number FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public String getFuel_product(final int sequence_num) {
    final String _sql = "SELECT fuel_product FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public long getStart_load(final int sequence_num) {
    final String _sql = "SELECT start_load FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final long _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getLong(0);
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
  public long getEnd_load(final int sequence_num) {
    final String _sql = "SELECT end_load FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final long _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getLong(0);
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
  public double getGross_picked_up(final int sequence_num) {
    final String _sql = "SELECT gross_picked_up FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getNet_picked_up(final int sequence_num) {
    final String _sql = "SELECT net_picked_up FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public String getProduct_name(final int sequence_num) {
    final String _sql = "SELECT product_name FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getGross_delivered(final int sequence_num) {
    final String _sql = "SELECT gross_delivered FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getNet_delivered(final int sequence_num) {
    final String _sql = "SELECT net_delivered FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public int getTicket_num(final int sequence_num) {
    final String _sql = "SELECT ticket_num FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getStart_truck_meter(final int sequence_num) {
    final String _sql = "SELECT start_truck_meter FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getEnd_truck_meter(final int sequence_num) {
    final String _sql = "SELECT end_truck_meter FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getStart_stick_meter(final int sequence_num) {
    final String _sql = "SELECT start_stick_meter FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public double getEnd_stick_meter(final int sequence_num) {
    final String _sql = "SELECT end_stick_meter FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
  public String getDelivery_comments(final int sequence_num) {
    final String _sql = "SELECT delivery_comments FROM deliveredproduct WHERE sequence_number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sequence_num);
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
}
