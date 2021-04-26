package csci4060.project.aimsmobileapp.UI.Fragments.navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.guidance.AudioPlayerDelegate;


import com.here.android.mpa.guidance.VoiceCatalog;
import com.here.android.mpa.guidance.VoiceGuidanceOptions;
import com.here.android.mpa.guidance.VoicePackage;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapRoute;

import java.io.File;;
import java.io.IOException;
import java.lang.ref.WeakReference;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;

import com.here.android.mpa.common.MapEngine;

import com.here.android.mpa.common.RoadElement;

import com.here.android.mpa.guidance.LaneInformation;
import com.here.android.mpa.guidance.NavigationManager;


import com.here.android.mpa.mapping.PositionIndicator;
import com.here.android.mpa.routing.CoreRouter;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteTta;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.Router;
import com.here.android.mpa.routing.RoutingError;


import java.util.List;
import java.util.Objects;

import csci4060.project.aimsmobileapp.R;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;


//This is route screen
public class RouteFragment extends Fragment {

    TextView instructions;
    double destinationLat;
    double destinationLon;
    private LocationManager locationManager;
    private LocationListener locationListner;
    double latitude;
    double longitude;

    private MapRoute m_mapRoute;


    public void setDestination(double lat, double lon) {
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
    private Button m_naviControlButton;
    private MapMarker m_positionIndicatorFixed = null;

    private boolean m_returningToRoadViewMode = false;
    private double m_lastZoomLevelInRoadViewMode = 0.0;
    private MapRoute m_currentRoute;
    private LinearLayout m_laneInfoView;
    private NavigationManager m_navigationManager;
    private GeoBoundingBox m_geoBoundingBox;
    private Route m_route;
    private boolean m_foregroundServiceStarted;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        @SuppressLint("MissingPermission")
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
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
            initNaviControlButton();



        } else {
            ActivityCompat
                    .requestPermissions(getActivity(), RUNTIME_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
            initMapFragment();
            initNaviControlButton();


        }

    }

    private AndroidXMapFragment getMapFragment() {
        return (AndroidXMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);
    }

    private void initMapFragment() {




        /* Locate the mapFragment UI element */
        m_mapFragment = getMapFragment();

        m_laneInfoView = getActivity().findViewById(R.id.laneInfoLayout);
        // This will use external storage to save map cache data, it is also possible to set
        // private app's path
        String path = new File(getActivity().getExternalFilesDir(null), ".here-map-data")
                .getAbsolutePath();
        // This method will throw IllegalArgumentException if provided path is not writable
        com.here.android.mpa.common.MapSettings.setDiskCacheRootPath(path);
        if (m_mapFragment != null) {


            /* Initialize the AndroidXMapFragment, results will be given via the called back. */
            m_mapFragment.init(new OnEngineInitListener() {
                @Override
                public void onEngineInitializationCompleted(Error error) {

                    if (error == OnEngineInitListener.Error.NONE) {

                        m_mapFragment.getMapGesture().addOnGestureListener(gestureListener, 100, true);
                        m_map = m_mapFragment.getMap();



                        m_map.setCenter(new GeoCoordinate(latitude ,longitude),Map.Animation.NONE);
                        m_mapFragment.getPositionIndicator().setVisible(true);

                        //Put this call in Map.onTransformListener if the animation(Linear/Bow)
                        //is used in setCenter()
                        m_map.setZoomLevel((m_map.getMaxZoomLevel() + m_map.getMinZoomLevel()) / 2);
                        /*
                         * Get the NavigationManager instance.It is responsible for providing voice
                         * and visual instructions while driving and walking
                         */
                        m_navigationManager = NavigationManager.getInstance();
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
        }
    }

    private void createRoute() {
        /* Initialize a CoreRouter */
        CoreRouter coreRouter = new CoreRouter();

        /* Initialize a RoutePlan */
        RoutePlan routePlan = new RoutePlan();

        /*
         * Initialize a RouteOption.
         */
        RouteOptions routeOptions = new RouteOptions();
        /* Other transport modes are also available e.g Pedestrian */
        routeOptions.setTransportMode(RouteOptions.TransportMode.TRUCK);
        /* Enable highway in this route. */
        routeOptions.setHighwaysAllowed(true);
        /* Calculate the fastest route available. */
        routeOptions.setRouteType(RouteOptions.Type.FASTEST);
        /* Calculate 1 route. */
        routeOptions.setRouteCount(1);
        /* Finally set the route option */
        routePlan.setRouteOptions(routeOptions);


        RouteWaypoint startPoint = new RouteWaypoint(new GeoCoordinate(latitude, longitude));

        RouteWaypoint destination = new RouteWaypoint(new GeoCoordinate(destinationLat, destinationLon));

        /* Add both waypoints to the route plan */
        routePlan.addWaypoint(startPoint);
        routePlan.addWaypoint(destination);

        /* Trigger the route calculation,results will be called back via the listener */
        coreRouter.calculateRoute(routePlan,
                new Router.Listener<List<RouteResult>, RoutingError>() {

                    @Override
                    public void onProgress(int i) {
                        /* The calculation progress can be retrieved in this callback. */
                    }

                    @Override
                    public void onCalculateRouteFinished(List<RouteResult> routeResults,
                                                         RoutingError routingError) {
                        /* Calculation is done.Let's handle the result */
                        if (routingError == RoutingError.NONE) {
                            if (routeResults.get(0).getRoute() != null) {

                                m_route = routeResults.get(0).getRoute();
                                /* Create a MapRoute so that it can be placed on the map */
                                MapRoute mapRoute = new MapRoute(routeResults.get(0).getRoute());

                                /* Show the maneuver number on top of the route */
                                mapRoute.setManeuverNumberVisible(true);

                                /* Add the MapRoute to the map */
                                m_map.addMapObject(mapRoute);

                                /*
                                 * We may also want to make sure the map view is orientated properly
                                 * so the entire route can be easily seen.
                                 */
                                m_geoBoundingBox = routeResults.get(0).getRoute().getBoundingBox();
                                m_map.zoomTo(m_geoBoundingBox, Map.Animation.NONE,
                                        Map.MOVE_PRESERVE_ORIENTATION);

                                startNavigation();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Error:route results returned is not valid",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(),
                                    "Error:route calculation returned error code: " + routingError,
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private void initNaviControlButton() {
        m_naviControlButton = (Button) getActivity().findViewById(R.id.calculate);
        m_naviControlButton.setText(R.string.start_navi);
        m_naviControlButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                /*
                 * To start a turn-by-turn navigation, a concrete route object is required.We use
                 * the same steps from Routing sample app to create a route from 4350 Still Creek Dr
                 * to Langley BC without going on HWY.
                 *
                 * The route calculation requires local map data.Unless there is pre-downloaded map
                 * data on device by utilizing MapLoader APIs,it's not recommended to trigger the
                 * route calculation immediately after the MapEngine is initialized.The
                 * INSUFFICIENT_MAP_DATA error code may be returned by CoreRouter in this case.
                 *
                 */
                if (m_route == null) {
                    createRoute();

                } else {
                    m_navigationManager.stop();
                    /*
                     * Restore the map orientation to show entire route on screen
                     */
                    m_map.zoomTo(m_geoBoundingBox, Map.Animation.NONE, 0f);
                    m_naviControlButton.setText(R.string.start_navi);
                    m_route = null;
                }
            }
        });
    }


    private void startForegroundService() {
        if (!m_foregroundServiceStarted) {
            m_foregroundServiceStarted = true;
            Intent startIntent = new Intent(getActivity(), ForegroundService.class);
            startIntent.setAction(ForegroundService.START_ACTION);
            getActivity().getApplicationContext().startService(startIntent);

        }
    }

    private void stopForegroundService() {
        if (m_foregroundServiceStarted) {
            m_foregroundServiceStarted = false;
            Intent stopIntent = new Intent(getActivity(), ForegroundService.class);
            stopIntent.setAction(ForegroundService.STOP_ACTION);
            getActivity().getApplicationContext().startService(stopIntent);
        }
    }

    private void startNavigation() {
        m_naviControlButton.setText(R.string.stop_navi);
        /* Configure Navigation manager to launch navigation on current map */
        m_navigationManager.setMap(m_map);
        // show position indicator
        // note, it is also possible to change icon for the position indicator
        m_mapFragment.getPositionIndicator().setVisible(true);

        /*
         * Start the turn-by-turn navigation.Please note if the transport mode of the passed-in
         * route is pedestrian, the NavigationManager automatically triggers the guidance which is
         * suitable for walking. Simulation and tracking modes can also be launched at this moment
         * by calling either simulate() or startTracking()
         */

        /* Choose navigation modes between real time navigation and simulation */
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Navigation");
        alertDialogBuilder.setMessage("Choose Mode");
        alertDialogBuilder.setNegativeButton("Navigation", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                m_navigationManager.startNavigation(m_route);
                initVoicePackages();
                m_map.setTilt(60);
                startForegroundService();
            }

            ;
        });
        alertDialogBuilder.setPositiveButton("Simulation", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                m_navigationManager.simulate(m_route, 60);//Simualtion speed is set to 60 m/s

                initVoicePackages();
                m_map.setTilt(60);
                startForegroundService();
            }

            ;
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        /*
         * Set the map update mode to ROADVIEW.This will enable the automatic map movement based on
         * the current location.If user gestures are expected during the navigation, it's
         * recommended to set the map update mode to NONE first.
         */
        m_navigationManager.setMapUpdateMode(NavigationManager.MapUpdateMode.ROADVIEW);

        /*
         * Sets the measuring unit system that is used by voice guidance.
         */
        m_navigationManager.setDistanceUnit(NavigationManager.UnitSystem.IMPERIAL_US);

        addNavigationListeners();
    }

    private void addNavigationListeners() {

        /*
         * Register a NavigationManagerEventListener to monitor the status change on
         * NavigationManager
         */
        m_navigationManager.addNavigationManagerEventListener(
                new WeakReference<NavigationManager.NavigationManagerEventListener>(
                        m_navigationManagerEventListener));

        // start listening for navigation events
        m_navigationManager.addNewInstructionEventListener(
                new WeakReference<NavigationManager.NewInstructionEventListener>(instructListener));

        /* Register a PositionListener to monitor the position updates */
        m_navigationManager.addPositionListener(
                new WeakReference<NavigationManager.PositionListener>(m_positionListener));

        /* Register a AudioPlayerDelegate to monitor TTS text */
        m_navigationManager.getAudioPlayer().setDelegate(m_audioPlayerDelegate);

        m_navigationManager.addLaneInformationListener(new WeakReference<NavigationManager.LaneInformationListener>(m_laneInformationListener));

        m_navigationManager.addRerouteListener(new WeakReference<NavigationManager.RerouteListener>(rerouteListener));

        m_navigationManager.setRealisticViewMode(NavigationManager.RealisticViewMode.DAY);
       m_navigationManager.addRealisticViewAspectRatio(NavigationManager.AspectRatio.AR_16x9);
        m_navigationManager.addRealisticViewListener(new WeakReference<NavigationManager.RealisticViewListener>(realisticViewListener));


    }
    ImageView img ;

    private NavigationManager.RealisticViewListener realisticViewListener = new NavigationManager.RealisticViewListener() {

        @Override
        public void onRealisticViewShow(NavigationManager.AspectRatio aspectRatio, @Nullable Image image, @Nullable Image image1) {
            img= getActivity().findViewById(R.id.imageView2);

            if (image1.getType() == Image.Type.SVG) {
                // full size is too big (will cover most of the screen), so cut the size in half
                Bitmap bmpImage = image1.getBitmap((int) (image1.getWidth() * 0.5),
                        (int) (image1.getHeight() * 0.5));
                if (bmpImage != null) {
                    //
                    // show bmpImage on-screen
                    //
                    img.setImageBitmap(bmpImage);
                    img.setVisibility(View.VISIBLE);
                }

            }

        }




    };
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
        public boolean onMapObjectsSelected(@NonNull List<ViewObject> list) {
            return false;
        }

        @Override
        public boolean onTapEvent(@NonNull PointF pointF) {
            NavigationManager.getInstance().setMapUpdateMode(NavigationManager.MapUpdateMode.ROADVIEW);
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
            // Reset the map view
            double level = m_map.getMinZoomLevel() + m_map.getMaxZoomLevel() / 2;
            m_map.setZoomLevel(level);
            return false;

        }
    };

    private void pauseRoadView() {
        // pause RoadView so that map will stop moving, the map marker will use updates from
        // PositionManager callback to update its position.

        if (NavigationManager.getInstance().getMapUpdateMode().equals(NavigationManager.MapUpdateMode.ROADVIEW)) {
            NavigationManager.getInstance().setMapUpdateMode(NavigationManager.MapUpdateMode.NONE);
            m_lastZoomLevelInRoadViewMode = m_map.getZoomLevel();
        }
    }




    final private NavigationManager.LaneInformationListener
            m_laneInformationListener = new NavigationManager.LaneInformationListener() {
        @Override
        public void onLaneInformation(@NonNull List<LaneInformation> items,
                                      @Nullable RoadElement roadElement) {
            LaneInfoUtils.displayLaneInformation(m_laneInfoView, items);
        }
    };
    private NavigationManager.PositionListener m_positionListener = new NavigationManager.PositionListener() {
        @Override
        public void onPositionUpdated(GeoPosition geoPosition) {
            // the position we get in this callback can be used
            // to reposition the map and change orientation.
            geoPosition.getCoordinate();
            geoPosition.getHeading();
            geoPosition.getSpeed();

            // also remaining time and distance can be
            // fetched from navigation manager
            m_navigationManager.getTta(Route.TrafficPenaltyMode.DISABLED, true);
            m_navigationManager.getDestinationDistance();

        }
    };


    private NavigationManager.RerouteListener rerouteListener=new NavigationManager.RerouteListener() {
        @Override
        public void onRerouteBegin() {
            Toast.makeText(getActivity(), "reroute begin", Toast.LENGTH_SHORT).show();

            super.onRerouteBegin();
        }


        @Override
        public void onRerouteEnd(@NonNull RouteResult routeResult, RoutingError routingError) {
            Toast.makeText(getActivity(), "reroute end", Toast.LENGTH_SHORT).show();
            super.onRerouteEnd(routeResult, routingError);
        }

    };
    private NavigationManager.NewInstructionEventListener instructListener
            = new NavigationManager.NewInstructionEventListener() {

        @Override
        public void onNewInstructionEvent() {
            // Interpret and present the Maneuver object as it contains
            // turn by turn navigation instructions for the user.
            m_navigationManager.getNextManeuver();
        }
    };

    private NavigationManager.NavigationManagerEventListener m_navigationManagerEventListener = new NavigationManager.NavigationManagerEventListener() {
        @Override
        public void onRunningStateChanged() {
            Toast.makeText(getActivity(), "Running state changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNavigationModeChanged() {
            Toast.makeText(getActivity(), "Navigation mode changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEnded(NavigationManager.NavigationMode navigationMode) {
            Toast.makeText(getActivity(), navigationMode + " was ended", Toast.LENGTH_SHORT).show();
            m_naviControlButton.setText(R.string.start_navi);
            stopForegroundService();


        }

        @Override
        public void onMapUpdateModeChanged(NavigationManager.MapUpdateMode mapUpdateMode) {
            Toast.makeText(getActivity(), "Map update mode is changed to " + mapUpdateMode,
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRouteUpdated(Route route) {
            // remove old MapRoute object from the map
            m_map.removeMapObject(m_mapRoute);
            // create a new MapRoute object
            m_mapRoute = new MapRoute(route);
            // display new route on the map
            m_map.addMapObject(m_mapRoute);
            Toast.makeText(getActivity(), "Route updated", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCountryInfo(String s, String s1) {
            Toast.makeText(getActivity(), "Country info updated from " + s + " to " + s1,
                    Toast.LENGTH_SHORT).show();
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
                .removeRerouteListener(rerouteListener);
    }


    /**
     * Only when the app's target SDK is 23 or higher, it requests each dangerous permissions it
     * needs when the app is running.
     */
    private static boolean hasPermissions(Context context, String... permissions) {
        if (permissions != null) {
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
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            for (int index = 0; index < permissions.length; index++) {
                if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {


                    /*
                     * If the user turned down the permission request in the past and chose the
                     * Don't ask again option in the permission request system dialog.
                     */
                    if (!ActivityCompat
                            .shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), permissions[index])) {
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
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    void onBackPressed() {
        if (NavigationManager.getInstance().getMapUpdateMode().equals(NavigationManager
                .MapUpdateMode.NONE)) {
            resumeRoadView();
        } else {
            getActivity().finish();
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

    private AudioPlayerDelegate m_audioPlayerDelegate = new AudioPlayerDelegate() {
        @Override
        public boolean playText(final String s) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    instructions = getActivity().findViewById(R.id.instructions);
                    instructions.setText(s);
                    //Toast.makeText(getActivity(), "TTS output: " + s, Toast.LENGTH_SHORT).show();

                }
            });
            return false;
        }

        @Override
        public boolean playFiles(String[] strings) {
            return false;
        }
    };


    private void initVoicePackages() {

        VoiceCatalog voiceCatalog = VoiceCatalog.getInstance();

        // Download the catalog of voices if we haven't done so already.
        if (voiceCatalog.getCatalogList().isEmpty()) {
            voiceCatalog.downloadCatalog(new VoiceCatalog.OnDownloadDoneListener() {
                @Override
                public void onDownloadDone(VoiceCatalog.Error error) {

                    if (error == VoiceCatalog.Error.NONE) {

                    } else {
                        Toast.makeText(getActivity(),
                                "Download catalog failed: " + error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        // Get the list of voice packages from the voice catalog list
        List<VoicePackage> voicePackages = VoiceCatalog.getInstance().getCatalogList();

        long id = -1;

// select
        for (VoicePackage vPackage : voicePackages) {
            if (vPackage.getMarcCode().compareToIgnoreCase("eng") == 0) {
                if (vPackage.isTts()) {
                    id = vPackage.getId();
                    break;
                }
            }
        }
        if (!voiceCatalog.isLocalVoiceSkin(id))
        {
            voiceCatalog.downloadVoice(id, new VoiceCatalog.OnDownloadDoneListener() {
                @Override
                public void onDownloadDone(VoiceCatalog.Error error) {

                    if (error == VoiceCatalog.Error.NONE) {

                    }
                }
            });
        }
        // obtain VoiceGuidanceOptions object
        VoiceGuidanceOptions voiceGuidanceOptions = m_navigationManager.getVoiceGuidanceOptions();

// set the voice skin for use by navigation manager
        voiceGuidanceOptions.setVoiceSkin(voiceCatalog.getLocalVoiceSkin(id));

    }

}













