package tech.bailey.rod.scenario1;

import android.support.annotation.Nullable;

/**
 * Contains all the view tate info about the Scenario 1 tab
 */
public class Scenario1Model implements IScenario1Model {

    private NamedColor fillColor;

    private NamedColor swatchColor;

    @Nullable
    @Override
    public NamedColor getFillColor() {
        return fillColor;
    }

    @Nullable
    @Override
    public NamedColor getSwatchColor() {
        return swatchColor;
    }

    @Override
    public void setFillColor(@Nullable NamedColor fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public void setSwatchColor(@Nullable NamedColor swatchColor) {
        this.swatchColor = swatchColor;
    }
}
