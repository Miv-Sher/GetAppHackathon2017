package com.existentialponytomas.getappprospero;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.existentialponytomas.getappprospero.adapters.ViewPagerAdapter;
import com.existentialponytomas.getappprospero.fragments.HistoryExpensesFragment;
import com.existentialponytomas.getappprospero.fragments.HistoryIncomeFragment;
import com.existentialponytomas.getappprospero.fragments.ShopBoughtFragment;
import com.existentialponytomas.getappprospero.fragments.ShopBuyFragment;

public class ShopActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        setTitle("Магазин");

        viewPager = (ViewPager) this.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ShopBuyFragment(), "Купить");
        adapter.addFragment(new ShopBoughtFragment(), "Приобретено");
        viewPager.setAdapter(adapter);
    }

}
