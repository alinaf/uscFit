package charstars.uscfit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import charstars.uscfit.Adapters.BadgeAdapter;
import charstars.uscfit.DataHandlers.BadgeCalculator;
import charstars.uscfit.RootObjects.Badge;

public class BadgesDisplay extends AppCompatActivity
{
    public static boolean created = false;
    private static ArrayList<Badge> badges;
    private static ListView listView;
    private static BadgeAdapter mAdapter;
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
                    navigation = findViewById(R.id.navigationGoals);
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
        //initBadges();
        BadgeCalculator.initDB();
        badges = BadgeCalculator.getBadges();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        listView = (ListView) findViewById(R.id.movies_list);
        mAdapter = new BadgeAdapter(this,badges);
        listView.setAdapter(mAdapter);
        created = true;
        Log.d("BADGES DISPLAY", "HERERE");


    }

    @Override
    public void onResume(){
        super.onResume();
         setContentView(R.layout.activity_badges_display);
        listView = (ListView) findViewById(R.id.movies_list);
        mAdapter = new BadgeAdapter(this,badges);
        listView.setAdapter(mAdapter);
        created = true;
        Log.d("BADGES DISPLAY", "HERERE");

    }



    public static void onChangeData(ArrayList<Badge> goals){
//        if(created) {
//            badges = goals;
//            ((BadgeAdapter) listView.getAdapter()).notifyDataSetChanged();
//        }

    }

    public void initBadges(){
        BadgeCalculator.addBadge("Swam 50 miles", 50, new Date());
        BadgeCalculator.addBadge("Ran 500 miles", 500, new Date());
//        ArrayList<Badge> badgesList = new ArrayList<>();
//        badgesList.add(new Badge(R.drawable.small_trophy, "Swam 10 miles" , "10/20/18"));
//        badgesList.add(new Badge(R.drawable.med_trophy, "Ran 20 miles" , "10/13/18"));
//        badgesList.add(new Badge(R.drawable.big_trophy, "Biked 50 miles" , "10/08/18"));
//        badgesList.add(new Badge(R.drawable.small_trophy, "Hiked 10 miles" , "10/01/18"));
//        badgesList.add(new Badge(R.drawable.med_trophy, "Roller-bladed 20 miles" , "9/13/18"));
//        badgesList.add(new Badge(R.drawable.big_trophy, "Biked 50 miles" , "9/08/18"));
//        badgesList.add(new Badge(R.drawable.small_trophy, "Swam 10 miles" , "8/20/18"));
//        badgesList.add(new Badge(R.drawable.med_trophy, "Ran 20 miles" , "8/13/18"));
//        badgesList.add(new Badge(R.drawable.big_trophy, "Biked 50 miles" , "7/08/18"));
    }
}
