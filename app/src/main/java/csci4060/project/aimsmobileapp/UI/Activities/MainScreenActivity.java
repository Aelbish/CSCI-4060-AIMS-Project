package csci4060.project.aimsmobileapp.UI.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.UI.Fragments.AlertFragment;
import csci4060.project.aimsmobileapp.UI.Fragments.navigation.RouteFragment;
import csci4060.project.aimsmobileapp.UI.Fragments.TripFragment;

//This is the main screen
public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        BottomNavigationView bottomNav = findViewById(R.id.botom_navbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new TripFragment();
            switch (item.getItemId()){
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
}