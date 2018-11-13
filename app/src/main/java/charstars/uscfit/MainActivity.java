package charstars.uscfit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import charstars.uscfit.DatabaseHandlers.BadgeDatabaseManager;
import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserInfo userInfo;
    private String email;
    private final String[] messages = {
            "Way to go",
            "You're doing great",
            "It's going to be a good day",
            "You got this",
            "Stellar job",
            "Stay smiling",
            "Great work",
            "You're killing it",
            "Excellent work",
            "Keep grinding",
            "Amazing effort",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        Log.d("MyApp",email +" IS IN THE MAIN PAGE");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // update image, name, email programatically

        // set up
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        final ImageView profilePic = (ImageView)hView.findViewById(R.id.profilePic);
        final Uri imageUri;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        StorageReference mStorage = FirebaseStorage.getInstance().getReference();
        StorageReference filePath = mStorage.child("ProfilePics").child(currentUser.getUid()).child("profilePic");
        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if (uri != null) {
                    Picasso.get().load(uri).into(profilePic);
                }
            }
        });

        userInfo = new UserInfo(false);
        TextView name = (TextView) hView.findViewById(R.id.full_name);
        name.setText(userInfo.getFirstName());

        TextView email = (TextView) hView.findViewById(R.id.email);
        email.setText(userInfo.getEmail());

        TextView day = (TextView)findViewById(R.id.dayatglance);
        TextView steps = (TextView)findViewById(R.id.steps);
        TextView calories = (TextView)findViewById(R.id.calories);
        TextView minute = (TextView)findViewById(R.id.minutes);
        TextView happy = (TextView)findViewById(R.id.happymessage);

        day.setText("Your day at a glance");
        steps.setText("You've taken "+10+" steps today.");
        calories.setText("You've burned "+90+" calories.");
        minute.setText("You've exercised for "+10+" minutes.");
        if (userInfo.getFirstName().equals("")){
            happy.setText(messages[(int)Math.random()*messages.length]+ "!");
        }
        else {
            happy.setText(messages[(int)Math.random()*messages.length]+ ", " + userInfo.getFirstName());
        }

        BadgeDatabaseManager bm = BadgeDatabaseManager.getInstance();

        GoalDatabaseManager gm = GoalDatabaseManager.getInstance();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        TextView happy = (TextView)findViewById(R.id.happymessage);
        if (userInfo.getFirstName().equals("")){
            happy.setText(messages[(int)Math.random()*messages.length]+ "!");
        }
        else {
            happy.setText(messages[(int)Math.random()*messages.length]+ ", " + userInfo.getFirstName());
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        userInfo = new UserInfo(false);
        TextView name = (TextView) hView.findViewById(R.id.full_name);
        name.setText(userInfo.getFirstName());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle goalnavigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_badges) {
            // go to badge page
            Intent i = new Intent(MainActivity.this, GoalsDisplay.class);
            i.putExtra("EMAIL", "Tianqin");
            startActivity(i);
        } else if (id == R.id.nav_personalInfo) {
            // go to user info page
            Intent i = new Intent(MainActivity.this, UserInfoDisplay.class);
            i.putExtra("EMAIL", "Tianqin");
            startActivity(i);
        } else if (id == R.id.nav_workoutList) {
            Intent i = new Intent(MainActivity.this, WorkoutList.class);
            i.putExtra("EMAIL", "Tianqin");
            startActivity(i);
        }
        else if (id == R.id.nav_steps) {
            Intent i = new Intent(MainActivity.this,StepsDisplay.class);
            i.putExtra("EMAIL", "Tianqin");
            startActivity(i);
        } else if (id == R.id.nav_view) {

        }

        DrawerLayout  drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}