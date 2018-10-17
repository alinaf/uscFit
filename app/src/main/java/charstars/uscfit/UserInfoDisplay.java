package charstars.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class UserInfoDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
//                email = null;
            } else {
//                email = extras.getString("EMAIL");
            }
        } else {
//            email = (String) savedInstanceState.getSerializable("EMAIL");
        }

        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserInfo userInfo = new UserInfo(); // make singleton
        final TextView mTextView = (TextView) findViewById(R.id.name_display);
        mTextView.setText("Hello " + userInfo.getFirstName());

        TextView mTextView2 = (TextView) findViewById(R.id.age_display);
        mTextView2.setText(String.valueOf(userInfo.getAge()) + " years");

        final TextView mTextView3 = (TextView) findViewById(R.id.weight_display);
        mTextView3.setText("Weight: " + String.valueOf(Double.toString(userInfo.getWeight()) + " lbs"));

        final TextView mTextView4 = (TextView) findViewById(R.id.height_display);
        int feet = (int)(userInfo.getHeight() / 12);
        int inches = (int)(userInfo.getHeight() - feet);
        mTextView4.setText(String.valueOf("Height: " + feet + "'" + inches));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit_user_info);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(UserInfoDisplay.this, PopUpInfo.class));
            }
        });
    }

}
