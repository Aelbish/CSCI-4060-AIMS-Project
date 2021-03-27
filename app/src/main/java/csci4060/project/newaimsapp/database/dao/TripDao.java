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


}
