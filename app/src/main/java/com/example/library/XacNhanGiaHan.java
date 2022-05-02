package com.example.library;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class XacNhanGiaHan extends AppCompatActivity {

    Button xngh;

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
                .setMessage("Bạn đồng ý gia hạn  “Giáo trình chuẩn HSK - 2 “ đến ngày 12/04/2022")
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent iSubActivity01 = new Intent(XacNhanGiaHan.this, MainActivity.class);
                        startActivity(iSubActivity01);
                    }
                })
                .show();
    }
}
