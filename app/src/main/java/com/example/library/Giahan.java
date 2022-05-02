package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Giahan extends AppCompatActivity {

    CalendarView cld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giahan);
        cld = findViewById(R.id.calendarView2);
        cld.setOnDateChangeListener(this::onDateChangeListener);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
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

    private void onDateChangeListener(CalendarView calendarView, int i, int i1, int i2) {
        Intent iSubActivity01 = new Intent(Giahan.this, XacNhanGiaHan.class);
        startActivity(iSubActivity01);
    }
}
