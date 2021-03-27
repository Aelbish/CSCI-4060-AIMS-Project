package csci4060.project.newaimsapp;

import androidx.lifecycle.LiveData;

import java.util.List;
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

    public LiveData<List<Trip>> getAllTrips(){
        return tripDao.getAllTrips();
    }

    public LiveData<List<Load>> getAllLoads(int trip_id) {
        return loadDao.getLoads(trip_id);
    }
}