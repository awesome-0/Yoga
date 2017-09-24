package com.example.samuel.yoga;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

public class Settings extends AppCompatActivity {
    ToggleButton toggle;
    TimePicker timePicker;
    Button save;
    Spinner spinner;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if(savedInstanceState != null){
            int state = savedInstanceState.getInt("state");
            if(state == 0){
                toggle.setChecked(true);
            }
            else{
                toggle.setChecked(false);
            }

        }
        toggle = (ToggleButton) findViewById(R.id.toggle);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        save = (Button) findViewById(R.id.settings_Save);
        spinner = (Spinner) findViewById(R.id.spinner);
        setUpSpinner();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAlarm(toggle.isChecked());
                finish();

            }
        });


        prefs = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        int storedTime = prefs.getInt("key", 0);
        spinner.setSelection(storedTime);


    }

    private void saveAlarm(boolean checked) {

        if(checked) {

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(Settings.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();
            calendar.set(today.getYear(), today.getMonth(), today.getDay(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());

            manager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    else{
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(Settings.this,AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            manager.cancel(pendingIntent);

        }
    }

    private void setUpSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.modes, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(genderSpinnerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String mode_Selected = (String)adapterView.getItemAtPosition(i);

                if (!TextUtils.isEmpty(mode_Selected)) {
                    if(mode_Selected.equals("Easy")){
                        setPreference(0);
                    }
                    else if(mode_Selected.equals("Medium")){
                        setPreference(1);
                    }
                    else if(mode_Selected.equals("Hard")){
                        setPreference(2);
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        int state = 0;
        if(toggle.isChecked()){
            state = 0;
        }
        else{
            state = 1;
        }
        outState.putInt("state",state);
    }

    private void setPreference(int i) {
        prefs = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt("key",i);
        edit.commit();
    }
}
