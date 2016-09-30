package tech.bailey.rod.scenario2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkBlueMountainsDataIsDisplayed;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkBondiBeachDataIsDisplayed;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkModeTravelTimeData;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.checkTarongaZooDataIsDisplayed;
import static tech.bailey.rod.scenario2.Scenario2TestUtils.selectDestination;

/**
 * Tests associated with the transition between device orientations while scenario 2 tab is shown.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario2OrientationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void selectScenario2Tab() {
        // Click on "Scenario 2" tab
        Scenario2TestUtils.selectScenario2Tab();
    }

    @Test
    public void rotatePortraitToLandscapeToPortraitNoMapPreservesBlueMountainsSelection() {
        // Ensure we start in Portrait orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Select the "Scenario 2" tab
        Scenario2TestUtils.selectScenario2Tab();

        // Default selection is blue mountains
        selectDestination("Blue Mountains");
        checkBlueMountainsDataIsDisplayed();

        // ***** Change screen orientation to landscape ****
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Check "Scenario 2" tab is still selected. "Blue Mountains" TT data is dstill isplayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        checkBlueMountainsDataIsDisplayed();

        // ***** Change screen orientation back to portrait
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Check "Scenario 2" tab is still selected. "Blue Mountains" TT data is dstill isplayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        checkBlueMountainsDataIsDisplayed();
    }

    @Test
    public void rotatePortraitToLandscapeToPortraitNoMapPreservesTarongaZooSelection() {
        // Ensure we start in Portrait orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Select the "Scenario 2" tab
        Scenario2TestUtils.selectScenario2Tab();

        // Default selection is blue mountains,so explicitly select Taronga Zoo
        selectDestination("taronga zoo");
        checkTarongaZooDataIsDisplayed();

        // ***** Change screen orientation to landscape ****
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Check "Scenario 2" tab is still selected. "Taronga Zoo" TT data is dstill isplayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        checkTarongaZooDataIsDisplayed();

        // ***** Change screen orientation back to portrait
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Check "Scenario 2" tab is still selected. "Taronga Zoo" TT data is dstill isplayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        checkTarongaZooDataIsDisplayed();
    }

    @Test
    public void rotatePortraitToLandscapeToPortraitNoMapPreservesBondiBeachSelection() {
        // Ensure we start in Portrait orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Select the "Scenario 2" tab
        Scenario2TestUtils.selectScenario2Tab();

        // Default selection is blue mountains,so explicitly select Taronga Zoo
        selectDestination("Bondi Beach");
        checkBondiBeachDataIsDisplayed();

        // ***** Change screen orientation to landscape ****
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Check "Scenario 2" tab is still selected. "Taronga Zoo" TT data is dstill isplayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        checkBondiBeachDataIsDisplayed();

        // ***** Change screen orientation back to portrait
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Check "Scenario 2" tab is still selected. "Taronga Zoo" TT data is dstill isplayed.
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));
        checkBondiBeachDataIsDisplayed();
    }




}
