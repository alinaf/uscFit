package charstars.uscfit;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.v7.app.AppCompatActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
public class AddBadgesTest
{
    @Rule
   // public final ActivityRule<BadgeDatabase> main = new ActivityRule<>(BadgeDatabase.class);

    @Test
    public void seeMessageFromHomeScreen() {
        onView(withText("You've taken 10 steps today.")).check(ViewAssertions.matches(isDisplayed()));
    }
}