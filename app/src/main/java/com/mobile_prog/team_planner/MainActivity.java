package com.mobile_prog.team_planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.huawei.hms.ads.HwAds;
import com.mobile_prog.team_planner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_PASSWORD = "extra_password";
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    private ArrayList<Integer> menuIdList = new ArrayList<Integer>() {
        {
            add(R.id.home_menu_item);
            add(R.id.team_menu_item);
            add(R.id.profile_menu_item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Team Planner");

        setContentView(R.layout.activity_main);

        HwAds.init(this);
        setMenuListener();
        setViewPager();
    }

    private void setMenuListener() {
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_menu_item:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.team_menu_item:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.profile_menu_item:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        });
    }

    private void setViewPager() {
        FragmentStateAdapter fragmentStateAdapter = new MainViewSlidePagerAdapter(this);
        viewPager = findViewById(R.id.mainViewPager);
        viewPager.setAdapter(fragmentStateAdapter);
        ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.setSelectedItemId(menuIdList.get(position));
            }
        };
        viewPager.registerOnPageChangeCallback(pageChangeCallback);

    }


    private static class MainViewSlidePagerAdapter extends FragmentStateAdapter {
        public MainViewSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment currentFragment = new HomeFragment();
            switch (position) {
                case 0:
                    currentFragment=new HomeFragment();
                    break;
                case 1:
                    currentFragment=new TeamFragment();
                    break;
                case 2:
                    currentFragment=new UserProfileFragment();
                    break;
            }
            return currentFragment;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}