package tech.bailey.rod;

import android.support.annotation.NonNull;

/**
 * Created by rodbailey on 21/06/2016.
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
