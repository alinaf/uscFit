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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import charstars.uscfit.DataHandlers.GoalCalculations;
import charstars.uscfit.RootObjects.Quantifier;

public class EditPopUpInfo extends AppCompatActivity implements View.OnClickListener {
    Goal g;
    Button btnDatePicker;
    TextView txtDate;
    private int mYear, mMonth, mDay;
    private int eYear =-1, eMonth=-1, eDay=-1;


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
        EditText e = (EditText)findViewById(R.id.editgoalexercise);
        e.setText(g.getDescription());
        NumberPicker n = (NumberPicker)findViewById(R.id.editGoalnumberPicker);
        btnDatePicker=(Button)findViewById(R.id.btn_duedate);
        txtDate=(TextView)findViewById(R.id.text_date);
        Calendar c = Calendar.getInstance();

        if(g.getDueDate()!=null){
            c.setTime(g.getDueDate());
        }
        btnDatePicker.setOnClickListener(this);


        e.setHint("ex. run, swim, bike");

        n.setMinValue(1);
        n.setMaxValue(1000);
        n.setValue(g.goalNum);

        getWindow().setLayout((int)(width*.8), (int)(height*.6));
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        Log.d("EDIT", "GOAL BEFORE: "+ g.getDescription());
        EditText desc = (EditText)findViewById(R.id.editgoalexercise);
        NumberPicker num = (NumberPicker)findViewById(R.id.editGoalnumberPicker);
        int year = eYear;
        int month = eMonth;
        int day = eDay;


        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Goal gg = new Goal();
        gg.setDescription(desc.getText().toString());
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
