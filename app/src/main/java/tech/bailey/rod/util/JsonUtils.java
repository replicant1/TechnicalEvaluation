package tech.bailey.rod.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import tech.bailey.rod.json.Destination;

/**
 * JSON-related utility methods
 */
public final class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static List<Destination> parseDestinationArray(@NonNull String jsonString) {
        // Use Google's GSON library to parse the JSON
        Gson gson = new Gson();
        Destination[] destinationArray = gson.fromJson(
                jsonString, new Destination[0].getClass());
        return Arrays.asList(destinationArray);
    }
}
