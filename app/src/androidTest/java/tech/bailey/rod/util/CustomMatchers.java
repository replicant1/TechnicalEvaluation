package tech.bailey.rod.util;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

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

    public static Matcher<View> withTextViewGravity(final int gravity)  {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            public void describeTo(Description description) {
                description.appendText("gravity:" + gravity);
            }

            @Override
            protected boolean matchesSafely(TextView item) {
                return (item.getGravity() == gravity);
            }
        };
    }

}
