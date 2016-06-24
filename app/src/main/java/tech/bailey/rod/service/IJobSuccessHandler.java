package tech.bailey.rod.service;

/**
 * Handles successful execution of an asynchronous network operation ('job').
 */
public interface IJobSuccessHandler<T> {

    /**
     * @param result The result of the network operation e.g. the contents of a remote
     *               JSON file as a string.
     */
    public void onJobSuccess(T result);
}
