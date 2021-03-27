package csci4060.project.newaimsapp.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.R;
import csci4060.project.newaimsapp.UI.Adapters.TripListAdapter;
import csci4060.project.newaimsapp.UI.ViewModels.TripViewModel;

public class TripsFragment extends Fragment {

    private TripListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TripViewModel tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        adapter = new TripListAdapter(new TripListAdapter.TripDiff());
        tripViewModel.getAllTrips().observe(getViewLifecycleOwner(), trips -> {
            adapter.submitList(trips);
        });

        View view = inflater.inflate(R.layout.fragment_trips2, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.tripRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}

