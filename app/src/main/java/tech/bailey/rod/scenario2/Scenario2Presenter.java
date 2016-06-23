package tech.bailey.rod.scenario2;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.LinkedList;
import java.util.List;

import tech.bailey.rod.bus.DestinationsUpdatedEvent;
import tech.bailey.rod.bus.EventBusSingleton;
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
        Log.i(TAG, "==== Registering Scenario2Presenter for events =====");
        EventBusSingleton.getInstance().getBus().register(this);
        Log.i(TAG, "====== Back from registering Scenario2Presenter ======");
    }

    @Override
    public void destinationNameSelected(String destinationName) {
        // Transmit newly selected destination to model
        scenario2Model.setSelectedDestinationByName(destinationName);

        // Tell the view to update the Travel Times list with info from the newly
        // selected destination. The model doesn't care about this.
        Destination selectedDestination = scenario2Model.getSelectedDestination();

        String carTime = selectedDestination.fromcentral.car;
        String trainTime = selectedDestination.fromcentral.train;

        List<ModeTravelTime> times = new LinkedList<ModeTravelTime>();

        if (carTime != null) {
            times.add(new ModeTravelTime(ModeOfTransport.CAR, carTime));
        }

        if (trainTime != null) {
            times.add(new ModeTravelTime(ModeOfTransport.TRAIN, trainTime));
        }

        scenario2View.setModeTravelTimes(times);

        if (scenario2Model.getMapIsShowing()) {
            float latitude = selectedDestination.location.latitude;
            float longitude = selectedDestination.location.longitude;
            scenario2View.showMap(latitude, longitude);
        }
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

    @Subscribe
    public void onBusEvent(DestinationsUpdatedEvent event) {
        Log.i(TAG, "==============================================");
        Log.i(TAG, "===== Into Scenario2Presenter.onBusEvent =====");
        Log.i(TAG, "==== event.getDestinations=" + event.getDestinations());
        if (event.getDestinations() != null) {
            Log.i(TAG, "==== #destinations=" + event.getDestinations().size());
        }

        Log.i(TAG, "==============================================");

        List<String> names = new LinkedList<String>();
        for (Destination destination : event.getDestinations())  {
            names.add(destination.name);
        }

        scenario2View.setDestinationNames(names);
    }
}
