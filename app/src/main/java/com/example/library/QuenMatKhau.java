package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuenMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mat_khau_01);

        Button btn_sendEmail = (Button) findViewById(R.id.btn_sendEmail);
        Button btnDangKy = (Button) findViewById(R.id.qmk_dangky);
        Button btnQuenMK = (Button) findViewById(R.id.qmk_dangnhap);

        // Handle click event
        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau.this, QuenMatKhau2.class);
                startActivity(iSubActivity01);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau.this, DangKy.class);
                startActivity(iSubActivity01);
            }
        });

        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau.this, DangNhap.class);
                startActivity(iSubActivity01);
            }
        });

    }
}