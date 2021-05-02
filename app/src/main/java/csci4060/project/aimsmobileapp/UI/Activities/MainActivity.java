package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import csci4060.project.aimsmobileapp.R;

//This is the Login Page
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_main);

        btnLogin= findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    public void sendMessage(){
        Intent intent = new Intent(MainActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnLogin){
            this.sendMessage();
        }
    }
}