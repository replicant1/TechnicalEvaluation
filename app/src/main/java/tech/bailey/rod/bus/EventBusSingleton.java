package tech.bailey.rod.bus;

import com.squareup.otto.Bus;

/**
 * Created by rodbailey on 23/06/2016.
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
