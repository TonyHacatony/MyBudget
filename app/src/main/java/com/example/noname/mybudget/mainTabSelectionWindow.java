package com.example.noname.mybudget;


import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;



import android.os.Bundle;
import android.widget.Toast;

import com.example.noname.mybudget.mainMenuFragments.SectionsPageAdapter;
import com.example.noname.mybudget.mainMenuFragments.dataFragment;
import com.example.noname.mybudget.mainMenuFragments.historyFragment;
import com.example.noname.mybudget.mainMenuFragments.mainFragment;


public class mainTabSelectionWindow extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab_win);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new mainFragment(),"TAB1");
        adapter.addFragment(new dataFragment(),"TAB2");
        adapter.addFragment(new historyFragment(),"TAB3");
        viewPager.setAdapter(adapter);
    }


}

