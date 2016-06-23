package tech.bailey.rod.tech.bailey.rod.service;

import android.content.Context;
import android.util.Log;

import com.android.internal.util.Predicate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * An implementation of ITravelTimeService that is suitable for local testing. Returns dummy data.
 */
public class FakeTravelTimeService implements ITravelTimeService {
    private static final String TAG = FakeTravelTimeService.class.getSimpleName();

    private final Context context;

    public FakeTravelTimeService(Context context) {
        this.context = context;
    }

    @Override
    public void getTravelTimes(IJobSuccessHandler<List<Destination>> successHandler,
                               IJobFailureHandler failureHandler) {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();

        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("sample.json")));

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
        Destination[] destinationArray = gson.fromJson(jsonString, new Destination[0].getClass());
        List<Destination> destinationList = Arrays.asList(destinationArray);

        successHandler.onJobSuccess(destinationList);
    }
}
