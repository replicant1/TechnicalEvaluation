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
import android.widget.Button;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.bailey.rod.R;
import tech.bailey.rod.app.AppDirectorSingleton;

/**
 * The View that constitutes the entire body of the "Scenario 1" tab.
 */
public class Scenario1Fragment extends Fragment implements IScenario1View {

    private static final String TAG = Scenario1Fragment.class.getSimpleName();

    private final IScenario1Presenter presenter;

    private final SwatchClickListener swatchClickListener = new SwatchClickListener();

    private final FillColorButtonClickListener fillColorButtonClickListener = new FillColorButtonClickListener();

    // The ViewGroup that contains the "Red","Green" and "Blue" buttons
    @BindView(R.id.scenario_1_row_5)
    View fillColorView;

    // The text view in the Card that shows the name associated with the currently selected
    // swatch.
    @BindView(R.id.scenario_1_row_4)
    TextView itemTextView;

    @BindView(R.id.scenario_1_numbered_fragment_view_pager)
    ViewPager numberedFragmentViewPager;

    @BindView(R.id.scenario_1_numbered_fragmen_circle_page_indicator)
    CirclePageIndicator pageIndicator;

    @BindView(R.id.scenario_1_swatch_1)
    View swatch1;

    @BindView(R.id.scenario_1_swatch_2)
    View swatch2;

    @BindView(R.id.scenario_1_swatch_3)
    View swatch3;

    @BindView(R.id.scenario_1_swatch_4)
    View swatch4;

    @BindView(R.id.scenario_1_swatch_5)
    View swatch5;

    @BindView(R.id.scenario_1_button_red)
    Button redButton;

    @BindView(R.id.scenario_1_button_green)
    Button greenButton;

    @BindView(R.id.scenario_1_button_blue)
    Button blueButton;

    public Scenario1Fragment() {
        presenter = new Scenario1Presenter(this, AppDirectorSingleton.getInstance().getScenario1Model());
    }

    /**
     * Standard idiom for creating new fragments
     */
    public static Scenario1Fragment newInstance() {
        return new Scenario1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_scenario_1, container, false);

        ButterKnife.bind(this, fragmentView);

        // Attach click listener to every swatch so that when clicked we can send a corresponding
        // "swatchPressed" to the presenter.
        swatch1.setOnClickListener(swatchClickListener);
        swatch2.setOnClickListener(swatchClickListener);
        swatch3.setOnClickListener(swatchClickListener);
        swatch4.setOnClickListener(swatchClickListener);
        swatch5.setOnClickListener(swatchClickListener);

        // Attach click listener to every fill color button so that when clicked we can send a
        // corresponding "fillColorButtonPressed" to the presenter.
        redButton.setOnClickListener(fillColorButtonClickListener);
        greenButton.setOnClickListener(fillColorButtonClickListener);
        blueButton.setOnClickListener(fillColorButtonClickListener);

        // Row containing 4 numbered fragments and a page indicator is underneath.
        // Bind the adapter to the pager
        NumberedFragmentPagerAdapter adapter = new NumberedFragmentPagerAdapter(
                getActivity().getSupportFragmentManager());
        numberedFragmentViewPager.setAdapter(adapter);
        numberedFragmentViewPager.addOnPageChangeListener(new NumberedFragmentPageChangeListener());

        // Bind the pager to the page indicator
        pageIndicator.setViewPager(numberedFragmentViewPager);

        return fragmentView;
    }

    @Override
    public void setFillColor(String colorString) {
        fillColorView.setBackgroundColor(Color.parseColor(colorString));
    }

    @Override
    public void setItemText(@NonNull String text) {
        itemTextView.setText(text);
    }

    @Override
    public void setPageNumber(int pageNumber) {
        // Item indexes are 0-based and page numbers are 1-based
        numberedFragmentViewPager.setCurrentItem(pageNumber - 1);
    }

    /**
     * Notifies presenter when a swatch is clicked
     */
    private class SwatchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            NamedColor clickedColor = NamedColor.valueOf((String) view.getTag());
            presenter.swatchPressed(clickedColor);
        }
    }

    /**
     * Notififes presenter when a fill colour button is clicked
     */
    private class FillColorButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            NamedColor clickedColor = NamedColor.valueOf((String) view.getTag());
            presenter.fillColorButtonPressed(clickedColor);
        }
    }

    private class NumberedFragmentPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            // Empty
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // Empty
        }

        @Override
        public void onPageSelected(int position) {
            // Position is 0-based but page numbes are 1-based
            presenter.pageSelected(position + 1);
        }
    }
}
