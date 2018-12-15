package ca.bcit.new_westminster_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class CheckList extends AppCompatActivity {

    public static CheckBox skyTrainBox;
    public static CheckBox busStopsBox;
    public static CheckBox careHomesBox;
    public static CheckBox playgroundsBox;
    public static CheckBox schoolsBox;
    public static CheckBox hospitalsBox;
    public static int radius;
    public static EditText radiusBox;
    public static Button searchButton;

    @NonNull
    private final static String TAG = CheckList.class.getName();

    @NonNull
    private TextToSpeech texttoSpeech;

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
        radiusBox = findViewById(R.id.rentalRadius);
        searchButton = findViewById(R.id.search);
        texttoSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) texttoSpeech.setLanguage(Locale.CANADA);
            }
        });


        radiusBox.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                String speak = "default radius is 200 meters";
                Toast.makeText(getApplicationContext(), speak, Toast.LENGTH_SHORT).show();
                texttoSpeech.speak(speak, texttoSpeech.QUEUE_FLUSH, null);
                return false;
            }
        });

        ((EditText) findViewById(R.id.rentalRadius)).setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        //done typing
                        String speak = "selected radius to " + radiusBox.getText().toString() + " meters";
                        Toast.makeText(getApplicationContext(), speak, Toast.LENGTH_SHORT).show();
                        texttoSpeech.speak(speak, texttoSpeech.QUEUE_FLUSH, null);


                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            return true;
                        }
                    }
                }
                return false; // pass on to other listeners.
            }
        });
    }

    public void find_results(final @NonNull View view) {
        String radiusText = radiusBox.getText().toString();
        if (TextUtils.isEmpty(radiusText)) {
            radius = 200;
        } else {
            radius = Integer.parseInt(radiusBox.getText().toString());
        }


        if (!skyTrainBox.isChecked() && !busStopsBox.isChecked() && !careHomesBox.isChecked() && !playgroundsBox.isChecked() && !schoolsBox.isChecked() && !hospitalsBox.isChecked()) {
            String speak = "Please select one of the options to search";
            Toast.makeText(getApplicationContext(), speak, Toast.LENGTH_SHORT).show();
            texttoSpeech.speak(speak, texttoSpeech.QUEUE_FLUSH, null);
        } else {
            Log.i(TAG, "go button Pressed");
            final Intent intent;

            intent = new Intent(this, Results.class);
            intent.putExtra("radiusExtraString", radius);
            startActivity(intent);
        }
    }

    public void checkCheckBox(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.skytrain:
                switchStatementHelper(checked, "Skytrain");
                break;
            case R.id.busStop:
                switchStatementHelper(checked, "Bus Stop");
                break;
            case R.id.careHomes:
                switchStatementHelper(checked, "Care Home");
                break;
            case R.id.playground:
                switchStatementHelper(checked, "Playground");
                break;
            case R.id.school:
                switchStatementHelper(checked, "School");
                break;
            case R.id.hospital:
                switchStatementHelper(checked, "Hospital");
                break;
            default:
                break;
        }
    }

    public void onPause() {
        if (texttoSpeech != null) {
            texttoSpeech.stop();
            texttoSpeech.shutdown();
        }
        super.onPause();
    }

    public void switchStatementHelper(boolean checker, String options) {
        if (checker) {
            String speak = options + " is checked";
            Toast.makeText(getApplicationContext(), speak, Toast.LENGTH_SHORT).show();
            texttoSpeech.speak(speak, texttoSpeech.QUEUE_FLUSH, null);
        } else {
            String speak = options + " is unchecked";
            Toast.makeText(getApplicationContext(), speak, Toast.LENGTH_SHORT).show();
            texttoSpeech.speak(speak, texttoSpeech.QUEUE_FLUSH, null);
        }
    }

    public int getRadius() {
        return Integer.parseInt(radiusBox.getText().toString());
    }


}
