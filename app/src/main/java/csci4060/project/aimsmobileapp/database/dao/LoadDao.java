package csci4060.project.aimsmobileapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import csci4060.project.aimsmobileapp.database.entity.Load;

/**
 * This class communicates directly with the database using methods with SQL queries specified in the @query annotations
 * To get or store more data, you will need to create a method like below and annotate it with @query then write your query in SQL
 */
@Dao
public interface LoadDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addLoad(Load load);

    @Delete
    void deleteLoad(Load load);

    @Update
    void updateLoad(Load load);

    @Query("SELECT trip_id FROM Load WHERE sequence_number = :sequence_number")
    LiveData<Integer> getTripId(int sequence_number);

    @Query("SELECT * FROM Load WHERE trip_id = :trip_id")
    LiveData<List<Load>> getLoads(int trip_id);

    @Query("SELECT is_complete FROM Load WHERE sequence_number = :sequence_number")
    int getIsComplete(int sequence_number);

    @Query("UPDATE Load SET is_complete = :complete WHERE sequence_number = :sequence_number")
    void setIsComplete(int sequence_number, int complete);

    @Query("SELECT trip_id FROM Load")
    int getTripId();

    @Query("SELECT waypoint_description FROM Load")
    String getWaypointDescription();
}
