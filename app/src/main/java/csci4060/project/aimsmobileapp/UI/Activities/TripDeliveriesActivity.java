package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.DataRepository;
import csci4060.project.aimsmobileapp.NetworkUtil;
import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.model.TripInfoModel;

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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.DataRepository;
import csci4060.project.aimsmobileapp.NetworkUtil;
import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.database.entity.TripJson;
import csci4060.project.aimsmobileapp.model.TripInfoModel;
import csci4060.project.aimsmobileapp.adapter.TripListAdapter;
import csci4060.project.aimsmobileapp.database.entity.Customer;
import csci4060.project.aimsmobileapp.database.entity.Delivery;
import csci4060.project.aimsmobileapp.database.entity.Driver;
import csci4060.project.aimsmobileapp.database.entity.Load;
import csci4060.project.aimsmobileapp.database.entity.Trip;
import csci4060.project.aimsmobileapp.database.entity.Vendor;


public class TripDeliveriesActivity extends AppCompatActivity {

    private List<TripInfoModel> tripInfoModelList = new ArrayList<>();
    private RecyclerView TripListRecyclerView;
    private DataRepository repository = AIMSApp.repository;

    String DriverCode;
    String DriverName;
    int TruckId;
    String TruckCode;
    String TruckDesc;
    int TrailerId;
    String TrailerCode;
    String TrailerDesc;
    int TripId;
    String TripName;
    String TripDate;
    int SeqNum;
    String WaypointTypeDescription;
    double Latitude;
    double Longitude;
    String DestinationCode;
    String DestinationName;
    String SiteContainerCode;
    String SiteContainerDescription;
    String Address1;
    String Address2;
    String City;
    String StateAbbrev;
    int PostalCode;
    int DelReqNum;
    int DelReqLineNum;
    int ProductId;
    String ProductCode;
    String ProductDesc;
    double RequestedQty;
    String UOM;
    String Fill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_deliveries);

        //        Referencing the recycler view UI and setting the layout manager as linear layout
        TripListRecyclerView = findViewById(R.id.display_delivery_list_recycler_view);
        TripListRecyclerView.setLayoutManager(new LinearLayoutManager(TripDeliveriesActivity.this));

        if (NetworkUtil.getConnectivityStatus(TripDeliveriesActivity.this) == 1) {
            getDataForTripList();
        }
        else getDataForTripListOffline();
    }

    private void getDataForTripListOffline() {

        try {
            JSONObject data = new JSONObject(repository.getJsonData(1));
            JSONArray resultSet1 = data.getJSONArray("resultSet1");

            for (int i = 0; i < resultSet1.length(); i++) {
                DriverCode = resultSet1.getJSONObject(i).getString("DriverCode");
                DriverName = resultSet1.getJSONObject(i).getString("DriverName");
                TruckId = resultSet1.getJSONObject(i).getInt("TruckId");
                TruckCode = resultSet1.getJSONObject(i).getString("TruckCode");
                TruckDesc = resultSet1.getJSONObject(i).getString("TruckDesc");
                TrailerId = resultSet1.getJSONObject(i).getInt("TrailerId");
                TrailerCode = resultSet1.getJSONObject(i).getString("TrailerCode");
                TrailerDesc = resultSet1.getJSONObject(i).getString("TrailerDesc");
                TripId = resultSet1.getJSONObject(i).getInt("TripId");
                TripName = resultSet1.getJSONObject(i).getString("TripName");
                TripDate = resultSet1.getJSONObject(i).getString("TripDate");
                SeqNum = resultSet1.getJSONObject(i).getInt("SeqNum");
                WaypointTypeDescription = resultSet1.getJSONObject(i).getString("WaypointTypeDescription");
                Latitude = resultSet1.getJSONObject(i).getDouble("Latitude");
                Longitude = resultSet1.getJSONObject(i).getDouble("Longitude");
                DestinationCode = resultSet1.getJSONObject(i).getString("DestinationCode");
                DestinationName = resultSet1.getJSONObject(i).getString("DestinationName");
                SiteContainerCode = resultSet1.getJSONObject(i).getString("SiteContainerCode");
                SiteContainerDescription = resultSet1.getJSONObject(i).getString("SiteContainerDescription");
                Address1 = resultSet1.getJSONObject(i).getString("Address1");
                Address2 = resultSet1.getJSONObject(i).getString("Address2");
                City = resultSet1.getJSONObject(i).getString("City");
                StateAbbrev = resultSet1.getJSONObject(i).getString("StateAbbrev");
                PostalCode = resultSet1.getJSONObject(i).getInt("PostalCode");
                DelReqNum = resultSet1.getJSONObject(i).optInt("DelReqNum");
                DelReqLineNum = resultSet1.getJSONObject(i).optInt("DelReqLineNum");
                ProductId = resultSet1.getJSONObject(i).optInt("ProductId");
                ProductCode = resultSet1.getJSONObject(i).getString("ProductCode");
                ProductDesc = resultSet1.getJSONObject(i).getString("ProductDesc");
                RequestedQty = resultSet1.getJSONObject(i).optDouble("RequestedQty", 0.0);
                UOM = resultSet1.getJSONObject(i).getString("UOM");
                Fill = resultSet1.getJSONObject(i).getString("Fill");

                tripInfoModelList.add(new TripInfoModel(TripId, DriverCode, DriverName, TruckId, TruckCode, TruckDesc, TrailerId,
                        TrailerCode, TrailerDesc, TripName, TripDate, SeqNum, WaypointTypeDescription, DestinationCode, DestinationName, Address1, City, PostalCode, ProductId,
                        ProductCode, ProductDesc, RequestedQty, UOM, Fill, Latitude, Longitude));
            }

            if (tripInfoModelList.size() > 0) {
                TripListRecyclerView.setAdapter(new TripListAdapter(tripInfoModelList, this));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    Get trip data from api and add into the following array list
//    The array list publishes the data into the recycler view adapter and the recycler view adapter renders the data into recycler view

    //    Method to add or fetch data from api
    private void getDataForTripList() {

        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this);

        // Start the queue
        requestQueue.start();

        //making the Rest api call
        String url = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/GetTripListDetailByDriver/BrewingJava?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String in = response.toString();
                        try {
                            JSONObject reader = new JSONObject(in);
                            JSONObject data = reader.getJSONObject("data");
                            JSONArray resultSet1 = data.getJSONArray("resultSet1");

                            repository.addTripJson(new TripJson(1, data.toString()));
                            for(int i = 0; i < resultSet1.length(); i++) {
                                DriverCode = resultSet1.getJSONObject(i).getString("DriverCode");
                                DriverName = resultSet1.getJSONObject(i).getString("DriverName");
                                TruckId = resultSet1.getJSONObject(i).getInt("TruckId");
                                TruckCode = resultSet1.getJSONObject(i).getString("TruckCode");
                                TruckDesc = resultSet1.getJSONObject(i).getString("TruckDesc");
                                TrailerId = resultSet1.getJSONObject(i).getInt("TrailerId");
                                TrailerCode = resultSet1.getJSONObject(i).getString("TrailerCode");
                                TrailerDesc = resultSet1.getJSONObject(i).getString("TrailerDesc");
                                TripId = resultSet1.getJSONObject(i).getInt("TripId");
                                TripName = resultSet1.getJSONObject(i).getString("TripName");
                                TripDate = resultSet1.getJSONObject(i).getString("TripDate");
                                SeqNum = resultSet1.getJSONObject(i).getInt("SeqNum");
                                WaypointTypeDescription = resultSet1.getJSONObject(i).getString("WaypointTypeDescription");
                                Latitude = resultSet1.getJSONObject(i).getDouble("Latitude");
                                Longitude = resultSet1.getJSONObject(i).getDouble("Longitude");
                                DestinationCode = resultSet1.getJSONObject(i).getString("DestinationCode");
                                DestinationName = resultSet1.getJSONObject(i).getString("DestinationName");
                                SiteContainerCode = resultSet1.getJSONObject(i).getString("SiteContainerCode");
                                SiteContainerDescription = resultSet1.getJSONObject(i).getString("SiteContainerDescription");
                                Address1 = resultSet1.getJSONObject(i).getString("Address1");
                                Address2 = resultSet1.getJSONObject(i).getString("Address2");
                                City = resultSet1.getJSONObject(i).getString("City");
                                StateAbbrev = resultSet1.getJSONObject(i).getString("StateAbbrev");
                                PostalCode = resultSet1.getJSONObject(i).getInt("PostalCode");
                                DelReqNum = resultSet1.getJSONObject(i).optInt("DelReqNum");
                                DelReqLineNum = resultSet1.getJSONObject(i).optInt("DelReqLineNum");
                                ProductId = resultSet1.getJSONObject(i).optInt("ProductId");
                                ProductCode = resultSet1.getJSONObject(i).getString("ProductCode");
                                ProductDesc = resultSet1.getJSONObject(i).getString("ProductDesc");
                                RequestedQty = resultSet1.getJSONObject(i).optDouble("RequestedQty", 0.0);
                                UOM = resultSet1.getJSONObject(i).getString("UOM");
                                Fill = resultSet1.getJSONObject(i).getString("Fill");

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
                                        ProductCode, ProductDesc, RequestedQty, UOM, Fill, Latitude, Longitude));
                            }

                            if (tripInfoModelList.size() > 0) {

                                TripListRecyclerView.setAdapter(new TripListAdapter(tripInfoModelList, TripDeliveriesActivity.this));
                            } else {
                                Toast.makeText(TripDeliveriesActivity.this, "List empty, please try again", Toast.LENGTH_SHORT).show();
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