package tech.bailey.rod.scenario1;

import android.support.annotation.Nullable;

/**
 * This interface must be implemented by any party wanting to serve as the model for some
 * associated IScenario1Presenter.
 */
public interface IScenario1Model {

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
     * @param fillColor May be null, which means 'no fill colour'
     */
    public void setFillColor(@Nullable NamedColor fillColor);

    /**
     * Sets the current swatch color.
     *
     * @param swatchColor May be null, which means 'no switch color'
     */
    public void setSwatchColor(@Nullable NamedColor swatchColor);
}
