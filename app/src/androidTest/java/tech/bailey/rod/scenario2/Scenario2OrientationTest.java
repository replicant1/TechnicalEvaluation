package tech.bailey.rod.scenario2;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.app.MainActivity;

/**
 * Tests associated with the transition between device orientations while scenario 2 tab is shown.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario2OrientationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void orientationChangeNoMapPreservesBlueMountains() {
        Scenario2TestUtils.orientationChangeNoMapPreservesDestination(
                mActivityRule.getActivity(), Destination.BLUE_MOUNTAINS);
    }

    @Test
    public void orientationChangeNoMapPreservesTarongaZoo() {
        Scenario2TestUtils.orientationChangeNoMapPreservesDestination(
                mActivityRule.getActivity(), Destination.TARONGA_ZOO);
    }

    @Test
    public void orientationChangeNoMapPreservesBondiBeach() {
        Scenario2TestUtils.orientationChangeNoMapPreservesDestination(
                mActivityRule.getActivity(), Destination.BONDI_BEACH);
    }

    @Test
    public void orientationChangeWithMapPreservesBlueMountains() {
        Scenario2TestUtils.orientationChangeWithMapPreservesLocation(
                mActivityRule.getActivity(), Destination.BLUE_MOUNTAINS);
    }

    @Test
    public void orientationChangeWithMapPreservesTarongaZoo() {
        Scenario2TestUtils.orientationChangeWithMapPreservesLocation(
                mActivityRule.getActivity(), Destination.TARONGA_ZOO);
    }


    public void orientationChangeWithMapPreservesBondiBeach() {
        Scenario2TestUtils.orientationChangeWithMapPreservesLocation(
                mActivityRule.getActivity(), Destination.BONDI_BEACH);
    }

    @Before
    public void selectScenario2Tab() {
        Scenario2TestUtils.selectScenario2Tab();
    }

}
