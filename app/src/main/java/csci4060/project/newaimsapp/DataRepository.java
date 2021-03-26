package csci4060.project.newaimsapp;

import androidx.lifecycle.LiveData;

import java.util.Queue;

import csci4060.project.newaimsapp.database.AppDatabase;
import csci4060.project.newaimsapp.database.dao.*;
import csci4060.project.newaimsapp.database.entity.Customer;
import csci4060.project.newaimsapp.database.entity.Delivery;
import csci4060.project.newaimsapp.database.entity.Driver;
import csci4060.project.newaimsapp.database.entity.Load;
import csci4060.project.newaimsapp.database.entity.Trip;
import csci4060.project.newaimsapp.database.entity.Vendor;

public class DataRepository {
    private DriverDao driverDao;
    private TripDao tripDao;
    private LoadDao loadDao;
    private CustomerDao customerDao;
    private VendorDao vendorDao;
    //private ContainerDao containerDao;
    //private BillOfLadingDao billOfLadingDao;
    private DeliveryDao deliveryDao;

    private static DataRepository instance;
    private static AppDatabase db;

    private Queue<LiveData<String>> queue;

    private DataRepository(final AppDatabase db) {
        DataRepository.db = db;
        driverDao = db.userDao();
        tripDao = db.tripDao();
        loadDao = db.loadDao();
        customerDao = db.customerDao();
        vendorDao = db.vendorDao();
        deliveryDao = db.deliveryDao();
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

    public void getDriverCode(){
        /*AppDatabase.databaseWriteExecutor.execute(() -> {
            queue.add(driverDao.getDriverName("D1"));
        });
        return queue.remove();*/

        AppDatabase.databaseWriteExecutor.execute(() -> {
            driverDao.addDriver(new Driver("1", "1", 1, "1", "1", 1, "1", "1"));
        });
    }

    public void addDriver(Driver driver) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            driverDao.addDriver(driver);
        });
    }

    public void addTrip(Trip trip) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.addTrip(trip);
        });
    }

    public void addLoad(Load load) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            loadDao.addLoad(load);
        });
    }

    public void addCustomer(Customer customer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            customerDao.addCustomer(customer);
        });
    }

    public void addVendor(Vendor vendor) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            vendorDao.addVendor(vendor);
        });
    }

    public void addDelivery(Delivery delivery) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveryDao.addDelivery(delivery);
        });
    }

    public void storeData(Driver driver, Trip trip, Load load, Vendor vendor, Customer customer, Delivery delivery) {
        addDriver(driver);
        addTrip(trip);
        addLoad(load);
        addVendor(vendor);
        addCustomer(customer);
        addDelivery(delivery);
    }
}

//TODO I need to add some methods to get data and make them run on the background threads
//TODO I also need to figure out the best way to initialize and call the repository (https://github.com/android/architecture-components-samples/tree/master/BasicSample/app/src/main)
//TODO Might want to do AsyncTask? (https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-4-saving-user-data/lesson-10-storing-data-with-room/10-1-c-room-livedata-viewmodel/10-1-c-room-livedata-viewmodel.html)
//TODO Set up the correct ViewModel classes to be able to view database data in the ui of the app