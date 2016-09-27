package tech.bailey.rod;

import android.graphics.Color;
import android.support.test.espresso.action.ViewActions;
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
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests the row of coloured swatches at top of Scenario 1 tab.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Scenario1SwatchTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
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
        // Correct width of all swatches is 80dp
        onView(withId(R.id.scenario_1_swatch_1)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_2)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_3)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_4)).check(matches(CustomMatchers.withWidth(80)));
        onView(withId(R.id.scenario_1_swatch_5)).check(matches(CustomMatchers.withWidth(80)));
    }

    @Test
    public void clickOnSwatchDisplaysCorrectColorNameInRow4() {
        // Scroll until swatch 1 is in view
        onView(withId(R.id.scenario_1_swatch_1)).perform(ViewActions.scrollTo());

        // Swatch 1 = Medium Sea Green
        onView(withId(R.id.scenario_1_swatch_1)).perform(ViewActions.click());
        onView(withId(R.id.scenario_1_row_4)).check(matches(ViewMatchers.withText("Medium Sea Green")));

        // Scroll until swatch 2 is in view
        onView(withId(R.id.scenario_1_swatch_2)).perform(ViewActions.scrollTo());

        // Swatch 2 = Deep Sky Blue
        onView(withId(R.id.scenario_1_swatch_2)).perform(ViewActions.click());
        onView(withId(R.id.scenario_1_row_4)).check(matches(ViewMatchers.withText("Deep Sky Blue")));

        // Scroll until swatch 3 is in view
        onView(withId(R.id.scenario_1_swatch_3)).perform(ViewActions.scrollTo());

        // Swatch 3 = Light Goldenrod Yellow
        onView(withId(R.id.scenario_1_swatch_3)).perform(ViewActions.click());
        onView(withId(R.id.scenario_1_row_4)).check(matches(ViewMatchers.withText("Light Goldenrod Yellow")));

        // Scroll until swatch 4 is in view
        onView(withId(R.id.scenario_1_swatch_5)).perform(ViewActions.scrollTo());

        // Swatch 4 = Medium Orchid
        onView(withId(R.id.scenario_1_swatch_4)).perform(ViewActions.click());
        onView(withId(R.id.scenario_1_row_4)).check(matches(ViewMatchers.withText("Medium Orchid")));

        // Scroll until swatch 5 is in view
        onView(withId(R.id.scenario_1_swatch_3)).perform(ViewActions.scrollTo());

        // Swatch 5 = Dodger Blue
        onView(withId(R.id.scenario_1_swatch_5)).perform(ViewActions.click());
        onView(withId(R.id.scenario_1_row_4)).check(matches(ViewMatchers.withText("Dodger Blue")));
    }

    private static String colorIntToHex(int color) {
        return String.format("#%06X", 0xFFFFFF & color);
    }

    private static int colorHexToInt(String hexColor) {
        return Color.parseColor(hexColor);
    }
}
