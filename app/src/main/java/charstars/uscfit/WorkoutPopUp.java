package charstars.uscfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import charstars.uscfit.DataHandlers.UpdateWorkouts;

public class WorkoutPopUp extends AppCompatActivity implements View.OnClickListener {
    Workout w;
    private String email;
    private FirebaseDatabase mDatabase;


    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int eYear =-1, eMonth=-1, eDay=-1, eHour = -1, eMinute = -1;

    private ArrayList<Activity> activityList = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // int width = dm.widthPixels;
        // int height = dm.heightPixels;

        // getWindow().setLayout((int)(width*.8), (int)(height*.6));
        mDatabase = FirebaseDatabase.getInstance();

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

        initializeFields();

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

    }

    public void initializeFields(){
        Spinner spinner = (Spinner) findViewById(R.id.workoutSpinner);
        DatabaseReference myRef = mDatabase.getReference("activities");
        final ArrayAdapter<Activity> arrayAdapter = new ArrayAdapter<Activity>(this, android.R.layout.simple_spinner_item,  activityList);
        spinner.setAdapter(arrayAdapter);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Activity act = new Activity(dataSnapshot.getKey(), (Long)dataSnapshot.getValue());
                activityList.add(act);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        spinner.setSelection(0);
        NumberPicker num = (NumberPicker)findViewById(R.id.lengthPicker);
        num.setMinValue(1);
        num.setMaxValue(1000);
        num.setValue(1);
    }


    public void sendMessage(View view) {
        Activity activity = (Activity)((Spinner)findViewById(R.id.workoutSpinner)).getSelectedItem();
        int length = (((NumberPicker)findViewById(R.id.lengthPicker)).getValue());
        Quantifier quant = Quantifier.valueOf(((Spinner)findViewById(R.id.quantifierOption)).getSelectedItem().toString());
        int year = eYear;
        int month = eMonth;
        int day = eDay;
        int hour = eHour;
        int minute = eMinute;

        if(!addWorkout(activity, quant, length, year, month, day, hour, minute)) {
            DisplayToast();
            return;
        }
        finish();
    }

    public boolean addWorkout(Activity activity, Quantifier quant, int length, int year, int month, int day, int hour, int minute) {
        if(activity == null || !isCalendarSet(year, month, day, hour, minute)) {
            return false;
        }
        GregorianCalendar cal = new GregorianCalendar(year, month, day, hour, minute);
        Workout workout = new Workout(activity, quant, length, cal);
        writeToDatabase(workout);
        return true;
    }

    //Need to include firebase
    public void writeToDatabase(Workout workout) {
        UpdateWorkouts.addWorkout(workout,email);
    }

    @Override
    public void onClick(View v) {

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
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                            eHour = hourOfDay;
                            eMinute = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public boolean isCalendarSet(int year, int month, int day, int hour, int minute) {
        if(year < 1970)
            return false;
        if(month < 0 || month > 12)
            return false;
        if(day < 1 || day > 31)
            return false;
        if(hour < 0 || hour >=24 )
            return false;
        if(minute < 0 || minute >= 60)
            return false;
        return true;
    }

    public void DisplayToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout;

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);

        layout = inflater.inflate(R.layout.workoutfail,null);
        toast.setView(layout);
        toast.show();
    }
}
