package csci4060.project.newaimsapp.UI.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import csci4060.project.newaimsapp.AIMSApp;
import csci4060.project.newaimsapp.DataRepository;
import csci4060.project.newaimsapp.database.entity.Trip;

public class TripViewModel extends AndroidViewModel {
    private DataRepository repository;

    private LiveData<List<Trip>> allTrips;

    public TripViewModel(@NonNull Application application) {
        super(application);

        repository = AIMSApp.repository;
        allTrips = repository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }
}
