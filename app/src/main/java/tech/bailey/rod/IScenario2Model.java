package tech.bailey.rod;

import android.support.annotation.NonNull;

import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 *
 */
public interface IScenario2Model {

    @NonNull
    public List<Destination> getDestinations();

    public boolean getMapIsShowing();

    public Destination getSelectedDestination();

    public void setDestinations(@NonNull List<Destination> destinations);

    public void setMapIsShowing(boolean showing);

    public void setSelectedDestinationByName(@NonNull String destinationName);
}
