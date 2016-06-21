package tech.bailey.rod;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter behind the list of ModeTravelTime instances that appear in Scenario 2. Each item
 * in that list corresponds to a ModeTravelTime instance which is rendered with an icon
 * on the left and some travel time text on the right.
 */
public class ModeTravelTimesListAdapter extends ArrayAdapter<ModeTravelTime> {

    private static final String TAG = ModeTravelTimesListAdapter.class.getSimpleName();

    private final List<ModeTravelTime> modeTravelTimes;

    public ModeTravelTimesListAdapter(Context context, int resource, List<ModeTravelTime> modeTravelTimes) {
        super(context, resource, R.id.mode_of_transport_travel_tine, modeTravelTimes);
        this.modeTravelTimes = modeTravelTimes;
    }

    @Override
    public int getCount() {
        return modeTravelTimes.size();
    }

    @Override
    public ModeTravelTime getItem(int position) {
        return modeTravelTimes.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = super.getView(position, convertView, parent);

        ImageView modeIcon = (ImageView) row.findViewById(R.id.mode_of_transport_icon);
        TextView modeText = (TextView) row.findViewById(R.id.mode_of_transport_travel_tine);

        ModeTravelTime modeTravelTime = modeTravelTimes.get(position);

        switch (modeTravelTime.getMode()) {
            case CAR:
                modeIcon.setImageResource(R.drawable.ic_directions_car_black_18dp);
                break;

            case TRAIN:
                modeIcon.setImageResource(R.drawable.ic_directions_railway_black_18dp);
                break;
        }

        modeText.setText(modeTravelTime.getTravelTime());

        return row;
    }
}
