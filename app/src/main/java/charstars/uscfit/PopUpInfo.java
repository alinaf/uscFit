package charstars.uscfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class PopUpInfo extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_up_info);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        try {
            int age = Integer.parseInt(((TextView)findViewById(R.id.input_age)).getText().toString());
            double height = Double.parseDouble(((TextView)findViewById(R.id.input_height)).getText().toString());
            double weight = Double.parseDouble(((TextView)findViewById(R.id.input_weight)).getText().toString());
            UserInfo userInfo = new UserInfo(); // make singleton
            userInfo.setAge(age);
            userInfo.setHeight(height);
            userInfo.setWeight(weight);

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            String username = currentUser.getEmail().replace('.',' ');
            DatabaseReference myRef = database.getReference(username);

            myRef.setValue(age);

            finish();
        }
        catch (NumberFormatException nfe) {

            LayoutInflater inflater = getLayoutInflater();
            View layout;

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);

            layout = inflater.inflate(R.layout.goalfail,null);
            toast.setView(layout);
            toast.show();

            return;
        }
    }
}
