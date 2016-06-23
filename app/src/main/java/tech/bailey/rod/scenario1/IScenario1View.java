package tech.bailey.rod.scenario1;

import android.support.annotation.NonNull;

/**
 * This interface must be implemented by any view of the "Scenario 1" data.
 * It contains those directives from an IScenario1Presenter to make changes to its view state.
 */
public interface IScenario1View {

    /**
     * Clears the background fill color of the bottom-most Card, resetting it to its default white.
     */
    public void clearFillColor();

    /**
     * Clears all text from the second-to-bottom Card
     */
    public void clearItemText();

    /**
     * Sets the fill color used for the background of the bottom-most Card
     *
     * @param colorString A parsable color string as described in android.graphics.Color.parseColor.
     *                    e.g. "#FA55D3", "red", "#FFAAB5C0"
     * @see android.graphics.Color#parseColor(String)
     */
    public void setFillColor(@NonNull String colorString);

    /**
     * Sets the the text that appears centered in the Card that is second-to-bottom
     *
     * @param text Any displayable text. Keep it short.
     */
    public void setItemText(@NonNull String text);
}
