package tech.bailey.rod;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matchers need particularly for the TechnicalEvaluation tests but not provided by Espresso or Hamcrest
 */
public abstract class CustomMatchers {

    public static Matcher<View> withBackgroundColor(final int color) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("has background color:" + color);
            }

            @Override
            protected boolean matchesSafely(View item) {
                int actualBackgroundColor = ((ColorDrawable) item.getBackground()).getColor();
                return color == actualBackgroundColor;
            }
        };
    }

    public static Matcher<View> withSize(final int width, final int height) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("width:" + width + ",height:" + height);
            }

            @Override
            protected boolean matchesSafely(View item) {
                return ((item.getWidth() == width) && (item.getHeight() == height));
            }
        };
    }

    public static Matcher<View> withWidth(final int widthDP) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("width:" + widthDP);
            }

            @Override
            protected boolean matchesSafely(View item) {
                System.out.println("width="+ item.getWidth());
                float density = item.getContext().getResources().getDisplayMetrics().density;
                return (widthDP * density == item.getWidth());
            }
        };
    }

}
