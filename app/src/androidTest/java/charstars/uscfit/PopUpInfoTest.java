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

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
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
public class PopUpInfoTest {

    @Rule
    public final ActivityRule<PopUpInfo> main = new ActivityRule<>(PopUpInfo.class);

    @Test
    public void allBlankPersonalInfo() {
        onView(withId(R.id.input_height)).perform(typeText(""));
        onView(withId(R.id.input_age)).perform(typeText(""));
        onView(withId(R.id.input_weight)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.personal_info_submit)).perform(ViewActions.click());
        onView(withText(R.string.please_fill_out_all_fields)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void oneBlankPersonalInfo() {
        onView(withId(R.id.input_height)).perform(typeText("5"));
        onView(withId(R.id.input_age)).perform(typeText("4"));
        onView(withId(R.id.input_weight)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.personal_info_submit)).perform(ViewActions.click());
        onView(withText(R.string.please_fill_out_all_fields)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void validPersonalInfo() {
        onView(withId(R.id.input_height)).perform(typeText(String.valueOf("15")));
        onView(withId(R.id.input_age)).perform(typeText(String.valueOf("15")));
        onView(withId(R.id.input_weight)).perform(typeText(String.valueOf("15")));
        closeSoftKeyboard();
        onView(withId(R.id.personal_info_submit)).perform(ViewActions.click());
        assertTrue(main.get().isFinishing());
    }
}
