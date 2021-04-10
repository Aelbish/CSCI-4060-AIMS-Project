package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import csci4060.project.aimsmobileapp.R;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class DriverInputSourceActivity extends AppCompatActivity {
    String
            //productType,
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
            //editTextProductType,
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

    Button buttonTakePicture;
    ImageView imageView;
    String pathToFile;

    private Spinner spinnerProductType;
    String yourProduct;

    public void clickFunction(View view) {
        //TODO Need to add validation for driver input before allowing the driver to press submit button
        //productType = editTextProductType.getText().toString();
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
        pickupGrossToNetRatio = Integer.parseInt(pickupNetQuantity) / Integer.parseInt(pickupGrossQuantity);
        Toast.makeText(this, "Ratio is " + pickupGrossToNetRatio, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_input_source);

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
        editTextTrailerGrossQuantity = (EditText) findViewById(R.id.editTextTrailerGrossQuantity);
        editTextTrailerNetQuantity = (EditText) findViewById(R.id.editTextTrailerNetQuantity);
        editTextStartMeterReading = (EditText) findViewById(R.id.editTextStartMeterReading);
        editTextEndMeterReading = (EditText) findViewById(R.id.editTextEndMeterReading);
        editTextPickupGrossQuantity = (EditText) findViewById(R.id.editTextPickupGrossQuantity);
        editTextPickupNetQuantity = (EditText) findViewById(R.id.editTextPickupNetQuantity);
        editTextBOLNumber = (EditText) findViewById(R.id.editTextBOLNumber);

        /**Camera Button**/
        buttonTakePicture = findViewById(R.id.buttonTakePicture);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });
        imageView = findViewById(R.id.image);
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
        new DatePickerDialog(DriverInputSourceActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
        new TimePickerDialog(DriverInputSourceActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    /**Camera functions below**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void dispatchPictureTakerAction() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createPhotoFile();

            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(DriverInputSourceActivity.this, "com.csci4060.project.aimsmobileapp.fileprovider", photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePicture, 1);
            }
        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDirectory = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDirectory);
        } catch (IOException e) {
            Log.d("errorLog", "Exception: " + e.toString());
        }
        return image;
    }
}