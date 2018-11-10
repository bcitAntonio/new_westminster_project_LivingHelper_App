package ca.bcit.new_westminster_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private final static String TAG = MainActivity.class.getName();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }


    public void home_search(final @NonNull View view) {
        Log.i(TAG, "go button Pressed");

        final Intent intent;
        intent = new Intent(this, CheckList.class);
        startActivity(intent);
    }

}

