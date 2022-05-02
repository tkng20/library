package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangcanhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        // View by id
        Button btnProfile = (Button) findViewById(R.id.btnProfile);
        Button btnChinhSua = (Button) findViewById(R.id.btnChinhSua);
        Button btnDSYT = (Button) findViewById(R.id.btnDSYT);
        Button btnLichSu = (Button) findViewById(R.id.btnLichSu);

        Button btnDangXuat = findViewById(R.id.btnDangXuat);
        tvName = findViewById(R.id.tvName);

        TextView txtCXN = (TextView) findViewById(R.id.txtCXN);
        TextView txtDangMuon = (TextView) findViewById(R.id.txtDangMuon);
        TextView txtDaMuon = (TextView) findViewById(R.id.txtDaMuon);
        TextView txtDenHan = (TextView) findViewById(R.id.txtDenHan);
        ImageView imgDSC = (ImageView) findViewById(R.id.imgDSC);
        ImageView imgCaiDat = (ImageView) findViewById(R.id.imgCaiDat);

        checkLogin();

        // Handle click event
        btnProfile.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, Thongtincanhan.class);
            startActivity(iSubActivity01);
        });

        btnChinhSua.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, ChinhSua.class);
            startActivity(iSubActivity01);
        });

        btnDSYT.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, DanhSachYeuThich.class);
            startActivity(iSubActivity01);
        });

        btnLichSu.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtCXN.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtDangMuon.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtDaMuon.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtDenHan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        imgDSC.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, DanhSachCho.class);
            startActivity(iSubActivity01);
        });

        imgCaiDat.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(MainActivity.this, CaiDat.class);
            startActivity(iSubActivity01);
        });

        btnDangXuat.setOnClickListener(view -> {
            logout();
        });
    }

    public void checkLogin(){
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("username")){
            tvName.setText(sp.getString("username",""));
        }
        else {
            startActivity(new Intent(getApplicationContext(),DangNhap.class));
        }
    }

    public void logout(){
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("username").commit();
        editor.remove("email").commit();
        editor.remove("password").commit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(getApplicationContext(),DangNhap.class));
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
}