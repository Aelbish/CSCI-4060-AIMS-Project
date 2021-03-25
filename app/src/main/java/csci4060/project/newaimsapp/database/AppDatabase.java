package csci4060.project.newaimsapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import csci4060.project.newaimsapp.database.dao.*;
import csci4060.project.newaimsapp.database.entity.*;

@androidx.room.Database(entities = {User.class, Trip.class, Load.class, Customer.class, Vendor.class, Container.class, BillOfLading.class, Delivery.class}, version = 1)
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

    public static AppDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "local_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
