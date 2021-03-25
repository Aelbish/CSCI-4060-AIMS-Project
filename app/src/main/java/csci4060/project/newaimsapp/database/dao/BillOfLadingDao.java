package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;

import csci4060.project.newaimsapp.database.entity.BillOfLading;

@Dao
public interface BillOfLadingDao {
    @Insert
    void addBillOfLading(BillOfLading bol);

    @Delete
    void deleteBillOfLading(BillOfLading bol);

    @Update
    void updateBillOfLading(BillOfLading bol);

    @Query("SELECT product_picked_up FROM BillOfLading WHERE bill_of_lading_id = :bill_of_lading_id")
    LiveData<String> getProductPickedUp(int bill_of_lading_id);

    @Query("SELECT time_started FROM BillOfLading WHERE bill_of_lading_id = :bill_of_lading_id")
    LiveData<Date> getTimeStarted(int bill_of_lading_id);

    @Query("SELECT time_stopped FROM BillOfLading WHERE bill_of_lading_id = :bill_of_lading_id")
    LiveData<Date> getTimeStopped(int bill_of_lading_id);

    @Query("SELECT gross_picked_up FROM BillOfLading WHERE bill_of_lading_id = :bill_of_lading_id")
    LiveData<Double> getGrossPickedUp(int bill_of_lading_id);

    @Query("SELECT net_picked_up FROM BillOfLading WHERE bill_of_lading_id = :bill_of_lading_id")
    LiveData<Double> getNetPickedUp(int bill_of_lading_id);
}
