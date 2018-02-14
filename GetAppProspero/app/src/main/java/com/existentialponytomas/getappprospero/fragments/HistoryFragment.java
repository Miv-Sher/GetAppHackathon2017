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

public class HistoryFragment extends Fragment {


    View rootView;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    public HistoryFragment() {
        // Required empty public constructor
    }



    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_history, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HistoryExpensesFragment(), "Расходы");
        adapter.addFragment(new HistoryIncomeFragment(), "Доходы");
        viewPager.setAdapter(adapter);
    }

}
