package tech.bailey.rod.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import tech.bailey.rod.R;
import tech.bailey.rod.json.Destination;
import tech.bailey.rod.scenario1.IScenario1Model;
import tech.bailey.rod.scenario1.Scenario1Model;
import tech.bailey.rod.scenario2.IScenario2Model;
import tech.bailey.rod.scenario2.Scenario2Model;
import tech.bailey.rod.service.FakeTravelTimeService;
import tech.bailey.rod.service.IJobFailureHandler;
import tech.bailey.rod.service.IJobSuccessHandler;
import tech.bailey.rod.service.ITravelTimeService;

public class MainActivity extends AppCompatActivity {

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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Empty
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "==== onPageSelected position = " + position + "  ====");
                // If user selected "Scenario 2" tab and we haven't previously loaded the
                // travel times, start loading them asynchronously now.
                if (position == 1) {
                    if (MainModel.getInstance().getScenario2Model().getDestinations() == null) {
                        loadTravelTimes();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Empty
            }
        });

    }

    private void loadTravelTimes() {
        Log.i(TAG, "==== INto loadTravelTimes ====");

        // Note: Could use dependency injection to sway between fake
        // and real impl's of ITravelTimeService.
        ITravelTimeService service = new FakeTravelTimeService(this);

        service.getTravelTimes(
                new IJobSuccessHandler<List<Destination>>() {
                    @Override
                    public void onJobSuccess(List<Destination> result) {
                        Log.i(TAG, "Into success handler");
                        for (Destination destination : result) {
                            Log.i(TAG, "destination: " + destination);
                        }
                        MainModel.getInstance().getScenario2Model().setDestinations(result);
                    }
                },
                new IJobFailureHandler() {
                    @Override
                    public void onJobFailure(String failureReason) {
                        Log.e(TAG, "Failed to load travel times: " + failureReason);
                    }
                }
        );
    }

}
