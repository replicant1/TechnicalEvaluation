package tech.bailey.rod;

import android.support.annotation.NonNull;

/**
 * This interface must be implemented by any party wanting to present an IScenario1View
 */
public interface IScenario1Presenter {


    /**
     * Invoked when one of the buttons for setting the background fill color of the bottom-most
     * card in the corresponding IScenarioView is presssed.
     *
     * @param buttonColor The NamedColor associated with the button that was pressed
     */
    public void fillColorButtonPressed(@NonNull NamedColor buttonColor);

    /**
     * Invoked when one of the swatches presented in the top card of the corresponding
     * IScenario1View is pressed.
     *
     * @param swatchColor The NamedColor associated with the swatch that was pressed
     */
    public void swatchPressed(@NonNull NamedColor swatchColor);

}
