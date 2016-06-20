package tech.bailey.rod;

import android.support.annotation.Nullable;

/**
 * This interface must be implemented by any party wanting to serve as the model for some
 * associated IScenario1Presenter.
 */
public interface IScenario1Model {

    /**
     * Clears the fill color, so that no fill color is selected and a call
     * to #getFillColor will return null.
     */
    public void clearFillColor();

    /**
     * Clears the currently selected swatch, so that no swatch is selected
     * and a call to #getSwatchColor will return null.
     */
    public void clearSwatchColor();

    /**
     * @return The current fill color, or null if there is none.
     */
    @Nullable
    public NamedColor getFillColor();

    /**
     * @return The current swatch color, or null if there is none.
     */
    @Nullable
    public NamedColor getSwatchColor();

    /**
     * Sets the current fill color.
     *
     * @param fillColor May be null, which is the same as calling #clearFillColor
     */
    public void setFillColor(@Nullable NamedColor fillColor);

    /**
     * Sets the current swatch color.
     *
     * @param swatchColor May be null, which is the same as calling #clearSwatchColor
     */
    public void setSwatchColor(@Nullable NamedColor swatchColor);
}
