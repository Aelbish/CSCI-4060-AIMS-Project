package csci4060.project.aimsmobileapp.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;

@Dao
public interface DeliveryDao {

    @Insert
    void insertDelivery(Delivery delivery);

    @Query("UPDATE delivery SET bill_of_lading_number = :number")
    void updateBillOfLadingNumber(int number);

    @Query("UPDATE delivery SET bill_of_lading_product = :product")
    void updateBillOfLadingProduct(String product);

    @Query("UPDATE delivery SET bill_of_lading_start_load = :date")
    void updateBillOfLadingStartLoadTime(Date date);

    @Query("UPDATE delivery SET bill_of_lading_stop_load = :date")
    void updateBillOfLadingStopLoadTime(Date date);

    @Query("UPDATE delivery SET bill_of_lading_gross = :gross")
    void updateBillOfLadingGrossPickedUp(double gross);

    @Query("UPDATE delivery SET bill_of_lading_net = :net")
    void updateBillOfLadingNetPickedUp(double net);

    @Query("UPDATE delivery SET product = :product")
    void updateProductDelivered(String product);

    @Query("UPDATE delivery SET product_gross_delivered = :gross")
    void updateProductDeliveredGross(double gross);

    @Query("UPDATE delivery SET product_net_delivered = :net")
    void updateProductDeliveredNet(double net);

    @Query("UPDATE delivery SET delivery_ticket_number = :number")
    void updateDeliveryTicketNumber(long number);

    @Query("UPDATE delivery SET meter_reading_before = :reading")
    void updateMeterReadingBeginning(double reading);

    @Query("UPDATE delivery SET meter_reading_after = :reading")
    void updateMeterReadingEnd(double reading);

    @Query("UPDATE delivery SET stick_reading_before = :reading")
    void updateStickReadingBeginning(double reading);

    @Query("UPDATE delivery SET stick_reading_after = :reading")
    void updateStickReadingEnd(double reading);

    @Query("UPDATE delivery SET delivery_comments = :comment")
    void updateDeliveryComments(String comment);
    /*
    @Query("SELECT trip_id FROM delivery")
    void getTripID();

    void getVendorName();

    void getTerminalName();

    void getCustomerName();

    void getCustomerAddress();

    void getCustomerSite();*/
}
