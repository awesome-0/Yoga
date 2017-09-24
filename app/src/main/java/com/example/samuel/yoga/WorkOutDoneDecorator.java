package com.example.samuel.yoga;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

/**
 * Created by Samuel on 08/09/2017.
 */

public class WorkOutDoneDecorator implements DayViewDecorator {

   HashSet<CalendarDay> list ;
    ColorDrawable drawable;

    public WorkOutDoneDecorator(HashSet<CalendarDay> list) {
        this.list = list;
        drawable = new ColorDrawable(Color.parseColor("#e57373"));
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setBackgroundDrawable(drawable);
    }
}
