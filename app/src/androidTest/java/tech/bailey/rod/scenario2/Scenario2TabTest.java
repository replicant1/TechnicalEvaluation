package tech.bailey.rod.scenario2;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.base.UiControllerModule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;
import tech.bailey.rod.util.CustomActions;
import tech.bailey.rod.util.CustomMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Tests for "Scenario 2" tab
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario2TabTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void destinationCardIsDisplayedMapCardIsNot() {
        // After an async load of JSON data, the destination card appears,primed with the
        // data just loaded.
        onView(withId(R.id.scenario_2_destination_card)).check(matches(isDisplayed()));

        onView(withId(R.id.scenario_2_map_card)).check(matches(Matchers.not(isDisplayed())));
    }

    @Test
    public void expectedDestinationsAreInSpinner() {
        // Position 0 = "Blue Mountains"
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(
                R.id.scenario_2_destination_spinner)).atPosition(0).check(
                matches(withText("Blue Mountains")));

        // Position 1 = ""taronga zoo"
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(
                R.id.scenario_2_destination_spinner)).atPosition(1).check(
                matches(withText("taronga zoo")));

        // Position 2 = "Bondi Beach"
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(
                R.id.scenario_2_destination_spinner)).atPosition(2).check(
                matches(withText("Bondi Beach")));
    }

    /**
     * {
     "id": 1,
     "name": "Blue Mountains",
     "fromcentral": {
     "car": "80 Mins",
     "train": "120 Mins"
     },
     "location": {
     "latitude": -33.7181,
     "longitude": 150.3160
     }
     }
     */
    @Test
    public void blueMountainsDataIsDisplayedInTravelTimesList() {

        // First row of list has "80 Mins"
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("80 Mins")));

        // First row of list has car icon - how to test?
        // Second row of list has train icon - how to test?

        // Second row of list has "120 Mins"
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(1).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("120 Mins")));
    }

    @Before
    public void selectScenario2Tab() {
        // Click on "Scenario 2" tab
        onView(withText(R.string.scenario_2_tab_title)).perform(click());
    }

}
