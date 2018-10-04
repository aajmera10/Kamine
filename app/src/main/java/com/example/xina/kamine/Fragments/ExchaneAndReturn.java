package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class ExchaneAndReturn extends android.support.v4.app.Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipment_exchange_return_main,container,false);
        viewPager = view.findViewById(R.id.viewpager_exc_ret);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) view.findViewById(R.id.tabs_ret_exc);
        tabLayout.setupWithViewPager(viewPager);


        return view;

    }
    private void setupViewPager(ViewPager viewPager) {
        ExchaneAndReturn.ViewPagerAdapter adapter = new ExchaneAndReturn.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ExchangeFragment(), "Exchange");
        adapter.addFragment(new ReturnFragment(), "Return");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
