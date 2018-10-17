package charstars.uscfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PopUpInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        int age = Integer.parseInt(((TextView)findViewById(R.id.input_age)).getText().toString());
        double height = Double.parseDouble(((TextView)findViewById(R.id.input_height)).getText().toString());
        double weight = Double.parseDouble(((TextView)findViewById(R.id.input_weight)).getText().toString());

        UserInfo userInfo = new UserInfo(); // make singleton
        userInfo.setAge(age);
        userInfo.setHeight(height);
        userInfo.setWeight(weight);

        Intent i = new Intent(PopUpInfo.this, UserInfoDisplay.class);
        startActivity(i);
    }
}
