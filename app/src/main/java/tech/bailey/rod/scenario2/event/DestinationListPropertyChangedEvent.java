package tech.bailey.rod.scenario2.event;

import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * Created by rodbailey on 25/06/2016.
 */
public class DestinationListPropertyChangedEvent {
    private final List<Destination> destinations;
    public DestinationListPropertyChangedEvent(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }
}
