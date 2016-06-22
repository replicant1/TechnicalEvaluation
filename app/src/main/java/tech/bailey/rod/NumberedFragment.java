package tech.bailey.rod;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rodbailey on 22/06/2016.
 */
public class NumberedFragment extends Fragment {

    private static final String BUNDLE_ARG_THIS_FRAGMENT_NUMBER = "thisFragmentNumber";

    private static final String BUNDLE_ARG_TOTAL_FRAGMENTS = "totalFragments";

    private int thisFragmentNumber;

    private int totalFragments;

    public NumberedFragment() {
        // Empty
    }

    public static NumberedFragment newInstance(int thisFragmentNumber, int totalFragments) {
        NumberedFragment result = new NumberedFragment();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_ARG_THIS_FRAGMENT_NUMBER, thisFragmentNumber);
        args.putInt(BUNDLE_ARG_TOTAL_FRAGMENTS, totalFragments);

        result.setArguments(args);

        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        totalFragments = args.getInt(BUNDLE_ARG_TOTAL_FRAGMENTS);
        thisFragmentNumber = args.getInt(BUNDLE_ARG_THIS_FRAGMENT_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_numbered, container, false);

        TextView numberText = (TextView) fragmentView.findViewById(R.id.fragment_number_text);
        numberText.setText(String.format("Page %d of %d", thisFragmentNumber, totalFragments));

        return fragmentView;
    }
}
