package charstars.uscfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

public class WorkoutPopUp extends AppCompatActivity {
    Workout w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));
        initializeFields();
    }

    public void initializeFields(){
        // EditText e = findViewById(R.id.exercise);
        // e.getText().clear();
        //  e.setHint("ex. run, swim, bike");
        Spinner spinner = (Spinner) findViewById(R.id.workoutSpinner);
        spinner.setSelection(0);
        NumberPicker num = (NumberPicker)findViewById(R.id.lengthPicker);
        num.setMinValue(1);
        num.setMaxValue(1000);
        num.setValue(1);
    }


    public void sendMessage(View view) {
        //Activity activity = new Activity(findViewById(R.id.spinner).getText().toString()), 50)
        Activity activity = new Activity("Swimming", 200);
        int length = (((NumberPicker)findViewById(R.id.lengthPicker)).getValue());
        Quantifier quant = Quantifier.valueOf(((TextView)findViewById(R.id.quantifierOption)).getText().toString());
        Workout workout = new Workout(activity, quant, length);

        finish();
    }
}
