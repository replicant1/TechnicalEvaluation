package tech.bailey.rod.scenario1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This interface must be implemented by any party wanting to serve as the model for some
 * associated IScenario1Presenter. The moddel has the following properties:
 * <ul>
 * <li> Currently selected fill color (if any)</li>
 * <li> Currently selected swatch color (if any)</li>
 * <li> Currently selected page number (there is always one)</li>
 * </ul>
 * Note that the text contents of the card that displays the current swatch color is not
 * modelled separately. It can be deduced from the value of the curren swatch color.
 */
public interface IScenario1Model {

    /**
     * @return The current fill color, or null if there is none.
     */
    @Nullable
    public NamedColor getFillColor();

    /**
     * @return 1-based page number of the currently viewed page. There is always exactly one page
     * being viewed.
     */
    @NonNull
    public int getPageNumber();

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
     * Sets the page currently being viewed
     *
     * @param page 1-based page number of currently selected page
     */
    public void setPageNumber(int page);

    /**
     * Sets the current swatch color.
     *
     * @param swatchColor May be null, which means 'no switch color'
     */
    public void setSwatchColor(@Nullable NamedColor swatchColor);
}
