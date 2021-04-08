package csci4060.project.aimsmobileapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TripActivity extends AppCompatActivity implements View.OnClickListener {

    TextView
            txtTripId,
            txtDriverCode,
            txtDriverName,
            txtTruckId,
            txtTruckCode,
            txtTruckDesc,
            txtTrailerId,
            txtTrailerCode,
            txtTrailerDesc,
            txtTripName,
            txtTripDate,
            txtDestinationCode,
            txtAddress,
            txtCity,
            txtPostalCode,
            txtProductId,
            txtProductDesc,
            txtRequestedQty,
            txtUOM,
            txtFill;

    String
            TripId,
            DriverCode,
            DriverName,
            TruckId,
            TruckCode,
            TruckDesc,
            TrailerId,
            TrailerCode,
            TrailerDesc,
            TripName,
            TripDate,
            DestinationCode,
            Address,
            City,
            PostalCode,
            ProductId,
            ProductDesc,
            RequestedQty,
            UOM,
            Fill;

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        txtTripId = findViewById(R.id.TripId);
        txtDriverCode = findViewById(R.id.DriverCode);
        txtDriverName = findViewById(R.id.DriverName);
        txtTruckId = findViewById(R.id.TruckId);
        txtTruckCode = findViewById(R.id.TruckCode);
        txtTruckDesc = findViewById(R.id.TruckDesc);
        txtTrailerId = findViewById(R.id.TrailerId);
        txtTrailerCode = findViewById(R.id.TrailerCode);
        txtTrailerDesc = findViewById(R.id.TrailerDesc);
        txtTripName = findViewById(R.id.TripName);
        txtTripDate = findViewById(R.id.TripDate);
        txtDestinationCode = findViewById(R.id.DestinationCode);
        txtAddress = findViewById(R.id.Address1);
        txtCity = findViewById(R.id.City);
        txtPostalCode = findViewById(R.id.PostalCode);
        txtProductId = findViewById(R.id.ProductId);
        txtProductDesc = findViewById(R.id.ProductDesc);
        txtRequestedQty = findViewById(R.id.RequestedQty);
        txtUOM = findViewById(R.id.UOM);
        txtFill = findViewById(R.id.Fill);

        btnStart = findViewById(R.id.btn_start_trip);
        btnStart.setOnClickListener(this);

        getSingleTripDetails();

    }

    private void getSingleTripDetails() {
//        Getting and storing each value of specific trip list in the following variables
        Bundle bundle = getIntent().getExtras();

        TripId = bundle.getString("TripId");
        DriverCode = bundle.getString("DriverCode");
        DriverName = bundle.getString("DriverName");
        TruckId = bundle.getString("TruckId");
        TruckCode = bundle.getString("TruckCode");
        TruckDesc = bundle.getString("TruckDesc");
        TrailerId = bundle.getString("TrailerId");
        TrailerCode = bundle.getString("TrailerCode");
        TrailerDesc = bundle.getString("TrailerDesc");
        TripName = bundle.getString("TripName");
        TripDate = bundle.getString("TripDate");
        DestinationCode = bundle.getString("DestinationCode");
        Address = bundle.getString("Address");
        City = bundle.getString("City");
        PostalCode = bundle.getString("PostalCode");
        ProductId = bundle.getString("ProductId");
        ProductDesc = bundle.getString("ProductDesc");
        RequestedQty = bundle.getString("RequestedQty");
        UOM = bundle.getString("UOM");
        UOM = bundle.getString("UOM");
        Fill = bundle.getString("Fill");


//       Rendering the subscribed data from recycler view into text views
        txtTripId.setText("Trip id: " + TripId);
        txtDriverCode.setText("Driver code: " + DriverCode);
        txtDriverName.setText("Driver name: " + DriverName);
        txtTruckId.setText("Truck id: " + TruckId);
        txtTruckCode.setText("Truck code: " + TruckCode);
        txtTruckDesc.setText("Truck Desc: " + TruckDesc);
        txtTrailerId.setText("Trailer id: " + TrailerId);
        txtTrailerCode.setText("Trailer code: " + TrailerCode);
        txtTrailerDesc.setText("Trailer Desc: " + TrailerDesc);
        txtTripName.setText("Trip name: " + TripName);
        txtTripDate.setText("Trip date: " + TripDate);
        txtDestinationCode.setText("Destination code: " + DestinationCode);
        txtAddress.setText("Address1: " + Address);
        txtCity.setText("City: " + City);
        txtPostalCode.setText("Postal code: " + PostalCode);
        txtProductId.setText("Product id: " + ProductId);
        txtProductDesc.setText("Product desc: " + ProductDesc);
        txtRequestedQty.setText("Request qunatity: " + RequestedQty);
        txtUOM.setText("UOM: " + UOM);
        txtFill.setText("Fill: " + Fill);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start_trip) {
//            Send put request update to AIMS api
            Toast.makeText(this, "Send update to aims", Toast.LENGTH_SHORT).show();
        }
    }
}