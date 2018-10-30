package charstars.uscfit;
import android.os.IBinder;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.Root;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import androidx.test.espresso.matcher.ViewMatchers;

import static android.app.PendingIntent.getActivity;
import static android.service.autofill.Validators.not;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class StepsUITest
{
    @Rule
    public final ActivityRule<StepsDisplay> mActivityRule = new ActivityRule<>(StepsDisplay.class);

    @Test
    public void isStepsNumDisplayed() {
        onView(withId(R.id.tv_steps)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void navIsDisplayed() {
        onView(withId(R.id.navigation)).check(matches(isDisplayed()));
    }

    @Test
    public void navIsClickable() {
        onView(withId(R.id.navigation_addGoal)).check(matches(isClickable()));
    }

    @Test
    public void popUpAppears(){
        onView(withId(R.id.navigation_addGoal)).perform(click());
        onView(withId(R.id.ExerciseLabel_steps)).check(matches(isDisplayed()));
    }

    @Test
    public void submitStepsGoalIsClickable(){
        onView(withId(R.id.navigation_addGoal)).perform(click());
        onView(withId(R.id.addStepsGoal_button)).check(matches(isClickable()));
    }

    @Test
    public void stepsGoalSubmission(){
        onView(withId(R.id.navigation_addGoal)).perform(click());
        onView(withId(R.id.numSteps_input)).perform(typeText("500"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addStepsGoal_button)).perform(click());
        onView(withText("Successfully saved goal!")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

}

