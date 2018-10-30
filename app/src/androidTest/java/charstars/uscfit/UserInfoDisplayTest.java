package charstars.uscfit;

import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


@RunWith(AndroidJUnit4.class)
public class UserInfoDisplayTest {

    @Rule
    public final ActivityRule<UserInfoDisplay> main = new ActivityRule<>(UserInfoDisplay.class);

    @Test
    public void doesButtonLaunchComponent() {
        onView(withId(R.id.edit_user_info)).check(matches(isClickable()));
        Intents.init();
        onView(withId(R.id.edit_user_info)).perform(android.support.test.espresso.action.ViewActions.click());
        intended(hasComponent(PopUpInfo.class.getName()));
    }



}
