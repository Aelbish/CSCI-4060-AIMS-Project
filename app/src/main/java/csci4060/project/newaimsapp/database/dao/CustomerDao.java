package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Customer;

@Dao
public interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCustomer(Customer customer);

    @Delete
    void deleteCustomer(Customer customer);

    @Update
    void updateCustomer(Customer customer);

}
