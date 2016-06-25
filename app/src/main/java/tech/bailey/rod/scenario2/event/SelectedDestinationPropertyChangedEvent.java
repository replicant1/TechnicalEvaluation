package tech.bailey.rod.scenario2.event;

import tech.bailey.rod.json.Destination;

/**
 * Created by rodbailey on 25/06/2016.
 */
public class SelectedDestinationPropertyChangedEvent {

    private final Destination selectedDestination;

    public SelectedDestinationPropertyChangedEvent(Destination selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public Destination getSelectedDestination() {
        return selectedDestination;
    }
}
