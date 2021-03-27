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

    //private OnItemSelectedListener listener;

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

       /*Button summaryButton = (Button) view.findViewById(R.id.btn_summary);
       summaryButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                inflater.inflate(R.layout.fragment_loads, container, false);
           }
       });*/

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.tripRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    /*public interface OnItemSelectedListener {
        public void onButtonSelected();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        }
        else {
            throw new ClassCastException(context.toString() + "must implement TripFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public void activateTripSummary() {

    }*/
}

