package charstars.uscfit;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import charstars.uscfit.DataHandlers.UpdateWorkouts;
import charstars.uscfit.RootObjects.Quantifier;
import charstars.uscfit.RootObjects.Workout;

public class WorkoutPopUp extends AppCompatActivity implements View.OnClickListener {
    Workout w = null;
    private String email;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;


    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int eYear =-1, eMonth=-1, eDay=-1, eHour = -1, eMinute = -1;

    private ArrayList<Activity> activityList = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
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
        setDefaultWorkouts();
        DatabaseReference myRef = mDatabase.getReference("activities");
        final ArrayAdapter<Activity> arrayAdapter = new ArrayAdapter<Activity>(this, android.R.layout.simple_spinner_item,  activityList);
        spinner.setAdapter(arrayAdapter);

        /*myRef.addChildEventListener(new ChildEventListener() {
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
        });*/





        spinner.setSelection(0);
        NumberPicker num = (NumberPicker)findViewById(R.id.lengthPicker);
        num.setMinValue(1);
        num.setMaxValue(1000);
        num.setValue(1);
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
            DisplayToast(false);
            return;
        }
        else {
            DisplayToast(true);
           // mNotificationHelper = new NotificationHelper(WorkoutPopUp.this);
            //sendNotification("Workout added!", "Workout has successfully been added!");


            Calendar cal = Calendar.getInstance();
            Date d = w.getDate();
            Log.d("currentTime", d.toString());
            d.setHours(d.getHours()-3);
           // cal.set(d.getYear(), d.getMonth(), d.getDate(), d.getHours()-3, d.getMinutes());
            Log.d("EarlyWorkoutTime", d.toString());
            setStartAlarm(d.getTime());
            d = w.getDate();
            d.setMinutes(d.getMinutes()+length);
            setFinishAlarm(d.getTime());
        }
        finish();
    }

    public boolean addWorkout(Activity activity, Quantifier quant, int length, int year, int month, int day, int hour, int minute) {
        if(activity == null || !isCalendarSet(year, month, day, hour, minute)) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        w = new Workout(activity, quant, length, cal.getTime());
        return UpdateWorkouts.addWorkout(w);
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

    public void DisplayToast(boolean value) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = null;

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        if (!value)
            layout = inflater.inflate(R.layout.workoutfail, null);
        else if (value)
            layout = inflater.inflate(R.layout.workoutsuccess, null);
        toast.setView(layout);
        toast.show();
    }

    @TargetApi(19)
    public void setStartAlarm(long time) {
        Log.d("setalarm", "alarm has been set");
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("message", w.getActivity().getCategory() + " for " + w.getLength() + " " + w.getQuant().getMeasurement());
         PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        am.setExact(AlarmManager.RTC_WAKEUP, time, broadcast);

    }

    @TargetApi(19)
    public void setFinishAlarm(long time) {
        Log.d("setalarm", "alarm has been set");
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("message", "Did you complete your workout?");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        am.setExact(AlarmManager.RTC_WAKEUP, time, broadcast);

    }
}
