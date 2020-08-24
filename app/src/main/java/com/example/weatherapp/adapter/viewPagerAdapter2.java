package com.example.weatherapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPagerAdapter2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();;
//    private final List<String> fragmentTitle = new ArrayList<>();

    public viewPagerAdapter2(FragmentManager fm){
        super(fm);
    }


//    public Fragment getItem(int position) {
//        It fragment = new MyFragment();
//        fragment.setTitle(titles.get(position));
//        return fragment;
//    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }
//
//    @Override
//    public int getItemPosition(Object item) {
//        Fragment fragment = (Fragment) item;
//        String title = fragment.getC();
//        int position = titles.indexOf(title);
//
//        if (position >= 0) {
//            return position;
//        } else {
//            return POSITION_NONE;
//        }
//    }

//    @Override
//    public Fragment getItem(int position) {
//        switch (position)
//        {
//            case 0:
//                return fragmentList.get(0); //ChildFragment1 at position 0
//            case 1:
//                return fragmentList.get(1); //ChildFragment2 at position 1
//            case 2:
//                return fragmentList.get(2); //ChildFragment3 at position 2
//        }
//        return null; //does not happen
//    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
//        fragmentTitle.add(title);
        fragmentList.add(fragment);

    }
}
