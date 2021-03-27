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
import csci4060.project.newaimsapp.UI.Adapters.LoadListAdapter;
import csci4060.project.newaimsapp.UI.ViewModels.LoadViewModel;

public class LoadsFragment extends Fragment {

    private LoadListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadViewModel loadViewModel = new ViewModelProvider(this).get(LoadViewModel.class);
        adapter = new LoadListAdapter(new LoadListAdapter.LoadDiff());
        loadViewModel.setTrip_id(159);
        loadViewModel.getAllLoads().observe(getViewLifecycleOwner(), loads -> {
            adapter.submitList(loads);
        });

        View view = inflater.inflate(R.layout.fragment_loads, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.loadRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
