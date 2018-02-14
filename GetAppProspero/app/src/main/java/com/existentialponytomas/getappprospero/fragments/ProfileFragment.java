package com.existentialponytomas.getappprospero.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.adapters.ViewPagerAdapter;


public class ProfileFragment extends Fragment {

    View rootView;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PointsFragment(), "Баллы");
        adapter.addFragment(new AchievementFragment(), "Достижения");
        adapter.addFragment(new RatingFragment(), "Рейтинг");
        viewPager.setAdapter(adapter);
    }
}
