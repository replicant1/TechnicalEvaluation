package tech.bailey.rod.scenario2;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.LinkedList;
import java.util.List;

import tech.bailey.rod.R;
import tech.bailey.rod.app.TechnicalEvaluationApplication;
import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.json.Destination;
import tech.bailey.rod.scenario2.event.DestinationListPropertyChangedEvent;
import tech.bailey.rod.scenario2.event.LoadingConditionPropertyChangedEvent;
import tech.bailey.rod.scenario2.event.MapVisibilityPropertyChangedEvent;
import tech.bailey.rod.scenario2.event.SelectedDestinationPropertyChangedEvent;

/**
 * Presentation layer for Scenario2View. This presenter subscribes to the following events:
 * <ul>
 * <li>DestinationsLoadFailureEvent</li>
 * <li>DestinationsLoadSuccessEvent</li>
 * <li>MapVisibilityPropertyChangedEvent</li>
 * <li>SelectedDestinationPropertyChangedEvent</li>
 * </ul>
 */
public class Scenario2Presenter implements IScenario2Presenter {

    private static final String TAG = Scenario2Presenter.class.getSimpleName();

    private final IScenario2View scenario2View;

    private final IScenario2Model scenario2Model;

    public Scenario2Presenter(@NonNull IScenario2View view, @NonNull IScenario2Model model) {
        this.scenario2View = view;
        this.scenario2Model = model;
        EventBusSingleton.getInstance().getBus().register(this);
    }

    @Override
    public void destinationNameSelected(String destinationName) {
        Log.i(TAG, "destinationNameSelected: " + destinationName);

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
    public void hideMapButtonPressed() {
        // Hide the map, even if it is already hidden
        scenario2Model.setMapIsShowing(false);
    }

    @Override
    public void navigateButtonPressed() {
        // Make the map show, even if it is already showing
        scenario2Model.setMapIsShowing(true);
    }

    @Subscribe
    public void onBusEvent(LoadingConditionPropertyChangedEvent event) {
        Log.i(TAG, hashCode() + " Into onBusEvent(LoadingCondPCE) event.cond=" + event.getLoadingCondition());

        switch (event.getLoadingCondition()) {
            case DESTINATIONS_LOADED_OK:
                scenario2View.hideProgressPanel();
                scenario2View.hideMap();
                scenario2View.showDestinationSelectionPanel();
                break;

            case DESTINATIONS_LOADING_FAILED:
                scenario2View.hideMap();
                scenario2View.hideDestinationSelectionPanel();
                scenario2View.showProgressPanel(
                        IScenario2View.ProgressPanelMode.MODE_FAILURE,
                        "Failed to load travel times.");
                break;

            case DESTINATIONS_LOADING_IN_PROGRESS:
                scenario2View.hideMap();
                scenario2View.hideDestinationSelectionPanel();
                scenario2View.showProgressPanel(
                        IScenario2View.ProgressPanelMode.MODE_INDETERMINATE_PROGRESS,
                        "Loading travel times...");
                break;
        }
    }

    @Subscribe
    public void onBusEvent(DestinationListPropertyChangedEvent event) {
        Log.i(TAG, hashCode() + " onBusEvent(DestinationListPCE) event.dest=" + event.getDestinations());

        if (event.getDestinations() != null) {
            List<String> names = new LinkedList<String>();
            for (Destination dest : event.getDestinations()) {
                names.add(dest.name);
            }
            scenario2View.setDestinationNames(names);
        }
    }

    @Subscribe
    public void onBusEvent(MapVisibilityPropertyChangedEvent event) {
        if (event.isMapVisible()) {
            scenario2View.showMap(event.getLatitude(), event.getLongitude());
        } else {
            scenario2View.hideMap();
        }
    }

    @Subscribe
    public void onBusEvent(SelectedDestinationPropertyChangedEvent event) {
        Log.i(TAG, hashCode() + " onBusEvent(SelectedDestinationPCE) event.selectedDestination=" + event.getSelectedDestination());
        Destination destination = event.getSelectedDestination();
        if (destination != null) {
            scenario2View.setSelectedDestinationName(destination.name);
        }
    }

    @Override
    public void retryButtonPressed() {
        scenario2View.showProgressPanel(
                IScenario2View.ProgressPanelMode.MODE_INDETERMINATE_PROGRESS,
                TechnicalEvaluationApplication.context.getString(
                        R.string.scenario_2_progress_message));
        scenario2Model.loadDestinationsAsync();
    }
}
