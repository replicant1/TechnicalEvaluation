package tech.bailey.rod.service;

import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * All access to remote data occurs via this interface. There are two implementations of it
 * provided - one for testing and one for production. The "test" implementation reads JSON from
 * assets/sample.json. The "production" implementation reads the JSON from a remote web server.
 */
public interface ITravelTimeService {

    /**
     * Kicks off asynchronous loading of travel times data. Callbacks occur in the main thread.
     *
     * @param successHandler Once loaded and parsed OK, the data is passed back to this party
     * @param failureHandler If data can't be loaded or parsed, for any reason, this party is
     *                       called with a displayable string indicating the reason for failure.
     */
    public void getTravelTimes(IJobSuccessHandler<List<Destination>> successHandler,
                               IJobFailureHandler failureHandler);
}
