package tech.bailey.rod;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the "scenario" tabs.
 */
public class ScenarioPagerAdapter extends FragmentPagerAdapter {

    public ScenarioPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
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
