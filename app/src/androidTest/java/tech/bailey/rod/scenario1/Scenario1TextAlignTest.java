package tech.bailey.rod.scenario1;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static tech.bailey.rod.util.CustomMatchers.withTextViewGravity;

/**
 * Tests for the panel in Scenario 1 that shows text with top, middle and bottom alignments vertically.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario1TextAlignTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @BeforeClass
    public void selectScenario1Tab() {
        // Click on "Scenario 1" tab
        onView(withText(R.string.scenario_1_tab_title)).perform(click());
    }

    @Test
    public void testTextAlignments() {
        // "Top" vertical alignment
        onView(ViewMatchers.withId(R.id.text_alignment_top)).check(matches(
                withTextViewGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP)));

        // "Center" vertical alignment
        onView(withId(R.id.text_alignment_center)).check(matches(
                withTextViewGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL)));

        // "Bottom" vertical alignment
        onView(withId(R.id.text_alignment_bottom)).check(matches(
                withTextViewGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM)));
    }

    @Test
    public void testTextLabels() {
        // "Top" text view
        onView(withId(R.id.text_alignment_top)).check(matches(withText("Top")));

        // "Center" text view
        onView(withId(R.id.text_alignment_center)).check(matches(withText("Center")));

        // "Bottom" text view
        onView(withId(R.id.text_alignment_bottom)).check(matches(withText("Bottom")));

    }


}
