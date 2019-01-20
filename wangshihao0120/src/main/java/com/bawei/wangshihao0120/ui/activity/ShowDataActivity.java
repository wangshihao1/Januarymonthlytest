package com.bawei.wangshihao0120.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bawei.wangshihao0120.R;
import com.bawei.wangshihao0120.ui.adapter.FragPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDataActivity extends AppCompatActivity {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private FragPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        viewpager = findViewById(R.id.viewpager);
        tablayout = findViewById(R.id.tablayout);
        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewpager);
    }


}
