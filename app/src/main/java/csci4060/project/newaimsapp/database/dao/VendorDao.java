package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Vendor;

@Dao
public interface VendorDao {
    @Insert
    void addVendor(Vendor vendor);

    @Delete
    void deleteVendor(Vendor vendor);

    @Update
    void updateVendor(Vendor vendor);

    @Query("SELECT full_name FROM Vendor WHERE vendor_id = :vendor_id")
    LiveData<String> getName(int vendor_id);

    @Query("SELECT terminal_name FROM Vendor WHERE vendor_id = :vendor_id")
    LiveData<String> getTerminalName(int vendor_id);

    @Query("SELECT terminal_address FROM Vendor WHERE vendor_id = :vendor_id")
    LiveData<String> getTerminalAddress(int vendor_id);
}
