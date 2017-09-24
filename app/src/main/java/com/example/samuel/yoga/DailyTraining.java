package com.example.samuel.yoga;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyTraining extends AppCompatActivity {
    Button btn_start;
    ImageView ex_image;
    ProgressBar progressBar;
    LinearLayout layout;
    TextView txt_getReady,txtcountdown,txtTimer,ex_name;
    int exercise_id ;
    static int limit_time ;
    CountDownTimer exerciseCountDown;
    private List<Exercise> exerciseList = new ArrayList<>();
    private SharedPreferences prefs;
    CountDownTimer restCountDown;
    YogaDb db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);
        init();
        btn_start = (Button) findViewById(R.id.btn_start);
        ex_image = (ImageView) findViewById(R.id.image);
        layout = (LinearLayout) findViewById(R.id.layout_get_ready);
        txt_getReady = (TextView) findViewById(R.id.text_get_ready);
        txtTimer = (TextView) findViewById(R.id.timer);
        txtcountdown = (TextView) findViewById(R.id.text_countdown);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        db= new YogaDb(this);

        exercise_id = 0;

        ex_name = (TextView) findViewById(R.id.exercise_details);
        progressBar.setMax(exerciseList.size());

        prefs = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        int storedTime = prefs.getInt("key", 0);
        print.l(storedTime + "");
        switch (storedTime) {
            case 0:
                limit_time = TimeMode.LENGHT_EASY;
                break;
            case 1:
                limit_time = TimeMode.LENGHT_MEDIUM;
                break;
            case 2:
                limit_time = TimeMode.LENGHT_HARD;
                break;
        }
        print.l("limit time " + limit_time);
        btn_start.setText("Start");



        setExerciseInfo(exercise_id);
        exerciseCountDown = new CountDownTimer(limit_time,1000) {
            @Override
            public void onTick(long l) {
                // txtTimer.setVisibility(View.VISIBLE);
                txtTimer.setText(""+ (l/1000));
                print.l(""+ (l/1000));
            }
            @Override
            public void onFinish() {
                if(exercise_id < exerciseList.size() -1){
                    exercise_id++;
                    progressBar.setProgress(exercise_id);
                    txtTimer.setText("");
                    setExerciseInfo(exercise_id);
                    btn_start.setText("START");

                }
                else{

                    showFinished();
                }
            }
        };

        restCountDown = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                txtcountdown.setText(""+ (l/1000));

            }

            @Override
            public void onFinish() {
                setExerciseInfo(exercise_id);
                showExercises();
                btn_start.setText("START");




            }
        };

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_start.getText().toString().toLowerCase().equals("start")) {

                    showGetReady();
                    btn_start.setText("DONE");
                }
                else if (btn_start.getText().toString().toLowerCase().equals("done")) {
                    exerciseCountDown.cancel();
                   // restCountDown.cancel();

                    if(exercise_id < exerciseList.size()){
                        showRest();
                        exercise_id++;
                        progressBar.setProgress(exercise_id);
                        btn_start.setText("SKIP");
                        txtTimer.setText("");
                    }
                }
                else {
                    exerciseCountDown.cancel();
                    restCountDown.cancel();

                    if(exercise_id < exerciseList.size()){

                       // progressBar.setProgress(exercise_id);
                        btn_start.setText("START");
                        setExerciseInfo(exercise_id);
                    }
                    else {
                        showFinished();
                    }
                }

            }
        });


    }

    private void showRest() {
        ex_image.setVisibility(View.INVISIBLE);
        btn_start.setVisibility(View.VISIBLE);
      //  txtTimer.setVisibility(View.VISIBLE);
        layout.setVisibility(View.VISIBLE);
        btn_start.setText("SKIP");
        restCountDown.start();
        txt_getReady.setText("REST TIME");

    }

    private void init() {
        exerciseList.add(new Exercise(R.drawable.boat_pose,"boat pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose,"bow pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose,"cobra pose"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge,"crescent lunge"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog,"downward facing dog"));
        exerciseList.add(new Exercise(R.drawable.easy_pose,"easy pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon,"half pigeon"));
        exerciseList.add(new Exercise(R.drawable.upward_bow,"upward bow"));
    }

    private void showGetReady() {
        ex_image.setVisibility(View.INVISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);
        txt_getReady.setText("GET READY");

        new CountDownTimer(6000,1000){


            @Override
            public void onTick(long l) {
                txtcountdown.setText("" + ((l-1000)/1000));
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();

    }



    private void showExercises() {

        if(exercise_id < exerciseList.size()){
            print.l("exercise ID = " + exercise_id + " exercise list size " + exerciseList.size()  );
            ex_image.setVisibility(View.VISIBLE);
            btn_start.setVisibility(View.VISIBLE);
            txtTimer.setVisibility(View.VISIBLE);
            ex_image.setImageResource(exerciseList.get(exercise_id).getImageId());
            ex_name.setText(exerciseList.get(exercise_id).getName() + "  " + exercise_id);
            layout.setVisibility(View.INVISIBLE);
            exerciseCountDown.start();

        }
        else{

            showFinished();
        }

    }


    private void showFinished() {
        ex_image.setVisibility(View.INVISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);
        txt_getReady.setText("FINISHED");
        txtcountdown.setText("CONGRATULATIONS \nYOU ARE DONE WITH TODAY'S EXERCISE");
        txtcountdown.setTextSize(20);
        db.saveDays(""+ Calendar.getInstance().getTimeInMillis());

    }

    private void setExerciseInfo(int id) {
        ex_image.setImageResource(exerciseList.get(id).getImageId());
        ex_name.setText(exerciseList.get(id).getName() + "  " + id);

      //  btn_start.setText("START");
        ex_image.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);

    }
}
