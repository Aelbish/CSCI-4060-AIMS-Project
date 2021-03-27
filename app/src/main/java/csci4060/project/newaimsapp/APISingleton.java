package csci4060.project.newaimsapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APISingleton {
    private static APISingleton INSTANCE;
    private RequestQueue requestQueue;
    private static Context context;

    private APISingleton(Context context) {
        APISingleton.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized APISingleton getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new APISingleton(context);
        }
        return INSTANCE;
    }

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
