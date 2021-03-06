package tech.bailey.rod.json;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.List;

import tech.bailey.rod.R;

/**
 * Adapts an array of destination names (strings) to a spinner in scenario 2.
 */
public class DestinationAdapter extends ArrayAdapter<String> {

    private final List<String> destinations;

    public DestinationAdapter(Context context, int resource, List<String> destinations) {
        super(context, resource, R.id.destination_text, destinations);
        this.destinations = destinations;
    }

    @Override
    public int getCount() {
        return destinations.size();
    }

    @Override
    public String getItem(int position) {
        return destinations.get(position);
    }


}
