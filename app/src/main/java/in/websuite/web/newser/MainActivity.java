package in.websuite.web.newser;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    NewsApi newsApi;

    ProgressDialog pd;

    ImageView head_img;


    String mNewsType[]={"Trending","Education","Science","Sports","Health","Business","Crime"};

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        head_img = (ImageView) findViewById(R.id.news_head_img);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);


        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter();
        myFragmentAdapter.fragments.add(new TrendingFragment(MainActivity.this));
        myFragmentAdapter.fragments.add(new SportFragment(MainActivity.this));
        myFragmentAdapter.fragments.add(new EducationFragment(MainActivity.this));
        myFragmentAdapter.title.add("Trending");
        myFragmentAdapter.title.add("Sport");
        myFragmentAdapter.title.add("Education");

        viewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class MyFragmentAdapter extends FragmentPagerAdapter
    {

        ArrayList<Fragment> fragments=new ArrayList<Fragment>();
        ArrayList<String> title=new ArrayList<String>();

        public MyFragmentAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
