package tech.bailey.rod.app;

import tech.bailey.rod.scenario1.IScenario1Model;
import tech.bailey.rod.scenario1.Scenario1Model;
import tech.bailey.rod.scenario2.IScenario2Model;
import tech.bailey.rod.scenario2.Scenario2Model;

/**
 * Aggregates all the state information held by the application.
 */
public class MainModel {

    private final static MainModel singleton = new MainModel();

    private final IScenario1Model scenario1Model = new Scenario1Model();
    private final IScenario2Model scenario2Model = new Scenario2Model();

    private MainModel() {
        // Empty
    }

    public static MainModel getInstance() {
        return singleton;
    }

    public IScenario1Model getScenario1Model() {
        return scenario1Model;
    }

    public IScenario2Model getScenario2Model() {
        return scenario2Model;
    }
}
