package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import csci4060.project.newaimsapp.database.entity.Trip;

/**
 * This class communicates directly with the database using methods with SQL queries specified in the @query annotations
 * To get or store more data, you will need to create a method like below and annotate it with @query then write your query in SQL
 */
@Dao
public interface TripDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTrip(Trip trip);

    @Delete
    void deleteTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Query("SELECT * FROM trip")
    LiveData<List<Trip>> getAllTrips();

    //This method/query will set the is_selected field to 1 to show that the trip is updated
    @Query("UPDATE trip SET is_selected = 1 WHERE trip_id = :trip_id")
    void updateIsSelected(int trip_id);
}
