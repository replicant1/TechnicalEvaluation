package tech.bailey.rod;

import android.support.annotation.NonNull;

import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 *
 */
public interface IScenario2Model {

    public void setSelectedDestination(@NonNull Destination destination);

    @NonNull
    public Destination getSelectedDestination();

    @NonNull
    public List<Destination> getDestinations();
}
