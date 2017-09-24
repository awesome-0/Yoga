package com.example.samuel.yoga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView recyclerView ;
    ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.exercise_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();

        adapter = new ExerciseAdapter(exerciseList,ListActivity.this);
        recyclerView.setAdapter(adapter);
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
}
