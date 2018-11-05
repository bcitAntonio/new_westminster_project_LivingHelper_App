package ca.bcit.new_westminster_project;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.maps.android.clustering.ClusterManager;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Random;

import ca.bcit.new_westminster_project.data.JsonFile;
import ca.bcit.new_westminster_project.data.JsonfileTwo;

public class MapSearch extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<Cluster> mClusterManager;
    private ClusterManager<Cluster>[] managers = new ClusterManager[7];
    private int clusterCounter = 0;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (CheckList.busStopsChecked) {
            downloadData("http://opendata.newwestcity.ca/downloads/bus-stops/BUS_STOPS.json", "bus");
        }
        if (CheckList.skyTrainChecked) {
            downloadData("http://opendata.newwestcity.ca/downloads/skytrain-stations-points/SKYTRAIN_STATIONS_PTS.json", "skytrain");
        }
        if (CheckList.careHomesChecked) {
            downloadData("http://opendata.newwestcity.ca/downloads/care-homes/CARE_HOMES.json", "careHomes");
        }
        if (CheckList.playgroundsChecked) {
            downloadData("http://opendata.newwestcity.ca/downloads/playgrounds/PLAYGROUNDS.json", "playgrounds");
        }
        if (CheckList.schoolsChecked) {
            downloadData("http://opendata.newwestcity.ca/downloads/significant-buildings-schools/SIGNIFICANT_BLDG_SCHOOLS.json", "schools");
        }
        if (CheckList.hospitalsChecked) {
            downloadData("http://opendata.newwestcity.ca/downloads/significant-buildings-hospitals/SIGNIFICANT_BLDG_HOSPITALS.json", "hospitals");
        }
       // downloadData("https://drive.google.com/uc?export=download&id=17Rk22SYqjeYQB_m7o0K5Pbj6vxDLT3xW", "housing");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void addPoint(final double latitude, final double longitude, final String title, float color) {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title).icon(BitmapDescriptorFactory.defaultMarker(color)));

        float zoomLevel = 12.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }

    private void downloadData(@NonNull final String url, final String type) {

        Ion.with(this).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(final Exception ex, final JsonObject json) {
                if (ex != null) {

                }

                if (json != null && type == "housing") {
                    //ONLY FOR RENTAL
                    parseJSONRental(json);
                } else if (json != null && type != "housing") {
                    parseJSON(json);
                }
            }
        });
    }

    private void parseJSON(final JsonObject json) {
        final Gson gson;
        final JsonFile jsonFile;
        mClusterManager = new ClusterManager<>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        float color = 0 + r.nextFloat() * (300 - 0);


        gson = new Gson();
        jsonFile = gson.fromJson(json, JsonFile.class);

        for (JsonFile.Feature feature : jsonFile.getFeatures()) {
            final JsonFile.Feature.Properties properties;
            properties = feature.getProperties();
            String name_ = find_correct_listname(properties);

            //addCluster(Double.parseDouble(properties.getY()), Double.parseDouble(properties.getX()),name_);
            addPoint(Double.parseDouble(properties.getY()), Double.parseDouble(properties.getX()), name_, color);
        }
    }

    private void parseJSONRental(final JsonObject json) {
        final Gson gson;
        final JsonfileTwo jsonFile;
        mClusterManager = new ClusterManager<>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        float color = 359;


        gson = new Gson();
        jsonFile = gson.fromJson(json, JsonfileTwo.class);

        for (JsonfileTwo.Feature feature : jsonFile.getFeatures()) {
            final JsonfileTwo.Feature.Geometry geo;
            geo = feature.getGeometry();
            double[] a = geo.getFirstCoordinates();
            String name_ = "hello";

            //addCluster(a[1], a[0],name_);
            addPoint(a[1], a[0], name_, color);
        }
    }

    public void add_list_name(ArrayList<String> list_name, JsonFile.Feature.Properties p) {
        list_name.add(p.getStopName());
        list_name.add(p.getName());
        list_name.add(p.getBuildingName());
        list_name.add(p.getParkName());
    }

    public String find_correct_listname(JsonFile.Feature.Properties p) {
        ArrayList<String> list_name = new ArrayList<String>();
        add_list_name(list_name, p);

        for (String s : list_name) {
            if (s != null) return s;
        }
        return null;
    }


    private void addCluster(double lat, double lng, String name) {
        LatLng location = new LatLng(lat, lng);
        float zoomLevel = 12.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 2; i++) {
            double offset = i / 10d;
            lat = lat + offset;
            lng = lng + offset;
            Cluster offsetItem = new Cluster(lat, lng, name);
            mClusterManager.addItem(offsetItem);
        }
    }

}

