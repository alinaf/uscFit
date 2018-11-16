package charstars.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import charstars.uscfit.Adapters.GoalAdapter;
import charstars.uscfit.Adapters.WorkoutAdapter;
import charstars.uscfit.DataHandlers.GoalCalculations;
import charstars.uscfit.DataHandlers.UpdateWorkouts;
import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;
import charstars.uscfit.RootObjects.Quantifier;
import charstars.uscfit.RootObjects.Workout;

public class WorkoutList extends AppCompatActivity implements View.OnClickListener {
    private static String email;
    private static List<Workout> workoutList = UpdateWorkouts.getWorkouts();

    CheckBox checkBox;

    private static RecyclerView mRecyclerView;
    private static WorkoutAdapter mAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                email = null;
            } else {
                email = extras.getString("EMAIL");
            }
        } else {
            email = (String) savedInstanceState.getSerializable("EMAIL");
        }
        workoutList = UpdateWorkouts.getWorkouts();
        setContentView(R.layout.activity_workout_list);
        createTable();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_workout_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WorkoutList.this, WorkoutPopUp.class);
                i.putExtra("EMAIL", "Tianqin");
                startActivity(i);
            }
        });
        /*
        checkBox = (CheckBox)(findViewById(R.id.checkBox));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    View v1 = checkBox.getParent()
                }
            }
        });
        checkBox.getParen */
    }

    public void createTable() {

        mRecyclerView = findViewById(R.id.workoutListLayout);

        mAdapter = new WorkoutAdapter(workoutList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onResume() {
        super.onResume();
        createTable();
    }

    public static void onChangeData(List<Workout> workouts) {
        workoutList = workouts;
        //    System.out.println("qianze: " + workouts.get(0).getDescription());
        if (mRecyclerView != null) {
            ((WorkoutAdapter)mRecyclerView.getAdapter()).notifyDataSetChanged();
            mAdapter = new WorkoutAdapter(workoutList);
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.checkBox){
            Workout w = (Workout) v.getTag();
            Log.d("onclick", "getting in here");
            UpdateWorkouts.changeWorkoutCompletionStatus(w);

            onChangeData(workoutList);
        }
    }
}