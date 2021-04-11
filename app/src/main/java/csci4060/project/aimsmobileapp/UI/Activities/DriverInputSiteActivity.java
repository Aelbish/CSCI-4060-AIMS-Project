package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import csci4060.project.aimsmobileapp.R;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class DriverInputSiteActivity extends AppCompatActivity {

    //TODO need to be able to capture the customer signature
    String
            //productType,
            startDate,
            startTime,
            endDate,
            endTime,
            beginSiteContainerReading,
            endSiteContainerReading,
            startMeterReading,
            endMeterReading,
            deliveredGrossQuantity,
            deliveredNetQuantity,
            deliveryTicketNumber,
            deliveryComment,
            barcode;

    int pickupGrossToNetRatio;

    EditText
            //editTextProductType,
            editTextStartDate,
            editTextStartTime,
            editTextEndDate,
            editTextEndTime,
            editTextBeginSiteContainerReading,
            editTextEndSiteContainerReading,
            editTextStartMeterReading,
            editTextEndMeterReading,
            editTextDeliveredGrossQuantity,
            editTextDeliveredNetQuantity,
            editTextDeliveryTicketNumber,
            editTextDeliveryComment,
            editTextBarcode;

    private Spinner spinnerProductType;
    String yourProduct;

    /**Scan button for barcode scanner**/
    Button buttonScan;

    public static final  int SIGNATURE_ACTIVITY = 1;

    /**When submit button is pressed**/
    public void clickFunction(View view) {
        //TODO Need to add validation for driver input before allowing the driver to press submit button
        //productType = editTextProductType.getText().toString();
        startDate = editTextStartDate.getText().toString();
        startTime = editTextStartTime.getText().toString();
        endDate = editTextEndDate.getText().toString();
        endTime = editTextEndTime.getText().toString();
        beginSiteContainerReading = editTextBeginSiteContainerReading.getText().toString();
        endSiteContainerReading = editTextEndSiteContainerReading.getText().toString();
        startMeterReading = editTextStartMeterReading.getText().toString();
        endMeterReading = editTextEndMeterReading.getText().toString();
        deliveredGrossQuantity = editTextDeliveredGrossQuantity.getText().toString();
        deliveredNetQuantity = editTextDeliveredNetQuantity.getText().toString();
        deliveryTicketNumber = editTextDeliveryTicketNumber.getText().toString();
        deliveryComment = editTextDeliveryComment.getText().toString();
        pickupGrossToNetRatio = Integer.parseInt(deliveredNetQuantity) / Integer.parseInt(deliveredGrossQuantity);
        Toast.makeText(this, "Ratio is " + pickupGrossToNetRatio, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_input_site);

        /**Spinner for product types**/
        spinnerProductType = findViewById(R.id.spinnerProductType);
        List<String> productType = new ArrayList<>();
        //TODO force user to select one
        productType.add("Select product type");
        productType.add("87 AKI");
        productType.add("89 AKI");
        productType.add("92 AKI");
        productType.add("Diesel");
        productType.add("Bio-diesel");
        productType.add("Ethanol");
        ArrayAdapter<String> productTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productType);
        productTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductType.setAdapter(productTypeAdapter);
        spinnerProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yourProduct = spinnerProductType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //editTextProductType = (EditText) findViewById(R.id.editTextProductType);

        /**Get start date**/
        editTextStartDate = (EditText) findViewById(R.id.editTextStartDate);
        editTextStartDate.setInputType(InputType.TYPE_NULL);
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(editTextStartDate);
            }
        });

        /**Get start time**/
        editTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        editTextStartTime.setInputType(InputType.TYPE_NULL);
        editTextStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(editTextStartTime);
            }
        });

        /**Get end date**/
        editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
        editTextEndDate.setInputType(InputType.TYPE_NULL);
        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(editTextEndDate);
            }
        });

        /**Get end time**/
        editTextEndTime = (EditText) findViewById(R.id.editTextEndTime);
        editTextEndTime.setInputType(InputType.TYPE_NULL);
        editTextEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(editTextEndTime);
            }
        });
        editTextBeginSiteContainerReading = (EditText) findViewById(R.id.editTextBeginSiteContainerReading);
        editTextEndSiteContainerReading = (EditText) findViewById(R.id.editTextEndSiteContainerReading);
        editTextStartMeterReading = (EditText) findViewById(R.id.editTextStartMeterReading);
        editTextEndMeterReading = (EditText) findViewById(R.id.editTextEndMeterReading);
        editTextDeliveredGrossQuantity = (EditText) findViewById(R.id.editTextDeliveredGrossQuantity);
        editTextDeliveredNetQuantity = (EditText) findViewById(R.id.editTextDeliveredNetQuantity);
        editTextDeliveryTicketNumber = (EditText) findViewById(R.id.editTextDeliveryTicketNumber);
        editTextDeliveryComment = (EditText) findViewById(R.id.editTextDeliveryComment);
        editTextBarcode = (EditText) findViewById(R.id.editTextBarcode);

        /**Barcode Scanner Button**/
        buttonScan = findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        DriverInputSiteActivity.this
                );
                intentIntegrator.setPrompt("Use volume up key for flash");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

        //TODO need to be able to capture the customer signature

//        /**Signature Button**/
//        Button buttonSignature = findViewById(R.id.buttonSignature);
//        buttonSignature.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DriverInputSiteActivity.this, CaptureSignature.class);
//                startActivityForResult(intent, SIGNATURE_ACTIVITY);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**Scan barcode**/
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );
        if (intentResult.getContents()!= null){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    DriverInputSiteActivity.this
            );
            builder.setTitle("Result");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    barcode = intentResult.getContents();
                    editTextBarcode.setText(barcode);
                }
            });
            builder.show();
        }else {
            Toast.makeText(getApplicationContext()
            , "Please scan the barcode", Toast.LENGTH_SHORT).show();
        }
    }

    /***Start and End Date*/
    private void showDateDialog(EditText editText){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(DriverInputSiteActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**Start and End Time**/
    private void showTimeDialog(EditText editText){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(DriverInputSiteActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }
}