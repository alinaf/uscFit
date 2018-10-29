//package charstars.uscfit;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//public class BadgesDisplay extends AppCompatActivity {
//
//    GridView androidGridView;
//
//    Integer[] imageIDs = {
//            R.drawable.trophy, R.drawable.trophy, R.drawable.trophy,
//            R.drawable.trophy, R.drawable.trophy, R.drawable.trophy,
//            R.drawable.trophy, R.drawable.trophy, R.drawable.trophy,
//            R.drawable.trophy, R.drawable.trophy, R.drawable.trophy
//
////            R.drawable.email, R.drawable.mobile, R.drawable.alram,
////            R.drawable.android, R.drawable.wordpress, R.drawable.web,
////            R.drawable.email, R.drawable.mobile, R.drawable.alram,
////            R.drawable.android, R.drawable.wordpress, R.drawable.web,
////            R.drawable.email, R.drawable.mobile, R.drawable.alram,
////            R.drawable.android, R.drawable.wordpress, R.drawable.web,
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_badges_display);
//
//        androidGridView = (GridView) findViewById(R.id.gridview_android_example);
//        androidGridView.setAdapter(new ImageAdapterGridView(this));
//
//        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent,
//                                    View v, int position, long id) {
//                Toast.makeText(getBaseContext(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    public class ImageAdapterGridView extends BaseAdapter {
//        private Context mContext;
//
//        public ImageAdapterGridView(Context c) {
//            mContext = c;
//        }
//
//        public int getCount() {
//            return imageIDs.length;
//        }
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView mImageView;
//
//            if (convertView == null) {
//                mImageView = new ImageView(mContext);
//                mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
//                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                mImageView.setPadding(16, 16, 16, 16);
//            } else {
//                mImageView = (ImageView) convertView;
//            }
//            mImageView.setImageResource(imageIDs[position]);
//            return mImageView;
//        }
//    }
//}


//package charstars.uscfit;
//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//
//public class BadgesDisplay extends AppCompatActivity {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_badges_display);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//}

package charstars.uscfit;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
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

import com.bumptech.glide.Glide;

public class BadgesDisplay extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BadgeAdapter adapter;
    private List<Badge> badgeList;
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
                    setContentView(R.layout.activity_mainbadgedisplay);
                    //Intent i = new Intent(GoalsDisplay.this, BadgesDisplay.class);
                    //startActivity(i);
                    navigation = findViewById(R.id.navigation2);
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
        setContentView(R.layout.activity_mainbadgedisplay);

        // show = (ListView)findViewById(R.id.badges_list_view);

        //BadgeDatabase.badgeCollection.add("Logged in Successfully");

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , goals);
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.badges_list_row, BadgeDatabase.badgeCollection);

        //show.setAdapter(adapter);

        recyclerView = (RecyclerView) findViewById(R.id.badgelayout);

        badgeList = new ArrayList<Badge>();
        adapter = new BadgeAdapter(this, badgeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareBadges();

        BottomNavigationView navigation = findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    public void prepareBadges(){
        this.badgeList.add(BadgeFactory.generateFifty("You ran 50 miles", new Date()));
        this.badgeList.add(BadgeFactory.generateHundred("You ran 100 miles", new Date()));
        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}



