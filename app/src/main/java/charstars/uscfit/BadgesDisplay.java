package charstars.uscfit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import charstars.uscfit.DataHandlers.GoalCalculations;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BadgesDisplay extends AppCompatActivity
{
    private ListView listView;
    private BadgeAdapter mAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            BottomNavigationView navigation;
            switch (item.getItemId()) {
                case R.id.navigation_goals:
                    setContentView(R.layout.activity_goals_display);
                    //createTable();
                    Intent i = new Intent(BadgesDisplay.this, GoalsDisplay.class);
                    startActivity(i);
                    navigation = findViewById(R.id.navigation);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;

                case R.id.navigation_badges:
                    setContentView(R.layout.activity_badges_display);
                    //Intent i = new Intent(GoalsDisplay.this, BadgesDisplay.class);
                    //startActivity(i);
                    navigation = findViewById(R.id.navigation);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;

                case R.id.navigation_addGoal:
                    setContentView(R.layout.addgoal);
                    //clearAddGoalFields();
                    Intent i2 = new Intent(BadgesDisplay.this, GoalsDisplay.class);
                    startActivity(i2);
                    navigation = findViewById(R.id.navigationAddGoal);
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges_display);

        listView = (ListView) findViewById(R.id.movies_list);
        ArrayList<Badge> badgesList = new ArrayList<>();
        badgesList.add(new Badge(R.drawable.small_trophy, "Swam 10 miles" , "10/20/18"));
        badgesList.add(new Badge(R.drawable.med_trophy, "Ran 20 miles" , "10/13/18"));
        badgesList.add(new Badge(R.drawable.big_trophy, "Biked 50 miles" , "10/08/18"));
        badgesList.add(new Badge(R.drawable.small_trophy, "Hiked 10 miles" , "10/01/18"));
        badgesList.add(new Badge(R.drawable.med_trophy, "Roller-bladed 20 miles" , "9/13/18"));
        badgesList.add(new Badge(R.drawable.big_trophy, "Biked 50 miles" , "9/08/18"));
        badgesList.add(new Badge(R.drawable.small_trophy, "Swam 10 miles" , "8/20/18"));
        badgesList.add(new Badge(R.drawable.med_trophy, "Ran 20 miles" , "8/13/18"));
        badgesList.add(new Badge(R.drawable.big_trophy, "Biked 50 miles" , "7/08/18"));

        mAdapter = new BadgeAdapter(this,badgesList);
        listView.setAdapter(mAdapter);

    }
}
