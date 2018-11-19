package charstars.uscfit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import charstars.uscfit.DataHandlers.GoalCalculations;
import charstars.uscfit.RootObjects.Quantifier;

public class EditPopUpInfo extends AppCompatActivity implements View.OnClickListener {
    Goal g;
    Button btnDatePicker;
    TextView txtDate;
    private int mYear, mMonth, mDay;
    private int eYear =-1, eMonth=-1, eDay=-1;

    private ArrayList<Activity> activityList = new ArrayList<Activity>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editgoalpopup);
        Intent intent = getIntent();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                g = null;
            } else {
                g = (Goal) getIntent().getSerializableExtra("GOAL");
            }
        } else {
            g = (Goal) savedInstanceState.getSerializable("GOAL");
        }
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        TextView t = (TextView)findViewById(R.id.goalOption);
        t.setText(g.getQuantifier());
        NumberPicker n = (NumberPicker)findViewById(R.id.editGoalnumberPicker);
        btnDatePicker=(Button)findViewById(R.id.btn_duedate);
        txtDate=(TextView)findViewById(R.id.text_date);
        Calendar c = Calendar.getInstance();

        if(g.getDueDate()!=null){
            c.setTime(g.getDueDate());
        }
        btnDatePicker.setOnClickListener(this);


        Spinner workoutSpinner = (Spinner) findViewById(R.id.workoutSpinner);
        setDefaultWorkouts();

        final ArrayAdapter<Activity> arrayAdapter = new ArrayAdapter<Activity>(this, android.R.layout.simple_spinner_item,  activityList);
        workoutSpinner.setAdapter(arrayAdapter);
        boolean set = false;
        for(int i = 0; i<activityList.size(); i++){
            if(activityList.get(i).getCategory().toLowerCase().trim().equals(g.getDescription().trim().toLowerCase())){
                workoutSpinner.setSelection(i);
                set = true;
            }
        }
        if(!set){
            workoutSpinner.setSelection(0);
        }

        n.setMinValue(1);
        n.setMaxValue(1000);
        n.setValue(g.goalNum);

        getWindow().setLayout((int)(width*.8), (int)(height*.6));
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

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        Log.d("EDIT", "GOAL BEFORE: "+ g.getDescription());
        NumberPicker num = (NumberPicker)findViewById(R.id.editGoalnumberPicker);
        int year = eYear;
        int month = eMonth;
        int day = eDay;


        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Goal gg = new Goal();
        gg.setDescription(((Activity)((Spinner)findViewById(R.id.workoutSpinner)).getSelectedItem()).getCategory());
        gg.setGoalNum(num.getValue());
        gg.setTrackingNum(0);
        gg.setDueDate(cal.getTime());
        GoalCalculations.editGoal(gg, g);
        Log.d("EDIT", "GOAL BEFORE: "+ g.getDescription());

        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            if(g.getDueDate()!=null){
                c.setTime(g.getDueDate());
            }
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

    }
}
