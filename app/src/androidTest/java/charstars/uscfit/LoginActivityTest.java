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
public class LoginActivityTest {

    @Rule
    public final ActivityRule<LoginActivity> main = new ActivityRule<>(LoginActivity.class);

    @Test
    public void buttonIsEnabled() {
        onView(withId(R.id.email_sign_in_button)).check(matches(isEnabled()));
    }

    @Test
    public void buttonIsCompletelyDisplayed() {
        onView(withId(R.id.email_sign_in_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void buttonIsNotSelectable() {
        onView(withId(R.id.email_sign_in_button)).check(matches(not(isSelected())));
    }

    @Test
    public void buttonIsClickable() {
        onView(withId(R.id.email_sign_in_button)).check(matches(isClickable()));
    }
    @Test
    public void buttonWithText() {
        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Sign in or register")));
    }

}
