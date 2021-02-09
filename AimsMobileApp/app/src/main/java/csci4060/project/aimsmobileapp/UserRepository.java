package csci4060.project.aimsmobileapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    void insert(User user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }
}
