package tech.bailey.rod.scenario2.event;

import tech.bailey.rod.scenario2.IScenario2Model;

/**
 * Created by rodbailey on 25/06/2016.
 */
public class LoadingConditionPropertyChangedEvent {

    private IScenario2Model.LoadingCondition loadingCondition;

    public LoadingConditionPropertyChangedEvent(IScenario2Model.LoadingCondition loadingCondition) {
        this.loadingCondition = loadingCondition;
    }

    public IScenario2Model.LoadingCondition getLoadingCondition() {
        return loadingCondition;
    }
}
