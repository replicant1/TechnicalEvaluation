package tech.bailey.rod.app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import tech.bailey.rod.R;
import tech.bailey.rod.bus.DestinationsLoadFailureEvent;
import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.json.Destination;
import tech.bailey.rod.service.FakeTravelTimeService;
import tech.bailey.rod.service.IJobFailureHandler;
import tech.bailey.rod.service.IJobSuccessHandler;
import tech.bailey.rod.service.ITravelTimeService;

/**
 * Hosts the application's view at the highest level. It's only serious responsibility is to
 * initiate the loading of Destination data the first time that the "Scenario 2" tab is clicked.
 */
public class MainActivity extends AppCompatActivity {

    public static final int SCENARIO_2_TAB_INDEX = 1;

    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ScenarioPagerAdapter mScenarioPagerAdapter;

    /**
     * The {@link ViewPager} that will host the tab contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mScenarioPagerAdapter = new ScenarioPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the scenario adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mScenarioPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ScenarioTabChangeListener(this));
    }


    /**
     * Listens to the app's primary tab and whenever the "Scenario 2" tab is first clicked,
     * triggers the asynchronous loading of the Destination data that is required for manipulation
     * in the Scenario 2 tab.
     */
    private class ScenarioTabChangeListener implements ViewPager.OnPageChangeListener {

        private final Context context;

        public ScenarioTabChangeListener(Context context) {
            this.context = context;
        }

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
            // If user selected "Scenario 2" tab and we haven't previously loaded the
            // travel times, start loading them asynchronously now.
            if (position == SCENARIO_2_TAB_INDEX) {
                AppDirectorSingleton app = AppDirectorSingleton.getInstance();
                if (app.getScenario2Model().getDestinations() == null) {
                    app.loadTravelTimeData();
                }
            }
        }
    }
}
