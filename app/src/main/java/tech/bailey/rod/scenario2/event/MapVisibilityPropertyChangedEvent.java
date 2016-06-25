package tech.bailey.rod.scenario2.event;

/**
 * Event that is posted by an IScenario2Model whenever it's "map visible" property changes
 * value. This lets subscribers (probably instances of IScenario2Presenter) to communicate
 * this change to their associated IScenario2View's.
 */
public class MapVisibilityPropertyChangedEvent {

    private float latitude;

    private float longitude;

    private boolean mapVisible;

    public MapVisibilityPropertyChangedEvent(boolean mapVisible, float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.mapVisible = mapVisible;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public boolean isMapVisible() {
        return mapVisible;
    }
}
