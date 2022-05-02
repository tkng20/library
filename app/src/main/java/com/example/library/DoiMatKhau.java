package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DoiMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        // View by id
        Button btnGuiEmail = (Button) findViewById(R.id.btnGuiEmail);
        // Handle click event
        btnGuiEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(DoiMatKhau.this, TaoMKMoi.class);
                startActivity(iSubActivity01);
            }
        });
    }
}