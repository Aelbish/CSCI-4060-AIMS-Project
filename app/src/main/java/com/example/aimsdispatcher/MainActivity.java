package com.example.aimsdispatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListner;
    String latitude ="";
    String longitude ="";
    EditText city;
    TextView coordinates;
    RadioGroup group;
    RadioButton bt1, bt2;
    Button btncoordinates,btnsavedata,btnshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setIcon(R.mipmap.aims);
        actionbar.setTitle(" AIMS Dispatcher");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);

        coordinates = (TextView) findViewById(R.id.coordinates);
        city = (EditText) findViewById(R.id.city);
        group = (RadioGroup) findViewById(R.id.group);
        bt1 = (RadioButton) findViewById(R.id.bt1);
        bt2 = (RadioButton) findViewById(R.id.bt2);
        btncoordinates =(Button) findViewById(R.id.btncoordinates);
        btnsavedata = (Button) findViewById(R.id.btnsavedata);
        btnshow= (Button) findViewById(R.id.btnshow);

        coordinates.setVisibility(View.GONE);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListner = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude=location.getLatitude()+"";
                longitude=location.getLongitude()+"";
                coordinates.setText("Latitude: "+location.getLatitude()+" Longitude: "+location.getLongitude());
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Intent intent =new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET},1);
            return;
        }
        else {
            configureButton();
        }

    }
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }

    }

    public void configureButton() {
        btncoordinates.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                coordinates.setVisibility(View.VISIBLE);
                locationManager.requestLocationUpdates("gps", 100, 100, locationListner);
            }
        });


    }

    public void btnshow(View v) {
        Intent intent =new Intent(this, Data.class);
        startActivity(intent);
    }

    public void btnsavedata(View v){

        String c = city.getText().toString().trim();
        String s="";
        if(bt1.isChecked()){
            s = bt1.getText().toString().trim();
        }
        if(bt2.isChecked()){
            s=bt2.getText().toString().trim();
        }
        if((!latitude.isEmpty())&&(!longitude.isEmpty())&&(!c.isEmpty())&&(!s.isEmpty())) {
            LocationsDB db = new LocationsDB(this);
            db.open();
            db.createEntry(latitude, longitude,c,s);
            db.close();
            Toast.makeText(this, "Successfully Saved!!", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Incomplete Data!!", Toast.LENGTH_SHORT).show();
        }
    }

}
