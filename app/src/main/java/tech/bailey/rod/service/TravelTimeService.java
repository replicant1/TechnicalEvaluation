package tech.bailey.rod.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import tech.bailey.rod.json.Destination;
import tech.bailey.rod.util.ConfigSingleton;
import tech.bailey.rod.util.JsonUtils;

/**
 * A proper implementation of ITravelTimeService that uses Google's Volley library
 * to fetch data over the internet.
 */
public class TravelTimeService implements ITravelTimeService {

    public static final String JSON_URL = ConfigSingleton.getInstance().TravelTimeServiceUrl();

    private static final int INITIAL_TIMEOUT_MS =
            ConfigSingleton.getInstance().TraveTimeServiceTimeoutMillis();

    /**
     * Not only is this string used as the tag for each log statement, is is used as the 'tag'
     * for each request that gets issued, so they can all be cancelled on this basis.
     */
    public static final String TAG = TravelTimeService.class.getSimpleName();

    private final RequestQueue requestQueue;

    public TravelTimeService(@NonNull Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void cancelAll() {
        requestQueue.cancelAll(TAG);
    }

    @Override
    public void getTravelTimes(IJobSuccessHandler<List<Destination>> successHandler, IJobFailureHandler failureHandler) {
        StringRequest request = new StringRequest(Request.Method.GET, //
                JSON_URL, //
                new GetTravelTimesResponseListener(successHandler), //
                new GetTravelTimesErrorListener(failureHandler)); //
        request.setTag(TAG);
        request.setRetryPolicy(new DefaultRetryPolicy(INITIAL_TIMEOUT_MS, 0, 0));

        Log.i(TAG, "Launching asynch request for travel times data");

        requestQueue.add(request);
    }

    private static class GetTravelTimesResponseListener implements Response.Listener<String> {
        private final IJobSuccessHandler success;

        public GetTravelTimesResponseListener(IJobSuccessHandler<List<Destination>> success) {
            this.success = success;
        }

        @Override
        public void onResponse(String response) {
            Log.i(TAG, "GetTravelTimesresponseListener success!!");
            success.onJobSuccess(JsonUtils.parseDestinationArray(response));
        }
    }

    private static class GetTravelTimesErrorListener implements Response.ErrorListener {
        private final IJobFailureHandler failureHandler;

        public GetTravelTimesErrorListener(IJobFailureHandler failureHandler) {
            this.failureHandler = failureHandler;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i(TAG, "GetTravelTimesErrorListener failure!");
            failureHandler.onJobFailure("Failed to load travel times.");
        }
    }
}
