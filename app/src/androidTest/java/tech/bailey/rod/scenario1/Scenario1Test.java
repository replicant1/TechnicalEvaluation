package tech.bailey.rod.scenario1;

import android.graphics.Color;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
 * Tests the Scenario 1 tab (not contents thereof)
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario1Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @BeforeClass
    public void selectScenario1Tab() {
        // Click on "Scenario 1" tab
        onView(withText(R.string.scenario_1_tab_title)).perform(click());
    }

    @Test
    public void scenario1TabIsSelected() {
        // Main layout is displayed
        onView(ViewMatchers.withId(R.id.main_content)).check(matches(isDisplayed()));

        // Tab bar is displayed
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));

        // "Scenario 1" tab is selected
        onView(ViewMatchers.withText(R.string.scenario_1_tab_title)).check(matches(isSelected()));

        // Main layout for scenario 1 fragment is displayed
        onView(withId(R.id.scenario_1_rows)).check(matches(isDisplayed()));
    }

    @Test
    public void scenario2TabIsNotSelected() {
        //"Scenario 2" tab is not selected
        onView(withText(R.string.scenario_2_tab_title)).check(matches(not(isSelected())));
    }


}
