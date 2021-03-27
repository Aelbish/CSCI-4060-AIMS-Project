package csci4060.project.newaimsapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import csci4060.project.newaimsapp.APISingleton;
import csci4060.project.newaimsapp.ApiCall;
import csci4060.project.newaimsapp.R;
import csci4060.project.newaimsapp.TripJSONParser;
import csci4060.project.newaimsapp.UI.Fragments.DetailsFragment;
import csci4060.project.newaimsapp.UI.Fragments.InputsFragment;
import csci4060.project.newaimsapp.UI.Fragments.LoadsFragment;
import csci4060.project.newaimsapp.UI.Fragments.TripsFragment;

/**
 * The main entry point for our app. Currently, this will obtain and parse the data from AIMS for all trips assigned to our driver
 */
public class MainActivity extends AppCompatActivity{


    /**
     * Sets up a bottom navbar and also calls the ApiCall class which handles all ApiCalls
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets up the navbar and creates the listener to listen for clicks
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Instantiates the ApiCall class to make Api Requests
        ApiCall call = new ApiCall(this.getApplicationContext());

        //Gets the trip information from AIMS
        call.getTripInformation();
    }

    /**
     * Navbar listener to determine what button on the navbar open which fragment
     */
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.nav_trips:
                selectedFragment = new TripsFragment();
                break;

            case R.id.nav_details:
                selectedFragment = new LoadsFragment();
                break;

            case R.id.nav_input:
                selectedFragment = new InputsFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();

        return true;
    };
}