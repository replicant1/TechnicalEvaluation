package tech.bailey.rod.scenario2;

import com.squareup.otto.Subscribe;

import tech.bailey.rod.bus.DestinationsUpdatedEvent;

/**
 * This interface must be implemented by any party wanting to present an IScenario2View
 */
public interface IScenario2Presenter {

    public void destinationNameSelected(String destinationName);

    public void navigateButtonPressed();

    public void hideMapButtonPressed();

    public void retryButtonPressed();

}
