package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Trip;

@Dao
public interface TripDao {
    @Insert
    void addTrip(Trip trip);

    @Delete
    void deleteTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Query("SELECT delivery_sequence FROM Trip WHERE trip_id = :trip_id")
    LiveData<String> getDeliverySequence(int trip_id);

    @Query("SELECT route FROM Trip WHERE trip_id = :trip_id")
    LiveData<String> getRoute(int trip_id);
}
