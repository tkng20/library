package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Lichsu extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    VPAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.pager);
        vpAdapter = new VPAdapter(this);
        viewPager.setAdapter(vpAdapter);

        new TabLayoutMediator(tabLayout,viewPager, (tab, position) -> {
            switch (position){
            case 0:
                tab.setText("Chờ xác nhận");
                break;
            case 1:
                tab.setText("Đang mượn");
                break;
            case 2:
                tab.setText("Đã mượn");
                break;
            case 3:
                tab.setText("Đến hạn");
                break;
        }
        }).attach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}