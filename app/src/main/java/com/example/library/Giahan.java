package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Giahan extends AppCompatActivity {

    CalendarView cld;
    TextView tenSach, ngayMuon, ngayTra;
    int id_Muon;

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

        tenSach = findViewById(R.id.gh_tenSach);
        ngayMuon = findViewById(R.id.gh_ngayMuon);
        ngayTra = findViewById(R.id.gh_ngayTra);

        Bundle extras = getIntent().getExtras();
        final String gh_tenSach = extras.getString("tenSach");
        final String gh_ngayMuon = extras.getString("ngayMuon");
        final String gh_ngayTra = extras.getString("ngayTra");
        final int maMuon = extras.getInt("maMuon");
        id_Muon = maMuon;

        tenSach.setText(gh_tenSach);
        ngayMuon.setText(gh_ngayMuon);
        ngayTra.setText(gh_ngayTra);
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
        Intent intent = new Intent(Giahan.this, XacNhanGiaHan.class);
        intent.putExtra("maMuon",id_Muon);
        intent.putExtra("tenSach",tenSach.getText());
        intent.putExtra("ngayMuon",ngayMuon.getText());
        intent.putExtra("ngayTra",ngayTra.getText());
        intent.putExtra("year",i);
        intent.putExtra("month",i1+1);
        intent.putExtra("date",i2);
        startActivity(intent);
    }
}
