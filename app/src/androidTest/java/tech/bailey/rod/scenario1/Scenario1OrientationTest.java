package tech.bailey.rod.scenario1;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;
import tech.bailey.rod.util.CustomMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests associated with the transition between device orientations while scenario 1 tab is shown.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario1OrientationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void rotatePortraitToLandscapeToPortraitPreservesSelections() {
        // Ensure we start in Portrait orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Select the "Scenario 1" tab
        onView(withText(R.string.scenario_1_tab_title)).perform(click());

        // Click on swatch 2 (Deep Sky Blue)
        onView(withId(R.id.scenario_1_swatch_2)).perform(ViewActions.scrollTo());
        onView(withId(R.id.scenario_1_swatch_2)).perform(ViewActions.click());

        // Click on "red" button
        onView(withId(R.id.scenario_1_button_red)).perform(click());

        checkForExpectedStates();

        // ***** Change screen orientation to Landscape *****
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        checkForExpectedStates();

        // ***** Change screen orientation back to Portrait ****
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        checkForExpectedStates();
    }

    private void checkForExpectedStates() {
        // Check that "Scenario 1" tab is still selected
        onView(ViewMatchers.withText(R.string.scenario_1_tab_title)).check(matches(isSelected()));

        // Check that "Deep Sky Blue" is still being displayed in row 4
        onView(withId(R.id.scenario_1_row_4)).check(matches(ViewMatchers.withText("Deep Sky Blue")));

        // Check that background of row 5 is still red
        onView(withId(R.id.scenario_1_row_5)).check(matches(
                CustomMatchers.withBackgroundColor(Color.RED)));
    }
}
