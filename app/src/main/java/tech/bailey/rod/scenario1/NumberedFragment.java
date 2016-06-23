package tech.bailey.rod.scenario1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import tech.bailey.rod.R;

/**
 * Created by rodbailey on 22/06/2016.
 */
public class NumberedFragment extends Fragment {

    private static String TAG = NumberedFragment.class.getSimpleName();

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

        fragmentView.setOnClickListener(new FragmentClickListener());

        TextView numberText = (TextView) fragmentView.findViewById(R.id.fragment_number_text);
        numberText.setText(String.format("Page %d of %d", thisFragmentNumber, totalFragments));

        return fragmentView;
    }

    private class FragmentClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // To save time I am raising the Toast here directly. But this is short-cicuiting the
            // architecture. To do it properly we would send a message to the presenter to indicate
            // that the fragment had been clicked. Then that info would be communicated to the model
            // which would eventually result in a message reaching the IScenario1View which would
            // raise the Toast.
            String toastText = String.format("Page %d of %d", thisFragmentNumber, totalFragments);
            Toast.makeText(getContext(),toastText, Toast.LENGTH_SHORT).show();
        }
    }
}
