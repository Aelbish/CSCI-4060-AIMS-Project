package csci4060.project.aimsmobileapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import csci4060.project.aimsmobileapp.database.entity.Driver;

/**
 * This class communicates directly with the database using methods with SQL queries specified in the @query annotations
 * To get or store more data, you will need to create a method like below and annotate it with @query then write your query in SQL
 */
@Dao
public interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addDriver(Driver driver);

    @Query("DELETE FROM Driver WHERE driver_code = :driver_code")
    void deleteDriver(String driver_code);

    @Query("SELECT driver_name FROM Driver")
    String getDriverName();

    @Query("UPDATE Driver SET driver_name = :name WHERE driver_code = :driver_code")
    void setDriverName(String driver_code, String name);
}
