package ca.bcit.new_westminster_project;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;

public class MapSearch extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    List<MarkerOptions> markers = new ArrayList<MarkerOptions>();
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //Color: HUE_AZURE,
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
        addingAllMakers();
        addingAllCircle();
    }

    private void addPoint(final double latitude, final double longitude, final String title, Float color) {
        LatLng location = new LatLng(latitude, longitude);
        MarkerOptions marker = new MarkerOptions().position(location).title(title).icon(BitmapDescriptorFactory.defaultMarker(color));
        markers.add(marker);
        float zoomLevel = 12.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }

    private void addPointCircle(final double latitude, final double longitude, final String title, float color) {
        LatLng location = new LatLng(latitude, longitude);
        CircleOptions circle = new CircleOptions().center(location).radius(CheckList.radius).strokeColor(Color.RED);
        MarkerOptions marker = new MarkerOptions().position(location).title(title).icon(BitmapDescriptorFactory.defaultMarker(color));
        float[] distance = new float[2];
        for (int i = 0; i < markers.size(); i++) {
            Location.distanceBetween(markers.get(i).getPosition().latitude, markers.get(i).getPosition().longitude, latitude, longitude, distance);
            if (distance[0] <= circle.getRadius()) {
                mMap.addCircle(circle);
                mMap.addMarker(marker);
                mMap.addMarker(markers.get(i));
            }
        }
        double scale = circle.getRadius() / 500;
        float zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
        zoomLevel -= .4f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }

    public void addingAllMakers() {
        ArrayList<Double> markersLatitude = (ArrayList<Double>) getIntent().getSerializableExtra("markersLatitude");
        ArrayList<Double> markersLongitude = (ArrayList<Double>) getIntent().getSerializableExtra("markersLongitude");
        ArrayList<String> markersName = (ArrayList<String>) getIntent().getSerializableExtra("markersName");
        ArrayList<Float> markersColour = (ArrayList<Float>) getIntent().getSerializableExtra("markersColour");
        printColours();
        for (int i = 0; i < markersLatitude.size(); ++i) {
            addPoint(markersLatitude.get(i), markersLongitude.get(i), markersName.get(i), markersColour.get(i));
        }
    }

    public void addingAllCircle() {
        ArrayList<Double> FinalMarkersLatitude = (ArrayList<Double>) getIntent().getSerializableExtra("FinalMarkersLatitude");
        ArrayList<Double> FinalMarkersLongitude = (ArrayList<Double>) getIntent().getSerializableExtra("FinalMarkersLongitude");
        ArrayList<String> FinalMarkersName = (ArrayList<String>) getIntent().getSerializableExtra("FinalMarkersName");

        int listViewClickedIndex = getIntent().getIntExtra("listViewClickedIndex", 0);

        addPointCircle(FinalMarkersLatitude.get(listViewClickedIndex), FinalMarkersLongitude.get(listViewClickedIndex), FinalMarkersName.get(listViewClickedIndex), HUE_RED);

    }

    public void printColours() {
        ArrayList<Float> markersColour = (ArrayList<Float>) getIntent().getSerializableExtra("markersColour");
        for (int i = 0; i < markersColour.size(); ++i) {
            System.out.println(markersColour.get(i));
        }
    }
}