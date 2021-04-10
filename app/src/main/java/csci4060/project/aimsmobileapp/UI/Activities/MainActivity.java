package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import csci4060.project.aimsmobileapp.R;

//This is the Login Page
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(MainActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }
}