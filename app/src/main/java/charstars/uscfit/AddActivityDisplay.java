package charstars.uscfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import charstars.uscfit.DataHandlers.UpdateActivities;

public class AddActivityDisplay extends AppCompatActivity {

    Activity a = null;
    EditText txtActivityName, txtActivityCalories;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_display);

    }


    public void addCustomActivity(View view) {
        String activityName = ((EditText)(findViewById(R.id.customActivityName))).getText().toString();
        int activityCalories = Integer.parseInt(((EditText)(findViewById(R.id.caloriesPerHour))).getText().toString());
        a = new Activity(activityName, activityCalories);
        UpdateActivities.addActivity(a);
        displayToast();
    }



    public void displayToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = null;

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        layout = inflater.inflate(R.layout.activity_success, null);
        /*if (!value)
            layout = inflater.inflate(R.layout.workoutfail, null);
        else if (value)
            layout = inflater.inflate(R.layout.workoutsuccess, null);*/
        toast.setView(layout);
        toast.show();
    }
}

