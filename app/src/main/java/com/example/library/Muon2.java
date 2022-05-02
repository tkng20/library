package com.example.library;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class Muon2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muon_2);
        btn = findViewById(R.id.btnChoose);
        FloatingActionButton btnDiscover = findViewById(R.id.discover);
        View btnHome = findViewById(R.id.home);
        View btnNotice = findViewById(R.id.notice);
        btn.setOnClickListener(v -> showDatePickerDialog());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(Muon2.this, TrangChu.class);
                startActivity(iSubActivity01);
            }
        });

        btnDiscover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(Muon2.this, DanhMucSach.class);
            startActivity(iSubActivity01);
        });

        btnNotice.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(Muon2.this, ThongBao.class);
            startActivity(iSubActivity01);
        });

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

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Intent iSubActivity01 = new Intent(Muon2.this, XacNhanMuon.class);
        startActivity(iSubActivity01);
    }
}
