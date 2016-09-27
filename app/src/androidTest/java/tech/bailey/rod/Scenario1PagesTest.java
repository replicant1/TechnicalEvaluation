package tech.bailey.rod;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import tech.bailey.rod.app.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;

/**
 * Tests the row of Scenario 1 tab that has 4 pages in a view pager.  Note that these
 * test methods are executed in a predefined order,which is why they are named "testXXX"
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Scenario1PagesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void test001FirstPageIsSelectedByDefault() {
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 1 of 4"))));
    }

    @Test
    public void test010SwipeLeftsRevealRemainingPages() {
        // Swipe left advances to Page 2
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 2 of 4"))));

        // Swipe left advances to Page 3
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 3 of 4"))));

        // Swipe left advances to Page 4
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 4 of 4"))));

    }

    @Test
    public void test020SwipesRightGoToPreviousPages() {
        // Swipe right retreats to Page 3
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 3 of 4"))));

        // Swipe right retreats to Page 2
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 2 of 4"))));

        // Swipe right retreats to Page 1
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.scenario_1_numbered_fragment_view_pager)).check(
                matches(hasDescendant(withText("Page 1 of 4"))));

    }

    @Test
    public void test030PageChangesAreReflectedInPageIndicator() {
        // I can find no way to test the page indicator. It doesn't use child
        // views to represent each circle, rather it is just a single view that
        // draws the circles within itself. Itdoesn't expse any properties that
        // might be tested.
        // TODO: Add a listener that changes the tag key of the indicator View
        // and then check value of tag key from Esporesson

    }

}
