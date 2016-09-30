package tech.bailey.rod.scenario2;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Before;

import tech.bailey.rod.R;
import tech.bailey.rod.util.CustomMatchers;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Utility methods for Espresso tests of Scenario 2 tab.
 */
public class Scenario2TestUtils {

    public static void clickNavigateButton() {
        onView(withId(R.id.scenario_2_button_navigate)).perform(ViewActions.click());
    }

    public static void closeMapCard() {
        onView(withId(R.id.scenario_2_button_close_map_card)).perform(ViewActions.click());
    }

    public static void selectScenario2Tab() {
        onView(withText(R.string.scenario_2_tab_title)).perform(click());
    }

    public static void selectDestination(String destination) {
        // Click on destinations spinner to raise the drop-down list
        onView(withId(R.id.scenario_2_destination_spinner)).perform(click());

        // Select "Blue Mountains" from the drop-down list
        onData(allOf(is(instanceOf(String.class)), is(destination))).perform(click());
    }

    public static void checkModeTravelTimeData(int row, ModeTravelTime mtt) {
        // Check travel time figure in minutes
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(row).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText(mtt.getTravelTime())));

        // Check travel mode
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(row).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_icon),
                CustomMatchers.withModeOfTransport(mtt.getMode())));
    }

    // Default selection after data is loaded will be for "Blue Mountains":
    // "Car: 80 Mins"
    // "Train: 120 Mins".
    public static void checkBlueMountainsDataIsDisplayed() {
        Scenario2TestUtils.checkModeTravelTimeData(0, new ModeTravelTime(ModeOfTransport.CAR, "80 Mins"));
        Scenario2TestUtils.checkModeTravelTimeData(1, new ModeTravelTime(ModeOfTransport.TRAIN,"120 Mins"));
    }

    // Row 1: Car 30 Mins
    public static void checkTarongaZooDataIsDisplayed() {
        checkModeTravelTimeData(0, new ModeTravelTime(ModeOfTransport.CAR, "30 Mins"));
    }

    // Car: 20 Mins, Train: 40 Mins
    public static void checkBondiBeachDataIsDisplayed() {
        checkModeTravelTimeData(0, new ModeTravelTime(ModeOfTransport.CAR, "20 Mins"));

        //  Row 2: Train 40 Mins
        checkModeTravelTimeData(1, new ModeTravelTime(ModeOfTransport.TRAIN,"40 Mins"));
    }
}
