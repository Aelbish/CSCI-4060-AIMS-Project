package csci4060.project.newaimsapp;

import csci4060.project.newaimsapp.database.AppDatabase;
import csci4060.project.newaimsapp.database.dao.*;
import csci4060.project.newaimsapp.database.entity.Load;
import csci4060.project.newaimsapp.database.entity.User;

public class DataRepository {
    private UserDao userDao;
    private TripDao tripDao;
    private LoadDao loadDao;
    private CustomerDao customerDao;
    private VendorDao vendorDao;
    private ContainerDao containerDao;
    private BillOfLadingDao billOfLadingDao;
    private DeliveryDao deliveryDao;

    private static DataRepository instance;
    private static AppDatabase db;

    private DataRepository(final AppDatabase db) {
        DataRepository.db = db;
        userDao = db.userDao();
        loadDao = db.loadDao();
    }

    public static DataRepository getInstance(final AppDatabase db) {
        if(instance == null) {
            synchronized (DataRepository.class) {
                if(instance == null) {
                    instance = new DataRepository(db);
                }
            }
        }
        return instance;
    }

    public void addLoad() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.addUser(new User(3, "Christian Strickland"));
        });
    }
}

//TODO I need to add some methods to get data and make them run on the background threads
//TODO I also need to figure out the best way to initialize and call the repository (https://github.com/android/architecture-components-samples/tree/master/BasicSample/app/src/main)
//TODO Might want to do AsyncTask? (https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-4-saving-user-data/lesson-10-storing-data-with-room/10-1-c-room-livedata-viewmodel/10-1-c-room-livedata-viewmodel.html)
//TODO Set up the correct ViewModel classes to be able to view database data in the ui of the app