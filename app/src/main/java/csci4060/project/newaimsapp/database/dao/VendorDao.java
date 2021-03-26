package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Vendor;

@Dao
public interface VendorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addVendor(Vendor vendor);

    @Delete
    void deleteVendor(Vendor vendor);

    @Update
    void updateVendor(Vendor vendor);


}
