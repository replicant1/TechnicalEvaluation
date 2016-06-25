package tech.bailey.rod.app;

import tech.bailey.rod.scenario1.IScenario1Model;
import tech.bailey.rod.scenario1.Scenario1Model;
import tech.bailey.rod.scenario2.IScenario2Model;
import tech.bailey.rod.scenario2.Scenario2Model;

/**
 * Aggregates all the state information held by the application. Across device orientation changes,
 * this information remains intact, and the presenters and views can be recreated from scratch and
 * then configure themselves using the information retained in this Singleton.
 */
public class AppDirectorSingleton {

    private static final String TAG = AppDirectorSingleton.class.getSimpleName();

    private final static AppDirectorSingleton singleton = new AppDirectorSingleton();

    /**
     * All model and view state info behind the "Scenario 1" tab
     */
    private final IScenario1Model scenario1Model = new Scenario1Model();

    /**
     * Alll model and view state info behind the "Scenario 2" tab
     */
    private final IScenario2Model scenario2Model = new Scenario2Model();

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
}
