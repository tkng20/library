package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuenMatKhau3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mat_khau_03);


        Button btnDoiMatKhau = (Button) findViewById(R.id.qmk3_btnDoiMatKhau);
        Button btnDangKy = (Button) findViewById(R.id.qmk3_dangky);
        Button btnDangNhap = (Button) findViewById(R.id.qmk3_dangky);

        // Handle click event
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau3.this, DangNhap.class);
                startActivity(iSubActivity01);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau3.this, DangKy.class);
                startActivity(iSubActivity01);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau3.this, DangNhap.class);
                startActivity(iSubActivity01);
            }
        });

    }
}