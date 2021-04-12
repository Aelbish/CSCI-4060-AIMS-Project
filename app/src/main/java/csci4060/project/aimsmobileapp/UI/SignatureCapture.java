package csci4060.project.aimsmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import csci4060.project.aimsmobileapp.R;

public class SignatureCapture extends AppCompatActivity {
    SignaturePad signaturePad;
    Button saveButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_capture2);

        signaturePad = (SignaturePad)findViewById(R.id.signaturePad);
        saveButton = (Button)findViewById(R.id.saveButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        //disable both buttons at start
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        //change screen orientation to landscape mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write code for saving the signature here
                Toast.makeText(SignatureCapture.this, "Signature Saved", Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });
    }
}