package csci4060.project.newaimsapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import csci4060.project.newaimsapp.database.dao.*;
import csci4060.project.newaimsapp.database.entity.*;

@Database(entities = {User.class, Trip.class, Load.class, Customer.class, Vendor.class, Container.class, BillOfLading.class, Delivery.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TripDao tripDao();
    public abstract LoadDao loadDao();
    public abstract CustomerDao customerDao();
    public abstract VendorDao vendorDao();
    public abstract ContainerDao containerDao();
    public abstract BillOfLadingDao billOfLadingDao();
    public abstract DeliveryDao deliveryDao();

    private static AppDatabase INSTANCE;
    private static final int NUM_OF_EXECUTOR_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_OF_EXECUTOR_THREADS);


    public static AppDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "local_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDao userDao = INSTANCE.userDao();

                databaseWriteExecutor.execute(() -> {
                    userDao.deleteUser(1);
                });
            });
        }
    };
}
