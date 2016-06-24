package tech.bailey.rod.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tech.bailey.rod.R;
import tech.bailey.rod.scenario1.Scenario1Fragment;
import tech.bailey.rod.scenario2.Scenario2Fragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the "scenario" tabs. This class vends instances of Scenario1Fragment or Scenario2Fragment
 * for the bodies of each of the two major tabs.
 */
public class ScenarioPagerAdapter extends FragmentPagerAdapter {

    public ScenarioPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return (position == 0) ? Scenario1Fragment.newInstance() : Scenario2Fragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;  // Scenario1Fragment on first tab, Scenario2Fragment on second tab
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Context context = TechnicalEvaluationApplication.context;
        CharSequence result = null;

        switch (position) {
            case 0:
                result =  context.getString(R.string.scenario_1_tab_title);
                break;

            case 1:
                result = context.getString(R.string.scenario_2_tab_title);
                break;
        }

        return result;
    }
}
