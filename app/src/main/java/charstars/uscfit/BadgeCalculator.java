package charstars.uscfit;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

public class BadgeCalculator extends AppCompatActivity
{
    // adds badge to the database and shows a pop goal completed pop up message
    public static void addBadge(String b)
    {
        BadgeDatabase.badgeCollection.add(b);
//        Toast.makeText(BadgeCalculator.this, "Goal Completed! New Badge Added.",
//                Toast.LENGTH_SHORT).show();
    }

}
