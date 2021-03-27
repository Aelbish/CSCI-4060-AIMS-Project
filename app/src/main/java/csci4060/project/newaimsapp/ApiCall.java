package csci4060.project.newaimsapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Handles the different API calls we need to make
 */
public class ApiCall {
    //parser to parse the trip information we get from aims
    private TripJSONParser parser;
    private RequestQueue queue;

    /**
     * This instantiates a RequestQueue that will stay open for the life of the app like the repository and database in the AIMSApp class
     * @param context
     */
    public ApiCall(Context context) {
        queue = APISingleton.getInstance(context.getApplicationContext()).getRequestQueue();
    }

    /**
     * This is the method that calls the AIMS API and gets the trip information we need to display to the driver
     */
    public void getTripInformation() {
        String url = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/GetTripListDetailByDriver/D1?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            /**
             * This gives the JSON response from the API call and in the method we give it over to our JSON parser class to store the data in the database
             * @param response
             */
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

        //This is what actually sends the request to the API
        queue.add(jsonObjectRequest);
    }

    //***UNFINISHED METHOD***Attempt to send the status code to AIMS for our trip selection
    public void sendTripSelection(String driver_code, String trip_id, String status_code, String status_msg, String bIncoming, String status_date) {
        String url = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/TripStatusPut/" + driver_code + "/" + trip_id + "/" + status_code + "/" + status_msg + "/" + bIncoming + "/" + status_date + "?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";

    }
}
