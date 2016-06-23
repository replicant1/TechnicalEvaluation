package tech.bailey.rod.scenario2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainModel;
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

        // Spinners do not allow "no selection", so after repopulating the list
        // of available choices, we automatically select the first itme in the list
        setSelectedDestinationName(destinationNames.get(0));
    }

    @Override
    public void setSelectedDestinationName(String destinationName) {
        // Find out what index this item has
        int selectedIndex = -1;

        for (int i = 0; i < destinationSpinner.getAdapter().getCount(); i++) {
            String name = (String) destinationSpinner.getAdapter().getItem(i);
            if (destinationName.equals(name)) {
                selectedIndex = i;
            }
        }

        if (selectedIndex != -1) {
            destinationSpinner.setSelection(selectedIndex);
            navigateButton.setEnabled(true);
        }
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
        } else {
            Log.w(TAG, "showMap was called before map was ready");
        }
    }

    @Override
    public void hideMap() {
        mapCard.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // We defer creation of the presenter until here because as soon as it is created,
        // and linked to the model, there may be a flood of update events from the model
        // (e.g. if the destinations have already been read in, as happens when using the
        // FakeTravelTimeService), and we don't want to try and update views that have not
        // yet been created.
        presenter = new Scenario2Presenter(this, MainModel.getInstance().getScenario2Model());
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

        return fragmentView;
    }


    private class DestinationSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            Log.i(TAG, "onItemSelected: view=" + view + ",position=" + position + ",id=" + id);
            TextView textView = (TextView) view;
            presenter.destinationNameSelected(textView.getText().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // Empty
        }
    }

    /**
     * Listens for clicks on "Navigate" button and transmits them to the presenter
     */
    private class NavigateButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            presenter.navigateButtonPressed();
        }
    }

    /**
     * Listens for clicks on the close ("X") button at top right of map card and
     * transmits it to the presenter.
     */
    private class CloseMapCardButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            presenter.hideMapButtonPressed();
        }
    }
}
