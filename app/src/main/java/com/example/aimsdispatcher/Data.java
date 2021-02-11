package com.example.aimsdispatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Data extends AppCompatActivity {

    TextView tvdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        tvdata= (TextView) findViewById(R.id.tvdata);
        LocationsDB db = new LocationsDB(this);
        db.open();
        tvdata.setText(db.getData());
        db.close();
    }
}