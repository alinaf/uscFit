package charstars.uscfit;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import charstars.uscfit.DataHandlers.UpdateActivities;
import charstars.uscfit.DataHandlers.UpdateWorkouts;
import charstars.uscfit.RootObjects.Quantifier;
import charstars.uscfit.RootObjects.Workout;

public class WorkoutPopUp extends AppCompatActivity implements View.OnClickListener {
    Workout w = null;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;


    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int eYear =-1, eMonth=-1, eDay=-1, eHour = -1, eMinute = -1;

    private List<Activity> activityList = UpdateActivities.getActivities();

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

        //\\DatabaseReference myRef = mDatabase.getReference("activities");
        final ArrayAdapter<Activity> arrayAdapter = new ArrayAdapter<Activity>(this, android.R.layout.simple_spinner_item,  activityList);
        spinner.setAdapter(arrayAdapter);


        spinner.setSelection(0);
        NumberPicker num = (NumberPicker)findViewById(R.id.lengthPicker);
        num.setMinValue(1);
        num.setMaxValue(1000);
        num.setValue(1);
    }

    public void sendMessage(View view) {
        Activity activity = (Activity)((Spinner)findViewById(R.id.workoutSpinner)).getSelectedItem();
        int length = (((NumberPicker)findViewById(R.id.lengthPicker)).getValue());
        Quantifier quant = Quantifier.valueOf("MINUTES");
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

//        Calendar cal = Calendar.getInstance();
//        int currMonth = cal.get(cal.MONTH)+1;
//        int currDay = cal.get(cal.DAY_OF_MONTH);
//
//        // don't add workout older than 2 months
//        if(currMonth - month > 2)
//        {
//            return false;
//        }
//        if(currMonth - month == 2)
//        {
//            if(day < currDay) {
//                return false;
//            }
//        }
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
