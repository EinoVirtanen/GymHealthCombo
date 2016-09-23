package fi.dy.einovirtanen.gymhealthcombo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    SETUP:
     */
    long leftCounterEpochTimestampInSeconds = 1473962857-(60*60*24); // Minus one day
    long rightCounterEpochTimestampInSeconds = 1474600635;

    TextView firstCounterText;
    TextView secondCounterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize text fields:
        firstCounterText = (TextView) findViewById(R.id.firstCounterText);
        secondCounterText = (TextView) findViewById(R.id.secondCounterText);

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //firstCounterText.setText(Long.toString((System.currentTimeMillis()/1000-leftCounterEpochTimestampInSeconds)/60/60/24));
                                firstCounterText.setText("0");
                                secondCounterText.setText(Long.toString((System.currentTimeMillis()/1000-rightCounterEpochTimestampInSeconds)/60/60/24));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Hide status bar and notification bar:
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();


    }


}