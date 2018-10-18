package ca.bcit.new_westminster_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CheckList extends AppCompatActivity {

    @NonNull
    private final static String TAG = CheckList.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
    }

    public void search_map(final @NonNull View view) {
        Log.i(TAG, "go button Pressed");
        final Intent intent;
        intent = new Intent(this,MapSearch.class);
        startActivity(intent);
    }
}
