package com.example.library;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.Borrow;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacNhanMuon extends AppCompatActivity {

    Button btnXacNhanMuon,btnChonNgay;
    APIService userService;
    int id_user;
    int id_book;
    String date;
    Date ngaydk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhanmuon2);
        btnChonNgay = findViewById(R.id.chonngay);
        btnXacNhanMuon = findViewById(R.id.btnXNM1);
        btnXacNhanMuon.setOnClickListener(this::onClick);
        userService = ApiUtils.getAPIService();

        TextView ten = findViewById(R.id.m_ten);
        TextView email = findViewById(R.id.m_email);
        TextView phone = findViewById(R.id.m_phone);

        TextView title = findViewById(R.id.m_title);
        Bundle extras = getIntent().getExtras();
        final int sach_id = extras.getInt("maSach");
        final String tenSach = extras.getString("tenSach");
        final String i_ten = extras.getString("ten");
        final String i_email = extras.getString("email");
        final String i_phone = extras.getString("phone");
        final int i_year = extras.getInt("year");
        final int i_month = extras.getInt("month");
        final int i_date = extras.getInt("date");


        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        id_user = sp.getInt("id",0);
        id_book = sach_id;
        date = String.valueOf(i_date)+"/"+String.valueOf(i_month)+"/"+String.valueOf(i_year);
//        String str = "2017-09-11";
        String str = String.valueOf(i_year)+"-"+String.valueOf(i_month)+"-"+String.valueOf(i_date);
        // converting string into sql date
        ngaydk = Date.valueOf(str);

        title.setText(tenSach);
        ten.setText(i_ten);
        email.setText(i_email);
        phone.setText(i_phone);

        btnChonNgay.setText(i_date+"/"+i_month+"/"+i_year);

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

    public void onClick(View view) {
        MaterialAlertDialogBuilder test = new MaterialAlertDialogBuilder(this);
        test.setTitle("Xác nhận gia hạn")
                .setMessage("Bạn sẽ đến lấy vào ngày "+date)
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        borrow(id_user,id_book,ngaydk);
                    }
                })
                .show();
    }

    public void borrow(int user_id, int book_id,Date ngaydk) {
        userService.addBorrow(user_id,book_id,"0",ngaydk).enqueue(new Callback<Borrow>() {
            @Override
            public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                // this method is called when we get response from our api.
                if (response.isSuccessful()){
                        Intent intent = new Intent(XacNhanMuon.this, TrangChu.class);
                        startActivity(intent);
                        Toast.makeText(XacNhanMuon.this, "Đăng ký mượn thành công", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Borrow> call, Throwable t) {
                Toast.makeText(XacNhanMuon.this, "Bạn đã mượn quyển này", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
