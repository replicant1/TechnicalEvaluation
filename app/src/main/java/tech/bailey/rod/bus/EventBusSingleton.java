package tech.bailey.rod.bus;

import com.squareup.otto.Bus;

/**
 * Wraps the singleton instance of the Otto event bus. All events in the app must be posted to this
 * bus. All event posters and subscribers must register with this bus.
 */
public class EventBusSingleton {

    private final Bus bus = new Bus();

    private static final EventBusSingleton singleton = new EventBusSingleton();

    private EventBusSingleton() {
        // Empty
    }

    public static EventBusSingleton getInstance() {
        return singleton;
    }

    public Bus getBus() {
        return bus;
    }

}
