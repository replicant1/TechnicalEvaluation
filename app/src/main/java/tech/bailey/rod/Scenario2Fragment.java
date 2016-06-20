package tech.bailey.rod;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tech.bailey.rod.json.Destination;


public class Scenario2Fragment extends Fragment {

    private static final String TAG = Scenario2Fragment.class.getSimpleName();

    public Scenario2Fragment() {
        // Empty - Fragments are required to have a public, no-arg constructor
    }

    public static Scenario2Fragment newInstance() {
        return new Scenario2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Change to fragment_scenario_2
        View fragmentView = inflater.inflate(R.layout.fragment_scenario_2, container, false);

        Spinner destinationSpinner = (Spinner) fragmentView.findViewById(R.id.scenario_2_destination_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.planets_array, // Data source for spinner items
                R.layout.destination_name_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(adapter);

        readInSampleJson();

        return fragmentView;
    }

    private void readInSampleJson() {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();

        try {
            reader = new BufferedReader(new InputStreamReader(getContext().getAssets().open("sample.json")));

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

        Log.i(TAG, "====================");
        Log.i(TAG, "==   SAMPLE.JSON  ==");
        Log.i(TAG,"====================");

        Log.i(TAG, jsonString);

        Gson gson = new Gson();
        Destination[] destinations = gson.fromJson(jsonString, new Destination[0].getClass());

        Log.i(TAG, "destinations=" + destinations);
        Log.i(TAG, "destinations.length=" + destinations.length);

        for (Destination destination : destinations) {
            Log.i(TAG, "destination: " + destination);
        }
    }
}
