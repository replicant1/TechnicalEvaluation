package tech.bailey.rod.app;

import tech.bailey.rod.scenario1.IScenario1Model;
import tech.bailey.rod.scenario2.IScenario2Model;

/**
 * Aggregates all the state information held by the application.
 */
public class MainModel {

    private final IScenario1Model scenario1Model;
    private final IScenario2Model scenario2Model;

    public MainModel(IScenario1Model scenario1Model, IScenario2Model scenario2Model) {
        this.scenario1Model = scenario1Model;
        this.scenario2Model = scenario2Model;
    }

    public IScenario1Model getScenario1Model() {
        return scenario1Model;
    }

    public IScenario2Model getScenario2Model() {
        return scenario2Model;
    }
}
