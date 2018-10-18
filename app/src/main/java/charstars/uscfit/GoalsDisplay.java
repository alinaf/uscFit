package charstars.uscfit;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalsDisplay extends AppCompatActivity implements View.OnClickListener{
    private String email;
    private List<Goal> defaultGoals = SampleGoalDatabase.defaultGoals;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            BottomNavigationView navigation;
            switch (item.getItemId()) {
                case R.id.navigation_goals:
                    setContentView(R.layout.activity_goals_display);
                    createTable();

                    navigation = findViewById(R.id.navigation);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;

                case R.id.navigation_badges:
                    return true;
                case R.id.navigation_addGoal:
                    setContentView(R.layout.addgoal);
                    clearAddGoalFields();

                    navigation = findViewById(R.id.navigationAddGoal);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;
            }
            return false;
        }
    };

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

        setContentView(R.layout.activity_goals_display);
        createTable();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void createTable(){

        mRecyclerView = findViewById(R.id.goalsLayout);

        mAdapter = new GoalAdapter(defaultGoals);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    public boolean addGoal(String goalType, int goalNum, String exerciseDescription){

        if(goalType.equals("Miles")){
            defaultGoals.add(new MilesGoal(exerciseDescription, goalNum, 0));

        }else{
            defaultGoals.add(new MinutesGoal(exerciseDescription, goalNum, 0));

        }
        //assuming success;
        return true;
    }

    public void clearAddGoalFields(){
        EditText e = findViewById(R.id.exercise);
        e.getText().clear();
        e.setHint("ex. run, swim, bike");
        Spinner spinner = (Spinner) findViewById(R.id.goalSpinner);
        spinner.setSelection(0);
        NumberPicker num = (NumberPicker)findViewById(R.id.numberPicker);
        num.setMinValue(1);
        num.setMaxValue(1000);
        num.setValue(1);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.addGoal){
            EditText e = findViewById(R.id.exercise);
            Spinner spinner = (Spinner) findViewById(R.id.goalSpinner);
            NumberPicker num = (NumberPicker)findViewById(R.id.numberPicker);

            String exerciseDescription = e.getText().toString();
            int goalNum = num.getValue();
            String goalType = spinner.getSelectedItem().toString();

//            TextView toastText = (TextView)findViewById(R.id.goaltoastview);
//            LayoutInflater inflater = getLayoutInflater();
//            View layout = inflater.inflate(R.layout.goalsuccess,
//                    (ViewGroup) findViewById(R.id.custom_toast_container));
//            Toast toast = new Toast(getApplicationContext());
//            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//            toast.setDuration(Toast.LENGTH_LONG);
//            toast.setView(layout);

            if(exerciseDescription.equals("") || exerciseDescription == null || goalNum == 0 || goalType == null || goalType.equals("")){
//                toastText.setText("Please fill out all fields.");
//                toast.show();


                return;
            }
            boolean successful = addGoal(goalType, goalNum, exerciseDescription);

            if(successful){
//                toastText.setText("Successfully saved goal!");
//                toast.show();

                clearAddGoalFields();
            }else{
//                toastText.setText("Error saving goal. Please try again later.");
//                toast.show();
            }

            return;
        }
//        if(v.getId() == R.id.goback){
//            setContentView(R.layout.activity_goals_display);
//            createTable();
//            return;
//        }
//
//        Goal g = (Goal) v.getTag();
//        Log.d("goalsDisplay",g.getCategory());
//
//        setContentView(R.layout.goal_details);
//
//        TextView title = findViewById(R.id.title);
//        title.setText("GOAL: " + g.getCategory() + "-- " + g.getDescription());
//
//        mRecyclerView = findViewById(R.id.recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(g.getDueDates());
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(mAdapter);

    }

    public void addNewGoal(){

    }


}