package charstars.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import charstars.uscfit.DataHandlers.GoalCalculations;

public class EditPopUpInfo extends AppCompatActivity {
    Goal g;

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
        Goal gg = new Goal();
        gg.setDescription(desc.getText().toString());
        gg.setGoalNum(num.getValue());
        gg.setTrackingNum(0);
        GoalCalculations.editGoal(gg, g);
        Log.d("EDIT", "GOAL BEFORE: "+ g.getDescription());

        finish();
    }
}
