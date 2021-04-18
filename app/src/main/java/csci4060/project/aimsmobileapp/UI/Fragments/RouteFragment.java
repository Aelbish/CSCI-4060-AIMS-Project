package csci4060.project.aimsmobileapp.UI.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import csci4060.project.aimsmobileapp.R;

import static android.content.ContentValues.TAG;

//This is route screen
public class RouteFragment extends Fragment {

    private MapView mapView;
    private LocationManager locationManager;
    private LocationListener locationListner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        loadMapScene();
    }

    private void loadMapScene() {

        // Load a scene from the HERE SDK to render the map with a map scheme.
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            double latitude;
            double longitude;
            @Override
            public void onLoadScene(@Nullable MapError mapError) {
                locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
                locationListner = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        //live location
                        if (mapError == null) {
                            double distanceInMeters = 1000 * 10;
                            mapView.getCamera().lookAt(
                                    new GeoCoordinates(latitude, longitude), distanceInMeters);

                            MapImage mapImage = MapImageFactory.fromResource(getActivity().getResources(), R.drawable.small_pin);
                            MapMarker mapMarker = new MapMarker(new GeoCoordinates(latitude, longitude), mapImage);

                            mapView.getMapScene().addMapMarker(mapMarker);
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
        mapView.onDestroy();
    }
}
