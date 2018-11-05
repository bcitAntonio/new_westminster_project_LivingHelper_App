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
    CheckBox careHomesBox;
    CheckBox playgroundsBox;
    CheckBox schoolsBox;
    CheckBox hospitalsBox;

    public static boolean skyTrainChecked;
    public static boolean busStopsChecked;
    public static boolean careHomesChecked;
    public static boolean playgroundsChecked;
    public static boolean schoolsChecked;
    public static boolean hospitalsChecked;
    @NonNull
    private final static String TAG = CheckList.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        skyTrainBox = findViewById(R.id.skytrain);
        busStopsBox = findViewById(R.id.busStop);
        careHomesBox = findViewById(R.id.careHomes);
        playgroundsBox = findViewById(R.id.playground);
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
        if (careHomesBox.isChecked()) {
            careHomesChecked = true;
        }
        if (playgroundsBox.isChecked()) {
            playgroundsChecked = true;
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
