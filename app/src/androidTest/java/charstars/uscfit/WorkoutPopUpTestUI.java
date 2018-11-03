package charstars.uscfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import static org.junit.Assert.*;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class WorkoutPopUpTestUI {

    @Rule
    public final ActivityRule<WorkoutPopUp> main = new ActivityRule<>(WorkoutPopUp.class);

    @Test
    public void invalidAddingOfWorkout() {
        onView(withId(R.id.workoutSpinner)).perform(ViewActions.click());
        onData(allOf(instanceOf(Activity.class))).atPosition(0).perform(ViewActions.click());
        onView(withId(R.id.workoutSpinner)).check(matches(withSpinnerText(containsString("Aerobics"))));
        onView(withId(R.id.lengthPicker)).perform(new ViewAction() {
            @Override
            public Matcher getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }

            @Override
            public String getDescription() {
                return "Set the value of a NumberPicker";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((NumberPicker)view).setValue(60);
            }
        });
        onView(withId(R.id.quantifierOption)).perform(ViewActions.click());
        onData(allOf(instanceOf(String.class))).atPosition(1).perform(ViewActions.click());
        onView(withId(R.id.quantifierOption)).check(matches(withSpinnerText(containsString("MINUTES"))));

        onView(withId(R.id.addWorkoutSubmit)).perform(ViewActions.click());
        onView(withText(R.string.workoutFailString)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

    }

    @Test
    public void validAddingOfWorkout() {
        onView(withId(R.id.workoutSpinner)).perform(ViewActions.click());
        onData(allOf(instanceOf(Activity.class))).atPosition(0).perform(ViewActions.click());
        onView(withId(R.id.workoutSpinner)).check(matches(withSpinnerText(containsString("Aerobics"))));
        onView(withId(R.id.lengthPicker)).perform(new ViewAction() {
            @Override
            public Matcher getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }

            @Override
            public String getDescription() {
                return "Set the value of a NumberPicker";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((NumberPicker)view).setValue(60);
            }
        });
        onView(withId(R.id.quantifierOption)).perform(ViewActions.click());
        onData(allOf(instanceOf(String.class))).atPosition(1).perform(ViewActions.click());
        onView(withId(R.id.quantifierOption)).check(matches(withSpinnerText(containsString("MINUTES"))));

        onView(withId(R.id.btn_date)).perform(ViewActions.click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 6, 30));
        onView(withText("OK")).perform(ViewActions.click());
        onView(withId(R.id.btn_time)).perform(ViewActions.click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(6, 30));
        onView(withText("OK")).perform(ViewActions.click());
        onView(withId(R.id.addWorkoutSubmit)).perform(ViewActions.click());
        assertTrue(main.get().isFinishing());
    }

}
