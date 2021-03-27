package csci4060.project.aimsmobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//This is trips screen


public class TripFragment extends Fragment {

    private List<TripInfoModel> tripInfoModelList = new ArrayList<>();
    private RecyclerView TripListRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trips, container, false);


//        Referencing the recycler view UI and setting the layout manager as linear layout
        TripListRecyclerView = view.findViewById(R.id.display_trips_list_recycler_view);
        TripListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getDataForTripList();

        return view;
    }


//    Get trip data from api and add into the following array list
//    The array list publishes the data into the recycler view adapter and the recycler view adapter renders the data into recycler view

    //    Method to add or fetch data from api
    private void getDataForTripList() {

//        Adding a dummy data in the Model list of tripInfoModel for testing the recycler view UI
//        (Use data fetched from api)

        tripInfoModelList.add(new TripInfoModel(159, "D1",
                "CECIL DRIVER  ",
                4,
                "T3             ",
                "PETERBILT TRANSPORT",
                3,
                "TRL2           ",
                "TANKER #2",
                "A-159",
                "2021-03-08 00:00:00.0000000",
                "312   - UNL",
                "200 THOMAS RD",
                "WEST MONROE  ",
                "71291",
                1 - 1000,
                "REG87 GASOLINE",
                10000,
                "dsakhk",
                "7943uyb"));

        tripInfoModelList.add(new TripInfoModel(185, "D1",
                "dsadasd DRIVER  ",
                4,
                "T3             ",
                "PETERBILT TRANSPORT",
                3,
                "TRL2           ",
                "TANKER #2",
                "A-159",
                "2021-03-08 00:00:00.0000000",
                "312   - UNL",
                "200 THOMAS RD",
                "WEST MONROE  ",
                "71291",
                1 - 1000,
                "REG87 GASOLINE",
                10000,
                "dsakhk",
                "7943uyb"));

        if (tripInfoModelList.size() > 0) {
            TripListRecyclerView.setAdapter(new TripListAdapter(tripInfoModelList, getContext()));
        } else {
            Toast.makeText(getContext(), "List empty, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}
