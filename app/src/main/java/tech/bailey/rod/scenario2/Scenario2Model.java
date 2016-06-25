package tech.bailey.rod.scenario2;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.otto.Produce;

import java.util.List;

import tech.bailey.rod.app.TechnicalEvaluationApplication;
import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.json.Destination;
import tech.bailey.rod.scenario2.event.DestinationListPropertyChangedEvent;
import tech.bailey.rod.scenario2.event.LoadingConditionPropertyChangedEvent;
import tech.bailey.rod.scenario2.event.MapVisibilityPropertyChangedEvent;
import tech.bailey.rod.scenario2.event.SelectedDestinationPropertyChangedEvent;
import tech.bailey.rod.service.FakeTravelTimeService;
import tech.bailey.rod.service.IJobFailureHandler;
import tech.bailey.rod.service.IJobSuccessHandler;
import tech.bailey.rod.service.ITravelTimeService;
import tech.bailey.rod.service.TravelTimeService;
import tech.bailey.rod.util.ConfigSingleton;

/**
 * Contains all the view state info about the Scenario 2 tab.
 * This model broadcasts the following events:
 * <ul>
 * <li>DestinationListPropertyChangedEvent</li>
 * <li>MapVisibilityPropertyChangedEvent</li>
 * <li>SelectedDestinationPropertyChangedEvent</li>
 * </ul>
 */
public class Scenario2Model implements IScenario2Model {

    private static final String TAG = Scenario2Model.class.getSimpleName();

    private final boolean USE_REAL_TRAVEL_TIME_SERVICE =
            !ConfigSingleton.getInstance().UseFakeTravelTimeService();


    private List<Destination> destinations;

    private boolean mapIsShowing;

    private Destination selectedDestination;

    private LoadingCondition loadingCondition = LoadingCondition.DESTINATIONS_LOADING_IN_PROGRESS;

    /**
     * Facade to server supplying remote data
     */
    private ITravelTimeService travelTimeService;

    public Scenario2Model() {
        EventBusSingleton.getInstance().getBus().register(this);
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
        return selectedDestination;
    }

    @Produce
    public LoadingConditionPropertyChangedEvent produceLoadingConditionPropertyChangedEvent() {
        Log.i(TAG, "Producing LoadingConditionPCD with cond=" + loadingCondition);
        return new LoadingConditionPropertyChangedEvent(loadingCondition);
    }

    @Produce
    public DestinationListPropertyChangedEvent produceDestinationListPropertyChangedEvent() {
        Log.i(TAG, "Producing DestinationListPropertyChangedEvent with destinations=" + destinations);
        return new DestinationListPropertyChangedEvent(destinations);
    }

    @Produce
    public MapVisibilityPropertyChangedEvent produceMapVisibilityPropertyChangedEvent() {
        float latitude = 0F;
        float longitude = 0F;

        if (selectedDestination != null) {
            latitude = selectedDestination.location.latitude;
            longitude = selectedDestination.location.longitude;
        }

        Log.i(TAG, "Producing MapVisibilityPCEvent with " + mapIsShowing +
                ",latitide=" + latitude + ",longitude=" + longitude);
        return new MapVisibilityPropertyChangedEvent(mapIsShowing, latitude, longitude);
    }

    @Produce
    public SelectedDestinationPropertyChangedEvent produceSelectedDestinationPropertyChangedEvent() {
        Log.i(TAG, "Producing SelectedDestinationPCEvent for selectedDestination " + selectedDestination);
        return new SelectedDestinationPropertyChangedEvent(selectedDestination);
    }

    @Override
    public void setDestinations(List<Destination> destinations) {
        Log.i(TAG, "Into setDestinations with destinations=" + destinations);
        this.destinations = destinations;
        EventBusSingleton.getInstance().getBus().post(
                new DestinationListPropertyChangedEvent(destinations));
    }

    @Override
    public void setMapIsShowing(boolean showing) {
        this.mapIsShowing = showing;
        float latitude = 0F;
        float longitude = 0F;
        if (selectedDestination != null) {
            latitude = selectedDestination.location.latitude;
            longitude = selectedDestination.location.longitude;
        }
        EventBusSingleton.getInstance().getBus().post(
                new MapVisibilityPropertyChangedEvent(showing, latitude, longitude));
    }

    @Override
    public void setSelectedDestinationByName(@NonNull String destinationName) {
        Log.i(TAG, "Into setSelectedDestinationByName with name " + destinationName);

        // Find the index of the destination with the given name
        Destination foundDestination = null;

        for (int i = 0; i < destinations.size(); i++) {
            Destination destination = destinations.get(i);

            if (destinationName.equals(destination.name)) {
                foundDestination = destination;
                break;
            }
        }

        this.selectedDestination = foundDestination;

        EventBusSingleton.getInstance().getBus().post(
                new SelectedDestinationPropertyChangedEvent(foundDestination));
    }

    @Override
    public LoadingCondition getLoadingCondition() {
        return null;
    }

    private void setLoadingCondition(LoadingCondition cond) {
        this.loadingCondition = cond;
        EventBusSingleton.getInstance().getBus().post(new LoadingConditionPropertyChangedEvent(loadingCondition));
    }

    /**
     * Triggers the asynchronous loading of data from a remote server. This method is invoked when
     * the "scenario 2" tab is selected for the first time.
     */
    public void loadDestinationsAsync() {
        setLoadingCondition(LoadingCondition.DESTINATIONS_LOADING_IN_PROGRESS);

        if (travelTimeService == null) {
            if (USE_REAL_TRAVEL_TIME_SERVICE) {
                travelTimeService = new TravelTimeService(TechnicalEvaluationApplication.context);
            } else {
                travelTimeService = new FakeTravelTimeService(TechnicalEvaluationApplication.context);
            }
        }

        travelTimeService.getTravelTimes(
                new GetTravelTimesSuccessHandler(),
                new GetTravelTimesFailureHandler()
        );
    }

    /**
     * Listens for successful loading of Destinations data and, when it arrives, feeds it to the
     * central object that stores app-wide state data.
     */
    private class GetTravelTimesSuccessHandler implements IJobSuccessHandler<List<Destination>> {
        @Override
        public void onJobSuccess(List<Destination> result) {
            Log.i(TAG, "GetTravelTimesSuccessHandler.onJobSuccess. result=" + result);
            setLoadingCondition(LoadingCondition.DESTINATIONS_LOADED_OK);
            setDestinations(result);
        }
    }

    /**
     * Listens for unsuccessful loading of Destinations data. If this occurs, fire an event that
     * will eventually result in a failure message being displayed in the Scenario 2 tab.
     */
    private class GetTravelTimesFailureHandler implements IJobFailureHandler {

        @Override
        public void onJobFailure(String failureReason) {
            Log.i(TAG, "GetTravelTimesFailureHandler.onJobFailure. faillureReason=" + failureReason);
            setLoadingCondition(LoadingCondition.DESTINATIONS_LOADING_FAILED);
        }
    }

}
