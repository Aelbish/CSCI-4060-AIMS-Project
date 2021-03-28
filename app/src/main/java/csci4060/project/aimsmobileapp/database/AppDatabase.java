package csci4060.project.aimsmobileapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import csci4060.project.aimsmobileapp.database.dao.*;
import csci4060.project.aimsmobileapp.database.entity.*;

/**
 * This is the actual database class where you define all your entity classes to be turned into tables as
 * well as where you provide abstract constructors to your DAO's and instantiate the database
 */

//If you need to add new entity classes or change any of the existing entity classes, then you will need to increment version = # by 1 each time
@Database(entities = {Driver.class, Trip.class, Load.class, Customer.class, Vendor.class,  Delivery.class}, version = 8)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    /**
     * These are abstract references to all the DAO's. This way, the database can access them whenever it needs to without creating new instances
     * This also helps provide outside classes to call these because they just have to call AppDatabase.(entity)Dao(); and they can access
     * all the queries.
     */
    public abstract DriverDao userDao();
    public abstract TripDao tripDao();
    public abstract LoadDao loadDao();
    public abstract CustomerDao customerDao();
    public abstract VendorDao vendorDao();
    //public abstract ContainerDao containerDao();
    //public abstract BillOfLadingDao billOfLadingDao();
    public abstract DeliveryDao deliveryDao();

    private static AppDatabase INSTANCE; //This hold an instancce of the database to make sure we only ever have ONE database open
    private static final int NUM_OF_EXECUTOR_THREADS = 4; //The number of threads the database will use when writing data to the database
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_OF_EXECUTOR_THREADS); // This service will put any database writing requests into a background thread to prevent main UI lockout


    /**
     * All the checks to see if the INSTANCE is null is to make sure only one instance of the database exists as a time
     * This will ensure data integrity and prevent unexpected data loss due to multiple database instances being open
     * synchronized means that it will only allow one call to getDatabase to be running at a time
     */
    public static AppDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {  //The line below is the actual building of the database                                 //After version increment, it will completely destory the database and rebuild it
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "local_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
