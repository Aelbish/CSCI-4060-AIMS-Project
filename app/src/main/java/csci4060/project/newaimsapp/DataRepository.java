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

/**
 * The main access point for database operations
 * This is the first level of abstraction OUTSIDE of the Room database ecosystem
 * It provides access to the DAOs only for itself
 * You should NOT make a call to DAOs explicitly, but you should define them here and call them through the repository
 */
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

    //This instantiates all the dao instance variables with their proper abstract database instances
    private DataRepository(final AppDatabase db) {
        DataRepository.db = db;
        driverDao = db.userDao();
        tripDao = db.tripDao();
        loadDao = db.loadDao();
        customerDao = db.customerDao();
        vendorDao = db.vendorDao();
        deliveryDao = db.deliveryDao();
    }

    /**
     * Makes sure that only one instance of the repository exists or it could open
     * up a lot of problems as if their were two databases
     * @param db
     * @return
     */
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

    //ANY METHODS ADDING, DELETING, OR UPDATING SOMETHING IN THE DATABASE NEED TO FOLLOW THE PATTERN
    //BELOW FOR THE databaseWriteExecuter SO THSES CAN BE DONE ON THE BACKGROUND THREAD

    /**
     * Adds a driver to the driver table
     * @param driver
     */
    public void addDriver(Driver driver) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            driverDao.addDriver(driver);
        });
    }

    /**
     * Adds a trip to the trip table
     * @param trip
     */
    public void addTrip(Trip trip) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.addTrip(trip);
        });
    }

    /**
     * Adds a load to the load table
     * @param load
     */
    public void addLoad(Load load) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            loadDao.addLoad(load);
        });
    }

    /**
     * Adds a customer to the customer table
     * @param customer
     */
    public void addCustomer(Customer customer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            customerDao.addCustomer(customer);
        });
    }

    /**
     * Adds a vendor to the vendor table
     * @param vendor
     */
    public void addVendor(Vendor vendor) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            vendorDao.addVendor(vendor);
        });
    }

    /**
     * Adds a delivery to the delivery table
     * @param delivery
     */
    public void addDelivery(Delivery delivery) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveryDao.addDelivery(delivery);
        });
    }

    /**
     * Method used in the TripJSONParser class to store all the data we get from AIMS
     * @param driver
     * @param trip
     * @param load
     * @param vendor
     * @param customer
     * @param delivery
     */
    public void storeData(Driver driver, Trip trip, Load load, Vendor vendor, Customer customer, Delivery delivery) {
        addDriver(driver);
        addTrip(trip);
        addLoad(load);
        addVendor(vendor);
        addCustomer(customer);
        addDelivery(delivery);
    }

    /**
     * Gets all trips to be used with the tripfragment and associated helper classes
     * @return
     */
    public LiveData<List<Trip>> getAllTrips(){
        return tripDao.getAllTrips();
    }

    /**
     * Gets all loads to be used with the loadsfragment and associated helper classes
     * @return
     */
    public LiveData<List<Load>> getAllLoads(int trip_id) {
        return loadDao.getLoads(trip_id);
    }

    /**
     * Updates the column for the trip the driver selects
     * @param trip_id
     */
    public void updateIsSelected(int trip_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.updateIsSelected(trip_id);
        });
    }
}