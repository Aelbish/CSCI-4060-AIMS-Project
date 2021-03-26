package csci4060.project.newaimsapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import csci4060.project.newaimsapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.nav_trips:
                selectedFragment = new TripsFragment();
                break;

            case R.id.nav_details:
                selectedFragment = new DetailsFragment();
                break;

            case R.id.nav_input:
                selectedFragment = new InputsFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();

        return true;
    };
}