package csci4060.project.aimsmobileapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user WHERE id=user.id")
    void delete(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
}
