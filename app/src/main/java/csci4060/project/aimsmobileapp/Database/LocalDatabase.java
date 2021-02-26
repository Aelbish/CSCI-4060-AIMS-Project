package csci4060.project.aimsmobileapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Delivery.class}, version = 2, exportSchema = true)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract DeliveryDao deliveryDao();

    private static volatile LocalDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder((context.getApplicationContext()), LocalDatabase.class, "local_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
