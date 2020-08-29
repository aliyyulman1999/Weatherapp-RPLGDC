package com.example.weatherapp.adapter;


import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPagerAdapter2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();

    public viewPagerAdapter2(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getItemPosition(Object item) {
        return getCount();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
  
    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }
}
