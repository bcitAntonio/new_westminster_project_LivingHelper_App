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
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Random;

import ca.bcit.new_westminster_project.data.JsonFile;

public class MapSearch extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        downloadData("http://opendata.newwestcity.ca/downloads/bus-stops/BUS_STOPS.json");
        downloadData("http://opendata.newwestcity.ca/downloads/skytrain-stations-points/SKYTRAIN_STATIONS_PTS.json");
        downloadData("http://opendata.newwestcity.ca/downloads/care-homes/CARE_HOMES.json");
        downloadData("http://opendata.newwestcity.ca/downloads/playgrounds/PLAYGROUNDS.json");
        downloadData("http://opendata.newwestcity.ca/downloads/significant-buildings-schools/SIGNIFICANT_BLDG_SCHOOLS.json");
        downloadData("http://opendata.newwestcity.ca/downloads/significant-buildings-hospitals/SIGNIFICANT_BLDG_HOSPITALS.json");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

    private void addPoint (final double latitude, final double longitude, final String title, float color)
    {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title).
                icon(BitmapDescriptorFactory.defaultMarker(color)));
        float zoomLevel = 12.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }

    private void downloadData(@NonNull final String url)
    {
        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>()
                {
                    @Override
                    public void onCompleted(final Exception  ex,
                                            final JsonObject json)
                    {
                        if(ex != null)
                        {

                        }

                        if(json != null)
                        {
                            parseJSON(json);
                        }
                    }
                });
    }

    private void parseJSON(final JsonObject json)
    {
        final Gson gson;
        final JsonFile jsonFile;
        float color = new Random().nextInt(360);

        gson = new Gson();
        jsonFile = gson.fromJson(json, JsonFile.class);

        for(JsonFile.Feature feature : jsonFile.getFeatures())
        {
            final JsonFile.Feature.Properties properties;
            properties = feature.getProperties();
            String name_ = find_correct_listname(properties);
            addPoint( Double.parseDouble(properties.getY()),Double.parseDouble(properties.getX()),name_, color);
        }

    }

    public void add_list_name(ArrayList<String> list_name, JsonFile.Feature.Properties p)
    {
        list_name.add(p.getStopName());
        list_name.add(p.getName());
        list_name.add(p.getBuildingName());
        list_name.add(p.getParkName());

    }

    public String find_correct_listname(JsonFile.Feature.Properties p)
    {
        ArrayList<String> list_name = new ArrayList<String>();
        add_list_name(list_name, p);

        for(String s : list_name)
        {
            if(s !=  null)

                return s;
        }

        return null;
    }
}
