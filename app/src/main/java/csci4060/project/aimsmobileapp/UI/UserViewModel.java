package csci4060.project.aimsmobileapp.UI;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import csci4060.project.aimsmobileapp.Database.User;
import csci4060.project.aimsmobileapp.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;


    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
    }


    public void insert(User user) {
        repository.insert(user);
    }
}
