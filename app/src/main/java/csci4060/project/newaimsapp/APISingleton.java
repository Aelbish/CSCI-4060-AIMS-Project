package csci4060.project.newaimsapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * This class is purely to provide a constant access to the API request queue so we can make network
 * calls whenever we need to instead of having the instantiate it multiple times
 */
public class APISingleton {
    private static APISingleton INSTANCE;
    private RequestQueue requestQueue;
    private static Context context;

    private APISingleton(Context context) {
        APISingleton.context = context;
        requestQueue = getRequestQueue();
    }

    //Checks to make sure that only one instance of this class is ever instantiated
    public static synchronized APISingleton getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new APISingleton(context);
        }
        return INSTANCE;
    }

    //Same as above
    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
