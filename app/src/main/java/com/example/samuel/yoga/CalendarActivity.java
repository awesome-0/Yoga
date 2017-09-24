package com.example.samuel.yoga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> calendarDays = new HashSet<>();
    YogaDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        db = new YogaDb(this);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendar);

        List<String> workoutDays = db.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();

        for(String days : workoutDays){

            convertedList.add(CalendarDay.from(new Date(Long.parseLong(days))));
            materialCalendarView.addDecorator(new WorkOutDoneDecorator(convertedList));

        }
    }
}
