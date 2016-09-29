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

/**
 * Created by rodbailey on 29/09/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario2DestinationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    private void clickNavigateButton() {
        onView(withId(R.id.scenario_2_button_navigate)).perform(ViewActions.click());
    }

    private void closeMapCard() {
        onView(withId(R.id.scenario_2_button_close_map_card)).perform(ViewActions.click());
    }

    // @Test
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

    // @Test
    public void navigateToBlueMountainsShowsMapCard() {
        selectDestination("Blue Mountains");
        clickNavigateButton();

        // Card and map are displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
        onView(withId(R.id.scenario_2_support_map_fragment)).check(matches(isDisplayed()));

        onView(ViewMatchers.withParent(withId(R.id.scenario_2_support_map_fragment))).check(matches(isDisplayed()));


        SupportMapFragment mapFragment = (SupportMapFragment) mActivityRule.getActivity().getSupportFragmentManager().
                findFragmentById(R.id.scenario_2_support_map_fragment);

        Log.i("tag", "mapFragment=" + mapFragment); // Always comes out "null". So how can I see the latlng? Put in tag key/val?

//        mapFragment.getView()
        // Map is centered on (-33.7181, 150.3160)
//
//        // NOTE: I don't see any way to do this without using the deprecated method getMap()
//        GoogleMap map =mapFragment.getMap();
//
//        LatLng target = map.getCameraPosition().target;
//        Assert.assertEquals(-33.7181, target.latitude, 0.01F);
//        Assert.assertEquals(150.3160, target.longitude, 0.01F);

        closeMapCard();
    }

    @Test
    public void navigateToBondiBeachShowsCorrectlyConfiguredMapCard() {
        selectDestination("Bondi Beach");
        clickNavigateButton();

        // Card and map are displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
        onView(withId(R.id.scenario_2_support_map_fragment)).check(matches(isDisplayed()));

        closeMapCard();
    }

    @Test
    public void navigateToTarongaZooShowsCorrectlyConfiguredMapCard() {
        selectDestination("taronga zoo");
        clickNavigateButton();

        // Card and map are displayed
        onView(withId(R.id.scenario_2_map_card)).check(matches(isDisplayed()));
        onView(withId(R.id.scenario_2_support_map_fragment)).check(matches(isDisplayed()));

        closeMapCard();
    }

    @Test
    public void selectBlueMountainsDisplaysCorrectTravelTimesList() {
        selectDestination("Blue Mountains");

        // First row of list has "80 Mins"
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("80 Mins")));

        // First row of list has car icon
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_icon),
                CustomMatchers.withModeOfTransport(ModeOfTransport.CAR)));

        // Second row of list has "120 Mins"
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(1).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("120 Mins")));

        // Second row of list has train icon
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(1).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_icon),
                CustomMatchers.withModeOfTransport(ModeOfTransport.TRAIN)));
    }

    @Test
    public void selectBondiBeachDisplaysCorrectTravelTime() {
        selectDestination("Bondi Beach");

        // Row 1: Car 20 Mins
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_icon),
                CustomMatchers.withModeOfTransport(ModeOfTransport.CAR)));

        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("20 Mins")));

        //  Row 2: Train 40 Mins
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(1).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_icon),
                CustomMatchers.withModeOfTransport(ModeOfTransport.TRAIN)));

        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(1).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("40 Mins")));
    }

    private void selectDestination(String destination) {
        // Click on destinations spinner to raise the drop-down list
        onView(withId(R.id.scenario_2_destination_spinner)).perform(click());

        // Select "Blue Mountains" from the drop-down list
        onData(allOf(is(instanceOf(String.class)), is(destination))).perform(click());
    }

    @Before
    public void selectScenario2Tab() {
        // Click on "Scenario 2" tab
        onView(withText(R.string.scenario_2_tab_title)).perform(click());
    }

    @Test
    public void selectTarongaZooDisplaysCorrectTravelTime() {
        selectDestination("taronga zoo");

        // First row of list has ModeOfTransport car
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_icon),
                CustomMatchers.withModeOfTransport(ModeOfTransport.CAR)));

        // First row of list has travel time "30 Mins"
        onData(allOf(is(instanceOf(ModeTravelTime.class)))).inAdapterView(withId(
                R.id.scenario_2_travel_times_list)).atPosition(0).check(ViewAssertions.selectedDescendantsMatch(
                ViewMatchers.withId(R.id.mode_of_transport_travel_time),
                ViewMatchers.withText("30 Mins")));
    }

}
