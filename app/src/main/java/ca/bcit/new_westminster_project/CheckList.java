package ca.bcit.new_westminster_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class CheckList extends AppCompatActivity {

    public static CheckBox skyTrainBox;
    public static CheckBox busStopsBox;
    public static CheckBox careHomesBox;
    public static CheckBox playgroundsBox;
    public static CheckBox schoolsBox;
    public static CheckBox hospitalsBox;
    public static CheckBox housingBox;
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
        housingBox = findViewById(R.id.housing);
    }

    public void find_results(final @NonNull View view) {
        Log.i(TAG, "go button Pressed");
        final Intent intent;
        intent = new Intent(this, Results.class);
        startActivity(intent);
    }
}
