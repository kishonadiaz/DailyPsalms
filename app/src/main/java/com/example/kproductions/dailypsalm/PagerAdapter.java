package com.example.kproductions.dailypsalm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    FragmentManager fragmentManager;


    public PagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
    }
    public PagerAdapter(FragmentManager fm, int tabcount){
        super(fm);
        this.fragmentManager = fm;
        this.tabCount = tabcount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                PsalmOTheDay tab1 = new PsalmOTheDay();
                return tab1;


            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Daily Psalm";

        }
        return null;
    }
}
