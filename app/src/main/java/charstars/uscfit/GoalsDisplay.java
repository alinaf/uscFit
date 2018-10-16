package charstars.uscfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalsDisplay extends AppCompatActivity implements View.OnClickListener{
    private String email;
    private List<Goal> defaultGoals = new ArrayList<Goal>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting sample goal list (hardcoded)
        Goal sample1 = new Goal("get more fit", "workouts", new HashMap<Activity, Date>());
        Activity sample = new Activity("1 mile", 100, "Running");
        sample1.addActivity(sample, new Date(110));
        Goal sample2 = new Goal("take 100 steps", "steps", new HashMap<Activity, Date>());
        defaultGoals.add(sample1);
        defaultGoals.add(sample2);

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

        TableLayout ll = (TableLayout)findViewById(R.id.goalLayout);

        if(ll == null){
            Log.d("goalsDisplay","TABLE CONTEXT NULL NULL");
        }
        for (int i = 0; i <defaultGoals.size(); i++) {

            Goal current = defaultGoals.get(i);

            TableRow row = new TableRow(ll.getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);


           // ImageButton addBtn = new ImageButton(this);
            //addBtn.setImageResource(R.drawable.add);
           // ImageButton minusBtn = new ImageButton(this);
           // minusBtn.setImageResource(R.drawable.minus);

            TextView desc = new TextView(this);
            TextView cat = new TextView(this);
            ProgressBar prog = new ProgressBar(this);
            //HARD CODED VALUE FOR PROGRESS
            prog.setProgress(50);
            cat.setText(current.getCategory());
            desc.setText(current.getDescription());

            row.addView(desc);
            row.addView(cat);
            Button b = new Button(this);
            b.setTag(current);
            b.setOnClickListener(this);
            row.addView(b);

            /**
            LinearLayout linlay = new LinearLayout(this);
            linlay.setOrientation(LinearLayout.VERTICAL);
            for(Map.Entry<Activity, Date> entry: current.getDueDates().entrySet()){
                LinearLayout activ = new LinearLayout((this));
                activ.setOrientation(LinearLayout.HORIZONTAL);
                TextView name = new TextView(this);
                name.setText(entry.getKey().getName());
                TextView cat2 = new TextView(this);
                cat2.setText(entry.getKey().getCategory());
                TextView due = new TextView(this);
                due.setText(entry.getValue().toString());
                activ.addView(name);
                activ.addView(cat2);
                activ.addView(due);

                CheckBox c = new CheckBox(this);
                c.setChecked(entry.getKey().isCompleted());


                linlay.addView(activ);
            }

            //row.addView(checkBox);
          // row.addView(minusBtn);
          //  row.addView(addBtn);
            row.addView(linlay);

             **/

            if(row == null){
                Log.d("goalsDisplay","TABLE ROW NULL");
            }
            ll.addView(row,i);
        }



    }

    @Override
    public void onClick(View v) {
        Goal g = (Goal) v.getTag();
        Log.d("goalsDisplay",g.getCategory());

    }
}
