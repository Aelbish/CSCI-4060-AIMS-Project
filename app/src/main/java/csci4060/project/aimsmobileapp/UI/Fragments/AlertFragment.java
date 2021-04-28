package csci4060.project.aimsmobileapp.UI.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.DataRepository;
import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.database.AppDatabase;
import csci4060.project.aimsmobileapp.database.dao.DriverDao;
import csci4060.project.aimsmobileapp.model.TripInfoModel;

//This is alert screen
public class AlertFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alerts, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState){
        TextView txtDriverName = getView().findViewById(R.id.txtDriverName);

        final DataRepository repository = AIMSApp.repository;

        String driverName = repository.getDriver_name();

        txtDriverName.setText(driverName);
    }
}