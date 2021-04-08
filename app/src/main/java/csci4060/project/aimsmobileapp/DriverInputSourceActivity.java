package csci4060.project.aimsmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DriverInputSourceActivity extends AppCompatActivity {
    String
        productType,
        startDate,
        startTime,
        endDate,
        endTime,
        trailerGrossQuantity,
        trailerNetQuantity,
        startMeterReading,
        endMeterReading,
        pickupGrossQuantity,
        pickupNetQuantity,
        bolNumber;

    int pickupGrossToNetRatio;

    EditText
            editTextProductType,
            editTextStartDate,
            editTextStartTime,
            editTextEndDate,
            editTextEndTime,
            editTextTrailerGrossQuantity,
            editTextTrailerNetQuantity,
            editTextStartMeterReading,
            editTextEndMeterReading,
            editTextPickupGrossQuantity,
            editTextPickupNetQuantity,
            editTextBOLNumber;


    public void clickFunction (View view){
            editTextProductType = (EditText) findViewById(R.id.editTextProductType);
            editTextStartDate = (EditText) findViewById(R.id.editTextStartDate);
            editTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
            editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
            editTextEndTime = (EditText) findViewById(R.id.editTextEndTime);
            editTextTrailerGrossQuantity = (EditText) findViewById(R.id.editTextTrailerGrossQuantity);
            editTextTrailerNetQuantity = (EditText) findViewById(R.id.editTextTrailerNetQuantity);
            editTextStartMeterReading = (EditText) findViewById(R.id.editTextStartMeterReading);
            editTextEndMeterReading = (EditText) findViewById(R.id.editTextEndMeterReading);
            editTextPickupGrossQuantity = (EditText) findViewById(R.id.editTextPickupGrossQuantity);
            editTextPickupNetQuantity = (EditText) findViewById(R.id.editTextPickupNetQuantity);
            editTextBOLNumber = (EditText) findViewById(R.id.editTextBOLNumber);

            //TODO Need to add validation for driver input before allowing the driver to press submit button

            productType = editTextProductType.getText().toString();
            startDate = editTextStartDate.getText().toString();
            startTime = editTextStartTime.getText().toString();
            endDate = editTextEndDate.getText().toString();
            endTime = editTextEndTime.getText().toString();
            trailerGrossQuantity = editTextTrailerGrossQuantity.getText().toString();
            trailerNetQuantity = editTextTrailerNetQuantity.getText().toString();
            startMeterReading = editTextStartMeterReading.getText().toString();
            endMeterReading = editTextEndMeterReading.getText().toString();
            pickupGrossQuantity = editTextPickupGrossQuantity.getText().toString();
            pickupNetQuantity = editTextPickupNetQuantity.getText().toString();
            bolNumber = editTextBOLNumber.getText().toString();
            pickupGrossToNetRatio = Integer.parseInt(pickupNetQuantity)/Integer.parseInt(pickupGrossQuantity);
            Toast.makeText(this, "Ratio is " + pickupGrossToNetRatio, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_input_source);

        //TODO If possible add dropdown menu for product type
//        Spinner mySpinner = (Spinner) findViewById(R.id.);
//
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DriverInputSourceActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.productNames));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(myAdapter);
    }
}