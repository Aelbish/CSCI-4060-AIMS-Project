package csci4060.project.aimsmobileapp.UI.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import csci4060.project.aimsmobileapp.AIMSApp;
import csci4060.project.aimsmobileapp.DataRepository;
import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.UI.SignatureCapture;
import csci4060.project.aimsmobileapp.database.entity.SiteInput;

public class DriverInputSiteActivity extends AppCompatActivity implements View.OnClickListener {


        private IntentIntegrator intentIntegrator;
    String
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

    double pickupGrossToNetRatio;

    EditText
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

    Button btnSubmit;

    /**Scan button for barcode scanner**/
    Button buttonScan;
    ImageView imageSignature;

    String yourProduct;

    private Spinner spinnerProductType;

    private final DataRepository repository = AIMSApp.repository;
    public static final int SIGNATURE_ACTIVITY = 10;
    public static final int BARCODE_ACTIVITY = 20;

    int trip_id;
    int load_id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_input_site);

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
        String productTypeDatabaseEntry = repository.getProduct_type(trip_id, load_id);

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

        /**Get start date**/
        editTextStartDate = (EditText) findViewById(R.id.editTextStartDate);
        editTextStartDate.setInputType(InputType.TYPE_NULL);
        editTextStartDate.setText(repository.getStart_date(trip_id, load_id));
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(editTextStartDate);
            }
        });

        /**Get start time**/
        editTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        editTextStartTime.setInputType(InputType.TYPE_NULL);
        editTextStartTime.setText(repository.getStart_time(trip_id, load_id));
        editTextStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(editTextStartTime);
            }
        });

        /**Get end date**/
        editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
        editTextEndDate.setInputType(InputType.TYPE_NULL);
        editTextEndDate.setText(repository.getEnd_date(trip_id, load_id));
        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(editTextEndDate);
            }
        });

        /**Get end time**/
        editTextEndTime = (EditText) findViewById(R.id.editTextEndTime);
        editTextEndTime.setInputType(InputType.TYPE_NULL);
        editTextEndTime.setText(repository.getEnd_time(trip_id, load_id));
        editTextEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(editTextEndTime);
            }
        });

        editTextBeginSiteContainerReading = (EditText) findViewById(R.id.editTextBeginSiteContainerReading);
        editTextBeginSiteContainerReading.setText(Double.toString(repository.getBegin_site_container_reading(trip_id, load_id)));

        editTextEndSiteContainerReading = (EditText) findViewById(R.id.editTextEndSiteContainerReading);
        editTextEndSiteContainerReading.setText(Double.toString(repository.getEnd_site_container_reading(trip_id, load_id)));

        editTextStartMeterReading = (EditText) findViewById(R.id.editTextStartMeterReading);
        editTextStartMeterReading.setText(Double.toString(repository.getStart_meter_reading(trip_id, load_id)));

        editTextEndMeterReading = (EditText) findViewById(R.id.editTextEndMeterReading);
        editTextEndMeterReading.setText(Double.toString(repository.getEnd_meter_reading(trip_id, load_id)));

        editTextDeliveredGrossQuantity = (EditText) findViewById(R.id.editTextDeliveredGrossQuantity);
        editTextDeliveredGrossQuantity.setText(Double.toString(repository.getDelivered_gross_quantity(trip_id, load_id)));

        editTextDeliveredNetQuantity = (EditText) findViewById(R.id.editTextDeliveredNetQuantity);
        editTextDeliveredNetQuantity.setText(Double.toString(repository.getDelivered_net_quantity(trip_id, load_id)));

        editTextDeliveryTicketNumber = (EditText) findViewById(R.id.editTextDeliveryTicketNumber);
        editTextDeliveryTicketNumber.setText(Integer.toString(repository.getDelivery_ticket_number(trip_id, load_id)));

        editTextDeliveryComment = (EditText) findViewById(R.id.editTextDeliveryComment);
        editTextDeliveryComment.setText(repository.getDeliveryComment(trip_id, load_id));

        editTextBarcode = (EditText) findViewById(R.id.editTextBarcode);

        editTextStartDate.setSelectAllOnFocus(true);
        editTextStartTime.setSelectAllOnFocus(true);
        editTextEndDate.setSelectAllOnFocus(true);
        editTextEndTime.setSelectAllOnFocus(true);
        editTextBeginSiteContainerReading.setSelectAllOnFocus(true);
        editTextEndSiteContainerReading.setSelectAllOnFocus(true);
        editTextStartMeterReading.setSelectAllOnFocus(true);
        editTextEndMeterReading.setSelectAllOnFocus(true);
        editTextDeliveredGrossQuantity.setSelectAllOnFocus(true);
        editTextDeliveredNetQuantity.setSelectAllOnFocus(true);
        editTextDeliveryTicketNumber.setSelectAllOnFocus(true);
        editTextDeliveryComment.setSelectAllOnFocus(true);

        btnSubmit = findViewById(R.id.btnSubmitInputSiteData);
        btnSubmit.setOnClickListener(this);

        /**Signature Button**/
        imageSignature= (ImageView) findViewById(R.id.imageSignature);
        Button buttonSignature = findViewById(R.id.buttonSignature);
        buttonSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverInputSiteActivity.this, SignatureCapture.class);
                startActivityForResult(intent, SIGNATURE_ACTIVITY);
            }
        });
        
        /**Barcode Scanner Button**/
        buttonScan = findViewById(R.id.buttonScan);
         intentIntegrator = new IntentIntegrator(this);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentIntegrator.setPrompt("Use volume up key for flash");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(CaptureActivity.class);
                intentIntegrator.initiateScan();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==SIGNATURE_ACTIVITY) {

            /**Signature**/

            if (resultCode == RESULT_OK) {
                String status = data.getStringExtra("status").toString().trim();
                if (status.equalsIgnoreCase("done")) {
                    Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 105, 50);
                    if (data.hasExtra("byteArray")) {
                        Bitmap b = BitmapFactory.decodeByteArray(
                                data.getByteArrayExtra("byteArray"), 0, data.getByteArrayExtra("byteArray").length);
                        imageSignature.setImageBitmap(b);
                        toast.show();
                    }
                }
            }
        }
        if(requestCode==intentIntegrator.REQUEST_CODE){

                /**Scan barcode**/
                IntentResult intentResult = IntentIntegrator.parseActivityResult(
                        requestCode, resultCode, data
                );

                    if (intentResult.getContents() != null) {
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
                    } else {
                        Toast.makeText(getApplicationContext()
                                , "Please scan the barcode", Toast.LENGTH_SHORT).show();
                    }
                }

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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(DriverInputSiteActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(DriverInputSiteActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
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
        else if (beginSiteContainerReading.equals("")) {
            Toast.makeText(this, "Please enter site container reading before dropoff", Toast.LENGTH_SHORT).show();
            editTextBeginSiteContainerReading.setError("Please enter site container reading before dropoff");
            editTextBeginSiteContainerReading.requestFocus();
        } else if (!beginSiteContainerReading.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid site container reading", Toast.LENGTH_SHORT).show();
            editTextBeginSiteContainerReading.setError("Please enter valid site container reading");
            editTextBeginSiteContainerReading.requestFocus();
        } else if (endSiteContainerReading.equals("")) {
            Toast.makeText(this, "Please enter site container reading after dropoff", Toast.LENGTH_SHORT).show();
            editTextEndSiteContainerReading.setError("Please enter site container reading after dropoff");
            editTextEndSiteContainerReading.requestFocus();
        } else if (!endSiteContainerReading.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid site container reading", Toast.LENGTH_SHORT).show();
            editTextEndSiteContainerReading.setError("Please enter valid site container reading");
            editTextEndSiteContainerReading.requestFocus();
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
        else if (deliveredGrossQuantity.equals("")) {
            Toast.makeText(this, "Please enter delivered gross quantity", Toast.LENGTH_SHORT).show();
            editTextDeliveredGrossQuantity.setError("Please enter delivered gross quantity");
            editTextDeliveredGrossQuantity.requestFocus();
        } else if (!deliveredGrossQuantity.matches("[0-9]+\\.?[0-9]*") || deliveredGrossQuantity.matches("[0]+.*")) {
            Toast.makeText(this, "Please enter valid delivered gross quantity", Toast.LENGTH_SHORT).show();
            editTextDeliveredGrossQuantity.setError("Please enter valid delivered gross quantity");
            editTextDeliveredGrossQuantity.requestFocus();
        }


//        pickup net quantity
        else if (deliveredNetQuantity.equals("")) {
            Toast.makeText(this, "Please enter pickup net quantity", Toast.LENGTH_SHORT).show();
            editTextDeliveredNetQuantity.setError("Please enter pickup net quantity");
            editTextDeliveredNetQuantity.requestFocus();
        } else if (!deliveredNetQuantity.matches("[0-9]+\\.?[0-9]*")) {
            Toast.makeText(this, "Please enter valid pickup net quantity", Toast.LENGTH_SHORT).show();
            editTextDeliveredNetQuantity.setError("Please enter pickup net quantity");
            editTextDeliveredNetQuantity.requestFocus();
        }

//      bill of lading number
        else if (deliveryTicketNumber.equals("")) {
            Toast.makeText(this, "Please enter delivery ticket number", Toast.LENGTH_SHORT).show();
            editTextDeliveryTicketNumber.setError("Please enter delivery ticket number");
            editTextDeliveryTicketNumber.requestFocus();
        } else if (!deliveryTicketNumber.matches("[0-9]+")) {
            Toast.makeText(this, "Please enter valid delivery ticket number", Toast.LENGTH_SHORT).show();
            editTextDeliveryTicketNumber.setError("Please enter valid delivery ticket number");
            editTextDeliveryTicketNumber.requestFocus();
        } else {
//            set null free after validation
            editTextStartTime.setError(null);
            editTextEndTime.setError(null);
            editTextStartDate.setError(null);
            editTextEndDate.setError(null);
            editTextBeginSiteContainerReading.setError(null);
            editTextEndSiteContainerReading.setError(null);
            editTextStartMeterReading.setError(null);
            editTextEndMeterReading.setError(null);
            editTextDeliveredGrossQuantity.setError(null);
            editTextDeliveredNetQuantity.setError(null);
            editTextDeliveryTicketNumber.setError(null);

            pickupGrossToNetRatio = Double.parseDouble(deliveredNetQuantity) / Double.parseDouble(deliveredGrossQuantity);

            addSiteInputToDatabase();
            tempToastToShowInput();

//            callApiAndSendData();

        }

    }

    private void addSiteInputToDatabase(){
        repository.addSiteInput(new SiteInput(trip_id, load_id));
        repository.setProductType(yourProduct, trip_id, load_id);
        repository.setStart_date(startDate, trip_id, load_id);
        repository.setStart_time(startTime, trip_id, load_id);
        repository.setEnd_date(endDate, trip_id, load_id);
        repository.setEnd_time(endTime, trip_id, load_id);
        repository.setBegin_site_container_reading(Double.parseDouble(beginSiteContainerReading), trip_id, load_id);
        repository.setEnd_site_container_reading(Double.parseDouble(endSiteContainerReading), trip_id, load_id);
        repository.setStart_meter_reading(Double.parseDouble(startMeterReading), trip_id, load_id);
        repository.setEnd_meter_reading(Double.parseDouble(endMeterReading), trip_id, load_id);
        repository.setDelivered_gross_quantity(Double.parseDouble(deliveredGrossQuantity), trip_id, load_id);
        repository.setDelivered_net_quantity(Double.parseDouble(deliveredNetQuantity), trip_id, load_id);
        repository.setDelivery_ticket_number(Integer.parseInt(deliveryTicketNumber), trip_id, load_id);
        repository.setDeliveryComment(deliveryComment, trip_id, load_id);
        repository.setPickup_ratio(pickupGrossToNetRatio, trip_id, load_id);
    }

    private void tempToastToShowInput(){
        Toast.makeText(this,
                   "Product Type: " + repository.getProduct_type(trip_id, load_id)+"\n" +
                        "Start Date: " + repository.getStart_date(trip_id, load_id)+"\n" +
                        "Start Time: " + repository.getStart_time(trip_id, load_id)+"\n" +
                        "End Date: " + repository.getEnd_date(trip_id, load_id)+"\n" +
                        "End Time: " + repository.getEnd_time(trip_id, load_id)+"\n" +
                        "Container Before Dropoff: " + Double.toString(repository.getBegin_site_container_reading(trip_id, load_id))+"\n" +
                        "Container After Dropoff: " + Double.toString(repository.getEnd_site_container_reading(trip_id, load_id))+"\n" +
                        "Meter Reading Before Dropoff: " + Double.toString(repository.getStart_meter_reading(trip_id, load_id))+"\n" +
                        "Meter Reading After Dropoff: " + Double.toString(repository.getEnd_meter_reading(trip_id, load_id))+"\n" +
                        "Delivered Gross: " + Double.toString(repository.getDelivered_gross_quantity(trip_id, load_id))+"\n" +
                        "Delivered Net: " + Double.toString(repository.getDelivered_net_quantity(trip_id, load_id))+"\n" +
                        "Delivery Ticket Num: " + Integer.toString(repository.getDelivery_ticket_number(trip_id, load_id))+"\n" +
                        "Comments: " + repository.getDeliveryComment(trip_id, load_id), Toast.LENGTH_LONG).show();



    }

}