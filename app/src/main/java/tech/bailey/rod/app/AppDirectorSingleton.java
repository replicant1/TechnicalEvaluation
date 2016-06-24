package tech.bailey.rod.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import tech.bailey.rod.bus.DestinationsLoadFailureEvent;
import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.json.Destination;
import tech.bailey.rod.scenario1.IScenario1Model;
import tech.bailey.rod.scenario1.Scenario1Model;
import tech.bailey.rod.scenario2.IScenario2Model;
import tech.bailey.rod.scenario2.Scenario2Model;
import tech.bailey.rod.service.FakeTravelTimeService;
import tech.bailey.rod.service.IJobFailureHandler;
import tech.bailey.rod.service.IJobSuccessHandler;
import tech.bailey.rod.service.ITravelTimeService;
import tech.bailey.rod.service.TravelTimeService;
import tech.bailey.rod.util.ConfigSingleton;

/**
 * Aggregates all the state information held by the application. Across device orientation changes,
 * this information remains intact, and the presenters and views can be recreated from scratch and
 * then configure themselves using the information retained in this Singleton.
 */
public class AppDirectorSingleton {

    private static final String TAG = AppDirectorSingleton.class.getSimpleName();

    private final static AppDirectorSingleton singleton = new AppDirectorSingleton();

    private final IScenario1Model scenario1Model = new Scenario1Model();

    private final IScenario2Model scenario2Model = new Scenario2Model();

    private ITravelTimeService travelTimeService;

    private final boolean USE_REAL_TRAVEL_TIME_SERVICE =
            !ConfigSingleton.getInstance().UseFakeTravelTimeService();

    private AppDirectorSingleton() {
        // Empty
    }

    public static AppDirectorSingleton getInstance() {
        return singleton;
    }

    public IScenario1Model getScenario1Model() {
        return scenario1Model;
    }

    public IScenario2Model getScenario2Model() {
        return scenario2Model;
    }

    public void loadTravelTimeData() {
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
    private static class GetTravelTimesSuccessHandler implements IJobSuccessHandler<List<Destination>> {
        @Override
        public void onJobSuccess(List<Destination> result) {
            AppDirectorSingleton.getInstance().getScenario2Model().setDestinations(result);
        }
    }

    private static class GetTravelTimesFailureHandler implements IJobFailureHandler {

        public GetTravelTimesFailureHandler() {
            EventBusSingleton.getInstance().getBus().register(this);
        }

        @Override
        public void onJobFailure(String failureReason) {
            EventBusSingleton.getInstance().getBus().post(new DestinationsLoadFailureEvent(failureReason));
        }
    }
}
