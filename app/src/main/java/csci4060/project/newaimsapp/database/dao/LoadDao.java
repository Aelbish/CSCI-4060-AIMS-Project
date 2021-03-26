package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Load;

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
}
