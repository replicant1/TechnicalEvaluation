package tech.bailey.rod;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tech.bailey.rod.json.Destination;
import tech.bailey.rod.json.DestinationAdapter;


public class Scenario2Fragment extends Fragment implements IScenario2View {

    private static final String TAG = Scenario2Fragment.class.getSimpleName();

    private ImageButton closeMapCardButton;

    private Spinner destinationSpinner;

    private SupportMapFragment supportMapFragment;

    private View mapCard;

    private Button navigateButton;

    private ListView travelTimesList;

    private IScenario2Presenter presenter;

    private IScenario2Model model;

    private boolean mapIsReady;

    private GoogleMap googleMap;

    public Scenario2Fragment() {
        model = new Scenario2Model();
        presenter = new Scenario2Presenter(this, model);
    }

    public static Scenario2Fragment newInstance() {
        return new Scenario2Fragment();
    }

    @Override
    public void setDestinationNames(@NonNull List<String> destinationNames) {
        DestinationAdapter adapter = new DestinationAdapter( //
                getActivity(), //
                R.layout.destination_name_spinner_dropdown_item, //
                destinationNames); //
        destinationSpinner.setAdapter(adapter);
    }

    @Override
    public void setSelectedDestinationName(String destinationName) {

    }

    @Override
    public void setModeTravelTimes(List<ModeTravelTime> modeTravelTimes) {
        ModeTravelTimesListAdapter adapter = new ModeTravelTimesListAdapter( //
                getActivity(), //
                R.layout.adapter_mode_travel_time, //
                modeTravelTimes); //
        travelTimesList.setAdapter(adapter);
    }

    @Override
    public void showMap(float latitude, float longitude) {
        mapCard.setVisibility(View.VISIBLE);

        if (mapIsReady) {
            LatLng location = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(location));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
        }
        else {
            Log.w(TAG, "showMap was called before map was ready");
        }
    }

    @Override
    public void hideMap() {
        mapCard.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_scenario_2, container, false);

        destinationSpinner = (Spinner) fragmentView.findViewById(R.id.scenario_2_destination_spinner);
        destinationSpinner.setOnItemSelectedListener(new DestinationSelectedListener());

        travelTimesList = (ListView) fragmentView.findViewById(R.id.scenario_2_travel_times_list);

        navigateButton = (Button) fragmentView.findViewById(R.id.scenario_2_button_navigate);
        navigateButton.setOnClickListener(new NavigateButtonOnClickListener());

        mapCard = fragmentView.findViewById(R.id.scenario_2_map_card);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.scenario_2_support_map_fragment);

        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mapIsReady = true;
                    Scenario2Fragment.this.googleMap = googleMap;
                }
            });
        }

        closeMapCardButton = (ImageButton) fragmentView.findViewById(R.id.scenario_2_button_close_map_card);
        closeMapCardButton.setOnClickListener(new CloseMapCardButtonOnClickListener());

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
//                R.array.planets_array, // Data source for spinner items
//                R.layout.destination_name_spinner_dropdown_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        destinationSpinner.setAdapter(adapter);

        // Some dummy data for the Mode of Transport list
        ModeTravelTime car = new ModeTravelTime(ModeOfTransport.CAR, "30 Mins");
        ModeTravelTime train = new ModeTravelTime(ModeOfTransport.TRAIN, "15 Mins");
        List<ModeTravelTime> times = new LinkedList<ModeTravelTime>();
        times.add(car);
        times.add(train);
        setModeTravelTimes(times);

        // Read in some dummy data
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
        Log.i(TAG, "====================");

        Log.i(TAG, jsonString);

        Gson gson = new Gson();
        Destination[] destinations = gson.fromJson(jsonString, new Destination[0].getClass());

        Log.i(TAG, "destinations=" + destinations);
        Log.i(TAG, "destinations.length=" + destinations.length);

        for (Destination destination : destinations) {
            Log.i(TAG, "destination: " + destination);
        }

        // TODO Shouldn't bypass the presenter
        List<Destination> destinationList = Arrays.asList(destinations);
        model.setDestinations(destinationList);

        List<String> destinationNames = new LinkedList<String>();
        for (Destination destination : destinationList) {
            destinationNames.add(destination.name);
        }
        setDestinationNames(destinationNames);

        // setSelectedDestinationName(destinationNames.get(0));

        presenter.destinationNameSelected(destinationNames.get(0));
    }

    private static class DestinationSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            Log.i(TAG, "onItemSelected: view=" + view + ",position=" + position + ",id=" + id);
//                presenter.destinationNameSelected(                );
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // Empty
        }
    }

    /**
     * Listens for clicks on "Navigate" button and transmits them to the
     */
    private class NavigateButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            presenter.navigateButtonPressed();
        }
    }

    private class CloseMapCardButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            presenter.hideMapButtonPressed();
        }
    }
}
