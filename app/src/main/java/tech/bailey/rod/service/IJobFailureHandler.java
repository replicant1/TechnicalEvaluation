package tech.bailey.rod.service;

/**
 * Handles unsuccessful execution of an asynchronous network operation ('job')
 */
public interface IJobFailureHandler {

    /**
     * @param failureReason Displayable reason for the failure of the operation
     */
    public void onJobFailure(String failureReason);
}
