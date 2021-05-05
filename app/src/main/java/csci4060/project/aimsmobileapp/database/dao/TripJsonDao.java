package csci4060.project.aimsmobileapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import csci4060.project.aimsmobileapp.database.entity.TripJson;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TripJsonDao {
    @Insert(onConflict = REPLACE)
    void addTripJson(TripJson tripJson);

    @Query("SELECT data FROM tripjson WHERE id = :id")
    String getJsonString(int id);
}
