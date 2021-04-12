package csci4060.project.aimsmobileapp.UI.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.DataRepository;
import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.database.entity.SiteInput;
import csci4060.project.aimsmobileapp.database.entity.SourceInput;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class DriverInputSourceActivity extends AppCompatActivity implements View.OnClickListener {
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

    double pickupGrossToNetRatio;

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

    Button buttonTakePicture, btnSubmit;
    ImageView imageView;
    String pathToFile;
    String yourProduct;
    private Spinner spinnerProductType;
    private final DataRepository repository = AIMSApp.repository;
    int trip_id;
    int load_id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_input_source);

        Bundle bundle = getIntent().getExtras();
        trip_id = Integer.parseInt(bundle.getString("TripId"));
        load_id = Integer.parseInt(bundle.getString("SeqNum"));

        /**Spinner for product types**/
        spinnerProductType = findViewById(R.id.spinnerProductType);
        List<String> productType = new ArrayList<>();

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

        int spinnerPosition;
        String productTypeDatabaseEntry = repository.getProduct_typeSource(trip_id, load_id);

        int i = 0;
        for (String s : productType) {
            if (productTypeDatabaseEntry != null && productTypeDatabaseEntry.equals(s)) {
                spinnerPosition = i;
                spinnerProductType.setSelection(spinnerPosition);
            }
            i++;
        }

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
        editTextStartDate.setText(repository.getStart_dateSource(trip_id, load_id));
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(editTextStartDate);
            }
        });

        /**Get start time**/
        editTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        editTextStartTime.setInputType(InputType.TYPE_NULL);
        editTextStartTime.setText(repository.getStart_timeSource(trip_id, load_id));
        editTextStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(editTextStartTime);
            }
        });

        /**Get end date**/
        editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
        editTextEndDate.setInputType(InputType.TYPE_NULL);
        editTextEndDate.setText(repository.getEnd_dateSource(trip_id, load_id));
        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(editTextEndDate);
            }
        });

        /**Get end time**/
        editTextEndTime = (EditText) findViewById(R.id.editTextEndTime);
        editTextEndTime.setInputType(InputType.TYPE_NULL);
        editTextEndTime.setText(repository.getEnd_timeSource(trip_id, load_id));
        editTextEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(editTextEndTime);
            }
        });
        editTextTrailerGrossQuantity = (EditText) findViewById(R.id.editTextTrailerGrossQuantity);
        editTextTrailerGrossQuantity.setText(Double.toString(repository.getTrailer_gross_quantitySource(trip_id, load_id)));

        editTextTrailerNetQuantity = (EditText) findViewById(R.id.editTextTrailerNetQuantity);
        editTextTrailerNetQuantity.setText(Double.toString(repository.getTrailer_net_quantitySource(trip_id, load_id)));

        editTextStartMeterReading = (EditText) findViewById(R.id.editTextStartMeterReading);
        editTextStartMeterReading.setText(Double.toString(repository.getStart_meter_readingSource(trip_id, load_id)));

        editTextEndMeterReading = (EditText) findViewById(R.id.editTextEndMeterReading);
        editTextEndMeterReading.setText(Double.toString(repository.getEnd_meter_readingSource(trip_id, load_id)));

        editTextPickupGrossQuantity = (EditText) findViewById(R.id.editTextPickupGrossQuantity);
        editTextPickupGrossQuantity.setText(Double.toString(repository.getPickup_gross_quantitySource(trip_id, load_id)));

        editTextPickupNetQuantity = (EditText) findViewById(R.id.editTextPickupNetQuantity);
        editTextPickupNetQuantity.setText(Double.toString(repository.getPickup_net_quantitySource(trip_id, load_id)));

        editTextBOLNumber = (EditText) findViewById(R.id.editTextBOLNumber);
        editTextBOLNumber.setText(Integer.toString(repository.getBOLNumberSource(trip_id, load_id)));

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
        btnSubmit = findViewById(R.id.btnSubmitInputSiteData);
        btnSubmit.setOnClickListener(this);
    }

    /***Start and End Date*/
    private void showDateDialog(EditText editText) {
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

    /**
     * Start and End Time
     **/
    private void showTimeDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(DriverInputSourceActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    /**
     * Camera functions below
     **/
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
                Uri photoURI = FileProvider.getUriForFile(DriverInputSourceActivity.this, "csci4060.project.aimsmobileapp.fileprovider", photoFile);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmitInputSiteData) {
            validateAndSubmitFormData();
        }
    }

    //TODO rewrite if statements to something more performance friendly
    //TODO check input validation after changing inputs to numberDoubles
    public void validateAndSubmitFormData() {
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

//        Validate product type
        if (yourProduct.equals("") || yourProduct.equals("Select product type")) {
            Toast.makeText(this, "Please select product type", Toast.LENGTH_SHORT).show();
            spinnerProductType.requestFocus();
        }

        //start of validation for date and time
        else if (startTime.equals("")) {
            Toast.makeText(this, "Please select start time", Toast.LENGTH_SHORT).show();
            editTextStartTime.setError("Please select start time");
            editTextStartTime.requestFocus();
        } else if (startDate.equals("")) {
            Toast.makeText(this, "Please select start date", Toast.LENGTH_SHORT).show();
            editTextStartDate.setError("Please select start date");
            editTextStartDate.requestFocus();
        } else if (endDate.equals("")) {
            Toast.makeText(this, "Please select end date", Toast.LENGTH_SHORT).show();
            editTextEndDate.setError("Please select end date");
            editTextEndDate.requestFocus();
        } else if (endTime.equals("")) {
            Toast.makeText(this, "Please select end time", Toast.LENGTH_SHORT).show();
            editTextEndTime.setError("Please select end time");
            editTextEndTime.requestFocus();
        }
//        End of validation for date and time


//        validation of fields for value in gal
        else if (trailerGrossQuantity.equals("")) {
            Toast.makeText(this, "Please enter trailer gross quantity", Toast.LENGTH_SHORT).show();
            editTextTrailerGrossQuantity.setError("Please enter trailer gross quantity");
            editTextTrailerGrossQuantity.requestFocus();
        } else if (!trailerGrossQuantity.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid trailer gross quantity", Toast.LENGTH_SHORT).show();
            editTextTrailerGrossQuantity.setError("Please enter trailer valid gross quantity");
            editTextTrailerGrossQuantity.requestFocus();
        } else if (trailerNetQuantity.equals("")) {
            Toast.makeText(this, "Please enter trailer net quantity", Toast.LENGTH_SHORT).show();
            editTextTrailerNetQuantity.setError("Please enter trailer net quantity");
            editTextTrailerNetQuantity.requestFocus();
        } else if (!trailerNetQuantity.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid net quantity", Toast.LENGTH_SHORT).show();
            editTextTrailerNetQuantity.setError("Please enter valid net quantity");
            editTextTrailerNetQuantity.requestFocus();
        }


//       starting Meter reading
        else if (startMeterReading.equals("")) {
            Toast.makeText(this, "Please enter beginning meter reading", Toast.LENGTH_SHORT).show();
            editTextStartMeterReading.setError("Please enter beginning meter reading");
            editTextStartMeterReading.requestFocus();
        } else if (!startMeterReading.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid beginning meter reading", Toast.LENGTH_SHORT).show();
            editTextStartMeterReading.setError("Please enter valid beginning meter reading");
            editTextStartMeterReading.requestFocus();
        }

        //       ending Meter reading
        else if (endMeterReading.equals("")) {
            Toast.makeText(this, "Please enter ending meter reading", Toast.LENGTH_SHORT).show();
            editTextEndMeterReading.setError("Please enter ending meter reading");
            editTextEndMeterReading.requestFocus();
        } else if (!endMeterReading.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid ending meter reading", Toast.LENGTH_SHORT).show();
            editTextEndMeterReading.setError("Please enter valid ending meter reading");
            editTextEndMeterReading.requestFocus();
        }


//        pickup gross quantity
        else if (pickupGrossQuantity.equals("")) {
            Toast.makeText(this, "Please enter pickup gross quantity", Toast.LENGTH_SHORT).show();
            editTextPickupGrossQuantity.setError("Please enter pickup gross quantity");
            editTextPickupGrossQuantity.requestFocus();
        } else if (!pickupGrossQuantity.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid pickup gross quantity", Toast.LENGTH_SHORT).show();
            editTextPickupGrossQuantity.setError("Please enter pickup gross quantity");
            editTextPickupGrossQuantity.requestFocus();
        }


//        pickup net quantity
        else if (pickupNetQuantity.equals("")) {
            Toast.makeText(this, "Please enter pickup net quantity", Toast.LENGTH_SHORT).show();
            editTextPickupNetQuantity.setError("Please enter pickup net quantity");
            editTextPickupNetQuantity.requestFocus();
        } else if (!pickupNetQuantity.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid pickup net quantity", Toast.LENGTH_SHORT).show();
            editTextPickupNetQuantity.setError("Please enter pickup net quantity");
            editTextPickupNetQuantity.requestFocus();
        }

//      bill of lading number
        else if (bolNumber.equals("")) {
            Toast.makeText(this, "Please enter BOL number", Toast.LENGTH_SHORT).show();
            editTextBOLNumber.setError("Please enter BOL number");
            editTextBOLNumber.requestFocus();
        } else if (!bolNumber.matches("[0-9]+")) {
            Toast.makeText(this, "Please enter valid BOL number", Toast.LENGTH_SHORT).show();
            editTextBOLNumber.setError("Please enter valid BOL number");
            editTextBOLNumber.requestFocus();
        } else {
//            set null free after validation
            editTextStartTime.setError(null);
            editTextEndTime.setError(null);
            editTextStartDate.setError(null);
            editTextEndDate.setError(null);
            editTextTrailerGrossQuantity.setError(null);
            editTextTrailerNetQuantity.setError(null);
            editTextStartMeterReading.setError(null);
            editTextEndMeterReading.setError(null);
            editTextPickupGrossQuantity.setError(null);
            editTextPickupNetQuantity.setError(null);
            editTextBOLNumber.setError(null);

            pickupGrossToNetRatio = Double.parseDouble(pickupNetQuantity) / Double.parseDouble(pickupGrossQuantity);

            addSourceInputToDatabase();
            tempToastToShowInput();

//            callApiAndSendData();

        }

    }

    private void addSourceInputToDatabase(){
        repository.addSourceInput(new SourceInput(trip_id, load_id));
        repository.setProductTypeSource(yourProduct, trip_id, load_id);
        repository.setStart_dateSource(startDate, trip_id, load_id);
        repository.setStart_timeSource(startTime, trip_id, load_id);
        repository.setEnd_dateSource(endDate, trip_id, load_id);
        repository.setEnd_timeSource(endTime, trip_id, load_id);
        repository.setTrailer_gross_quantitySource(Double.parseDouble(trailerGrossQuantity), trip_id, load_id);
        repository.setTrailer_net_quantitySource(Double.parseDouble(trailerNetQuantity), trip_id, load_id);
        repository.setStart_meter_readingSource(Double.parseDouble(startMeterReading), trip_id, load_id);
        repository.setEnd_meter_readingSource(Double.parseDouble(endMeterReading), trip_id, load_id);
        repository.setPickup_gross_quantitySource(Double.parseDouble(pickupGrossQuantity), trip_id, load_id);
        repository.setPickup_net_quantitySource(Double.parseDouble(pickupNetQuantity), trip_id, load_id);
        repository.setBol_numberSource(Integer.parseInt(bolNumber), trip_id, load_id);
        repository.setPickup_ratioSource(pickupGrossToNetRatio, trip_id, load_id);
    }

    private void tempToastToShowInput(){
        Toast.makeText(this,
                "Product Type: " + repository.getProduct_typeSource(trip_id, load_id)+"\n" +
                        "Start Date: " + repository.getStart_date(trip_id, load_id)+"\n" +
                        "Start Time: " + repository.getStart_time(trip_id, load_id)+"\n" +
                        "End Date: " + repository.getEnd_date(trip_id, load_id)+"\n" +
                        "End Time: " + repository.getEnd_time(trip_id, load_id)+"\n" +
                        "Trailer Gross: " + Double.toString(repository.getTrailer_gross_quantitySource(trip_id, load_id)) +"\n" +
                        "Trailer Net: " + Double.toString(repository.getTrailer_net_quantitySource(trip_id, load_id)) +"\n" +
                        "Meter Reading Before Dropoff: " + Double.toString(repository.getStart_meter_reading(trip_id, load_id))+"\n" +
                        "Meter Reading After Dropoff: " + Double.toString(repository.getEnd_meter_reading(trip_id, load_id))+"\n" +
                        "Pickup Gross: " + Double.toString(repository.getPickup_gross_quantitySource(trip_id, load_id)) +"\n" +
                        "Pickup Net: " + Double.toString(repository.getPickup_net_quantitySource(trip_id, load_id)) +"\n" +
                        "BOL Num: " + Integer.toString(repository.getBOLNumberSource(trip_id, load_id))
                        , Toast.LENGTH_LONG).show();
    }

}

