package charstars.uscfit;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.assertion.ViewAssertions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public final ActivityRule<GoalsDisplay> main = new ActivityRule<>(GoalsDisplay.class);

    @Test
    public void goalNavigation()
    {
        //onView(withId(R.id.email_sign_in_button)).check(matches(isEnabled()));
        onView(withId(R.id.navigation_goals)).check(matches(isDisplayed()));
    }

    @Test
    public void badgeNavigation()
    {
        //onView(withId(R.id.email_sign_in_button)).check(matches(isEnabled()));
        onView(withId(R.id.navigation_badges)).check(matches(isDisplayed()));
    }

    @Test
    public void addGoalsNavigation()
    {
        //onView(withId(R.id.email_sign_in_button)).check(matches(isEnabled()));
        onView(withId(R.id.navigation_addGoal)).check(matches(isDisplayed()));
    }

    @Test
    public void goalButtonSelected()
    {
        onView(withId(R.id.navigation_goals)).check(matches(isSelected()));
    }
}
