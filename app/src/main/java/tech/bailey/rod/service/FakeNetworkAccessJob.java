package tech.bailey.rod.service;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Asynchronous job that simulates the latency involved with some network operation.
 */
public class FakeNetworkAccessJob extends AsyncTask<Void, Void, Void> {

    private static final String TAG = FakeNetworkAccessJob.class.getSimpleName();

    private final IJobSuccessHandler success;

    private final IJobFailureHandler failure;

    private final long millisToConsume;

    private final boolean succeed;

    /**
     * Constructs a FakeNetworkAccessJob for simulating network access with latency.
     *
     * @param success         Handler is called with null result if 'succeed' is true
     * @param failure         Handler that is called with an error string if 'succeed' is false
     * @param millisToConsume Amount of fake network latency in milliseconds
     * @param succeed         True if we are simulating a successful network operation.
     */
    public FakeNetworkAccessJob(@NonNull IJobSuccessHandler success, @NonNull IJobFailureHandler failure,
                                long millisToConsume, boolean succeed) {
        this.success = success;
        this.failure = failure;
        this.millisToConsume = millisToConsume;
        this.succeed = succeed;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(millisToConsume, 0);
        } catch (InterruptedException iex) {
            Log.w(TAG, iex);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.i(TAG, "FakeNetworkAccessJob has ended");

        if (succeed) {
            success.onJobSuccess(null);
        } else {
            failure.onJobFailure("Failed to access network.");
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "ConsumeTimeJob is beginning");
    }
}
