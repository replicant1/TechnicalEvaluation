package tech.bailey.rod.scenario2.event;

import android.support.annotation.NonNull;

/**
 * Event that is posted when an attempt to load a list of destinations over the network fails.
 */
public class DestinationsLoadFailureEvent {

    private final String failureReason;

    public DestinationsLoadFailureEvent(@NonNull  String failureReason) {
        this.failureReason = failureReason;
    }

    @NonNull
    public String getFailureReason() {
        return failureReason;
    }
}
