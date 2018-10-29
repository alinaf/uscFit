package charstars.uscfit;

import java.util.ArrayList;
import java.util.List;

public class BadgeDB {

    private static List<Badge> badges = new ArrayList<Badge>();

    public static List<Badge> getBadges() {
        return badges;
    }

    public static void setBadges(List<Badge> badges) {
        BadgeDB.badges = badges;
    }
}
