package tech.bailey.rod;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Scenario2Fragment extends Fragment {

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

        return fragmentView;
    }
}
