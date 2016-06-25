package tech.bailey.rod.scenario1.event;

/**
 * Event that is posted by an IScenario1Model whenever it's "pageNumber" property
 * changes value. This lets subscribers (probably instances of IScenario1Presenter) to
 * communicate this change to their associated IScenario1View's.
 */
public class PageNumberPropertyChangedEvent {

    private final int pageNumber;

    public PageNumberPropertyChangedEvent(int newPageNumber) {
        this.pageNumber = newPageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
