package com.existentialponytomas.getappprospero;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.existentialponytomas.getappprospero.fragments.BudgetFragment;
import com.existentialponytomas.getappprospero.fragments.ExpensesFragment;
import com.existentialponytomas.getappprospero.fragments.HistoryFragment;
import com.existentialponytomas.getappprospero.fragments.PlacesFragment;
import com.existentialponytomas.getappprospero.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    Fragment budgetFragment;
    Fragment expensesFragment;
    Fragment historyFragment;
    Fragment placesFragment;
    Fragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
   //         @Override
      //      public void onClick(View view) {
       //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
      //                  .setAction("Action", null).show();
       //     }
      //  });

        initFragments();
        initBottomMenu();
        showFragment(expensesFragment);
    }
    private void initFragments() {
        budgetFragment = BudgetFragment.newInstance();
        expensesFragment = ExpensesFragment.newInstance();
        historyFragment = HistoryFragment.newInstance();
        placesFragment = PlacesFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();
    }

    private void initBottomMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
       // bottomNavigationView.setItemTextColor();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottom_menu_costs:
                                showFragment(expensesFragment);
                                break;
                            case R.id.bottom_menu_budget:
                                showFragment(budgetFragment);
                                break;
                            case R.id.bottom_menu_history:
                                showFragment(historyFragment);
                                break;
                            case R.id.bottom_menu_places:
                                showFragment(placesFragment);
                                break;
                            case R.id.bottom_menu_profile:
                                showFragment(profileFragment);
                                break;
                        }
                        return true;
                    }
                });
    }


    private void showFragment(Fragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
