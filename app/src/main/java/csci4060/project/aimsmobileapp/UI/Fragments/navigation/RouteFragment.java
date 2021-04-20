package csci4060.project.aimsmobileapp.UI.Fragments.navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.here.sdk.mapview.WatermarkPlacement;

import csci4060.project.aimsmobileapp.R;

//This is route screen
public class RouteFragment extends Fragment {

    private MapView mapView;
    private LocationManager locationManager;
    private LocationListener locationListner;
    private App app;
    private static final String TAG = RouteFragment.class.getSimpleName();

    private PermissionsRequestor permissionsRequestor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        // Keeping the screen alive is essential for a car navigation app.
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Toolbar myToolbar = getActivity().findViewById(R.id.toolbar);
        getActivity().setActionBar(myToolbar);

        // Get a MapView instance from the layout.
        mapView = getView().findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.setOnReadyListener(new MapView.OnReadyListener() {
            @Override
            public void onMapViewReady() {
                // This will be called each time after this activity is resumed.
                // It will not be called before the first map scene was loaded.
                // Any code that requires map data may not work as expected beforehand.
                Log.d(TAG, "HERE Rendering Engine attached.");
            }
        });
        // Reposition HERE logo, so it's not hidden by Android's Snackbar.
        long bottomCenterMarginInPixels = (long) (getResources().getDisplayMetrics().density * 80);
        mapView.setWatermarkPosition(WatermarkPlacement.BOTTOM_CENTER, bottomCenterMarginInPixels);

        handleAndroidPermissions();

        ToggleButton toggleTrackingButton = getActivity().findViewById(R.id.toggleTrackingButton);
        toggleTrackingButton.setTextOn("Camera Tracking: ON");
        toggleTrackingButton.setTextOff("Camera Tracking: OFF");
        toggleTrackingButton.setChecked(true);
        toggleTrackingButton.setOnClickListener(v -> {
            if (toggleTrackingButton.isChecked()) {
                app.toggleTrackingButtonOnClicked();
            } else {
                app.toggleTrackingButtonOffClicked();
            }
        });
        //loadMapScene();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                // Required by HERE positioning.
                // User must be able to see & to change his consent to collect data.
                Intent intent = new Intent(getActivity(), ConsentStateActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleAndroidPermissions() {
        permissionsRequestor = new PermissionsRequestor(getActivity());
        permissionsRequestor.request(new PermissionsRequestor.ResultListener(){

            @Override
            public void permissionsGranted() {
                loadMapScene();
            }

            @Override
            public void permissionsDenied() {
                Log.e(TAG, "Permissions denied by user.");
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsRequestor.onRequestPermissionsResult(requestCode, grantResults);
    }

    private void loadMapScene() {

        // Load a scene from the HERE SDK to render the map with a map scheme.
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            double latitude;
            double longitude;
            MapImage mapImage = MapImageFactory.fromResource(getActivity().getResources(), R.drawable.small_pin);
            @Override
            public void onLoadScene(@Nullable MapError mapError) {
                locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);

                locationListner = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        GeoCoordinates geo = new GeoCoordinates(latitude, longitude);
                        MapMarker mapMarker = new MapMarker(geo, mapImage);
                        //live location
                        if (mapError == null) {
                            double distanceInMeters = 1000 * 10;
                            mapView.getCamera().lookAt(
                                    geo, distanceInMeters);
                            mapView.getMapScene().addMapMarker(mapMarker);
                            // Start the app that contains the logic to calculate routes & start TBT guidance.
                            app = new App(getActivity(), mapView);

                            // Enable traffic flows by default.
                            mapView.getMapScene().setLayerState(MapScene.Layers.TRAFFIC_FLOW, MapScene.LayerState.VISIBLE);


                        } else {
                            Log.d(TAG, "Loading map failed: mapError: " + mapError.name());
                        }




                    }

                    @Override
                    public void onProviderDisabled(@NonNull String provider) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                };
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 1);
                        configureButton();
                    } else {
                        configureButton();
                    }
                }

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void configureButton() {
                locationManager.requestLocationUpdates("gps", 100, 1, locationListner);
            }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                    return;
        }
    }


    public void addRouteSimulatedLocationButtonClicked(View view) {
        app.addRouteSimulatedLocation();
    }

    public void addRouteDeviceLocationButtonClicked(View view) {
        app.addRouteDeviceLocation();
    }

    public void clearMapButtonClicked(View view) {
        app.clearMapButtonPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (app != null) {
            app.stopLocating();
        }
        mapView.onDestroy();
    }
}
