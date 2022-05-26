package com.example.library;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.Borrow;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.sql.Date;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacNhanGiaHan extends AppCompatActivity {

    Button xngh;
    CalendarView cld;
    TextView tenSach, ngayMuon, ngayTra,ngayGiahan;
    int id_Muon;
    Date ngayGH;
    String str;
    APIService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhangiahan);
        xngh = findViewById(R.id.xacnhangiahan);
        xngh.setOnClickListener(this::onClick);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        userService = ApiUtils.getAPIService();

        tenSach = findViewById(R.id.xngh_tenSach);
        ngayMuon = findViewById(R.id.xngh_ngayMuon);
        ngayTra = findViewById(R.id.xngh_ngayTra);
        ngayGiahan = findViewById(R.id.xngh_ngayGh);

        Bundle extras = getIntent().getExtras();
        final String gh_tenSach = extras.getString("tenSach");
        final String gh_ngayMuon = extras.getString("ngayMuon");
        final String gh_ngayTra = extras.getString("ngayTra");
        final int maMuon = extras.getInt("maMuon");
        id_Muon = maMuon;

        final int i_year = extras.getInt("year");
        final int i_month = extras.getInt("month");
        final int i_date = extras.getInt("date");

        str = String.valueOf(i_year)+"-"+String.valueOf(i_month)+"-"+String.valueOf(i_date);
        // converting string into sql date
        ngayGH = Date.valueOf(str);

        tenSach.setText(gh_tenSach);
        ngayMuon.setText(gh_ngayMuon);
        ngayTra.setText(gh_ngayTra);
        ngayGiahan.setText(i_date+"/"+i_month+"/"+i_year);

    }

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

    public void onClick(View view) {
        MaterialAlertDialogBuilder test = new MaterialAlertDialogBuilder(XacNhanGiaHan.this);
        test.setTitle("Xác nhận gia hạn")
                .setMessage("Bạn đồng ý gia hạn "+ tenSach.getText() +" đến ngày "+str)
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        renew();
                        Intent iSubActivity01 = new Intent(XacNhanGiaHan.this, TrangCaNhan.class);
                        startActivity(iSubActivity01);
                    }
                })
                .show();
    }

    public void renew() {
        userService.updateBorrow(id_Muon,ngayGH).enqueue(new Callback<Borrow>() {
            @Override
            public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                // this method is called when we get response from our api.
                Toast.makeText(XacNhanGiaHan.this, "Gia hạn thành công", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Borrow> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
