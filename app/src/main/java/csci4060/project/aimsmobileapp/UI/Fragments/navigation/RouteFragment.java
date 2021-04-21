package csci4060.project.aimsmobileapp.UI.Fragments.navigation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapRoute;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.MapEngine;

import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.guidance.LaneInformation;
import com.here.android.mpa.guidance.NavigationManager;

import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.mapping.OnMapRenderListener;
import com.here.android.mpa.routing.CoreRouter;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.RoutingError;

import java.util.List;

import csci4060.project.aimsmobileapp.R;

//This is route screen
public class RouteFragment extends Fragment {

    double destinationLat;
    double destinationLon;

    public void setDestination(double lat, double lon){
        destinationLat = lat;
        destinationLon = lon;
    }

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private static final String[] RUNTIME_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    private AndroidXMapFragment m_mapFragment;
    private com.here.android.mpa.mapping.Map m_map;

    private MapMarker m_positionIndicatorFixed = null;
    private PointF m_mapTransformCenter;
    private boolean m_returningToRoadViewMode = false;
    private double m_lastZoomLevelInRoadViewMode = 0.0;
    private MapRoute m_currentRoute;
    private LinearLayout m_laneInfoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_mapFragment = getMapFragment();
        m_laneInfoView = getActivity().findViewById(R.id.laneInfoLayout);
        // Get a MapView instance from the layout.
        if (hasPermissions(getActivity(), RUNTIME_PERMISSIONS)) {
            initMapFragment();
        } else {
            ActivityCompat
                    .requestPermissions(getActivity(), RUNTIME_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
            initMapFragment();
        }

    }
    private AndroidXMapFragment getMapFragment() {
        return (AndroidXMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);
    }
    private void initMapFragment() {

        // This will use external storage to save map cache data, it is also possible to set
        // private app's path
        String path = new File(getActivity().getExternalFilesDir(null),
                ".here-map-data")
                .getAbsolutePath();
        // This method will throw IllegalArgumentException if provided path is not writable
        com.here.android.mpa.common.MapSettings.setDiskCacheRootPath(path);

        if (m_mapFragment != null) {

            /* Initialize the AndroidXMapFragment, results will be given via the called back. */
            m_mapFragment.init(new OnEngineInitListener() {
                @Override
                public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {

                    if (error == OnEngineInitListener.Error.NONE) {
                        m_mapFragment.getMapGesture().addOnGestureListener(gestureListener, 100, true);
                        // retrieve a reference of the map from the map fragment
                        m_map = m_mapFragment.getMap();
                        m_map.setCenter(new GeoCoordinate(32.5270 ,-92.0740,1),Map.Animation.NONE);
                        m_map.setZoomLevel(19);
                        m_map.addTransformListener(onTransformListener);

                        PositioningManager.getInstance().start(PositioningManager.LocationMethod.GPS_NETWORK);

                    } else {
                        new AlertDialog.Builder(getActivity()).setMessage(
                                "Error : " + error.name() + "\n\n" + error.getDetails())
                                .setTitle(R.string.engine_init_error)
                                .setNegativeButton(android.R.string.cancel,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                getActivity().finish();
                                            }
                                        }).create().show();
                    }
                }
            });

            m_mapFragment.addOnMapRenderListener(new OnMapRenderListener() {
                @Override
                public void onPreDraw() {
                    if (m_positionIndicatorFixed != null) {
                        if (NavigationManager.getInstance()
                                .getMapUpdateMode().equals(NavigationManager
                                        .MapUpdateMode.ROADVIEW)) {
                            if (!m_returningToRoadViewMode) {
                                // when road view is active, we set the position indicator to align
                                // with the current map transform center to synchronize map and map
                                // marker movements.
                                m_positionIndicatorFixed
                                        .setCoordinate(m_map.pixelToGeo(m_mapTransformCenter));
                            }
                        }
                    }
                }

                @Override
                public void onPostDraw(boolean var1, long var2) {
                }

                @Override
                public void onSizeChanged(int var1, int var2) {
                }

                @Override
                public void onGraphicsDetached() {
                }

                @Override
                public void onRenderBufferCreated() {
                }
            });
        }

        getActivity().findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndStartNavigation();
            }
        });
    }

    private void calculateAndStartNavigation() {
        if (m_map == null) {
            Toast.makeText(getActivity(), "Map is not ready yet", Toast.LENGTH_SHORT).show();
            return;
        }
        if (NavigationManager.getInstance().getRunningState()
                == NavigationManager.NavigationState.RUNNING) {
            Toast.makeText(getActivity(), "Navigation is currently running", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        final RoutePlan routePlan = new RoutePlan();
        // these two waypoints cover suburban roads
        routePlan.addWaypoint(new RouteWaypoint(new GeoCoordinate(32.5270,-92.0740)));
        routePlan.addWaypoint(new RouteWaypoint(new GeoCoordinate(destinationLat, destinationLon)));

        // calculate a route for navigation
        CoreRouter coreRouter = new CoreRouter();
        coreRouter.calculateRoute(routePlan, new CoreRouter.Listener() {
            @Override
            public void onCalculateRouteFinished(List<RouteResult> list,
                                                 RoutingError routingError) {
                if (routingError == RoutingError.NONE) {
                    Route route = list.get(0).getRoute();

                    m_currentRoute = new MapRoute(route);
                    m_map.addMapObject(m_currentRoute);

                    // move the map to the first waypoint which is starting point of
                    // the route
                    m_map.setCenter(routePlan.getWaypoint(0).getNavigablePosition(),
                            Map.Animation.NONE);

                    // setting MapUpdateMode to RoadView will enable automatic map
                    // movements and zoom level adjustments
                    NavigationManager navigationManager =
                            NavigationManager.getInstance();
                    navigationManager.setMapUpdateMode(
                            NavigationManager.MapUpdateMode.ROADVIEW);

                    // adjust tilt to show 3D view
                    m_map.setTilt(80);

                    // adjust transform center for navigation experience in portrait
                    // view
                    m_mapTransformCenter = new PointF(m_map.getTransformCenter().x, (m_map
                            .getTransformCenter().y * 85 / 50));
                    m_map.setTransformCenter(m_mapTransformCenter);

                    // create a map marker to show current position
                    Image icon = new Image();
                    m_positionIndicatorFixed = new MapMarker();
                    try {
                        icon.setImageResource(R.drawable.gps_position);
                        m_positionIndicatorFixed.setIcon(icon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    m_positionIndicatorFixed.setVisible(true);
                    m_positionIndicatorFixed.setCoordinate(m_map.getCenter());
                    m_map.addMapObject(m_positionIndicatorFixed);

                    m_mapFragment.getPositionIndicator().setVisible(false);

                    navigationManager.setMap(m_map);

                    // listen to real position updates. This is used when RoadView is
                    // not active.
                    PositioningManager.getInstance().addListener(
                            new WeakReference<>(mapPositionHandler));

                    // listen to updates from RoadView which tells you where the map
                    // center should be situated. This is used when RoadView is active.
                    navigationManager.getRoadView().addListener(
                            new WeakReference<>(roadViewListener));

                    // listen to navigation manager events.
                    navigationManager.addNavigationManagerEventListener(
                            new WeakReference<>(
                                    navigationManagerEventListener));

                    navigationManager.addLaneInformationListener(
                            new WeakReference<>(m_laneInformationListener));

                    // start navigation simulation travelling at 13 meters per second
                    navigationManager.simulate(route, 13);

                } else {
                    Toast.makeText(getActivity(),
                            "Error:route calculation returned error code: " + routingError,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onProgress(int i) {

            }
        });
    }

    // listen for positioning events
    private PositioningManager.OnPositionChangedListener mapPositionHandler = new PositioningManager.OnPositionChangedListener() {
        @Override
        public void onPositionUpdated(PositioningManager.LocationMethod method, GeoPosition position,
                                      boolean isMapMatched) {
            if (NavigationManager.getInstance().getMapUpdateMode().equals(NavigationManager
                    .MapUpdateMode.NONE) && !m_returningToRoadViewMode)
                // use this updated position when map is not updated by RoadView.
                m_positionIndicatorFixed.setCoordinate(position.getCoordinate());
        }

        @Override
        public void onPositionFixChanged(PositioningManager.LocationMethod method,
                                         PositioningManager.LocationStatus status) {

        }
    };

    private void pauseRoadView() {
        // pause RoadView so that map will stop moving, the map marker will use updates from
        // PositionManager callback to update its position.

        if (NavigationManager.getInstance().getMapUpdateMode().equals(NavigationManager.MapUpdateMode.ROADVIEW)) {
            NavigationManager.getInstance().setMapUpdateMode(NavigationManager.MapUpdateMode.NONE);
            NavigationManager.getInstance().getRoadView().removeListener(roadViewListener);
            m_lastZoomLevelInRoadViewMode = m_map.getZoomLevel();
        }
    }

    private void resumeRoadView() {
        // move map back to it's current position.
        m_map.setCenter(PositioningManager.getInstance().getPosition().getCoordinate(), Map
                        .Animation.BOW, m_lastZoomLevelInRoadViewMode, Map.MOVE_PRESERVE_ORIENTATION,
                80);
        // do not start RoadView and its listener until the map movement is complete.
        m_returningToRoadViewMode = true;
    }

    // application design suggestion: pause roadview when user gesture is detected.
    private MapGesture.OnGestureListener gestureListener = new MapGesture.OnGestureListener() {
        @Override
        public void onPanStart() {
            pauseRoadView();
        }

        @Override
        public void onPanEnd() {
        }

        @Override
        public void onMultiFingerManipulationStart() {
        }

        @Override
        public void onMultiFingerManipulationEnd() {
        }

        @Override
        public boolean onMapObjectsSelected(List<ViewObject> objects) {
            return false;
        }

        @Override
        public boolean onTapEvent(PointF p) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(PointF p) {
            return false;
        }

        @Override
        public void onPinchLocked() {
        }

        @Override
        public boolean onPinchZoomEvent(float scaleFactor, PointF p) {
            pauseRoadView();
            return false;
        }

        @Override
        public void onRotateLocked() {
        }

        @Override
        public boolean onRotateEvent(float rotateAngle) {
            return false;
        }

        @Override
        public boolean onTiltEvent(float angle) {
            pauseRoadView();
            return false;
        }

        @Override
        public boolean onLongPressEvent(PointF p) {
            return false;
        }

        @Override
        public void onLongPressRelease() {
        }

        @Override
        public boolean onTwoFingerTapEvent(PointF p) {
            return false;
        }
    };

    final private NavigationManager.RoadView.Listener roadViewListener = new NavigationManager.RoadView.Listener() {
        @Override
        public void onPositionChanged(GeoCoordinate geoCoordinate) {
            // an active RoadView provides coordinates that is the map transform center of it's
            // movements.
            m_mapTransformCenter = m_map.projectToPixel
                    (geoCoordinate).getResult();
        }
    };

    final private Map.OnTransformListener onTransformListener = new Map.OnTransformListener() {
        @Override
        public void onMapTransformStart() {
        }

        @Override
        public void onMapTransformEnd(MapState mapsState) {
            // do not start RoadView and its listener until moving map to current position has
            // completed
            if (m_returningToRoadViewMode) {
                NavigationManager.getInstance().setMapUpdateMode(NavigationManager.MapUpdateMode
                        .ROADVIEW);
                NavigationManager.getInstance().getRoadView()
                        .addListener(new WeakReference<>(roadViewListener));
                m_returningToRoadViewMode = false;
            }
        }
    };

    final private NavigationManager.NavigationManagerEventListener navigationManagerEventListener =
            new NavigationManager.NavigationManagerEventListener() {
                @Override
                public void onRouteUpdated(Route route) {
                    m_map.removeMapObject(m_currentRoute);
                    m_currentRoute = new MapRoute(route);
                    m_map.addMapObject(m_currentRoute);
                }
            };

    final private NavigationManager.LaneInformationListener
            m_laneInformationListener = new NavigationManager.LaneInformationListener() {
        @Override
        public void onLaneInformation(@NonNull List<LaneInformation> items,
                                      @Nullable RoadElement roadElement) {
            LaneInfoUtils.displayLaneInformation(m_laneInfoView, items);
        }
    };

    public void onDestroy() {
        super.onDestroy();
        if (m_map != null) {
            m_map.removeMapObject(m_positionIndicatorFixed);
        }
        if (MapEngine.isInitialized()) {
            NavigationManager.getInstance().stop();
            PositioningManager.getInstance().stop();
        }

        NavigationManager.getInstance().removeLaneInformationListener(m_laneInformationListener);
        NavigationManager.getInstance()
                .removeNavigationManagerEventListener(navigationManagerEventListener);
    }

    void onBackPressed() {
        if (NavigationManager.getInstance().getMapUpdateMode().equals(NavigationManager
                .MapUpdateMode.NONE)) {
            resumeRoadView();
        } else {
            getActivity().finish();
        }
    }

    /**
     * Only when the app's target SDK is 23 or higher, it requests each dangerous permissions it
     * needs when the app is running.
     */
    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                for (int index = 0; index < permissions.length; index++) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {

                        /*
                         * If the user turned down the permission request in the past and chose the
                         * Don't ask again option in the permission request system dialog.
                         */
                        if (!ActivityCompat
                                .shouldShowRequestPermissionRationale(getActivity(), permissions[index])) {
                            Toast.makeText(getActivity(), "Required permission " + permissions[index]
                                            + " not granted. "
                                            + "Please go to settings and turn on for sample app",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Required permission " + permissions[index]
                                    + " not granted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
