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

import ca.bcit.new_westminster_project.data.Skytrain;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_MAGENTA;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_YELLOW;

public class MapSearch extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        downloadData("http://opendata.newwestcity.ca/downloads/skytrain-stations-points/SKYTRAIN_STATIONS_PTS.json");
        downloadData("http://opendata.newwestcity.ca/downloads/bus-stops/BUS_STOPS.json");

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

    private void addPoint(final double latitude, final double longitude, final String title, final float hue) {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title).icon(BitmapDescriptorFactory.defaultMarker(hue)));
        float zoomLevel = 12.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }

    private void downloadData(@NonNull final String url) {
        Ion.with(this).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(final Exception ex, final JsonObject json) {
                if (ex != null) {

                }

                if (json != null) {
                    parseJSON(json);
                }
            }
        });
    }

    private void parseJSON(final JsonObject json) {
        final Gson gson;
        final Skytrain skytrain;

        gson = new Gson();
        skytrain = gson.fromJson(json, Skytrain.class);

        for (Skytrain.Feature feature : skytrain.getFeatures()) {
            final Skytrain.Feature.Geometry geo;
            final Skytrain.Feature.Properties properties;
            final String name;
            float colour;

            geo = feature.getGeometry();
            properties = feature.getProperties();
            double[] a = geo.getCoordinates();
            String name_ = "";
            colour = HUE_RED;

            if (properties.getName() != "") {
                name_ = properties.getName();
                colour = (HUE_MAGENTA);
            } else if (properties.getStopName() != "") {
                name_ = properties.getStopName();
                colour = (HUE_YELLOW);
            }

            double X = a[1];

            addPoint(a[1], a[0], name_, colour);
        }


    }


}
