package tech.bailey.rod.scenario1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import tech.bailey.rod.R;
import tech.bailey.rod.app.MainModel;


public class Scenario1Fragment extends Fragment implements IScenario1View {

    private static final String TAG = Scenario1Fragment.class.getSimpleName();

    private View fillColorView;

    private TextView itemTextView;

    private final IScenario1Presenter presenter;

    private final SwatchClickListener swatchClickListener = new SwatchClickListener();

    private final FillColorButtonClickListener fillColorButtonClickListener = new FillColorButtonClickListener();

    public Scenario1Fragment() {
        presenter = new Scenario1Presenter(this, MainModel.getInstance().getScenario1Model());
    }

    /**
     * Standard idiom for creating new fragments
     */
    public static Scenario1Fragment newInstance() {
        return new Scenario1Fragment();
    }

    @Override
    public void clearFillColor() {
        fillColorView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void clearItemText() {
        itemTextView.setText("");
    }

    @Override
    public void setFillColor(String colorString) {
        fillColorView.setBackgroundColor(Color.parseColor(colorString));
    }

    @Override
    public void setItemText(@NonNull String text) {
        itemTextView.setText(text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_scenario_1, container, false);

        // Attach click listener to every swatch so that when clicked we can send a corresponding
        // "swatchPressed" to the presenter.
        fragmentView.findViewById(R.id.scenario_1_swatch_1).setOnClickListener(swatchClickListener);
        fragmentView.findViewById(R.id.scenario_1_swatch_2).setOnClickListener(swatchClickListener);
        fragmentView.findViewById(R.id.scenario_1_swatch_3).setOnClickListener(swatchClickListener);
        fragmentView.findViewById(R.id.scenario_1_swatch_4).setOnClickListener(swatchClickListener);
        fragmentView.findViewById(R.id.scenario_1_swatch_5).setOnClickListener(swatchClickListener);

        // Attach click listener to every fill color button so that when clicked we can send a
        // corresponding "fillColorButtonPressed" to the presenter.
        fragmentView.findViewById(R.id.scenario_1_button_red).setOnClickListener(fillColorButtonClickListener);
        fragmentView.findViewById(R.id.scenario_1_button_green).setOnClickListener(fillColorButtonClickListener);
        fragmentView.findViewById(R.id.scenario_1_button_blue).setOnClickListener(fillColorButtonClickListener);

        // Row containing 4 numbered fragments and a page indicator is underneath.
        // Bind the adapter to the pager
        ViewPager numberedFragmentViewPager = (ViewPager) fragmentView.findViewById(
                R.id.scenario_1_numbered_fragment_view_pager);
        numberedFragmentViewPager.setAdapter(new NumberedFragmentPagerAdapter(
                getActivity().getSupportFragmentManager()));

        // Bind the pager to the page indicator
        CirclePageIndicator pageIndicator = (CirclePageIndicator) fragmentView.findViewById(
                R.id.scenario_1_numbered_fragmen_circle_page_indicator);
        pageIndicator.setViewPager(numberedFragmentViewPager);


        // The text view in the Card that shows the name associated with the currently selected
        // swatch.
        itemTextView = (TextView) fragmentView.findViewById(R.id.scenario_1_row_4);

        // The ViewGroup that contains the "Red","Green" and "Blue" buttons
        fillColorView = (View) fragmentView.findViewById(R.id.scenario_1_row_5);

        return fragmentView;
    }

    private class SwatchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick: view.tag=" + view.getTag());
            NamedColor clickedColor = NamedColor.valueOf((String) view.getTag());
            Log.i(TAG, "clickedColor=" + clickedColor);
            presenter.swatchPressed(clickedColor);
        }
    }

    private class FillColorButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick: view.tag=" + view.getTag());
            NamedColor clickedColor = NamedColor.valueOf((String) view.getTag());
            Log.i(TAG, "clickedColor=" + clickedColor);
            presenter.fillColorButtonPressed(clickedColor);
        }
    }
}
