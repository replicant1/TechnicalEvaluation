package tech.bailey.rod.service;

/**
 * Created by rodbailey on 23/06/2016.
 */
public interface IJobSuccessHandler<T> {

    public void onJobSuccess(T result);
}
