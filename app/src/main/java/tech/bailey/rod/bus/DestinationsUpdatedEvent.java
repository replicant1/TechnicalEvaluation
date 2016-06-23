package tech.bailey.rod.bus;

import android.support.annotation.NonNull;

import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * Created by rodbailey on 23/06/2016.
 */
public class DestinationsUpdatedEvent {

    private final List<Destination> destinations;

    public DestinationsUpdatedEvent(@NonNull List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }
}
