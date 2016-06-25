package tech.bailey.rod.scenario2;

import android.support.annotation.NonNull;

import java.util.List;

import tech.bailey.rod.json.Destination;
import tech.bailey.rod.scenario2.event.DestinationListPropertyChangedEvent;

/**
 * This interface must be implemented by any party wanting to serve as the model for some
 * associated IScenario2Presenter/IScenario2View pair. The model has the following properties:
 * <ul>
 * <li>Current list of possible destinations</li>
 * <li>Currently selected destination</li>
 * <li>Whether the map card is showing or not</li>
 * </ul>
 * Note that the "modes to transport" information that displays for the currently selected
 * destination is not modelled seperately. It can be deduced from the value of the currently
 * selected Destination. </p>
 * The enabled state of the navigate button is also not modelled here. To save effort, we do
 * that solely in the IScenario2View.
 */
public interface IScenario2Model {

    /**
     * @return List of Destinations that are available for selection. All destinations
     * will have unique names.
     */
    @NonNull
    public List<Destination> getDestinations();

    public LoadingCondition getLoadingCondition();

    /**
     * @return True if the Google map card is showing. If it is showing, it will be
     * displaying  a marker at the location of the currently selected destination. Map starts
     * life hidden and is only shown when user clicks "Navigate" button.
     */
    public boolean getMapIsShowing();

    /**
     * @return The currently selected destination.
     */
    public Destination getSelectedDestination();

    public void loadDestinationsAsync();

    /**
     * @param destinations The list of destinations for the user to choose from.
     */
    public void setDestinations(@NonNull List<Destination> destinations);

    /**
     * @param showing True if the card containing the map is currently visible
     */
    public void setMapIsShowing(boolean showing);

    /**
     * @param destinationName Name of the currently selected destination.
     */
    public void setSelectedDestinationByName(@NonNull String destinationName);

    public enum LoadingCondition {
        DESTINATIONS_LOADING_IN_PROGRESS, //
        DESTINATIONS_LOADED_OK, //
        DESTINATIONS_LOADING_FAILED;
    }


}
