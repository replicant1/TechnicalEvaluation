package tech.bailey.rod.scenario2;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

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
public class Scenario2TabTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void scenario2TabIsNotSelected() {
        //"Scenario 1" tab is not selected
        onView(withText(R.string.scenario_1_tab_title)).check(matches(not(isSelected())));
    }

    @Test
    public void scenario2TabIsSelected() {
        // Main layout is displayed
        onView(ViewMatchers.withId(R.id.main_content)).check(matches(isDisplayed()));

        // Tab bar is displayed
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));

        // "Scenario 2" tab is selected
        onView(ViewMatchers.withText(R.string.scenario_2_tab_title)).check(matches(isSelected()));

        // Main layout for scenario 2 fragment is displayed
        onView(withId(R.id.scenario_2_rows)).check(matches(isDisplayed()));
    }

    @Before
    public void selectScenario2Tab() {
        // Click on "Scenario 2" tab
        onView(withText(R.string.scenario_2_tab_title)).perform(click());
    }
}
