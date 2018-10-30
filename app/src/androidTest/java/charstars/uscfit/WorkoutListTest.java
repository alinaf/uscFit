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
public class WorkoutListTest {

    @Rule
    public final ActivityRule<WorkoutList> main = new ActivityRule<>(WorkoutList.class);

    @Test
    public void isEditButtonClickable() {
        onView(withId(R.id.add_workout_button)).check(matches(isClickable()));
        Intents.init();
        onView(withId(R.id.add_workout_button)).perform(android.support.test.espresso.action.ViewActions.click());
        intended(hasComponent(WorkoutPopUp.class.getName()));
    }



}
