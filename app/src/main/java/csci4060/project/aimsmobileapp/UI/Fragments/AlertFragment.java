package csci4060.project.aimsmobileapp.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.DataRepository;
import csci4060.project.aimsmobileapp.R;

//This is alert screen
public class AlertFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alerts, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState){
        TextView txtDriverName = getView().findViewById(R.id.txtDriverName);
        TextView txtDriverCode = getView().findViewById(R.id.driverID);

        final DataRepository repository = AIMSApp.repository;

        String driverName = repository.getDriver_name();

        String driverCode = repository.getDriver_code();

        txtDriverName.setText(driverName);
        txtDriverCode.setText(driverCode);
    }
}