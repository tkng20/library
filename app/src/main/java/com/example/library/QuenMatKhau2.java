package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuenMatKhau2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mat_khau_02);

        Button btnXacNhan = (Button) findViewById(R.id.qmk2_xacnhan);
        Button btnDangKy = (Button) findViewById(R.id.qmk2_dangky);
        Button btnDangNhap = (Button) findViewById(R.id.qmk2_dangnhap);

        // Handle click event
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau2.this, QuenMatKhau3.class);
                startActivity(iSubActivity01);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau2.this, DangKy.class);
                startActivity(iSubActivity01);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau2.this, DangNhap.class);
                startActivity(iSubActivity01);
            }
        });

    }
}