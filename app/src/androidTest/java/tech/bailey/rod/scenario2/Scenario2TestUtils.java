package tech.bailey.rod.scenario2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Before;

import tech.bailey.rod.R;
import tech.bailey.rod.util.CustomMatchers;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
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

    public static void selectDestination(Destination destination) {
        // Click on destinations spinner to raise the drop-down list
        onView(withId(R.id.scenario_2_destination_spinner)).perform(click());

        // Select destination by text from the drop-down list
        onData(allOf(is(instanceOf(String.class)), is(destination.getJsonName()))).perform(click());
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

    public static void checkTravelTimeDataIsDisplayed(Destination destination) {
        switch(destination) {
            case BLUE_MOUNTAINS:
                checkBlueMountainsDataIsDisplayed();
                break;

            case BONDI_BEACH:
                checkBondiBeachDataIsDisplayed();
                break;

            case TARONGA_ZOO:
                checkTarongaZooDataIsDisplayed();
                break;

        }
    }

    public static void orientationChangeNoMapPreservesDestination(Activity activity, Destination destination) {
        // Ensure we start in Portrait orientation
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Select the "Scenario 2" tab
        Scenario2TestUtils.selectScenario2Tab();

        // Select the destination
        selectDestination(destination);
        Scenario2TestUtils.checkTravelTimeDataIsDisplayed(destination);

        // ***** Change screen orientation to landscape ****
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Check "Scenario 2" tab is still selected. "Blue Mountains" TT data is still displayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        Scenario2TestUtils.checkTravelTimeDataIsDisplayed(destination);

        // ***** Change screen orientation back to portrait
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Check "Scenario 2" tab is still selected. "destination" TT data is still displayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));

        checkTravelTimeDataIsDisplayed(destination);
    }

    // Default selection after data is loaded will be for "Blue Mountains":
    // "Car: 80 Mins", "Train: 120 Mins".
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
        checkModeTravelTimeData(1, new ModeTravelTime(ModeOfTransport.TRAIN,"40 Mins"));
    }

    public static void orientationChangeWithMapPreservesLocation(Activity activity, Destination destination) {
        // Esnure we start in Portrait orientation
        // Select the "Scenario 2" tab
        // Select the destination, then click navigate
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Scenario2TestUtils.selectScenario2Tab();
        Scenario2TestUtils.selectDestination(destination);
        onView(withId(R.id.scenario_2_button_navigate)).perform(click());

        // The map card with be displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));

        // **** Change screen orientation to landsape ****
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Map card should *still** be displayed.
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));

        // **** Change orientation back to Portrait ****
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Map card should *still** be displayed.
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
    }
}
