package csci4060.project.aimsmobileapp.UI.Fragments.navigation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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


import com.here.android.mpa.routing.CoreRouter;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.Router;
import com.here.android.mpa.routing.RoutingError;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


import csci4060.project.aimsmobileapp.R;

import static android.content.ContentValues.TAG;


//This is route screen
public class RouteFragment<afChangeListener> extends Fragment {

    double longitude;
    double latitude;
    TextView instructions;
    double destinationLat;
    double destinationLon;
    private LocationManager locationManager;
    private LocationListener locationListner;
    LinearLayout detail;
    TextView turn;
    TextView street;
    TextView distance;
    ImageView ex;
    TextView time;


    private MapRoute m_mapRoute;
    FloatingActionButton center_navi;
    FloatingActionButton volume;


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

    private NavigationManager m_navigationManager;
    private GeoBoundingBox m_geoBoundingBox;
    private Route m_route;
    private boolean m_foregroundServiceStarted;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_route, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        time=(TextView) getActivity().findViewById(R.id.time);
        detail= getActivity().findViewById(R.id.detail);
        detail.setVisibility(View.GONE);
        turn = getActivity().findViewById(R.id.turn);
        street=getActivity().findViewById(R.id.street);
        distance=getActivity().findViewById(R.id.distance);
        ex=  (ImageView) getActivity().findViewById(R.id.ex);
        volume = (FloatingActionButton) getActivity().findViewById(R.id.volume);

        m_mapFragment = getMapFragment();
        // Get a MapView instance from the layout.
        if (hasPermissions(getActivity(), RUNTIME_PERMISSIONS)) {
            initMapFragment();
            initNaviControlButton();
            click();
            volumesettings();


        } else {
            ActivityCompat
                    .requestPermissions(getActivity(), RUNTIME_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
            initMapFragment();
            initNaviControlButton();
            click();
            volumesettings();

        }

    }

    private AndroidXMapFragment getMapFragment() {
        return (AndroidXMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);
    }

    private void initMapFragment() {


        /* Locate the mapFragment UI element */
        m_mapFragment = getMapFragment();


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


                        m_map.setCenter(new GeoCoordinate(latitude, longitude), Map.Animation.NONE);

//                        map_marker(latitude,longitude,R.drawable.navi);
//                        Image image= new Image();
//                        try {
//                            image.setImageResource(R.drawable.navi);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        m_mapFragment.getPositionIndicator().setMarker(image).setVisible(true);
                        m_mapFragment.getPositionIndicator().setVisible(true);
                        m_map.setTilt(20);

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

    private void click() {
        center_navi = (FloatingActionButton) getActivity().findViewById(R.id.center_navi);
        center_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                m_map.setCenter(new GeoCoordinate(latitude, longitude), Map.Animation.BOW);
                m_map.setZoomLevel(15);
                NavigationManager.getInstance().setMapUpdateMode(NavigationManager.MapUpdateMode.ROADVIEW);


            }
        });
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
                                map_marker(destinationLat,destinationLon, R.drawable.pin);

                                startNavigation();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Error:route results returned is not valid",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {



                            m_navigationManager.setMap(null);
                           if(getActivity()!=null) {
                               Toast.makeText(getContext(), "Error:No trip selected", Toast.LENGTH_LONG).show();
                           }
                            return;

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
                    m_navigationManager.getAudioPlayer().stop();
                    detail.setVisibility(View.GONE);
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

                m_map.setTilt(60);
                startForegroundService();
            }

            ;
        });
        alertDialogBuilder.setPositiveButton("Simulation", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                m_navigationManager.simulate(m_route, 55);


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




        m_navigationManager.addRerouteListener(new WeakReference<NavigationManager.RerouteListener>(rerouteListener));

        initVoicePackages();


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


    private NavigationManager.PositionListener m_positionListener = new NavigationManager.PositionListener() {
        @Override
        public void onPositionUpdated(GeoPosition geoPosition) {
            // the position we get in this callback can be used
            // to reposition the map and change orientation.
            geoPosition.getCoordinate();
            geoPosition.getHeading();
            geoPosition.getSpeed();
            latitude = geoPosition.getCoordinate().getLatitude();
            longitude = geoPosition.getCoordinate().getLongitude();

            // also remaining time and distance can be
            // fetched from navigation manager

            distance.setText(meter_converter(m_navigationManager.getNextManeuverDistance()));



        }

    };
    public String time_converter(int i,int h,int m){
        int minutes= (int) i/60;
        int hours;
        int min;
        String output;
        String d;
        if(minutes<60)
        {
            if(minutes+m<60){
                hours=h;
                min=minutes+m; }
            else{
                hours=h+1;
                min=60-minutes+m;
            }
        }
        else {
            if(minutes+m<60){
            hours=(minutes%60)+h;
            min=minutes-(60*(minutes%60));
            }
            else{
                hours=(minutes%60)+h+1;
                min=60-minutes+m;

            }

        }
        if (hours>11){
            if(min<10){
                output=hours+":"+"0"+min+"PM";
            }
            else{
                output=hours+":"+min+"PM";
            }
        }
        else{
            if(min<10){
                if(hours==0){
                    output="0"+hours+":"+"0"+min+"AM";
                }
                else{
                    output=hours+":"+"0"+min+"AM";
                }

            }
            else{
                if(hours==0){
                    output="0"+hours+":"+min+"AM";
                }else{
                    output=hours+":"+min+"AM";
                }

            }
        }

        return output;
    }

    public String meter_converter(long i){
        int feet= (int) (i*3.2804);
        double miles=  (i*0.000621371192);
        String output;
        if(feet<1001){
            output=feet+" ft";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.#");
            df.setRoundingMode(RoundingMode.CEILING);

            output=df.format(miles)+ " mi";
        }
        return output;
    }
    private NavigationManager.RerouteListener rerouteListener = new NavigationManager.RerouteListener() {
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
            Maneuver maneuver = m_navigationManager.getNextManeuver();


            int hours = Calendar.getInstance().getTime().getHours();
            int minute = Calendar.getInstance().getTime().getMinutes();
            time.setText(time_converter(m_route.getTtaIncludingTraffic(0).getDuration(),hours,minute));
            if (maneuver != null) {
                detail.setVisibility(View.VISIBLE);

                street.setText(maneuver.getNextRoadName());
                //distance.setText(maneuver.getDistanceToNextManeuver()+" m");

                if(maneuver.getIcon().value() == Maneuver.Icon.END.value())
                {
                    ex.setImageResource(R.drawable.pin);
                    turn.setText("Keep Straight");


                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.KEEP_LEFT.value() || maneuver.getIcon().value() == Maneuver.Icon.QUITE_LEFT.value())
                {
                    ex.setImageResource( R.drawable.direction_continue_left);
                    if(maneuver.getIcon().value() == Maneuver.Icon.KEEP_LEFT.value()) {
                        turn.setText("Keep Left");
                    }
                    else{
                        turn.setText("Turn Left");
                    }
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.LIGHT_LEFT.value() || maneuver.getIcon().value() == Maneuver.Icon.HIGHWAY_KEEP_LEFT.value())
                {
                    ex.setImageResource(R.drawable.direction_new_name_slight_left);
                    if(maneuver.getIcon().value() == Maneuver.Icon.LIGHT_LEFT.value()) {
                        turn.setText("Light Left");
                    }
                    else{
                        turn.setText("Highway Keep Left");
                    }
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.HEAVY_LEFT.value()) {
                    ex.setImageResource(R.drawable.direction_notification_sharp_left);
                    turn.setText("Sharp Left");
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.UTURN_LEFT.value() || maneuver.getIcon().value() == Maneuver.Icon.UTURN_RIGHT.value()) {
                    ex.setImageResource(R.drawable.direction_uturn);
                    turn.setText("Take U Turn On Left");
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.ENTER_HIGHWAY_LEFT_LANE.value())
                {
                    ex.setImageResource(R.drawable.direction_merge_slight_left);
                    turn.setText("Enter Highway Left Lane");
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.LEAVE_HIGHWAY_LEFT_LANE.value())
                {
                    ex.setImageResource(R.drawable.direction_off_ramp_slight_left);
                    turn.setText("Leave Highway Left Lane");
                }


                else if(maneuver.getIcon().value() == Maneuver.Icon.KEEP_RIGHT.value() || maneuver.getIcon().value() == Maneuver.Icon.QUITE_RIGHT.value())
                {
                    ex.setImageResource(R.drawable.direction_continue_right);
                    if(maneuver.getIcon().value() == Maneuver.Icon.KEEP_RIGHT.value()) {
                        turn.setText("Keep Right");
                    }
                    else{
                        turn.setText("Turn Right");
                    }
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.LIGHT_RIGHT.value() || maneuver.getIcon().value() == Maneuver.Icon.HIGHWAY_KEEP_RIGHT.value())
                {
                    ex.setImageResource(R.drawable.direction_new_name_slight_right);
                    if(maneuver.getIcon().value() == Maneuver.Icon.LIGHT_RIGHT.value()) {
                        turn.setText("Light Right");
                    }
                    else{
                        turn.setText("Highway Keep Right");
                    }
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.HEAVY_RIGHT.value()) {
                    ex.setImageResource(R.drawable.direction_notificaiton_sharp_right);
                    turn.setText("Sharp Right");
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.ENTER_HIGHWAY_RIGHT_LANE.value())
                {
                    ex.setImageResource(R.drawable.direction_merge_slight_right);
                    turn.setText("Enter Highway Right Lane");
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.LEAVE_HIGHWAY_RIGHT_LANE.value())
                {
                    ex.setImageResource(R.drawable.direction_off_ramp_slight_right);
                    turn.setText("Leave Highway Right Lane");
                }
                else if(maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_1.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_1_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_2.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_2_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_3.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_3_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_4.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_4_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_5.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_5_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_6.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_6_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_7.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_7_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_8.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_8_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_9.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_9_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_10.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_10_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_11.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_11_LH.value()||
                        maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_12.value() || maneuver.getIcon().value() == Maneuver.Icon.ROUNDABOUT_12_LH.value()
                ) {
                    ex.setImageResource(R.drawable.direction_roundabout);
                    turn.setText("Round About");
                }
                else {
                    ex.setImageResource(R.drawable.direction_new_name_straight);
                    turn.setText("Keep Straight");
                }
            }
        }
    };

    private NavigationManager.NavigationManagerEventListener m_navigationManagerEventListener = new NavigationManager.NavigationManagerEventListener() {
        @Override
        public void onRunningStateChanged() {
           // Toast.makeText(getActivity(), "Running state changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNavigationModeChanged() {
            //Toast.makeText(getActivity(), "Navigation mode changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEnded(NavigationManager.NavigationMode navigationMode) {
            Toast.makeText(getActivity(), navigationMode + " was ended", Toast.LENGTH_SHORT).show();
            m_naviControlButton.setText(R.string.start_navi);
            m_navigationManager.stop();
            detail.setVisibility(View.GONE);
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
        m_navigationManager.getAudioPlayer().stop();
        NavigationManager.getInstance()
                .removeRerouteListener(rerouteListener);



    }


    /**
     * Only when the app's target SDK is 23 or higher, it requests each dangerous permissions it
     * needs when the app is running.
     */
    private boolean hasPermissions(Context context, String... permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
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


    private void onBackPressed() {
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


    private void initVoicePackages() {
        if(m_navigationManager.getAudioPlayer().getVolume()!=0f) {
            volume.setImageResource(R.drawable.unmute);
            volume.setTooltipText("on");
            m_navigationManager.getAudioPlayer().setVolume(1f);
        }

        m_navigationManager = NavigationManager.getInstance();

        // Retrieve the VoiceCatalog and download the latest updates
        VoiceCatalog voiceCatalog = VoiceCatalog.getInstance();

        if (!voiceCatalog.isLocalCatalogAvailable()) {
            Log.d(TAG, "Voice catalog is not available in local storage.");


            voiceCatalog.downloadCatalog(new VoiceCatalog.OnDownloadDoneListener() {
                @Override
                public void onDownloadDone(VoiceCatalog.Error error) {
                    if (error == VoiceCatalog.Error.NONE) {
                        // catalog download successful
                        Log.d(TAG, "Download voice catalog successfully.");
                        Toast.makeText(getActivity().getApplicationContext(), "Voice catalog is not available in local storage.", Toast.LENGTH_LONG).show();


                    } else {
                        Log.d(TAG, "Download voice catalog failed.");

                        Toast.makeText(getActivity().getApplicationContext(), "Voice catalog download error.", Toast.LENGTH_LONG).show();
                    }

                    // Get the list of voice packages from the voice catalog list
                    List<VoicePackage> voicePackages =
                            VoiceCatalog.getInstance().getCatalogList();
                    if (voicePackages.size() == 0) {
                        Log.d(TAG, "Voice catalog size is 0.");

                        Toast.makeText(getActivity().getApplicationContext(), "Voice catalog size is 0.", Toast.LENGTH_LONG).show();
                    }

                    long id = -1;
                    // select
                    for (VoicePackage voicePackage : voicePackages) {
                        if (voicePackage.getMarcCode().compareToIgnoreCase("eng") == 0) {
                            if (voicePackage.isTts()) // TODO: need to figure out why always return false
                            {
                                id = voicePackage.getId();
                                break;
                            }
                        }
                    }

                    if (!VoiceCatalog.getInstance().isLocalVoiceSkin(id)) {
                        final long finalId = id;

                        VoiceCatalog.getInstance().downloadVoice(id, new VoiceCatalog.OnDownloadDoneListener() {
                            @Override
                            public void onDownloadDone(VoiceCatalog.Error error) {
                                if (error == VoiceCatalog.Error.NONE) {
                                    //voice skin download successful
                                    Log.d(TAG, "Download voice skin successfully.");

                                    Toast.makeText(getActivity().getApplicationContext(), "Voice skin download successful.", Toast.LENGTH_LONG).show();

                                    // set the voice skin for use by navigation manager
                                    if (VoiceCatalog.getInstance().getLocalVoiceSkin(finalId) != null) {
                                        // obtain VoiceGuidanceOptions object
                                        VoiceGuidanceOptions voiceGuidanceOptions = m_navigationManager.getVoiceGuidanceOptions();

                                        // set the voice skin for use by navigation manager
                                        voiceGuidanceOptions.setVoiceSkin(voiceCatalog.getLocalVoiceSkin(finalId));

                                    } else {
                                        Log.d(TAG, "Get local voice skin error.");

                                        Toast.makeText(getActivity().getApplicationContext(), "Navi manager set voice skin error.", Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Log.d(TAG, "Download voice skin failed.");
                                    Toast.makeText(getActivity().getApplicationContext(), "Voice skin download error.", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    } else {
                        // set the voice skin for use by navigation manager
                        if (VoiceCatalog.getInstance().getLocalVoiceSkin(id) != null) {
                            // obtain VoiceGuidanceOptions object
                            VoiceGuidanceOptions voiceGuidanceOptions = m_navigationManager.getVoiceGuidanceOptions();

                            // set the voice skin for use by navigation manager
                            voiceGuidanceOptions.setVoiceSkin(voiceCatalog.getLocalVoiceSkin(id));

                        } else {
                            Log.d(TAG, "Get local voice skin error.");
                            Toast.makeText(getActivity().getApplicationContext(), "Navi manager set voice skin error.", Toast.LENGTH_LONG).show();
                        }


                    }

                }

            });
        } else {
            // Get the list of voice packages from the voice catalog list
            List<VoicePackage> voicePackages =
                    VoiceCatalog.getInstance().getCatalogList();
            long id = -1;
            // select
            for (VoicePackage voicePackage : voicePackages) {
                if (voicePackage.getMarcCode().compareToIgnoreCase("eng") == 0) {
                    if (voicePackage.isTts()) // TODO: need to figure out why always return false
                    {
                        id = voicePackage.getId();
                        break;
                    }
                }
            }
            // obtain VoiceGuidanceOptions object
            VoiceGuidanceOptions voiceGuidanceOptions = m_navigationManager.getVoiceGuidanceOptions();

            // set the voice skin for use by navigation manager
            voiceGuidanceOptions.setVoiceSkin(voiceCatalog.getLocalVoiceSkin(id));
        }


    }

    public void volumesettings() {

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (volume.getTooltipText().toString().equals("off")){

                    volume.setImageResource(R.drawable.unmute);
                    volume.setTooltipText("on");
                    m_navigationManager.getAudioPlayer().setVolume(1f);
                }
                else
                {
                    volume.setImageResource(R.drawable.mute);
                    volume.setTooltipText("off");
                    m_navigationManager.getAudioPlayer().stop();
                    m_navigationManager.getAudioPlayer().setVolume(0f);



                }
            }
        });


    }
    public void map_marker(double d1,double d2,int i)  {
        Image image = new Image();
        try {
            image.setImageResource(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MapMarker custom = new MapMarker(new GeoCoordinate(d1,d2,0),image);
        m_map.addMapObject(custom);
    }
}









