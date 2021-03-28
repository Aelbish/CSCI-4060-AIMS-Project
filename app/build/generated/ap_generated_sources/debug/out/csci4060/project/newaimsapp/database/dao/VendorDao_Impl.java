package csci4060.project.newaimsapp.database.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.newaimsapp.database.entity.Vendor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VendorDao_Impl implements VendorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vendor> __insertionAdapterOfVendor;

  private final EntityDeletionOrUpdateAdapter<Vendor> __deletionAdapterOfVendor;

  private final EntityDeletionOrUpdateAdapter<Vendor> __updateAdapterOfVendor;

  public VendorDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVendor = new EntityInsertionAdapter<Vendor>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Vendor` (`destination_code`,`destination_name`,`address_1`,`address_2`,`city`,`state_short`,`postal_code`,`number`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vendor value) {
        if (value.getDestination_code() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDestination_code());
        }
        if (value.getDestination_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDestination_name());
        }
        if (value.getAddress_1() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress_1());
        }
        if (value.getAddress_2() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAddress_2());
        }
        if (value.getCity() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCity());
        }
        if (value.getState_short() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getState_short());
        }
        stmt.bindLong(7, value.getPostal_code());
        stmt.bindLong(8, value.getNumber());
      }
    };
    this.__deletionAdapterOfVendor = new EntityDeletionOrUpdateAdapter<Vendor>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Vendor` WHERE `destination_code` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vendor value) {
        if (value.getDestination_code() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDestination_code());
        }
      }
    };
    this.__updateAdapterOfVendor = new EntityDeletionOrUpdateAdapter<Vendor>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Vendor` SET `destination_code` = ?,`destination_name` = ?,`address_1` = ?,`address_2` = ?,`city` = ?,`state_short` = ?,`postal_code` = ?,`number` = ? WHERE `destination_code` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vendor value) {
        if (value.getDestination_code() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDestination_code());
        }
        if (value.getDestination_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDestination_name());
        }
        if (value.getAddress_1() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress_1());
        }
        if (value.getAddress_2() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAddress_2());
        }
        if (value.getCity() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCity());
        }
        if (value.getState_short() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getState_short());
        }
        stmt.bindLong(7, value.getPostal_code());
        stmt.bindLong(8, value.getNumber());
        if (value.getDestination_code() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDestination_code());
        }
      }
    };
  }

  @Override
  public void addVendor(final Vendor vendor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVendor.insert(vendor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteVendor(final Vendor vendor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfVendor.handle(vendor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateVendor(final Vendor vendor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfVendor.handle(vendor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
