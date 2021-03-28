package csci4060.project.newaimsapp.database.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.newaimsapp.database.entity.Delivery;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DeliveryDao_Impl implements DeliveryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Delivery> __insertionAdapterOfDelivery;

  private final EntityDeletionOrUpdateAdapter<Delivery> __deletionAdapterOfDelivery;

  private final EntityDeletionOrUpdateAdapter<Delivery> __updateAdapterOfDelivery;

  public DeliveryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDelivery = new EntityInsertionAdapter<Delivery>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Delivery` (`number`,`delivery_req_number`,`delivery_req_line_number`,`product_id`,`product_code`,`product_description`,`requested_quantity`,`unit_of_measurement`,`fill`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Delivery value) {
        stmt.bindLong(1, value.getNumber());
        stmt.bindLong(2, value.getDelivery_req_number());
        stmt.bindLong(3, value.getDelivery_req_line_number());
        stmt.bindLong(4, value.getProduct_id());
        if (value.getProduct_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getProduct_code());
        }
        if (value.getProduct_description() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getProduct_description());
        }
        stmt.bindDouble(7, value.getRequested_quantity());
        if (value.getUnit_of_measurement() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUnit_of_measurement());
        }
        if (value.getFill() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFill());
        }
      }
    };
    this.__deletionAdapterOfDelivery = new EntityDeletionOrUpdateAdapter<Delivery>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Delivery` WHERE `number` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Delivery value) {
        stmt.bindLong(1, value.getNumber());
      }
    };
    this.__updateAdapterOfDelivery = new EntityDeletionOrUpdateAdapter<Delivery>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Delivery` SET `number` = ?,`delivery_req_number` = ?,`delivery_req_line_number` = ?,`product_id` = ?,`product_code` = ?,`product_description` = ?,`requested_quantity` = ?,`unit_of_measurement` = ?,`fill` = ? WHERE `number` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Delivery value) {
        stmt.bindLong(1, value.getNumber());
        stmt.bindLong(2, value.getDelivery_req_number());
        stmt.bindLong(3, value.getDelivery_req_line_number());
        stmt.bindLong(4, value.getProduct_id());
        if (value.getProduct_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getProduct_code());
        }
        if (value.getProduct_description() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getProduct_description());
        }
        stmt.bindDouble(7, value.getRequested_quantity());
        if (value.getUnit_of_measurement() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUnit_of_measurement());
        }
        if (value.getFill() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFill());
        }
        stmt.bindLong(10, value.getNumber());
      }
    };
  }

  @Override
  public void addDelivery(final Delivery delivery) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDelivery.insert(delivery);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeDelivery(final Delivery delivery) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDelivery.handle(delivery);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateDelivery(final Delivery delivery) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDelivery.handle(delivery);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
