package csci4060.project.aimsmobileapp.UI.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.UI.Fragments.AlertFragment;
import csci4060.project.aimsmobileapp.UI.Fragments.TripFragment;
import csci4060.project.aimsmobileapp.UI.Fragments.navigation.RouteFragment;

//This is the main screen
public class MainScreenActivity extends AppCompatActivity {
    Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new TripFragment();
            switch (item.getItemId()) {
                case R.id.nav_trips:
                    selectedFragment = new TripFragment();
                    break;
                case R.id.nav_route:
                    selectedFragment = new RouteFragment();
                    break;
                case R.id.nav_alerts:
                    selectedFragment = new AlertFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        BottomNavigationView bottomNav = findViewById(R.id.botom_navbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_manual:
                Toast.makeText(this, "Clicked on user manual", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}