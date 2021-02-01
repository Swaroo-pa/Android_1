package com.example.markettrades;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

// to show the TopGainer and the TopLoser fragments to in top navigation tabs in HomeActivity
public class PageAdapter extends FragmentPagerAdapter {

    private int num_tabs;

    public PageAdapter(@NonNull FragmentManager fm, int num_tabs) {
        super(fm);
        this.num_tabs = num_tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment selectedFragment = null;
        switch (position){
            case 0:{
                selectedFragment = new TopGainers();
                return selectedFragment;
            }
            case 1: {
                selectedFragment = new TopLosers();
                return selectedFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num_tabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

}
