package csci4060.project.newaimsapp.UI;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.AIMSApp;
import csci4060.project.newaimsapp.R;

public class TripsFragment extends Fragment {

    private TripViewModel tripViewModel;
    private TripListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        adapter = new TripListAdapter(new TripListAdapter.TripDiff());
        tripViewModel.getAllTrips().observe(getViewLifecycleOwner(), trips -> {
            adapter.submitList(trips);
        });

        return inflater.inflate(R.layout.fragment_trips, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.tripRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}

