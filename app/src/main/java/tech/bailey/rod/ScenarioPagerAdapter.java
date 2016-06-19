package tech.bailey.rod;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        return 2;  // Scenario1Fragment on first tab, Scenario2Fragment on second tag
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Use formatted message string
        switch (position) {
            case 0:
                return "SCENARIO 1";
            case 1:
                return "SCENARIO 2";
        }
        return null;
    }
}
