package tech.bailey.rod;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * This interface must be implemented by any view of the "Scenario 2" data.
 * <p/>
 * Properties:
 * <ul>
 * <li>List ot destination names</li>
 * <li>Index of the currently selected destination name. -1 means there is no selection.</li>
 * <li>List of {icon,text} pairs that describe the travel details from central to the currently
 * selected destination. May be empty.</li>
 * <li>Lat/Lng of the destination on a Google Map. Null means no map is showing and the
 * button reads "Navigate". Non-null means that the Google Map is showing with the given
 * lat/lng marked, and the button is hidden.</li>
 * </ul>
 */
public interface IScenario2View {

    public class ModeTravelTime {
        private final ModeOfTransport mode;

        private final String travelTime;

        private ModeTravelTime(@NonNull ModeOfTransport mode, @NonNull String travelTime) {
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

    public void setDestinationNames(@NonNull List<String> destinationNames);

    public void setSelectedDestinationName(String destinationName);

    public void setModeTravelTimes(List<ModeTravelTime> modeTravelTimes);

    public void showMap(float latitude, float longitude);

    public void hideMap();
}
