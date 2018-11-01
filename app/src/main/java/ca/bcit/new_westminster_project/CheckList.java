package ca.bcit.new_westminster_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class CheckList extends AppCompatActivity {

    CheckBox skyTrainBox;
    CheckBox busStopsBox;
    CheckBox mallsBox;
    CheckBox careHomesBox;
    CheckBox parksBox;
    CheckBox playgroundsBox;
    CheckBox dogAreaBox;
    CheckBox schoolsBox;
    CheckBox hospitalsBox;

    boolean skyTrainChecked;
    boolean busStopsChecked;
    boolean mallsChecked;
    boolean careHomesChecked;
    boolean parksChecked;
    boolean playgroundsChecked;
    boolean dogAreaChecked;
    boolean schoolsChecked;
    boolean hospitalsChecked;
    @NonNull
    private final static String TAG = CheckList.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        skyTrainBox = findViewById(R.id.skytrain);
        busStopsBox = findViewById(R.id.busStop);
        mallsBox = findViewById(R.id.mall);
        careHomesBox = findViewById(R.id.careHomes);
        parksBox = findViewById(R.id.park);
        playgroundsBox = findViewById(R.id.playground);
        dogAreaBox = findViewById(R.id.dogArea);
        schoolsBox = findViewById(R.id.school);
        hospitalsBox = findViewById(R.id.hospital);
    }

    public void search_map(final @NonNull View view) {
        Log.i(TAG, "go button Pressed");
        if (skyTrainBox.isChecked()) {
            skyTrainChecked = true;
        }
        if (busStopsBox.isChecked()) {
            busStopsChecked = true;
        }
        if (mallsBox.isChecked()) {
            mallsChecked = true;
        }
        if (careHomesBox.isChecked()) {
            careHomesChecked = true;
        }
        if (parksBox.isChecked()) {
            parksChecked = true;
        }
        if (playgroundsBox.isChecked()) {
            playgroundsChecked = true;
        }
        if (dogAreaBox.isChecked()) {
            dogAreaChecked = true;
        }
        if (schoolsBox.isChecked()) {
            schoolsChecked = true;
        }
        if (hospitalsBox.isChecked()) {
            hospitalsChecked = true;
        }
        final Intent intent;
        intent = new Intent(this, MapSearch.class);
        startActivity(intent);
    }
}
