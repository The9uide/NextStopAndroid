package th.ac.kmitl.it.nextstop.Activity;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.it56070048.testtabbar3.databinding.ActivityDetailStationBinding;

public class DetailStationActivity extends AppCompatActivity {
    private ActivityDetailStationBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_station);

        viewPager = binding.viewpager;
        createViewPager(viewPager);

        tabLayout = binding.tabs;
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tabpassive);
                tab.getIcon().setColorFilter(tabIconColor,PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        createTabIcons();
    }

    private void createTabIcons() {

        TabLayout.Tab tabOne = tabLayout.getTabAt(0);
        tabOne.setIcon(R.drawable.iconactivestore);
        tabOne.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white),PorterDuff.Mode.SRC_IN);
        tabOne.setText("ร้านค้า");

        TabLayout.Tab tabTwo = tabLayout.getTabAt(1);
        tabTwo.setIcon(R.drawable.iconactivefood);
        tabTwo.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.tabpassive),PorterDuff.Mode.SRC_IN);
        tabTwo.setText("ร้านอาหาร");

        TabLayout.Tab tabThree = tabLayout.getTabAt(2);
        tabThree.setIcon(R.drawable.iconactiveother);
        tabThree.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.tabpassive),PorterDuff.Mode.SRC_IN);
        tabThree.setText("อื่น ๆ");
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Fragment1(), "Tab 1");
        adapter.addFrag(new Fragment1(), "Tab 2");
        adapter.addFrag(new Fragment1(), "Tab 3");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
