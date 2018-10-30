package charstars.uscfit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import charstars.uscfit.DataHandlers.BadgeCalculator;
import charstars.uscfit.RootObjects.Badge;

import static org.junit.Assert.*;

public class BadgeTest {

//    @Test
//    public void badgeCalculator() // checks whether new badge is being added to database
//    {
//        BadgeCalculator.addBadge("new badge");
//        Assert.assertTrue(BadgeDatabase.badgeCollection.contains("new badge"));
//    }

    @Test
    public void trophyTest() {
        Badge b = new Badge(5, "Running", "01/01/18");
        b.setmTrophyNum(10);
        Assert.assertEquals(10, b.getmTrophyNum());
    }

    @Test
    public void dateTest()
    {
        Badge b = new Badge(5, "Running", "01/01/18");
        b.setmDate("10/10/2018");
        Assert.assertEquals("10/10/2018", b.getmDate());
    }

    @Test
    public void nameTest()
    {
        Badge b = new Badge(5, "Running", "01/01/18");
        b.setmName("new name");
        Assert.assertEquals("new name", b.getmName());
    }

}