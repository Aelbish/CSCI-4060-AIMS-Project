package csci4060.project.aimsmobileapp.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import csci4060.project.aimsmobileapp.R;

public class TripActivity extends AppCompatActivity implements View.OnClickListener {

    TextView
            txtTripId,
            txtDriverCode,
            txtDriverName,
            txtTruckDesc,
            txtTrailerDesc,
            txtTripName,
            txtTripDate,
            txtDestinationName,
            txtAddress,
            txtCity,
            txtPostalCode,
            txtProductDesc,
            txtRequestedQty,
            txtUOM,
            txtFill;

    String
            TripId,
            DriverCode,
            DriverName,
            TruckDesc,
            TrailerDesc,
            TripName,
            TripDate,
            DestinationName,
            Address,
            City,
            PostalCode,
            ProductDesc,
            RequestedQty,
            UOM,
            Fill;

    Button btnStart, btnSiteInput, btnSourceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

//        Reference for text views
        txtTripId = findViewById(R.id.TripId);
        txtDriverCode = findViewById(R.id.DriverCode);
        txtDriverName = findViewById(R.id.DriverName);
        txtTruckDesc = findViewById(R.id.TruckDesc);
        txtTrailerDesc = findViewById(R.id.TrailerDesc);
        txtTripName = findViewById(R.id.TripName);
        txtTripDate = findViewById(R.id.TripDate);
        txtDestinationName = findViewById(R.id.DestinationName);
        txtAddress = findViewById(R.id.Address1);
        txtCity = findViewById(R.id.City);
        txtPostalCode = findViewById(R.id.PostalCode);
        txtProductDesc = findViewById(R.id.ProductDesc);
        txtRequestedQty = findViewById(R.id.RequestedQty);
        txtUOM = findViewById(R.id.UOM);
        txtFill = findViewById(R.id.Fill);

//        reference for buttons
        btnStart = findViewById(R.id.btn_start_trip);

//        Setting on click listener to buttons
        btnStart.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        getSingleTripDetails();

    }

    private void getSingleTripDetails() {
//        Getting and storing each value of specific trip list in the following variables
        Bundle bundle = getIntent().getExtras();

        TripId = bundle.getString("TripId");
        DriverCode = bundle.getString("DriverCode");
        DriverName = bundle.getString("DriverName");
        TruckDesc = bundle.getString("TruckDesc");
        TrailerDesc = bundle.getString("TrailerDesc");
        TripName = bundle.getString("TripName");
        TripDate = bundle.getString("TripDate");
        DestinationName = bundle.getString("DestinationName");
        Address = bundle.getString("Address");
        City = bundle.getString("City");
        PostalCode = bundle.getString("PostalCode");
        ProductDesc = bundle.getString("ProductDesc");
        RequestedQty = bundle.getString("RequestedQty");
        UOM = bundle.getString("UOM");
        UOM = bundle.getString("UOM");
        Fill = bundle.getString("Fill");


//       Rendering the subscribed data from recycler view into text views
        txtTripId.setText("TRIP ID: "+ TripId);
        txtDriverCode.setText(DriverCode);
        txtDriverName.setText(DriverName);
        txtTruckDesc.setText(TruckDesc);
        txtTrailerDesc.setText(TrailerDesc);
        txtTripName.setText(TripName);
        txtTripDate.setText(TripDate);
        txtDestinationName.setText(DestinationName);
        txtAddress.setText(Address);
        txtCity.setText(City);
        txtPostalCode.setText(PostalCode);
        txtProductDesc.setText(ProductDesc);
        txtRequestedQty.setText(RequestedQty);
        txtUOM.setText(UOM);
        txtFill.setText(Fill);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start_trip) {
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.start();

            DateFormat df = new SimpleDateFormat("MM/DD/YY");
            Date date = new Date();
            String url = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/TripStatusPut/" + DriverCode + "/" + TripId + "/ArriveSrc/ Hello /true/" + df.format(date) + "?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";

            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            Toast.makeText(this, "Send update to aims", Toast.LENGTH_SHORT).show();
        }

    }
}