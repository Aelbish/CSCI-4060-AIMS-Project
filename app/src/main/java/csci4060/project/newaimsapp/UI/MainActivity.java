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
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import csci4060.project.newaimsapp.APISingleton;
import csci4060.project.newaimsapp.R;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private String url = "https://run.mocky.io/v3/83dc69b1-f3bf-42ce-aec2-ab19225ef5e1";

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
                Log.e("ApiGet", response.toString());
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