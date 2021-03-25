package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import csci4060.project.newaimsapp.database.entity.User;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT full_name FROM User WHERE driver_id = :driver_id")
    LiveData<String> getFullName(int driver_id);

    @Query("UPDATE User SET full_name = :name WHERE driver_id = :driver_id")
    LiveData<String> setFullName(int driver_id, String name);
}
