package com.example.ricky.geoquiz.activities.materialtest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ricky.geoquiz.R;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener {
    //implements also maybe NavigationDrawerFragment.FragmentDrawerListener

    private Toolbar toolbar;
    private ViewPager mPager;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private NavigationDrawerFragment mDrawerFragment;
    FragmentManager fm;
    public static final int TAB_COUNT = 3;
    public static final int MOVIES_SEARCH_RESULTS = 0;
    public static final int MOVIES_HITS = 1;
    public static final int MOVIES_UPCOMING = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
        //i will have a set up for the tabs later to make it cleaner
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(adapter.getIcon(i))

                            .setTabListener(this));
        }
    }

    private void setupDrawer() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        //set the Toolbar as ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //setup the NavigationDrawer
        mDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

    public void onDrawerItemClicked(View view, int position) {
        displayView(position);
        //viewPager.setCurrentItem(index);
    }

    private void displayView(int position)  {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentSale();
                break;
            case 1:
                fragment = new FragmentRedeem();
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentContainer, fragment).addToBackStack(null);
            fragmentTransaction.commit();
            tabHost.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onBackPressed(){
        tabHost.setVisibility(View.VISIBLE);
        super.onBackPressed();
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

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int icons[] = {R.drawable.ic_action_calendar,
                R.drawable.ic_action_important,
                R.drawable.ic_action_personal,};
        //ic_action_articles

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;

        }

        public Fragment getItem(int num)    {
            Fragment    fragment    =   null;

            switch  (num)   {
                case MOVIES_SEARCH_RESULTS:
                    //fragment = FragmentSearch.newInstance("", "");
                    fragment = new FragmentSearch();

                    break;
                case MOVIES_HITS:
                    fragment = new FragmentBoxOffice();

                    break;
                case MOVIES_UPCOMING:
                    fragment = new FragmentUpcoming();
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }
        private Drawable getIcon(int position) {
            return getResources().getDrawable(icons[position]);
        }
    }
}