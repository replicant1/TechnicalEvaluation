package tech.bailey.rod.scenario2;

/**
 * This interface must be implemented by any party wanting to present an IScenario2View. It
 * receives messages from a corresponding view whenever some significant user event occurs.
 * This presenter coordinates two parties - an IScenario2View and an IScenario2Model
 */
public interface IScenario2Presenter {

    /**
     * Invoked when a new "current destination" is selected by the user e.g. by clicking on
     * an item in a list.
     * @param destinationName Name of the destination just selected. This should uniquely identify
     *                        it amongst all destinations.
     */
    public void destinationNameSelected(String destinationName);

    /**
     * Invoked when the "Navigate" button at the bottom of the destinations card is pressed.
     */
    public void navigateButtonPressed();

    /**
     * Invoked when the "X" close button on the map card is pressed, indicating the user wants
     * to hide the map.
     */
    public void hideMapButtonPressed();

    /**
     * Invoked when an attempt to load destination data over the net has failed, and the user
     * has pressed the "Retry" button.
     */
    public void retryButtonPressed();

}
