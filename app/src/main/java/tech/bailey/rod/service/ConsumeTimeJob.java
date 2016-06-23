package tech.bailey.rod.service;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

/**
 * Created by rodbailey on 23/06/2016.
 */
public class ConsumeTimeJob extends AsyncTask<Void, Void, Void> {

    private static final String TAG = ConsumeTimeJob.class.getSimpleName();

    private final IJobSuccessHandler success;

    private final IJobFailureHandler failure;

    private final long millisToConsume;

    private final boolean succeed;

    public ConsumeTimeJob(IJobSuccessHandler success, IJobFailureHandler failure, long millisToConsume, boolean succeed) {
        this.success = success;
        this.failure = failure;
        this.millisToConsume = millisToConsume;
        this.succeed = succeed;
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "ConsumeTIieJob is beginning");
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
        Log.i(TAG, "ConsumeTimeJob has ended");

        if (succeed) {
            success.onJobSuccess(null);
        } else {
            failure.onJobFailure("Something broke!");
        }
    }
}
