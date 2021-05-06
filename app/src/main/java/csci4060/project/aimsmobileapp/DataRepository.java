package csci4060.project.aimsmobileapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.transform.Source;

import csci4060.project.aimsmobileapp.database.AppDatabase;
import csci4060.project.aimsmobileapp.database.dao.*;
import csci4060.project.aimsmobileapp.database.entity.Customer;
import csci4060.project.aimsmobileapp.database.entity.DeliveredProduct;
import csci4060.project.aimsmobileapp.database.entity.Delivery;
import csci4060.project.aimsmobileapp.database.entity.Driver;
import csci4060.project.aimsmobileapp.database.entity.Load;
import csci4060.project.aimsmobileapp.database.entity.SiteInput;
import csci4060.project.aimsmobileapp.database.entity.SourceInput;
import csci4060.project.aimsmobileapp.database.entity.Trip;
import csci4060.project.aimsmobileapp.database.entity.TripJson;
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
    private DeliveryDao deliveryDao;
    private DeliveredProductDao deliveredProductDao;
    private SiteInputDao siteInputDao;
    private SourceInputDao sourceInputDao;
    private TripJsonDao tripJsonDao;

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
        siteInputDao = db.siteInputDao();
        sourceInputDao = db.sourceInputDao();
        tripJsonDao = db.tripJsonDao();
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

    public void addSiteInput(SiteInput siteInput) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.addSiteInput(siteInput);
        });
    }

    public void addSourceInput(SourceInput sourceInput) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.addSourceInput(sourceInput);
        });
    }

    public void addTripJson(TripJson tripJson) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            tripJsonDao.addTripJson(tripJson);
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

    /****************************************
     *    DeliveredProduct Table Methods    *
     ****************************************/
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

    /**********************************
     *     SiteInput Table Methods    *
     **********************************/
    public void setProductType(String product_type, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setProduct_type(product_type, trip_id, sequence_id);
        });
    }

    public void setStart_date(String start_date, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setStart_date(start_date, trip_id, sequence_id);
        });
    }

    public void setStart_time(String start_time, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setStart_time(start_time, trip_id, sequence_id);
        });
    }

    public void setEnd_date(String end_date, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setEnd_date(end_date, trip_id, sequence_id);
        });
    }

    public void setEnd_time(String end_time, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setEnd_time(end_time, trip_id, sequence_id);
        });
    }

    public void setBegin_site_container_reading(double begin_site_container_reading, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setBegin_site_container_reading(begin_site_container_reading, trip_id, sequence_id);
        });
    }

    public void setEnd_site_container_reading(double end_site_container_reading, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setEnd_site_container_reading(end_site_container_reading, trip_id, sequence_id);
        });
    }

    public void setStart_meter_reading(double start_meter_reading, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setStart_meter_reading(start_meter_reading, trip_id, sequence_id);
        });
    }

    public void setEnd_meter_reading(double end_meter_reading, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setEnd_meter_reading(end_meter_reading, trip_id, sequence_id);
        });
    }

    public void setDelivered_gross_quantity(double delivered_gross_quantity, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setDelivered_gross_quantity(delivered_gross_quantity, trip_id, sequence_id);
        });
    }

    public void setDelivered_net_quantity(double delivered_net_quantity, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setDelivered_net_quantity(delivered_net_quantity, trip_id, sequence_id);
        });
    }

    public void setDelivery_ticket_number(int delivery_ticket_number, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setDelivery_ticket_number(delivery_ticket_number, trip_id, sequence_id);
        });
    }

    public void setDeliveryComment(String deliveryComment, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setDeliveryComment(deliveryComment, trip_id, sequence_id);
        });
    }

    public void setPickup_ratio(double pickup_ratio, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            siteInputDao.setPickup_ratio(pickup_ratio, trip_id, sequence_id);
        });
    }

    public String getProduct_type(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return siteInputDao.getProduct_type(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getStart_date(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return siteInputDao.getStart_date(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getStart_time(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return siteInputDao.getStart_time(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getEnd_date(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return siteInputDao.getEnd_date(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getEnd_time(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return siteInputDao.getEnd_time(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public double getBegin_site_container_reading(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getBegin_site_container_reading(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getEnd_site_container_reading(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getEnd_site_container_reading(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getStart_meter_reading(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getStart_meter_reading(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getEnd_meter_reading(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getEnd_meter_reading(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getDelivered_gross_quantity(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getDelivered_gross_quantity(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getDelivered_net_quantity(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getDelivered_net_quantity(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public int getDelivery_ticket_number(int trip_id, int sequence_id) {
        Callable<Integer> start_date = () -> {
            return siteInputDao.getDelivery_ticket_number(trip_id, sequence_id);
        };

        Future<Integer> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getDeliveryComment(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return siteInputDao.getDeliveryComment(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public double getPickup_ratio(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return siteInputDao.getPickup_ratio(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**********************************
     *     SourceInput Table Methods    *
     **********************************/
    public void setProductTypeSource(String product_type, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setProduct_type(product_type, trip_id, sequence_id);
        });
    }

    public void setStart_dateSource(String start_date, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setStart_date(start_date, trip_id, sequence_id);
        });
    }

    public void setStart_timeSource(String start_time, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setStart_time(start_time, trip_id, sequence_id);
        });
    }

    public void setEnd_dateSource(String end_date, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setEnd_date(end_date, trip_id, sequence_id);
        });
    }

    public void setEnd_timeSource(String end_time, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setEnd_time(end_time, trip_id, sequence_id);
        });
    }

    public void setTrailer_gross_quantitySource(double trailer_gross_quantity, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setTrailer_gross_quantity(trailer_gross_quantity, trip_id, sequence_id);
        });
    }

    public void setTrailer_net_quantitySource(double trailer_net_quantity, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setTrailer_net_quantity(trailer_net_quantity, trip_id, sequence_id);
        });
    }

    public void setStart_meter_readingSource(double start_meter_reading, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setStart_meter_reading(start_meter_reading, trip_id, sequence_id);
        });
    }

    public void setEnd_meter_readingSource(double end_meter_reading, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setEnd_meter_reading(end_meter_reading, trip_id, sequence_id);
        });
    }

    public void setPickup_gross_quantitySource(double pickup_gross_quantity, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setPickup_gross_quantity(pickup_gross_quantity, trip_id, sequence_id);
        });
    }

    public void setPickup_net_quantitySource(double pickup_net_quantity, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setPickup_net_quantity(pickup_net_quantity, trip_id, sequence_id);
        });
    }

    public void setBol_numberSource(int bol_number, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setBol_number(bol_number, trip_id, sequence_id);
        });
    }

    public void setPickup_ratioSource(double pickup_ratio, int trip_id, int sequence_id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sourceInputDao.setPickup_ratio(pickup_ratio, trip_id, sequence_id);
        });
    }

    public String getProduct_typeSource(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return sourceInputDao.getProduct_type(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getStart_dateSource(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return sourceInputDao.getStart_date(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getStart_timeSource(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return sourceInputDao.getStart_time(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getEnd_dateSource(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return sourceInputDao.getEnd_date(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getEnd_timeSource(int trip_id, int sequence_id) {
        Callable<String> start_date = () -> {
            return sourceInputDao.getEnd_time(trip_id, sequence_id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public double getTrailer_gross_quantitySource(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return sourceInputDao.getTrailer_gross_quantity(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getTrailer_net_quantitySource(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return sourceInputDao.getTrailer_net_quantity(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getStart_meter_readingSource(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return sourceInputDao.getStart_meter_reading(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getEnd_meter_readingSource(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return sourceInputDao.getEnd_meter_reading(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getPickup_gross_quantitySource(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return sourceInputDao.getPickup_gross_quantity(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getPickup_net_quantitySource(int trip_id, int sequence_id) {
        Callable<Double> start_date = () -> {
            return sourceInputDao.getPickup_net_quantity(trip_id, sequence_id);
        };

        Future<Double> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public int getBOLNumberSource(int trip_id, int sequence_id) {
        Callable<Integer> start_date = () -> {
            return sourceInputDao.getBol_number(trip_id, sequence_id);
        };

        Future<Integer> future = AppDatabase.databaseWriteExecutor.submit(start_date);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getPickup_ratioSource(int trip_id, int sequence_id) {
        return sourceInputDao.getPickup_ratio(trip_id, sequence_id);
    }


    /*************
     * Driver Methods
     */

    public String getDriver_name(){
        Callable<String> driver_name = () -> {
            return driverDao.getDriverName();
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(driver_name);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDriver_code(){
        Callable<String> code = () -> {
            return driverDao.getDriverCode();
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(code);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getTripId(){
        Callable<String> tripId = () -> {
            return String.valueOf(loadDao.getTripId());
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(tripId);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getWaypointDescription(){
        Callable<String> waypoint = () -> {
            return loadDao.getWaypointDescription();
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(waypoint);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    /***********
     * Trip/Load Methods
     ***********/

    public int getTripIsComplete(int trip_id){
        Callable<Integer> is_complete = () -> {
            return tripDao.getIsComplete(trip_id);
        };

        Future<Integer> future = AppDatabase.databaseWriteExecutor.submit(is_complete);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return -1;
    }

    public void setTripIsCompleted(int trip_id, int is_complete) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.setIsComplete(trip_id, is_complete);
        });
    }

    public int getLoadIsComplete(int seq_num){
        Callable<Integer> is_complete = () -> {
            return loadDao.getIsComplete(seq_num);
        };

        Future<Integer> future = AppDatabase.databaseWriteExecutor.submit(is_complete);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return -1;
    }

    public void setLoadIsCompleted(int seq_num, int is_complete) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            loadDao.setIsComplete(seq_num, is_complete);
        });
    }

    /*************
     * TripJson Methods
     ************/
    public String getJsonData(int id){
        Callable<String> data = () -> {
            return tripJsonDao.getJsonString(id);
        };

        Future<String> future = AppDatabase.databaseWriteExecutor.submit(data);
        try{
            return future.get();
        }
        catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return "";
    }
}