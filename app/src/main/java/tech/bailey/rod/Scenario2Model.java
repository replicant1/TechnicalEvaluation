package tech.bailey.rod;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * Created by rodbailey on 21/06/2016.
 */
public class Scenario2Model implements IScenario2Model {

    private static final String TAG = Scenario2Model.class.getSimpleName();

    private List<Destination> destinations;

    private int selectedDestinationIndex;

    private boolean mapIsShowing;

    public Scenario2Model() {
        // Empty
    }

    @NonNull
    @Override
    public List<Destination> getDestinations() {
        return destinations;
    }

    @Override
    public boolean getMapIsShowing() {
        return mapIsShowing;
    }

    @Override
    public Destination getSelectedDestination() {
        Destination result = null;

        if ((destinations != null) && (selectedDestinationIndex >= 0) &&
                (selectedDestinationIndex < destinations.size())) {
            result = destinations.get(selectedDestinationIndex);
        }

        return result;
    }

    @Override
    public void setDestinations(@NonNull List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public void setMapIsShowing(boolean showing) {
        this.mapIsShowing = showing;
    }

    @Override
    public void setSelectedDestinationByName(@NonNull String destinationName) {
        // Find the index of the destination with the given name
        int index = -1;

        for (int i = 0; i < destinations.size(); i++) {
            Destination destination = destinations.get(i);

            if (destinationName.equals(destination.name)) {
                index = i;
                break;
            }
        }

        this.selectedDestinationIndex = index;
    }


}
