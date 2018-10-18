package charstars.uscfit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import charstars.uscfit.Activity;
import charstars.uscfit.ActivityAdapter;
import charstars.uscfit.R;

public class WorkoutList extends AppCompatActivity {
    private String email;

    private List<Workout> workoutList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private WorkoutAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                email = null;
            } else {
                email = extras.getString("EMAIL");
            }
        } else {
            email = (String) savedInstanceState.getSerializable("EMAIL");
        }

        setContentView(R.layout.activity_workout_list);
        createTable();
    }

    public void createTable(){

        mRecyclerView = findViewById(R.id.workoutListLayout);

        mAdapter = new WorkoutAdapter(workoutList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareActivityData();

    }

    private void prepareActivityData() {
        Workout workout = new Workout(new Activity("Basketball", 100), Quantifier.MINUTES, 60, Calendar.YEAR, Calendar.JANUARY, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE);
        workoutList.add(workout);

//        workout = new Workout(new Activity("Gardening", 50), Quantifier.MINUTES, 30, 2018, 10, 19, 12, 30);
        workoutList.add(workout);



        //mAdapter.notifyDataSetChanged();
    }
}