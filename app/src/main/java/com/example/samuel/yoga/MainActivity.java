package com.example.samuel.yoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn_exercise,btn_settings,btn_calendar;
    ImageView btn_training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_exercise = (Button) findViewById(R.id.btn_exercises);
        btn_settings = (Button)findViewById(R.id.btn_settings);
        btn_training = (ImageView) findViewById(R.id.btn_training);
        btn_calendar = (Button) findViewById(R.id.btn_calendar);
        btn_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent training = new Intent(MainActivity.this,DailyTraining.class);
                startActivity(training);
            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent training = new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(training);
            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(MainActivity.this,Settings.class);
                startActivity(settings);
            }
        });
        btn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseList = new Intent(MainActivity.this,ListActivity.class);
                startActivity(exerciseList);
            }
        });
    }
}
