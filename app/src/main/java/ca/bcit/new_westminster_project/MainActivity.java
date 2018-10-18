package ca.bcit.new_westminster_project;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void home_search(final @NonNull View view) {
        Log.i(TAG, "go button Pressed");
        final Intent intent;
        intent = new Intent(this,CheckList.class);
        startActivity(intent);
    }
}


