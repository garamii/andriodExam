
package com.example.android.androidexam.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.androidexam.R;
import com.example.android.androidexam.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-16.
 */
public class ScreenSlideActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private List<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tab_Layout);

        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new ColorFragment());
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab" + (i + 1)).setIcon(R.mipmap.ic_launcher));
        }

        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),
                mList);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    // FragmentStatePagerAdapter : 메모리 관리가 필요 할 때 사용
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mmList;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);

            mmList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mmList.get(position);
        }

        @Override
        public int getCount() {
            return mmList.size();
        }
    }
}
