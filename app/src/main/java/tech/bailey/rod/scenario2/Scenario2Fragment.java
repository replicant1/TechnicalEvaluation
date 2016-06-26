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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.bailey.rod.R;
import tech.bailey.rod.app.AppDirectorSingleton;
import tech.bailey.rod.app.TechnicalEvaluationApplication;
import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.json.DestinationAdapter;

/**
 * Implementation of the IScenario2View in the form of a Fragment. This view should be kept as
 * simple and as dumb as possible. Defer as much logic/functionality as possible to the
 * corresponding IScenario2Presenter that is presenting this view.
 */
public class Scenario2Fragment extends Fragment implements IScenario2View {

    private static final String TAG = Scenario2Fragment.class.getSimpleName();

    @BindView(R.id.scenario_2_button_close_map_card)
    ImageButton closeMapCardButton;

    @BindView(R.id.scenario_2_destination_card)
    View destinationCard;

    @BindView(R.id.scenario_2_destination_spinner)
    Spinner destinationSpinner;

    @BindView(R.id.scenario_2_map_card)
    View mapCard;

    @BindView(R.id.scenario_2_button_navigate)
    Button navigateButton;

    @BindView(R.id.scenario_2_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.scenario_2_progress_message)
    TextView progressMessage;

    @BindView(R.id.scenario_2_progress_monitor)
    ViewGroup progressMonitor;

    @BindView(R.id.scenario_2_button_retry)
    Button retryButton;

    @BindView(R.id.scenario_2_travel_times_list)
    ListView travelTimesList;

    private GoogleMap googleMap;

    private boolean mapIsReady;

    private IScenario2Model model;

    private IScenario2Presenter presenter;

    private SupportMapFragment supportMapFragment;

    public Scenario2Fragment() {
        // Empty
    }

    public static Scenario2Fragment newInstance() {
        return new Scenario2Fragment();
    }

    @Override
    public void hideDestinationSelectionPanel() {
        destinationCard.setVisibility(View.GONE);
    }

    @Override
    public void hideMap() {
        mapCard.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressPanel() {
        progressMonitor.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, hashCode() + " @@ onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_scenario_2, container, false);

        ButterKnife.bind(this, fragmentView);

        retryButton.setOnClickListener(new RetryButtonOnClickListener());
        destinationSpinner.setOnItemSelectedListener(new DestinationSelectedListener());
        navigateButton.setOnClickListener(new NavigateButtonOnClickListener());

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

        closeMapCardButton.setOnClickListener(new CloseMapCardButtonOnClickListener());

        return fragmentView;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, hashCode() + " @@ onDestroy");
        super.onDestroy();

        EventBusSingleton.getInstance().getBus().unregister(presenter);
    }

    @Override
    public void onPause() {
        Log.i(TAG, hashCode() + " @@ onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i(TAG, hashCode() + " @@ onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.i(TAG, hashCode() + " @@ onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.i(TAG, hashCode() + " @@ onStop");
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // We defer creation of the presenter until here because as soon as it is created,
        // and linked to the model, there may be a flood of update events from the model
        // (e.g. if the destinations have already been read in, as happens when using the
        // FakeTravelTimeService), and we don't want to try and update views that have not
        // yet been created.
        IScenario2Model model = AppDirectorSingleton.getInstance().getScenario2Model();
        presenter = new Scenario2Presenter(this, model);

        Log.i(TAG, hashCode() + " @@ onViewCreated: new prsenter is " + presenter.hashCode());
    }

    @Override
    public void setDestinationNames(@NonNull List<String> destinationNames) {
        Log.i(TAG, "setDestinationNames: " + destinationNames);
        DestinationAdapter adapter = new DestinationAdapter( //
                TechnicalEvaluationApplication.context, //
                R.layout.destination_name_spinner_dropdown_item, //
                destinationNames); //
        destinationSpinner.setAdapter(adapter);
    }

    @Override
    public void setModeTravelTimes(List<ModeTravelTime> modeTravelTimes) {
        ModeTravelTimesListAdapter adapter = new ModeTravelTimesListAdapter( //
                TechnicalEvaluationApplication.context, //
                R.layout.adapter_mode_travel_time, //
                modeTravelTimes); //
        travelTimesList.setAdapter(adapter);
    }

    @Override
    public void setSelectedDestinationName(String destinationName) {
        Log.i(TAG, hashCode() + " setSelectedDestinationName: " + destinationName);

        // Find out what index this item has
        int selectedIndex = -1;

        // Sometimes the selected destination name will be set before the adapter
        // in the destinationSpinner has been created (it is not created until the list of
        // destinations has been set). This depends on order of arrival of events on the Otto
        // event bus, which is unpredictable. When this occurs, we store the selected name in
        // the tag of the spinner. When the list of destinations finally
        // arrives, and the spinner adapter created, *then* we apply the pending selection.
        // Note: An event bus which allowed ordering or prioritizing of events would make this
        // unncessary.

        if (destinationSpinner.getAdapter() == null) {
            destinationSpinner.setTag(destinationName);
        } else {

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
    }

    @Override
    public void showDestinationSelectionPanel() {
        destinationCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMap(float latitude, float longitude) {
        mapCard.setVisibility(View.VISIBLE);

        if (mapIsReady) {
            LatLng location = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(location));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
        } else {
            // TODO Would be better to poll repeatedly until map is ready rather than
            // just do nothing.
            Log.w(TAG, "showMap was called before map was ready");
        }
    }

    @Override
    public void showProgressPanel(ProgressPanelMode mode, String message) {
        progressMonitor.setVisibility(View.VISIBLE);
        progressMessage.setText(message);

        switch (mode) {
            case MODE_FAILURE:
                progressBar.setVisibility(View.GONE);
                retryButton.setVisibility(View.VISIBLE);
                break;

            case MODE_INDETERMINATE_PROGRESS:
                progressBar.setVisibility(View.VISIBLE);
                retryButton.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * Listens for user to select a new destination with the spinner, then transmits
     * the newly selected destination to the presenter.
     */
    private class DestinationSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            if (view != null) {
                TextView textView = (TextView) view;
                presenter.destinationNameSelected(textView.getText().toString());
            }
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
            Log.i(TAG, "NavigateButtonOnClickListener.onClick");
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

    private class RetryButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            presenter.retryButtonPressed();
        }
    }
}
