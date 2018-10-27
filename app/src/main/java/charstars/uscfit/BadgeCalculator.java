package charstars.uscfit;

import java.util.List;

public class BadgeCalculator
{
    public static boolean addBadge(Badge e, String email)
    {
        //BadgeDatabase.badgeCollection.add(e);
        return true;
    }
    public static boolean removeBadge(Badge e, String email)
    {
        //BadgeDatabase.badgeCollection.remove(e);
        return true;
    }

    //THIS CAN BE INVOKED BY OTHER CLASSES WHEN STEPS ARE COMPLETED OR AN ACTIVITY IS COMPLETED
    public static void calculateGoalProgress(Activity a, String email){
        //CLARIFY ON THIS


    }
    public static void alertOnCompletion(Badge e, String email){
        //notification that goal is completed
        // add badge somwhere
        //remove from list
        removeBadge(e, email);

    }
//    public static List<Badge> getBadges(String email){
//        //return BadgeDatabase.badgeCollection;
//    }

//    public static void editGoal(Goal goal) {
//        for(Goal g : SampleGoalDatabase.defaultGoals){
//            if(g.id() == goal.id()){
//                g.setDescription(goal.getDescription());
//                g.setGoalNum(goal.getGoalNum());
//                g.setTrackingNum(goal.getTrackingNum());
//                return;
//            }
//        }
//    }
}
