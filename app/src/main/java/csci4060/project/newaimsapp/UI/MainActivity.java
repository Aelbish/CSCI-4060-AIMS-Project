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

public class MainActivity extends AppCompatActivity {


    /**
     * Sets up a bottom navbar and also calls the ApiCall class which handles all ApiCalls
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        queue = APISingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    parser = new TripJSONParser(response.toString());
                    parser.parseData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ApiGet", error.toString());
            }
        });

        queue.add(jsonObjectRequest);
    }

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