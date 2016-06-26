package tech.bailey.rod.scenario2;

import android.support.annotation.NonNull;

/**
 * Pairs a ModeOfTransport with a String that describes a travel time, typically in minutes. The
 * two represent a particular mode of transport used for a period of time to travel to a destination
 * from cental.
 */
public class ModeTravelTime {
    private final ModeOfTransport mode;

    private final String travelTime;

    public ModeTravelTime(@NonNull ModeOfTransport mode, @NonNull String travelTime) {
        this.mode = mode;
        this.travelTime = travelTime;
    }

    @NonNull
    public ModeOfTransport getMode() {
        return mode;
    }

    @NonNull
    public String getTravelTime() {
        return travelTime;
    }
}
