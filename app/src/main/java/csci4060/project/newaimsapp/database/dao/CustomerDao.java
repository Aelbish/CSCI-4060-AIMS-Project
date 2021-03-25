package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Customer;

@Dao
public interface CustomerDao {
    @Insert
    void addCustomer(Customer customer);

    @Delete
    void deleteCustomer(Customer customer);

    @Update
    void updateCustomer(Customer customer);

    @Query("SELECT container_id FROM Customer WHERE customer_id = :customer_id")
    LiveData<Integer> getContainerId(int customer_id);

    @Query("SELECT full_name FROM Customer WHERE customer_id = :customer_id")
    LiveData<String> getName(int customer_id);

    @Query("SELECT site_name FROM Customer WHERE customer_id = :customer_id")
    LiveData<String> getSiteName(int customer_id);

    @Query("SELECT site_address FROM Customer WHERE customer_id = :customer_id")
    LiveData<String> getSiteAddress(int customer_id);
}
