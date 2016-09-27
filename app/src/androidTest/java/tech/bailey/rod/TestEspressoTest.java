package tech.bailey.rod;

import android.graphics.Color;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.bailey.rod.app.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

/**
 * Created by rodbailey on 26/09/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    private static String colorIntToHex(int color) {
        return String.format("#%06X", 0xFFFFFF & color);
    }

    private static int colorHexToInt(String hexColor) {
        return Color.parseColor(hexColor);
    }

    @Test
    public void scenario1TabIsSelected() {
        // Main layout is displayed
        onView(withId(R.id.main_content)).check(matches(isDisplayed()));

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

    @Test
    public void swatchesAreOfCorrectColors() {
        // Swatch 1 is "Medium Sea Green" = #3CB371
        onView(withId(R.id.scenario_1_swatch_1)).check(matches(
                CustomMatchers.withBackgroundColor(colorHexToInt("#3CB371"))));

        // Swatch 2 is "Deep Sky Blue" = #00BFFF
        onView(withId(R.id.scenario_1_swatch_2)).check(matches(
                CustomMatchers.withBackgroundColor(colorHexToInt("#00BFFF"))));

        // Swatch 3 is "Light Goldenrod Yellow = #FAFAD2
        onView(withId(R.id.scenario_1_swatch_3)).check(matches(
                CustomMatchers.withBackgroundColor(colorHexToInt("#FAFAD2"))));

        // Swatch 4 is "Medium Orchid" = #BA55D3
        onView(withId(R.id.scenario_1_swatch_4)).check(matches(
                CustomMatchers.withBackgroundColor(colorHexToInt("#BA55D3"))));

        // Swatch 5 is "Dodger Blue" = #1E90FF
        onView(withId(R.id.scenario_1_swatch_5)).check(matches(
                CustomMatchers.withBackgroundColor(colorHexToInt("#1E90FF"))));
    }

    @Test
    public void swatchesAreOfCorrectWidth() {
        onView(withId(R.id.scenario_1_swatch_1)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_2)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_3)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_4)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_5)).check(matches(CustomMatchers.withWidth(80)));
    }
}
