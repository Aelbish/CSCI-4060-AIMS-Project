package csci4060.project.aimsmobileapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Queue;

import csci4060.project.aimsmobileapp.database.AppDatabase;
import csci4060.project.aimsmobileapp.database.dao.*;
import csci4060.project.aimsmobileapp.database.entity.Customer;
import csci4060.project.aimsmobileapp.database.entity.DeliveredProduct;
import csci4060.project.aimsmobileapp.database.entity.Delivery;
import csci4060.project.aimsmobileapp.database.entity.Driver;
import csci4060.project.aimsmobileapp.database.entity.Load;
import csci4060.project.aimsmobileapp.database.entity.Trip;
import csci4060.project.aimsmobileapp.database.entity.Vendor;

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
    private DeliveredProductDao deliveredProductDao;

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
        deliveredProductDao = db.deliveredProductDao();
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

    public void setBOLNumber(int bolNumber, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setBol_number(bolNumber, sequence_id);
        });
    }

    public void setFuelProduct(String fuelProduct, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setFuel_product(fuelProduct, sequence_id);
        });
    }

    public void setStartLoad(long start_load, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setStart_load(start_load, sequence_id);
        });
    }

    public void setEndLoad(long end_load, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setEnd_load(end_load, sequence_id);
        });
    }

    public void setGrossPickedUp(double gross_picked_up, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setGross_picked_up(gross_picked_up, sequence_id);
        });
    }

    public void setNetPickedUp(double net_picked_up, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setNet_picked_up(net_picked_up, sequence_id);
        });
    }

    public void setProductName(String product_name, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setProduct_name(product_name, sequence_id);
        });
    }

    public void setGrossDelivered(double gross_Delivered, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setGross_delivered(gross_Delivered, sequence_id);
        });
    }

    public void setNetDelivered(double net_delivered, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setNet_delivered(net_delivered, sequence_id);
        });
    }

    public void setTicketNum(int ticket_num, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setTicket_num(ticket_num, sequence_id);
        });
    }

    public void setStartTruckMeter(double start_truck_meter, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setStart_truck_meter(start_truck_meter, sequence_id);
        });
    }

    public void setEndTruckMeter(double end_truck_meter, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setEnd_truck_meter(end_truck_meter, sequence_id);
        });
    }

    public void setStartStickMeter(double start_stick_meter, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setStart_stick_meter(start_stick_meter, sequence_id);
        });
    }

    public void setEndStickMeter(double end_stick_meter, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setEnd_stick_meter(end_stick_meter, sequence_id);
        });
    }

    public void setDeliveryComments(String delivery_comments, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveredProductDao.setDelivery_comments(delivery_comments, sequence_id);
        });
    }

    public int getBOLNumber(int sequence_num) {
        return deliveredProductDao.getBol_number(sequence_num);
    }

    public String getFuelProduct(int sequence_num) {
        return deliveredProductDao.getFuel_product(sequence_num);
    }

    public Long getStartLoad(int sequence_num) {
        return deliveredProductDao.getStart_load(sequence_num);
    }

    public long getEndLoad(int sequence_num) {
        return deliveredProductDao.getEnd_load(sequence_num);
    }

    public double getGrossPickedUp(int sequence_num) {
        return deliveredProductDao.getGross_picked_up(sequence_num);
    }

    public double getNetPickedUp(int sequence_num) {
        return deliveredProductDao.getNet_picked_up(sequence_num);
    }

    public String getProductName(int sequence_num) {
        return deliveredProductDao.getProduct_name(sequence_num);
    }

    public double getGrossDelivered(int sequence_num) {
        return deliveredProductDao.getGross_delivered(sequence_num);
    }

    public double getNetDelivered(int sequence_num) {
        return deliveredProductDao.getNet_delivered(sequence_num);
    }

    public int getTicketNum(int sequence_num) {
        return deliveredProductDao.getTicket_num(sequence_num);
    }

    public double getStartTruckMeter(int sequence_num) {
        return deliveredProductDao.getStart_truck_meter(sequence_num);
    }

    public double getEndTruckMeter(int sequence_num) {
        return deliveredProductDao.getEnd_truck_meter(sequence_num);
    }

    public double getStartStickMeter(int sequence_num) {
        return deliveredProductDao.getStart_stick_meter(sequence_num);
    }

    public double getEndStickMeter(int sequence_num) {
        return deliveredProductDao.getEnd_stick_meter(sequence_num);
    }

    public String getDeliveryComments(int sequence_num) {
        return deliveredProductDao.getDelivery_comments(sequence_num);
    }
}