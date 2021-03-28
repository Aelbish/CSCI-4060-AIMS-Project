package csci4060.project.newaimsapp.database.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import csci4060.project.newaimsapp.database.entity.Customer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CustomerDao_Impl implements CustomerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Customer> __insertionAdapterOfCustomer;

  private final EntityDeletionOrUpdateAdapter<Customer> __deletionAdapterOfCustomer;

  private final EntityDeletionOrUpdateAdapter<Customer> __updateAdapterOfCustomer;

  public CustomerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCustomer = new EntityInsertionAdapter<Customer>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Customer` (`destination_code`,`destination_name`,`site_container_code`,`site_container_description`,`address_1`,`address_2`,`city`,`state_short`,`postal_code`,`sequence_number`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Customer value) {
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
        if (value.getSite_container_code() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSite_container_code());
        }
        if (value.getSite_container_description() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSite_container_description());
        }
        if (value.getAddress_1() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAddress_1());
        }
        if (value.getAddress_2() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAddress_2());
        }
        if (value.getCity() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCity());
        }
        if (value.getState_short() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getState_short());
        }
        stmt.bindLong(9, value.getPostal_code());
        stmt.bindLong(10, value.getSequence_number());
      }
    };
    this.__deletionAdapterOfCustomer = new EntityDeletionOrUpdateAdapter<Customer>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Customer` WHERE `destination_code` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Customer value) {
        if (value.getDestination_code() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDestination_code());
        }
      }
    };
    this.__updateAdapterOfCustomer = new EntityDeletionOrUpdateAdapter<Customer>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Customer` SET `destination_code` = ?,`destination_name` = ?,`site_container_code` = ?,`site_container_description` = ?,`address_1` = ?,`address_2` = ?,`city` = ?,`state_short` = ?,`postal_code` = ?,`sequence_number` = ? WHERE `destination_code` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Customer value) {
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
        if (value.getSite_container_code() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSite_container_code());
        }
        if (value.getSite_container_description() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSite_container_description());
        }
        if (value.getAddress_1() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAddress_1());
        }
        if (value.getAddress_2() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAddress_2());
        }
        if (value.getCity() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCity());
        }
        if (value.getState_short() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getState_short());
        }
        stmt.bindLong(9, value.getPostal_code());
        stmt.bindLong(10, value.getSequence_number());
        if (value.getDestination_code() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getDestination_code());
        }
      }
    };
  }

  @Override
  public void addCustomer(final Customer customer) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCustomer.insert(customer);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCustomer(final Customer customer) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCustomer.handle(customer);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateCustomer(final Customer customer) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCustomer.handle(customer);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
