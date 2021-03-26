package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import csci4060.project.newaimsapp.database.entity.Driver;

@Dao
public interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addDriver(Driver driver);

    @Query("DELETE FROM Driver WHERE driver_code = :driver_code")
    void deleteDriver(String driver_code);

    @Query("SELECT driver_name FROM Driver WHERE driver_code = :driver_code")
    LiveData<String> getDriverName(String driver_code);

    @Query("UPDATE Driver SET driver_name = :name WHERE driver_code = :driver_code")
    void setDriverName(String driver_code, String name);
}