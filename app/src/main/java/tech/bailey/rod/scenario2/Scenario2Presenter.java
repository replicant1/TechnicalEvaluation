package tech.bailey.rod.scenario2;

import android.support.annotation.NonNull;
import android.util.Log;

import tech.bailey.rod.json.Destination;

/**
 * Created by rodbailey on 21/06/2016.
 */
public class Scenario2Presenter implements IScenario2Presenter {

    private static final String TAG = Scenario2Presenter.class.getSimpleName();

    private final IScenario2View scenario2View;

    private final IScenario2Model scenario2Model;

    public Scenario2Presenter(@NonNull IScenario2View view, @NonNull IScenario2Model model) {
        this.scenario2View = view;
        this.scenario2Model = model;
    }

    @Override
    public void destinationNameSelected(String destinationName) {
        // Transmit newly selected destination to model
        scenario2Model.setSelectedDestinationByName(destinationName);

        // Note: Do not need to inform scenario2View of new selection
        // because the spinner will already have updated its own appearance at the
        // time of selection.
    }

    @Override
    public void navigateButtonPressed() {
        // Make the map show, even if it is already showing
        scenario2Model.setMapIsShowing(true);

        // Alert view that the model has changed
        Destination selectedDestination = scenario2Model.getSelectedDestination();
        if (selectedDestination != null) {
            float latitude = selectedDestination.location.latitude;
            float longitude = selectedDestination.location.longitude;
            scenario2View.showMap(latitude, longitude);
        }
        else {
            // This should never happen as the Navigate button should be disabled
            // whenever there is not a currently selected Destination.
            Log.e(TAG, "Code should be unreachable");
        }
    }

    @Override
    public void hideMapButtonPressed() {
        // Hide the map, even if it is already hidden
        scenario2Model.setMapIsShowing(false);

        // Alert the view that the model has changed
        scenario2View.hideMap();
    }
}
