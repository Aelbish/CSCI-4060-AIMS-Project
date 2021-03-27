package csci4060.project.newaimsapp.UI.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import csci4060.project.newaimsapp.AIMSApp;
import csci4060.project.newaimsapp.DataRepository;
import csci4060.project.newaimsapp.database.entity.Load;

public class LoadViewModel extends AndroidViewModel {
    private DataRepository repository;

    private LiveData<List<Load>> allLoads;
    private int trip_id;

    public LoadViewModel(@NonNull Application application) {
        super(application);

        repository = AIMSApp.repository;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public LiveData<List<Load>> getAllLoads() {
        allLoads = repository.getAllLoads(trip_id);
        return allLoads;
    }
}
