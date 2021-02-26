package csci4060.project.aimsmobileapp;

import android.app.Application;

import csci4060.project.aimsmobileapp.Database.User;
import csci4060.project.aimsmobileapp.Database.UserDao;
import csci4060.project.aimsmobileapp.Database.LocalDatabase;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        LocalDatabase db = LocalDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public void insert(User user) {
        LocalDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }
}
