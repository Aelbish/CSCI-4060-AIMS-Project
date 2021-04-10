package csci4060.project.aimsmobileapp.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import csci4060.project.aimsmobileapp.database.dao.CustomerDao;
import csci4060.project.aimsmobileapp.database.dao.CustomerDao_Impl;
import csci4060.project.aimsmobileapp.database.dao.DeliveredProductDao;
import csci4060.project.aimsmobileapp.database.dao.DeliveredProductDao_Impl;
import csci4060.project.aimsmobileapp.database.dao.DeliveryDao;
import csci4060.project.aimsmobileapp.database.dao.DeliveryDao_Impl;
import csci4060.project.aimsmobileapp.database.dao.DriverDao;
import csci4060.project.aimsmobileapp.database.dao.DriverDao_Impl;
import csci4060.project.aimsmobileapp.database.dao.LoadDao;
import csci4060.project.aimsmobileapp.database.dao.LoadDao_Impl;
import csci4060.project.aimsmobileapp.database.dao.TripDao;
import csci4060.project.aimsmobileapp.database.dao.TripDao_Impl;
import csci4060.project.aimsmobileapp.database.dao.VendorDao;
import csci4060.project.aimsmobileapp.database.dao.VendorDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DriverDao _driverDao;

  private volatile TripDao _tripDao;

  private volatile LoadDao _loadDao;

  private volatile CustomerDao _customerDao;

  private volatile VendorDao _vendorDao;

  private volatile DeliveryDao _deliveryDao;

  private volatile DeliveredProductDao _deliveredProductDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(9) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Driver` (`driver_code` TEXT NOT NULL, `driver_name` TEXT, `truck_id` INTEGER NOT NULL, `truck_code` TEXT, `truck_description` TEXT, `trailer_id` INTEGER NOT NULL, `trailer_code` TEXT, `trailer_description` TEXT, PRIMARY KEY(`driver_code`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Trip` (`trip_id` INTEGER NOT NULL, `trip_name` TEXT, `trip_date` TEXT, `is_selected` INTEGER NOT NULL, `driver_code` TEXT, PRIMARY KEY(`trip_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Load` (`sequence_number` INTEGER NOT NULL, `waypoint_description` TEXT, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `trip_id` INTEGER NOT NULL, PRIMARY KEY(`sequence_number`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Customer` (`destination_code` TEXT NOT NULL, `destination_name` TEXT, `site_container_code` TEXT, `site_container_description` TEXT, `address_1` TEXT, `address_2` TEXT, `city` TEXT, `state_short` TEXT, `postal_code` INTEGER NOT NULL, `sequence_number` INTEGER NOT NULL, PRIMARY KEY(`destination_code`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Vendor` (`destination_code` TEXT NOT NULL, `destination_name` TEXT, `address_1` TEXT, `address_2` TEXT, `city` TEXT, `state_short` TEXT, `postal_code` INTEGER NOT NULL, `number` INTEGER NOT NULL, PRIMARY KEY(`destination_code`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Delivery` (`number` INTEGER NOT NULL, `delivery_req_number` INTEGER NOT NULL, `delivery_req_line_number` INTEGER NOT NULL, `product_id` INTEGER NOT NULL, `product_code` TEXT, `product_description` TEXT, `requested_quantity` REAL NOT NULL, `unit_of_measurement` TEXT, `fill` TEXT, PRIMARY KEY(`number`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DeliveredProduct` (`sequence_number` INTEGER NOT NULL, `bol_number` INTEGER NOT NULL, `fuel_product` TEXT, `start_load` INTEGER NOT NULL, `end_load` INTEGER NOT NULL, `gross_picked_up` REAL NOT NULL, `net_picked_up` REAL NOT NULL, `product_name` TEXT, `gross_delivered` REAL NOT NULL, `net_delivered` REAL NOT NULL, `ticket_num` INTEGER NOT NULL, `start_truck_meter` REAL NOT NULL, `end_truck_meter` REAL NOT NULL, `start_stick_meter` REAL NOT NULL, `end_stick_meter` REAL NOT NULL, `delivery_comments` TEXT, PRIMARY KEY(`sequence_number`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '31c80a2525c5fbe0c7673362181d6314')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Driver`");
        _db.execSQL("DROP TABLE IF EXISTS `Trip`");
        _db.execSQL("DROP TABLE IF EXISTS `Load`");
        _db.execSQL("DROP TABLE IF EXISTS `Customer`");
        _db.execSQL("DROP TABLE IF EXISTS `Vendor`");
        _db.execSQL("DROP TABLE IF EXISTS `Delivery`");
        _db.execSQL("DROP TABLE IF EXISTS `DeliveredProduct`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDriver = new HashMap<String, TableInfo.Column>(8);
        _columnsDriver.put("driver_code", new TableInfo.Column("driver_code", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("driver_name", new TableInfo.Column("driver_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("truck_id", new TableInfo.Column("truck_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("truck_code", new TableInfo.Column("truck_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("truck_description", new TableInfo.Column("truck_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("trailer_id", new TableInfo.Column("trailer_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("trailer_code", new TableInfo.Column("trailer_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDriver.put("trailer_description", new TableInfo.Column("trailer_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDriver = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDriver = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDriver = new TableInfo("Driver", _columnsDriver, _foreignKeysDriver, _indicesDriver);
        final TableInfo _existingDriver = TableInfo.read(_db, "Driver");
        if (! _infoDriver.equals(_existingDriver)) {
          return new RoomOpenHelper.ValidationResult(false, "Driver(csci4060.project.aimsmobileapp.database.entity.Driver).\n"
                  + " Expected:\n" + _infoDriver + "\n"
                  + " Found:\n" + _existingDriver);
        }
        final HashMap<String, TableInfo.Column> _columnsTrip = new HashMap<String, TableInfo.Column>(5);
        _columnsTrip.put("trip_id", new TableInfo.Column("trip_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("trip_name", new TableInfo.Column("trip_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("trip_date", new TableInfo.Column("trip_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("is_selected", new TableInfo.Column("is_selected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("driver_code", new TableInfo.Column("driver_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrip = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrip = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrip = new TableInfo("Trip", _columnsTrip, _foreignKeysTrip, _indicesTrip);
        final TableInfo _existingTrip = TableInfo.read(_db, "Trip");
        if (! _infoTrip.equals(_existingTrip)) {
          return new RoomOpenHelper.ValidationResult(false, "Trip(csci4060.project.aimsmobileapp.database.entity.Trip).\n"
                  + " Expected:\n" + _infoTrip + "\n"
                  + " Found:\n" + _existingTrip);
        }
        final HashMap<String, TableInfo.Column> _columnsLoad = new HashMap<String, TableInfo.Column>(5);
        _columnsLoad.put("sequence_number", new TableInfo.Column("sequence_number", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoad.put("waypoint_description", new TableInfo.Column("waypoint_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoad.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoad.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoad.put("trip_id", new TableInfo.Column("trip_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoad = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoad = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoad = new TableInfo("Load", _columnsLoad, _foreignKeysLoad, _indicesLoad);
        final TableInfo _existingLoad = TableInfo.read(_db, "Load");
        if (! _infoLoad.equals(_existingLoad)) {
          return new RoomOpenHelper.ValidationResult(false, "Load(csci4060.project.aimsmobileapp.database.entity.Load).\n"
                  + " Expected:\n" + _infoLoad + "\n"
                  + " Found:\n" + _existingLoad);
        }
        final HashMap<String, TableInfo.Column> _columnsCustomer = new HashMap<String, TableInfo.Column>(10);
        _columnsCustomer.put("destination_code", new TableInfo.Column("destination_code", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("destination_name", new TableInfo.Column("destination_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("site_container_code", new TableInfo.Column("site_container_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("site_container_description", new TableInfo.Column("site_container_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("address_1", new TableInfo.Column("address_1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("address_2", new TableInfo.Column("address_2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("state_short", new TableInfo.Column("state_short", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("postal_code", new TableInfo.Column("postal_code", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCustomer.put("sequence_number", new TableInfo.Column("sequence_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCustomer = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCustomer = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCustomer = new TableInfo("Customer", _columnsCustomer, _foreignKeysCustomer, _indicesCustomer);
        final TableInfo _existingCustomer = TableInfo.read(_db, "Customer");
        if (! _infoCustomer.equals(_existingCustomer)) {
          return new RoomOpenHelper.ValidationResult(false, "Customer(csci4060.project.aimsmobileapp.database.entity.Customer).\n"
                  + " Expected:\n" + _infoCustomer + "\n"
                  + " Found:\n" + _existingCustomer);
        }
        final HashMap<String, TableInfo.Column> _columnsVendor = new HashMap<String, TableInfo.Column>(8);
        _columnsVendor.put("destination_code", new TableInfo.Column("destination_code", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("destination_name", new TableInfo.Column("destination_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("address_1", new TableInfo.Column("address_1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("address_2", new TableInfo.Column("address_2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("state_short", new TableInfo.Column("state_short", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("postal_code", new TableInfo.Column("postal_code", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendor.put("number", new TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVendor = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVendor = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVendor = new TableInfo("Vendor", _columnsVendor, _foreignKeysVendor, _indicesVendor);
        final TableInfo _existingVendor = TableInfo.read(_db, "Vendor");
        if (! _infoVendor.equals(_existingVendor)) {
          return new RoomOpenHelper.ValidationResult(false, "Vendor(csci4060.project.aimsmobileapp.database.entity.Vendor).\n"
                  + " Expected:\n" + _infoVendor + "\n"
                  + " Found:\n" + _existingVendor);
        }
        final HashMap<String, TableInfo.Column> _columnsDelivery = new HashMap<String, TableInfo.Column>(9);
        _columnsDelivery.put("number", new TableInfo.Column("number", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("delivery_req_number", new TableInfo.Column("delivery_req_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("delivery_req_line_number", new TableInfo.Column("delivery_req_line_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("product_id", new TableInfo.Column("product_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("product_code", new TableInfo.Column("product_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("product_description", new TableInfo.Column("product_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("requested_quantity", new TableInfo.Column("requested_quantity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("unit_of_measurement", new TableInfo.Column("unit_of_measurement", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDelivery.put("fill", new TableInfo.Column("fill", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDelivery = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDelivery = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDelivery = new TableInfo("Delivery", _columnsDelivery, _foreignKeysDelivery, _indicesDelivery);
        final TableInfo _existingDelivery = TableInfo.read(_db, "Delivery");
        if (! _infoDelivery.equals(_existingDelivery)) {
          return new RoomOpenHelper.ValidationResult(false, "Delivery(csci4060.project.aimsmobileapp.database.entity.Delivery).\n"
                  + " Expected:\n" + _infoDelivery + "\n"
                  + " Found:\n" + _existingDelivery);
        }
        final HashMap<String, TableInfo.Column> _columnsDeliveredProduct = new HashMap<String, TableInfo.Column>(16);
        _columnsDeliveredProduct.put("sequence_number", new TableInfo.Column("sequence_number", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("bol_number", new TableInfo.Column("bol_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("fuel_product", new TableInfo.Column("fuel_product", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("start_load", new TableInfo.Column("start_load", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("end_load", new TableInfo.Column("end_load", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("gross_picked_up", new TableInfo.Column("gross_picked_up", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("net_picked_up", new TableInfo.Column("net_picked_up", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("product_name", new TableInfo.Column("product_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("gross_delivered", new TableInfo.Column("gross_delivered", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("net_delivered", new TableInfo.Column("net_delivered", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("ticket_num", new TableInfo.Column("ticket_num", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("start_truck_meter", new TableInfo.Column("start_truck_meter", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("end_truck_meter", new TableInfo.Column("end_truck_meter", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("start_stick_meter", new TableInfo.Column("start_stick_meter", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("end_stick_meter", new TableInfo.Column("end_stick_meter", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeliveredProduct.put("delivery_comments", new TableInfo.Column("delivery_comments", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDeliveredProduct = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDeliveredProduct = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDeliveredProduct = new TableInfo("DeliveredProduct", _columnsDeliveredProduct, _foreignKeysDeliveredProduct, _indicesDeliveredProduct);
        final TableInfo _existingDeliveredProduct = TableInfo.read(_db, "DeliveredProduct");
        if (! _infoDeliveredProduct.equals(_existingDeliveredProduct)) {
          return new RoomOpenHelper.ValidationResult(false, "DeliveredProduct(csci4060.project.aimsmobileapp.database.entity.DeliveredProduct).\n"
                  + " Expected:\n" + _infoDeliveredProduct + "\n"
                  + " Found:\n" + _existingDeliveredProduct);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "31c80a2525c5fbe0c7673362181d6314", "2ce088284fee8bb366c4c1830ad7b106");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Driver","Trip","Load","Customer","Vendor","Delivery","DeliveredProduct");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Driver`");
      _db.execSQL("DELETE FROM `Trip`");
      _db.execSQL("DELETE FROM `Load`");
      _db.execSQL("DELETE FROM `Customer`");
      _db.execSQL("DELETE FROM `Vendor`");
      _db.execSQL("DELETE FROM `Delivery`");
      _db.execSQL("DELETE FROM `DeliveredProduct`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DriverDao userDao() {
    if (_driverDao != null) {
      return _driverDao;
    } else {
      synchronized(this) {
        if(_driverDao == null) {
          _driverDao = new DriverDao_Impl(this);
        }
        return _driverDao;
      }
    }
  }

  @Override
  public TripDao tripDao() {
    if (_tripDao != null) {
      return _tripDao;
    } else {
      synchronized(this) {
        if(_tripDao == null) {
          _tripDao = new TripDao_Impl(this);
        }
        return _tripDao;
      }
    }
  }

  @Override
  public LoadDao loadDao() {
    if (_loadDao != null) {
      return _loadDao;
    } else {
      synchronized(this) {
        if(_loadDao == null) {
          _loadDao = new LoadDao_Impl(this);
        }
        return _loadDao;
      }
    }
  }

  @Override
  public CustomerDao customerDao() {
    if (_customerDao != null) {
      return _customerDao;
    } else {
      synchronized(this) {
        if(_customerDao == null) {
          _customerDao = new CustomerDao_Impl(this);
        }
        return _customerDao;
      }
    }
  }

  @Override
  public VendorDao vendorDao() {
    if (_vendorDao != null) {
      return _vendorDao;
    } else {
      synchronized(this) {
        if(_vendorDao == null) {
          _vendorDao = new VendorDao_Impl(this);
        }
        return _vendorDao;
      }
    }
  }

  @Override
  public DeliveryDao deliveryDao() {
    if (_deliveryDao != null) {
      return _deliveryDao;
    } else {
      synchronized(this) {
        if(_deliveryDao == null) {
          _deliveryDao = new DeliveryDao_Impl(this);
        }
        return _deliveryDao;
      }
    }
  }

  @Override
  public DeliveredProductDao deliveredProductDao() {
    if (_deliveredProductDao != null) {
      return _deliveredProductDao;
    } else {
      synchronized(this) {
        if(_deliveredProductDao == null) {
          _deliveredProductDao = new DeliveredProductDao_Impl(this);
        }
        return _deliveredProductDao;
      }
    }
  }
}
