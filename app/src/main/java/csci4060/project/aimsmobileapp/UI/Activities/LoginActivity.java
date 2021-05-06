package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import csci4060.project.aimsmobileapp.R;

//This is the Login Page
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)){
            this.sendMessage();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnLogin= findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    public void sendMessage(){
        Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnLogin){

            this.sendMessage();
            sp.edit().putBoolean("logged",true).apply();
        }
    }
}