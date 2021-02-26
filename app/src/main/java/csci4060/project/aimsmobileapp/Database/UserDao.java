package csci4060.project.aimsmobileapp.Database;

import androidx.room.Dao;
import androidx.room.Insert;


@Dao
public interface UserDao {

    @Insert
    void insert(User user);
}
