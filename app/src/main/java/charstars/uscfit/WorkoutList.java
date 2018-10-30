package charstars.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import charstars.uscfit.Adapters.WorkoutAdapter;
import charstars.uscfit.DataHandlers.UpdateWorkouts;
import charstars.uscfit.RootObjects.Workout;

public class WorkoutList extends AppCompatActivity {
    private String email;

    private List<Workout> workoutList;
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
        workoutList = UpdateWorkouts.getWorkouts(email);
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
    }

    public void createTable(){

        mRecyclerView = findViewById(R.id.workoutListLayout);

        mAdapter = new WorkoutAdapter(workoutList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onResume(){
        super.onResume();
        createTable();
    }
}