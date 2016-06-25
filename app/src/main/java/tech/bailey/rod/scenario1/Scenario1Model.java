package tech.bailey.rod.scenario1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Contains all the view state info about the Scenario 1 tab
 */
public class Scenario1Model implements IScenario1Model {

    private NamedColor fillColor;

    private int pageNumber;

    private NamedColor swatchColor;

    @Nullable
    @Override
    public NamedColor getFillColor() {
        return fillColor;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
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

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public void setSwatchColor(@Nullable NamedColor swatchColor) {
        this.swatchColor = swatchColor;
    }
}
