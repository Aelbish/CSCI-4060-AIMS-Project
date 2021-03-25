package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Delivery;

@Dao
public interface DeliveryDao {
    @Insert
    void addDelivery(Delivery delivery);

    @Delete
    void removeDelivery(Delivery delivery);

    @Update
    void updateDelivery(Delivery delivery);

    @Query("SELECT load_id FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Integer> getLoadId(int delivery_id);

    @Query("SELECT bill_of_lading_id FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Integer> getBillOfLadingId(int delivery_id);

    @Query("SELECT product_delivered_name FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<String> getProductDeliveredName(int delivery_id);

    @Query("SELECT product_delivered_gross FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Double> getProductDeliveredGross(int delivery_id);

    @Query("SELECT product_delivered_net FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Double> getProductDeliveredNet(int delivery_id);

    @Query("SELECT delivery_ticket_number FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Integer> getDeliveryTickerNumber(int delivery_id);

    @Query("SELECT trailer_meter_readings FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Double> getTrailerMeterReading(int delivery_id);

    @Query("SELECT tank_reading_before FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Double> getTankReadingBefore(int delivery_id);

    @Query("SELECT tank_reading_after FROM Delivery WHERE delivery_id = :delivery_id")
    LiveData<Double> getTankReadingAfter(int delivery_id);
}
