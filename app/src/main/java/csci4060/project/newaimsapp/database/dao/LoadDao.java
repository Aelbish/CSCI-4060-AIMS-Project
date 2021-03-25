package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Load;

@Dao
public interface LoadDao {
    @Insert
    void addLoad(Load load);

    @Delete
    void deleteLoad(Load load);

    @Update
    void updateLoad(Load load);

    @Query("SELECT trip_id FROM Load WHERE load_id = :load_id")
    LiveData<Integer> getTripId(int load_id);

    @Query("SELECT customer_id FROM Load WHERE load_id = :load_id")
    LiveData<Integer> getCustomerId(int load_id);

    @Query("SELECT vendor_id FROM Load WHERE load_id = :load_id")
    LiveData<Integer> getVendorId(int load_id);

    @Query("SELECT product_requested FROM Load WHERE load_id = :load_id")
    LiveData<String> getProductRequested(int load_id);

    @Query("SELECT requested_quantity FROM Load WHERE load_id = :load_id")
    LiveData<String> getRequestedQuantity(int load_id);
}
