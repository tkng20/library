package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.adapter.HistoryAdapter;
import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangCaNhan extends AppCompatActivity {

    TextView tvName;
    SharedPreferences sp;
    ImageView imageViewAvatar3;
    APIService userService;
    ViewPager2 viewPager;
    TabLayout tabLayout;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangcanhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        imageViewAvatar3 = findViewById(R.id.imageViewAvatar3);
        sp=getSharedPreferences("credentials",MODE_PRIVATE);

        if(!sp.getString("dp","").equals("")){
            byte[] decodedString = Base64.decode(sp.getString("dp", ""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewAvatar3.setImageBitmap(decodedByte);
        }

        int id = sp.getInt("id",0);
        userService = ApiUtils.getAPIService();
        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response){
                if(response.isSuccessful()) {
                    final User user = response.body();
                    if(user.getAvatar() != null) {
                        byte[] decodedString = Base64.decode(user.getAvatar(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageViewAvatar3.setImageBitmap(decodedByte);
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

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
        ImageView imgCaiDat = (ImageView) findViewById(R.id.imgCaiDat);

        checkLogin();

        // Handle click event
        btnProfile.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, ThongTinCaNhan.class);
            startActivity(iSubActivity01);
        });

        btnChinhSua.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, ThongTinCaNhan.class);
            startActivity(iSubActivity01);
        });

        btnDSYT.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, DanhSachYeuThich.class);
            startActivity(iSubActivity01);
        });

        btnLichSu.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtCXN.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtDangMuon.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtDaMuon.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        txtDenHan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, Lichsu.class);
            startActivity(iSubActivity01);
        });

        imgCaiDat.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangCaNhan.this, CaiDat.class);
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
        editor.remove("dp").commit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(getApplicationContext(),DangNhap.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        } return true;
    }
}