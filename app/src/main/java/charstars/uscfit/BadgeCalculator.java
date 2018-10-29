package charstars.uscfit;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class BadgeCalculator extends AppCompatActivity
{
    // adds badge to the database and shows a pop goal completed pop up message
    public static void addBadge(String b, int quant, Date date)
    {

        Badge bb = null;
        if(quant>=1000){
            bb = BadgeFactory.generateThousand(b, date);

        }else if(quant>=500){
            bb = BadgeFactory.generateFiveHundred(b, date);

        }else if(quant>=100){
            bb = BadgeFactory.generateHundred(b, date);


        }else if(quant>=50){

            bb = BadgeFactory.generateFifty(b, date);


        }
        BadgeDB.getBadges().add(bb);
//        Toast.makeText(BadgeCalculator.this, "Goal Completed! New Badge Added.",
//                Toast.LENGTH_SHORT).show();
    }

    public static void removeBadge(Badge b){
        BadgeDB.getBadges().remove(b);
    }

    public static List<Badge> getBadges(){
        return BadgeDB.getBadges();
    }

}
