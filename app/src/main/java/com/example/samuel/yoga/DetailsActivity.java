package com.example.samuel.yoga;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView details, timer;
    private ImageView image;
    Button start;
    boolean isRunning = false;
    SharedPreferences prefs;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        details = (TextView) findViewById(R.id.exercise_details);
        timer = (TextView) findViewById(R.id.timer);
        image = (ImageView) findViewById(R.id.image);
        start = (Button) findViewById(R.id.btn_start);
        prefs = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        int storedTime = prefs.getInt("key", 0);
        int countdownLength = 0;
        switch (storedTime) {
            case 0:
                countdownLength = TimeMode.LENGHT_EASY;
                break;
            case 1:
                countdownLength = TimeMode.LENGHT_MEDIUM;
                break;
            case 2:
                countdownLength = TimeMode.LENGHT_HARD;
                break;


        }
         countDownTimer = new CountDownTimer(countdownLength, 1000) {


            @Override
            public void onTick(long l) {
                timer.setText("" + (l / 1000));
            }

            @Override
            public void onFinish() {
                print.t(DetailsActivity.this, "Exercise Finished");
                finish();
            }
        };
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isRunning) {
                    countDownTimer.start();
                    start.setText("STOP");
                    isRunning = true;

                } else {

                    isRunning = false;
                    countDownTimer.cancel();
                    start.setText("START");
                    print.t(DetailsActivity.this, "Exercise Done");
                    finish();
                }

            }
        });

        if (getIntent() != null) {
            String name = getIntent().getStringExtra("name");
            int exercise_image = getIntent().getIntExtra("image", 0);

            details.setText(name);

            image.setImageResource(exercise_image);


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        finish();
    }
}
