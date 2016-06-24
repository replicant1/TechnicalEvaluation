package tech.bailey.rod.bus;

import android.support.annotation.NonNull;

import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * Event that is posted when a list of destinations has been successfully loaded from the remote
 * source.
 */
public class DestinationsLoadSuccessEvent {

    private final List<Destination> destinations;

    public DestinationsLoadSuccessEvent(@NonNull List<Destination> destinations) {
        this.destinations = destinations;
    }

    @NonNull
    public List<Destination> getDestinations() {
        return destinations;
    }
}
