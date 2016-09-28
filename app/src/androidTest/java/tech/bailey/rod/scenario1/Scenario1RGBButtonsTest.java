package tech.bailey.rod.scenario1;

import android.graphics.Color;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;
import tech.bailey.rod.util.CustomMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests for the panel in Scenario 1 that shows three buttons labelled "Red", "Green" and "Blue"
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario1RGBButtonsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void selectScenario1Tab() {
        // Click on "Scenario 1" tab
        onView(withText(R.string.scenario_1_tab_title)).perform(click());
    }

    @Test
    public void testBlueButtonChangesContainerColorToBlue() {
        // Click "Blue" button
        onView(withId(R.id.scenario_1_button_blue)).perform(click());

        // Test that background of row changes to Green
        onView(withId(R.id.scenario_1_row_5)).check(matches(
                CustomMatchers.withBackgroundColor(Color.BLUE)));
    }

    @Test
    public void testGreenButtonChangesContainerColorToGreen() {
        // Click "Green" button
        onView(withId(R.id.scenario_1_button_green)).perform(click());

        // Test that background of row changes to Green
        onView(withId(R.id.scenario_1_row_5)).check(matches(
                CustomMatchers.withBackgroundColor(Color.GREEN)));
    }

    @Test
    public void testRedButtonChangesContainerColorToRed() {
        // Click "Red" button
        onView(withId(R.id.scenario_1_button_red)).perform(click());

        // Test that background of row changes to Red
        onView(withId(R.id.scenario_1_row_5)).check(matches(
                CustomMatchers.withBackgroundColor(Color.RED)));

    }
}
