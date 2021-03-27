package csci4060.project.newaimsapp.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.R;
import csci4060.project.newaimsapp.UI.Adapters.TripListAdapter;
import csci4060.project.newaimsapp.UI.ViewModels.TripViewModel;

/**
 * This is the main fragment class where all of the other helper classes will be called from
 * This will show the list of trips and automatically update the ui with new trips or changes when they occur
 */
public class TripsFragment extends Fragment {

    private TripListAdapter adapter;

    /**
     * This creates the viewmodel to observe any live changes in data and is what makes the user be able to see the data how we want it
     * Also creates the adapter to update the recyclerview whenever new trips happen
     * Finally shows the xml layout to the user so they can see the data
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

    /**
     * Allows the recyclerview to show its contents to the user
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.tripRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}

