package tech.bailey.rod.scenario1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Vends instances of NumberedFragment
 */
public class NumberedFragmentPagerAdapter extends FragmentPagerAdapter {

    public NumberedFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NumberedFragment.newInstance(position + 1, 4);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
