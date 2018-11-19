package charstars.uscfit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import charstars.uscfit.Adapters.BadgeAdapter;
import charstars.uscfit.Adapters.GoalAdapter;
import charstars.uscfit.Adapters.WorkoutAdapter;
import charstars.uscfit.DataHandlers.BadgeCalculator;
import charstars.uscfit.DataHandlers.GoalCalculations;
import charstars.uscfit.DataHandlers.UpdateWorkouts;
import charstars.uscfit.RootObjects.Badge;
import charstars.uscfit.RootObjects.Quantifier;
import charstars.uscfit.RootObjects.Workout;

public class GoalsDisplay extends AppCompatActivity implements View.OnClickListener{
    private static String email;
    private static List<Goal> defaultGoals = GoalCalculations.getGoals(email);

    private static RecyclerView mRecyclerView = null;
    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;


    //badge data:

    public static boolean created = false;
    private static ArrayList<Badge> badges;
    private static ListView listView;
    private static BadgeAdapter mBadgeAdapter;

    private ArrayList<Activity> activityList = new ArrayList<Activity>();


    Button btnDatePicker;
    TextView txtDate;
    private int mYear, mMonth, mDay;
    private int eYear =-1, eMonth=-1, eDay=-1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            BottomNavigationView navigation;
            switch (item.getItemId()) {
                case R.id.navigation_goals:
                    setContentView(R.layout.activity_goals_display);
                    createTable();

                    navigation = findViewById(R.id.navigationGoals);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;

                case R.id.navigation_badges:
                    Log.d("nav to badges", "badge");
                    setContentView(R.layout.activity_badges_display);
//                    Intent i = new Intent(GoalsDisplay.this, BadgesDisplay.class);
//                    startActivity(i);
                    initBadges();
                    navigation = findViewById(R.id.navigation);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;

                case R.id.navigation_addGoal:
                    Log.d("nav to add", "add goal");
                    setContentView(R.layout.addgoal);
                    clearAddGoalFields();

                    navigation = findViewById(R.id.navigationAddGoal);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;
            }
            Log.d("didnt get nav???", "create");
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
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

        createTable();

    }
    @Override
    public void onResume(){
        super.onResume();
        createTable();
    }

//    public static void onChangeData(List<Goal> goals){
//        defaultGoals = goals;
//        System.out.println("qianze: " + goals.get(0).getDescription());
//        if(mRecyclerView !=null) {
//            ((GoalAdapter) mRecyclerView.getAdapter()).notifyDataSetChanged();
//            mAdapter = new GoalAdapter(defaultGoals);
//            mRecyclerView.setAdapter(mAdapter);
//        }
//
//        //workout list
//
//    }


    public void createTable(){
        this.defaultGoals = GoalCalculations.getGoals(email);
        setContentView(R.layout.activity_goals_display);
        BottomNavigationView navigation = findViewById(R.id.navigationGoals);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mRecyclerView = findViewById(R.id.goalsLayout);
        Log.d("inside table", "create");
        Log.d("inside table", defaultGoals.toString());
        mAdapter = new GoalAdapter(defaultGoals);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);




    }

    public boolean addGoal(Date d, String goalType, int goalNum, String exerciseDescription){

        if(goalType.equals("Miles")){
           return GoalCalculations.addGoal(new MilesGoal(d, exerciseDescription, goalNum, 0), email);
        }else{
            Log.d("updating", exerciseDescription);
            return GoalCalculations.addGoal(new MinutesGoal(d, exerciseDescription, goalNum, 0), email);

        }
        //assuming success;
    }

    public void clearAddGoalFields(){
        Spinner spinner = (Spinner) findViewById(R.id.goalSpinner);
        spinner.setSelection(0);
        NumberPicker num = (NumberPicker)findViewById(R.id.numberPicker);
        num.setMinValue(1);
        num.setMaxValue(1000);
        num.setValue(1);
        btnDatePicker=(Button)findViewById(R.id.btn_duedate);
        txtDate=(TextView)findViewById(R.id.text_date);

        btnDatePicker.setOnClickListener(this);

        //workout list

        Spinner workoutSpinner = (Spinner) findViewById(R.id.workoutSpinner);
        setDefaultWorkouts();

        final ArrayAdapter<Activity> arrayAdapter = new ArrayAdapter<Activity>(this, android.R.layout.simple_spinner_item,  activityList);
        workoutSpinner.setAdapter(arrayAdapter);
        workoutSpinner.setSelection(0);
    }

    public void initBadges(){
        BadgeCalculator.initDB();
        badges = BadgeCalculator.getBadges();
        setContentView(R.layout.activity_badges_display);
        listView = (ListView) findViewById(R.id.movies_list);
        mBadgeAdapter = new BadgeAdapter(this,badges);
        listView.setAdapter(mBadgeAdapter);
        created = true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.goalRowLayout){
            Goal g = (Goal) v.getTag();
            if(g.getQuantifier().equals(Quantifier.DAYS.getMeasurement()) || g.getQuantifier().equals(Quantifier.STEPS.getMeasurement())){
                return;
            }
            Intent i = new Intent(GoalsDisplay.this, EditPopUpInfo.class);
            i.putExtra("GOAL", g);
            startActivity(i);


        }
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            eYear = year;
                            eMonth = monthOfYear;
                            eDay = dayOfMonth;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(v.getId() == R.id.addGoal){
            Spinner spinner = (Spinner) findViewById(R.id.goalSpinner);
            Activity activity = (Activity)((Spinner)findViewById(R.id.workoutSpinner)).getSelectedItem();

            NumberPicker num = (NumberPicker)findViewById(R.id.numberPicker);
            int year = eYear;
            int month = eMonth;
            int day = eDay;


            String exerciseDescription = activity.getCategory();
            int goalNum = num.getValue();
            String goalType = spinner.getSelectedItem().toString();



            LayoutInflater inflater = getLayoutInflater();

            View layout;


            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);


            if(exerciseDescription.equals("") || exerciseDescription == null || goalNum == 0 || goalType == null || goalType.equals("") || !isCalendarSet(year, month, day)){

                Log.d("Something happened", "");
                layout = inflater.inflate(R.layout.goalfail,null);
                toast.setView(layout);
                toast.show();

                return;
            }


            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            boolean successful = addGoal(cal.getTime(), goalType, goalNum, exerciseDescription);

            if(successful){
                layout = inflater.inflate(R.layout.goalsuccess,null);
                toast.setView(layout);
                toast.show();

                clearAddGoalFields();
            }else{
                layout = inflater.inflate(R.layout.goalerror,null);
                toast.setView(layout);
                toast.show();
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
    public boolean isCalendarSet(int year, int month, int day) {
        if(year < 1970)
            return false;
        if(month < 0 || month > 12)
            return false;
        if(day < 1 || day > 31)
            return false;
        return true;
    }

    public void setDefaultWorkouts() {
        activityList.add(new Activity("Aerobics", 384));
        activityList.add(new Activity("Badminton", 266));
        activityList.add(new Activity("Ballet", 266));
        activityList.add(new Activity("Ballroom Dancing", 177));
        activityList.add(new Activity("Baseball", 295));
        activityList.add(new Activity("Softball", 295));
        activityList.add(new Activity("Basketball", 400));
        activityList.add(new Activity("Biking ", 390));
        activityList.add(new Activity("Bowling", 177));
        activityList.add(new Activity("Boxing", 354));
        activityList.add(new Activity("Football", 472));
        activityList.add(new Activity("Gardening", 236));
        activityList.add(new Activity("General Cleaning", 207));
        activityList.add(new Activity("General Housework", 208));
        activityList.add(new Activity("Golf", 266));
        activityList.add(new Activity("Hiking", 354));
        activityList.add(new Activity("Hockey", 472));
        activityList.add(new Activity("Horseback Riding", 236));
        activityList.add(new Activity("Jumping Rope", 472));
        activityList.add(new Activity("Lacrosse", 472));
        activityList.add(new Activity("Martial Arts", 590));
        activityList.add(new Activity("Mowing Lawn", 325));
        activityList.add(new Activity("Painting", 266));
        activityList.add(new Activity("Ping Pong", 236));
        activityList.add(new Activity("Playing with Children", 236));
        activityList.add(new Activity("Rock Climbing", 472));
        activityList.add(new Activity("Roller Blading ", 708));
        activityList.add(new Activity("Running", 472));
        activityList.add(new Activity("Skateboarding", 295));
        activityList.add(new Activity("Skiing", 295));
        activityList.add(new Activity("Soccer", 450));
        activityList.add(new Activity("Swimming", 354));
        activityList.add(new Activity("Tennis", 472));
        activityList.add(new Activity("Ultimate Frisbee", 472));
        activityList.add(new Activity("Volleyball", 177));
        activityList.add(new Activity("Walking", 195));
        activityList.add(new Activity("Walking the Dog", 177));
        activityList.add(new Activity("Watering the Garden", 89));
        activityList.add(new Activity("Weight Lifting", 354));
        activityList.add(new Activity("Yoga", 234));
    }


}