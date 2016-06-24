package tech.bailey.rod.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * An implementation of ITravelTimeService that is suitable for local testing. Returns dummy data
 * read from /assets/sample.json with a delay of a few seconds artificially created to simulate
 * network latency.
 */
public class FakeTravelTimeService implements ITravelTimeService {

    private static final String TAG = FakeTravelTimeService.class.getSimpleName();

    private static final String SAMPLE_JSON_ASSET = "sample.json";

    private static final long MILLIS_FAKE_DELAY = 5000;

    public static final boolean OPERATION_SUCCEEDS = false;

    private final Context context;

    public FakeTravelTimeService(Context context) {
        this.context = context;
    }

    @Override
    public void getTravelTimes(final IJobSuccessHandler<List<Destination>> successHandler,
                               IJobFailureHandler failureHandler) {

        FakeNetworkAccessJob job = new FakeNetworkAccessJob(

                new IJobSuccessHandler() {
                    @Override
                    public void onJobSuccess(Object result) {
                        BufferedReader reader = null;
                        StringBuffer buffer = new StringBuffer();

                        try {
                            reader = new BufferedReader(new InputStreamReader(
                                    context.getAssets().open(SAMPLE_JSON_ASSET)));

                            String line;
                            while ((line = reader.readLine()) != null) {
                                buffer.append(line);
                            }
                        } catch (IOException iox) {
                            Log.w(TAG, iox);
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    Log.w(TAG, e);
                                }
                            }
                        }

                        String jsonString = buffer.toString();

                        // Use Google's GSON library to parse the JSON
                        Gson gson = new Gson();
                        Destination[] destinationArray = gson.fromJson(
                                jsonString, new Destination[0].getClass());
                        List<Destination> destinationList = Arrays.asList(destinationArray);

                        successHandler.onJobSuccess(destinationList);
                    }
                }
                , failureHandler, MILLIS_FAKE_DELAY, OPERATION_SUCCEEDS);
        job.execute();
    }
}
