package csci4060.project.aimsmobileapp.UI.Fragments;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.UI.TripInfoModel;
import csci4060.project.aimsmobileapp.UI.TripListAdapter;
import csci4060.project.aimsmobileapp.database.entity.Customer;
import csci4060.project.aimsmobileapp.database.entity.Delivery;
import csci4060.project.aimsmobileapp.database.entity.Driver;
import csci4060.project.aimsmobileapp.database.entity.Load;
import csci4060.project.aimsmobileapp.database.entity.Trip;
import csci4060.project.aimsmobileapp.database.entity.Vendor;

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

        RequestQueue requestQueue;

        // Instantiate the cache
        //Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        //Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        //requestQueue = new RequestQueue(cache, network);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Start the queue
        requestQueue.start();

        //making the Rest api call
        String url = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/GetTripListDetailByDriver/D1?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String in = response.toString();
                        try {
                            JSONObject reader = new JSONObject(in);
                            JSONObject data = reader.getJSONObject("data");
                            JSONArray resultSet1 = data.getJSONArray("resultSet1");
                            for(int i = 0; i < resultSet1.length(); i++) {
                                String DriverCode = resultSet1.getJSONObject(i).getString("DriverCode");
                                String DriverName = resultSet1.getJSONObject(i).getString("DriverName");
                                int TruckId = resultSet1.getJSONObject(i).getInt("TruckId");
                                String TruckCode = resultSet1.getJSONObject(i).getString("TruckCode");
                                String TruckDesc = resultSet1.getJSONObject(i).getString("TruckDesc");
                                int TrailerId = resultSet1.getJSONObject(i).getInt("TrailerId");
                                String TrailerCode = resultSet1.getJSONObject(i).getString("TrailerCode");
                                String TrailerDesc = resultSet1.getJSONObject(i).getString("TrailerDesc");
                                int TripId = resultSet1.getJSONObject(i).getInt("TripId");
                                String TripName = resultSet1.getJSONObject(i).getString("TripName");
                                String TripDate = resultSet1.getJSONObject(i).getString("TripDate");
                                int SeqNum = resultSet1.getJSONObject(i).getInt("SeqNum");
                                String WaypointTypeDescription = resultSet1.getJSONObject(i).getString("WaypointTypeDescription");
                                double Latitude = resultSet1.getJSONObject(i).getDouble("Latitude");
                                double Longitude = resultSet1.getJSONObject(i).getDouble("Longitude");
                                String DestinationCode = resultSet1.getJSONObject(i).getString("DestinationCode");
                                String DestinationName = resultSet1.getJSONObject(i).getString("DestinationName");
                                String SiteContainerCode = resultSet1.getJSONObject(i).getString("SiteContainerCode");
                                String SiteContainerDescription = resultSet1.getJSONObject(i).getString("SiteContainerDescription");
                                String Address1 = resultSet1.getJSONObject(i).getString("Address1");
                                String Address2 = resultSet1.getJSONObject(i).getString("Address2");
                                String City = resultSet1.getJSONObject(i).getString("City");
                                String StateAbbrev = resultSet1.getJSONObject(i).getString("StateAbbrev");
                                int PostalCode = resultSet1.getJSONObject(i).getInt("PostalCode");
                                int DelReqNum = resultSet1.getJSONObject(i).optInt("DelReqNum");
                                int DelReqLineNum = resultSet1.getJSONObject(i).optInt("DelReqLineNum");
                                int ProductId = resultSet1.getJSONObject(i).optInt("ProductId");
                                String ProductCode = resultSet1.getJSONObject(i).getString("ProductCode");
                                String ProductDesc = resultSet1.getJSONObject(i).getString("ProductDesc");
                                double RequestedQty = resultSet1.getJSONObject(i).optDouble("RequestedQty", 0.0);
                                String UOM = resultSet1.getJSONObject(i).getString("UOM");
                                String Fill = resultSet1.getJSONObject(i).getString("Fill");

                                Driver driver = new Driver(DriverCode, DriverName, TruckId, TruckCode,
                                        TruckDesc, TrailerId, TrailerCode, TrailerDesc);
                                Trip trip = new Trip(TripId, TripName, TripDate, DriverCode);
                                Load load = new Load(SeqNum, WaypointTypeDescription, Latitude, Longitude, TripId);
                                Vendor vendor = new Vendor(DestinationCode, DestinationName, Address1,
                                        Address2, City, StateAbbrev, PostalCode, SeqNum);
                                Customer customer = new Customer(DestinationCode, DestinationName,
                                        SiteContainerCode, SiteContainerDescription, Address1, Address2, City,
                                        StateAbbrev, PostalCode, SeqNum);
                                Delivery delivery = new Delivery(SeqNum, DelReqNum,
                                        DelReqLineNum, ProductId, ProductCode, ProductDesc,
                                        RequestedQty, UOM, Fill);

                                AIMSApp.repository.storeData(driver, trip, load, vendor ,customer, delivery);

                                tripInfoModelList.add(new TripInfoModel(TripId, DriverCode, DriverName, TruckId, TruckCode, TruckDesc, TrailerId,
                                        TrailerCode, TrailerDesc, TripName, TripDate, SeqNum, WaypointTypeDescription, DestinationCode, DestinationName, Address1, City, PostalCode, ProductId,
                                        ProductCode, ProductDesc, RequestedQty, UOM, Fill));
                            }

                            if (tripInfoModelList.size() > 0) {
                                TripListRecyclerView.setAdapter(new TripListAdapter(tripInfoModelList, getContext()));
                            } else {
                                Toast.makeText(getContext(), "List empty, please try again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(jsonObjectRequest);
//        Adding a dummy data in the Model list of tripInfoModel for testing the recycler view UI
//        (Use data fetched from api)
    }
}