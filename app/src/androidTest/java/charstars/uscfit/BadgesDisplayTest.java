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
public class BadgesDisplayTest
{
    @Rule
    public final ActivityRule<BadgesDisplay> main = new ActivityRule<>(BadgesDisplay.class);

    @Test
    public void seeMessageFromHomeScreen() {
        //onView(withText("Successfully created account")).check(ViewAssertions.matches(isDisplayed()));
    }
}