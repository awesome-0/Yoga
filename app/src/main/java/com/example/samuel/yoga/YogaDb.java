package com.example.samuel.yoga;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 07/09/2017.
 */

public class YogaDb extends SQLiteAssetHelper {
   private static final String DB_NAME = "Yoga.db";
    private static final int DB_VER = 1;


    public YogaDb(Context context) {
        super(context, DB_NAME, null, null, DB_VER);
    }

    public List<String> getWorkoutDays(){

       SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Day"};
        String sqlTable = "WorkoutDays";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if(c.moveToFirst()) {

            do {

                result.add(c.getString(c.getColumnIndex("Day")));
            }
            while (c.moveToNext());
        }
        return result;



    }

    public void saveDays (String value){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays (Day) VALUES ('%s');",value);
        db.execSQL(query);

    }


}
