package com.veyron.www.csdnclient;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.veyron.www.csdnclient.adapter.MyFragmentPagerAdapter;
import com.veyron.www.csdnclient.ui.fragment.CloudFragment;
import com.veyron.www.csdnclient.ui.fragment.MobileFragment;
import com.veyron.www.csdnclient.ui.fragment.NewsFragment;
import com.veyron.www.csdnclient.ui.fragment.StudyFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Veyron on 2017/1/29.
 * Function：主界面：Tablayout+ViewPager（填充Fragment）
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mFragmentList;
    private List<String> mStringList;

    private CloudFragment mCloudFragment;
    private MobileFragment mMobileFragment;
    private NewsFragment mNewsFragment;
    private StudyFragment mStudyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
      mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
      mViewPager = (ViewPager)findViewById(R.id.viewPager);

        mCloudFragment = new CloudFragment();
        mMobileFragment = new MobileFragment();
        mNewsFragment = new NewsFragment();
        mStudyFragment = new StudyFragment();

        mFragmentList = new ArrayList<>();
        mFragmentList.add(mNewsFragment);
        mFragmentList.add(mMobileFragment);
        mFragmentList.add(mCloudFragment);
        mFragmentList.add(mStudyFragment);

        mStringList = new ArrayList<>();
        mStringList.add("最热资讯");
        mStringList.add("移动开发");
        mStringList.add("云计算");
        mStringList.add("软件研发");

        //设置模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //设置tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(3)));

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList,mStringList);

        mViewPager.setAdapter(mFragmentPagerAdapter);

        //tablayout viewpage  绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
