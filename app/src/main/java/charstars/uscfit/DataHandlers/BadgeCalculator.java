package charstars.uscfit.DataHandlers;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import charstars.uscfit.RootObjects.Badge;
import charstars.uscfit.DatabaseHandlers.BadgeDatabaseManager;
import charstars.uscfit.BadgeFactory;

public class BadgeCalculator extends AppCompatActivity
{
    static BadgeDatabaseManager bm = BadgeDatabaseManager.getInstance();
    //takes in a name/description, quanitity of goal completed, and date of completion
    //generates correct badge and adds to database
    public static void addBadge(String b, int quant, Date date)
    {

        Log.d(("Adding Badge"), b+" "+quant+" "+date);

        Badge bb = null;
        if(quant>=500){
            bb = BadgeFactory.generateFiveHundred(b, date);

        }else if(quant>=100){
            bb = BadgeFactory.generateHundred(b, date);


        }else {

            bb = BadgeFactory.generateFifty(b, date);


        }
        BadgeDatabaseManager.getInstance().addBadge(bb);
//        Toast.makeText(BadgeCalculator.this, "Goal Completed! New Badge Added.",
//                Toast.LENGTH_SHORT).show();
    }

    public static void removeBadge(Badge b){
        bm.removeBadge(b);
    }

    public static ArrayList<Badge> getBadges(){
        if(bm==null){
           initDB();
        }
        return bm.getBadges();
    }

    public static void initDB(){
         bm = BadgeDatabaseManager.getInstance();
    }

}
