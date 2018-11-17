package ca.bcit.new_westminster_project;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.new_westminster_project.data.JsonFile;
import ca.bcit.new_westminster_project.data.JsonfileTwo;
import ca.bcit.new_westminster_project.data.Updater;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_CYAN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_MAGENTA;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_ORANGE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;

public class Results extends AppCompatActivity {
    private ListView resultListView;
    private String infoTitle;
    List<MarkerOptions> markersCircles = new ArrayList<MarkerOptions>();
    List<MarkerOptions> markersFinal = new ArrayList<MarkerOptions>();
    List<MarkerOptions> markers = new ArrayList<MarkerOptions>();
    private int actualDownloaded;
    private int expectedResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultListView = findViewById(R.id.resultListView);

        final List<CustomObject> objects;
        objects = new ArrayList<>();
        int expected = 1;

        if (CheckList.skyTrainBox.isChecked()) {
            expected++;
        }

        if (CheckList.busStopsBox.isChecked()) {
            expected++;
        }

        if (CheckList.careHomesBox.isChecked()) {
            expected++;
        }

        if (CheckList.playgroundsBox.isChecked()) {
            expected++;
        }

        if (CheckList.schoolsBox.isChecked()) {
            expected++;
        }

        if (CheckList.hospitalsBox.isChecked()) {
            expected++;
        }

        expectedResult = expected;

        //RENTAL INFORMATION << MUST DOWNLOAD >>
        downloadData("https://drive.google.com/uc?export=download&id=17Rk22SYqjeYQB_m7o0K5Pbj6vxDLT3xW", "housing", HUE_RED,
                new Updater() {
                    public void update(List<CustomObject> newObjects) {
                        actualDownloaded++;
                        updateResult(objects, newObjects, expectedResult);
                    }
                });

        if (CheckList.busStopsBox.isChecked()) {
            setInfoTitle("Bus Stop: ");
            downloadData("http://opendata.newwestcity.ca/downloads/bus-stops/BUS_STOPS.json", "bus", HUE_AZURE,
                    new Updater() {
                        public void update(List<CustomObject> newObjects) {
                            actualDownloaded++;
                            updateResult(objects, newObjects, expectedResult);
                        }
                    });
        }

        if (CheckList.skyTrainBox.isChecked()) {
            setInfoTitle("SkyTrain: ");
            downloadData("http://opendata.newwestcity.ca/downloads/skytrain-stations-points/SKYTRAIN_STATIONS_PTS.json", "bus", HUE_AZURE,
                    new Updater() {
                        public void update(List<CustomObject> newObjects) {
                            actualDownloaded++;
                            updateResult(objects, newObjects, expectedResult);
                        }
                    });
        }

        if (CheckList.careHomesBox.isChecked()) {
            setInfoTitle("Care Home: ");
            downloadData("http://opendata.newwestcity.ca/downloads/care-homes/CARE_HOMES.json", "careHomes", HUE_CYAN, new Updater() {
                public void update(List<CustomObject> newObjects) {
                    actualDownloaded++;
                    updateResult(objects, newObjects, expectedResult);
                }
            });
        }

        if (CheckList.playgroundsBox.isChecked()) {
            setInfoTitle("Playground: ");
            downloadData("http://opendata.newwestcity.ca/downloads/playgrounds/PLAYGROUNDS.json", "playgrounds", HUE_GREEN, new Updater() {
                public void update(List<CustomObject> newObjects) {
                    actualDownloaded++;
                    updateResult(objects, newObjects, expectedResult);
                }
            });
        }

        if (CheckList.schoolsBox.isChecked()) {
            setInfoTitle("School: ");
            downloadData("http://opendata.newwestcity.ca/downloads/significant-buildings-schools/SIGNIFICANT_BLDG_SCHOOLS.json", "schools", HUE_MAGENTA, new Updater() {
                public void update(List<CustomObject> newObjects) {
                    actualDownloaded++;
                    updateResult(objects, newObjects, expectedResult);
                }
            });
        }

        if (CheckList.hospitalsBox.isChecked()) {
            setInfoTitle("Hospital: ");
            downloadData("http://opendata.newwestcity.ca/downloads/significant-buildings-hospitals/SIGNIFICANT_BLDG_HOSPITALS.json", "hospitals", HUE_ORANGE, new Updater() {
                public void update(List<CustomObject> newObjects) {
                    actualDownloaded++;
                    updateResult(objects, newObjects, expectedResult);
                }
            });
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                System.out.println(markersFinal.size() + " marker size");
                addMarkerToObject(objects);
                CustomAdapter customerAdapter = new CustomAdapter(getApplicationContext(), objects);
                resultListView.setAdapter(customerAdapter);

            }
        }, 5000);


        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent;
                intent = new Intent(Results.this, MapSearch.class);
                ArrayList<Double> markersLatitude = extractPositionFromMarkers(markers, "latitude");
                ArrayList<Double> markersLongitude = extractPositionFromMarkers(markers, "longitude");
                ArrayList<String> markersName = extractNameFromMarkers(markers);

                ArrayList<Double> FinalMarkersLatitude = extractPositionFromMarkers(markersFinal, "latitude");
                ArrayList<Double> FinalMarkersLongitude = extractPositionFromMarkers(markersFinal, "longitude");
                ArrayList<String> FinalMarkersName = extractNameFromMarkers(markersFinal);

                intent.putExtra("markersLatitude", markersLatitude);
                intent.putExtra("markersLongitude", markersLongitude);
                intent.putExtra("markersName", markersName);

                intent.putExtra("FinalMarkersLatitude", FinalMarkersLatitude);
                intent.putExtra("FinalMarkersLongitude", FinalMarkersLongitude);
                intent.putExtra("FinalMarkersName", FinalMarkersName);
                intent.putExtra("listViewClickedIndex", position);
                startActivity(intent);
            }
        });
    }


    private void updateResult(final List<CustomObject> objects,
                              final List<CustomObject> newObjects,
                              final int expected) {
        objects.addAll(newObjects);

        if (expected == actualDownloaded) {
            CustomAdapter customerAdapter = new CustomAdapter(getApplicationContext(), objects);
            resultListView.setAdapter(customerAdapter);
        }
    }

    private void downloadData(@NonNull final String url, final String type,
                              final float col, final Updater updater) {

        Ion.with(this).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {

            @Override
            public void onCompleted(final Exception ex, final JsonObject json) {
                if (ex == null) {
                    final List<CustomObject> objects;

                    objects = new ArrayList<>();

                    if (json != null) {
                        if (type.equals("housing")) {
                            parseJSONRental(json, col, objects);
                        } else if (!(type.equals("housing"))) {
                            parseJSON(json, col, objects);
                        }
                    }
                    updater.update(objects);
                }
            }
        });
    }


    private void parseJSON(final JsonObject json, float col, final List<CustomObject> objects) {
        final Gson gson;
        final JsonFile jsonFile;
        //int index = 0;
        gson = new Gson();
        jsonFile = gson.fromJson(json, JsonFile.class);

        for (JsonFile.Feature feature : jsonFile.getFeatures()) {
            final JsonFile.Feature.Properties properties;
            properties = feature.getProperties();
            String name_ = find_correct_listname(properties);
            LatLng location = new LatLng(Double.parseDouble(properties.getY()), Double.parseDouble(properties.getX()));
            MarkerOptions marker = new MarkerOptions().position(location).title(name_);
            markers.add(marker);
        }
        //infoTitle += name_;
    }

    private void parseJSONRental(final JsonObject json, float col,
                                 final List<CustomObject> objects) {
        final Gson gson;
        final JsonfileTwo jsonFile;

        gson = new Gson();
        jsonFile = gson.fromJson(json, JsonfileTwo.class);

        for (JsonfileTwo.Feature feature : jsonFile.getFeatures()) {
            final JsonfileTwo.Feature.Geometry geo;
            geo = feature.getGeometry();
            double[] a = geo.getFirstCoordinates();
            String name_ = feature.getProperties().getStreetNum() + " " + feature.getProperties().getStreetName() + ", " + feature.getProperties().getBuildingName();

            LatLng location = new LatLng(a[1], a[0]);
            CircleOptions circle = new CircleOptions().center(location).radius(CheckList.radius);
            MarkerOptions marker = new MarkerOptions().position(location).title(name_);
            markersCircles.add(marker);

            float[] distance = new float[2];
            for (int i = 0; i < markers.size(); i++) {
                Location.distanceBetween(markers.get(i).getPosition().latitude, markers.get(i).getPosition().longitude, a[1], a[0], distance);
                if (distance[0] <= circle.getRadius()) {
                    markersFinal.add(marker);
                }
            }
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
            if (s != null)
                return s;
        }
        return null;
    }

    public void map_search(final @NonNull View view) {
        final Intent intent;
        intent = new Intent(this, MapSearch.class);
        startActivity(intent);
    }


    public void setInfoTitle(String title) {
        infoTitle = title;
    }

    public void addMarkerToObject(final List<CustomObject> objects) {
        for (int i = 0; i < markersFinal.size(); i++) {
            String radiusString = "" + getIntent().getIntExtra("radiusExtraString", 200);
            objects.add(new CustomObject(markersFinal.get(i).getTitle(), radiusString));
        }
    }

    public ArrayList<Double> extractPositionFromMarkers(List<MarkerOptions> marker, String itemToExtract)
    {
        ArrayList<Double> arrayListDirection = new ArrayList<>();

        if(itemToExtract.equals("latitude"))
        {
            for(int i=0; i < marker.size(); ++i)
            {
                arrayListDirection.add(marker.get(i).getPosition().latitude);
            }
            return arrayListDirection;
        }

        if(itemToExtract.equals("longitude"))
        {
            for(int i=0; i < marker.size(); ++i)
            {
                arrayListDirection.add(marker.get(i).getPosition().longitude);
            }
            return arrayListDirection;
        }

        return null;
    }

    public ArrayList<String> extractNameFromMarkers(List<MarkerOptions> marker)
    {
        ArrayList<String> nameList = new ArrayList<>();
        for(int i=0; i < marker.size(); ++i)
        {
            nameList.add(marker.get(i).getTitle());
        }
        return nameList;
    }
}
