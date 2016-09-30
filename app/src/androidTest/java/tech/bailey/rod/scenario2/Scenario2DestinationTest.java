package tech.bailey.rod.scenario2;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;
import tech.bailey.rod.util.CustomMatchers;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkBlueMountainsDataIsDisplayed;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkBondiBeachDataIsDisplayed;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkModeTravelTimeData;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkTarongaZooDataIsDisplayed;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.clickNavigateButton;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.closeMapCard;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.selectDestination;

/**
 * Tests for "Scenario 2" tab. Note that app must be configured so as to be reading data from /assets/sample.json
 * before the tests below will pass.
 * <p>
 * The following data must be in sample.json:
 * <pre>
 * [{
 *      "id": 1,
 *      "name": "Blue Mountains",
 *      "fromcentral": {
 *      "car": "80 Mins",
 *      "train": "120 Mins"
 * },
 *      "location": {
 *          "latitude": -33.7181,
 *          "longitude": 150.3160
 * }
 * }, {
 *      "id": 2,
 *      "name": "taronga zoo",
 *      "fromcentral": {
 *      "car": "30 Mins"
 * },
 *      "location": {
 *          "latitude": -33.8433,
 *          "longitude": 151.2411
 * }
 * }, {
 *      "id": 3,
 *      "name": "Bondi Beach",
 *      "fromcentral": {
 *      "car": "20 Mins",
 *      "train": "40 Mins"
 * },
 *      "location": {
 *          "latitude": -33.8910,
 *          "longitude": 151.27777
 * }
 * }]
 * </pre>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario2DestinationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    // @Test
    public void expectedDestinationsAreInSpinner() {
        // Position 0 = "Blue Mountains"
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(
                R.id.scenario_2_destination_spinner)).atPosition(0).check(
                matches(withText(Destination.BLUE_MOUNTAINS.getJsonName())));

        // Position 1 = ""taronga zoo"
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(
                R.id.scenario_2_destination_spinner)).atPosition(1).check(
                matches(withText(Destination.TARONGA_ZOO.getJsonName())));

        // Position 2 = "Bondi Beach"
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(
                R.id.scenario_2_destination_spinner)).atPosition(2).check(
                matches(withText(Destination.BONDI_BEACH.getJsonName())));
    }

    // @Test
    public void navigateToBlueMountainsShowsMapCard() {
        selectDestination(Destination.BLUE_MOUNTAINS);
        clickNavigateButton();

        // Card and map are displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
        onView(withId(R.id.scenario_2_support_map_fragment)).check(matches(isDisplayed()));

        closeMapCard();
    }

    @Test
    public void navigateToBondiBeachShowsCorrectlyConfiguredMapCard() {
        selectDestination(Destination.BONDI_BEACH);
        clickNavigateButton();

        // Card and map are displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
        onView(withId(R.id.scenario_2_support_map_fragment)).check(matches(isDisplayed()));

        closeMapCard();
    }

    @Test
    public void navigateToTarongaZooShowsCorrectlyConfiguredMapCard() {
        selectDestination(Destination.TARONGA_ZOO);
        clickNavigateButton();

        // Card and map are displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
        onView(withId(R.id.scenario_2_support_map_fragment)).check(matches(isDisplayed()));

        closeMapCard();
    }

    @Test
    public void selectBlueMountainsDisplaysCorrectTravelTimesList() {
        selectDestination(Destination.BLUE_MOUNTAINS);
        checkBlueMountainsDataIsDisplayed();
    }

    @Test
    public void selectBondiBeachDisplaysCorrectTravelTime() {
        selectDestination(Destination.BONDI_BEACH);
        checkBondiBeachDataIsDisplayed();
    }

    @Before
    public void selectScenario2Tab() {
        Scenario2TestUtils.selectScenario2Tab();
    }

    @Test
    public void selectTarongaZooDisplaysCorrectTravelTime() {
        selectDestination(Destination.TARONGA_ZOO);
        checkTarongaZooDataIsDisplayed();
    }

}
