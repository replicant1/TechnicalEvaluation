package tech.bailey.rod.scenario1;

import android.support.annotation.Nullable;


public class Scenario1Model implements IScenario1Model {

    private NamedColor fillColor;

    private NamedColor swatchColor;

    @Override
    public void clearFillColor() {
        fillColor = null;
    }

    @Override
    public void clearSwatchColor() {
        swatchColor = null;
    }

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
