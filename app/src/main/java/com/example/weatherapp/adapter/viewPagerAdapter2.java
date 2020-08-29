package com.example.weatherapp.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdapter2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();;


    public viewPagerAdapter2(FragmentManager fm){
        super(fm);
    }

//    @Override
//    public Fragment getItem(int i) {
//        return fragmentList.get(i);
//    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return fragmentList.get(0); //ChildFragment1 at position 0
            case 1:
                return fragmentList.get(1); //ChildFragment2 at position 1
            case 2:
                return fragmentList.get(2); //ChildFragment3 at position 2
        }
        return null; //does not happen
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}
