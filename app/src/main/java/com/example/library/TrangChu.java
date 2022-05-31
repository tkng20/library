package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.material.icons.sharp.BoltKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.adapter.BookAdapter;
import com.example.library.adapter.CategoryAdapter;
import com.example.library.adapter.HomeAdapter;
import com.example.library.model.Book;
import com.example.library.model.Categories;
import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity {

    TextView heyName;
    static APIService bookService;
    CategoryAdapter categoryAdapter;
    RecyclerView rcv_cate;
    SharedPreferences sp;
    ImageView imageViewAvatar3;
    APIService service;
    int id;
    public List<Categories> listCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        bookService = ApiUtils.getAPIService();
        onBackPressed();


        ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);
        ImageView imgDSC = (ImageView) findViewById(R.id.imgTC_DanhSachCho);
        Button btnGiaHan = findViewById(R.id.btnTC_GiaHan);
        Button btnLichSu = findViewById(R.id.btnTC_LichSu);
        Button btnChoXacNhan = findViewById(R.id.btnTC_ChoXacNhan);
        Button btnTroGiup = findViewById(R.id.tc_TroGiup);
        ImageView tc_btnHome = findViewById(R.id.tc_btnHome);
        ImageView tc_btnNotice = findViewById(R.id.tc_btnNotice);
        FloatingActionButton tc_btnDiscover = findViewById(R.id.tc_btnDiscover);
        heyName = findViewById(R.id.name);


        imageViewAvatar3 = findViewById(R.id.imgProfile);
        sp=getSharedPreferences("credentials",MODE_PRIVATE);

        if(!sp.getString("dp","").equals("")){
            byte[] decodedString = Base64.decode(sp.getString("dp", ""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewAvatar3.setImageBitmap(decodedByte);
        }

        id = sp.getInt("id",0);
        service = ApiUtils.getAPIService();
        user();
        getCategories();
        checkLogin();


        btnTroGiup.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, TroGiup.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
        imgProfile.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, TrangCaNhan.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
        btnChoXacNhan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, Lichsu.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
        btnLichSu.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, Lichsu.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
        btnGiaHan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
        tc_btnHome.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, TrangChu.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
        tc_btnNotice.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, ThongBao.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
        tc_btnDiscover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, DanhMucSach.class);
            startActivity(iSubActivity01);
            overridePendingTransition(0,0);
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void checkLogin(){
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("username") & !sp.contains("username_after")){
            heyName.setText("Hey "+sp.getString("username",""));
        }
        else if (sp.contains("username_after")){
            heyName.setText("Hey "+sp.getString("username_after",""));
        }
        else{
            startActivity(new Intent(getApplicationContext(),DangNhap.class));
        }
    }

    public List<Categories> getListCategory(){
        Call<List<Categories>> call = bookService.getListCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if(response.isSuccessful()) {
                    listCategories.clear();
                    listCategories.addAll(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        return listCategories;
    }

    public void getCategories()
    {
        Call<List<Categories>> call = bookService.getListCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if(response.isSuccessful()) {
                    listCategories = response.body();
                    categoryAdapter = new CategoryAdapter(TrangChu.this);
                    rcv_cate = findViewById(R.id.rcv_category);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TrangChu.this,RecyclerView.VERTICAL,false);
                    rcv_cate.setLayoutManager(linearLayoutManager);
                    categoryAdapter.setData(listCategories);
                    rcv_cate.setAdapter(categoryAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void user(){
        Call<User> call = service.getUser(id);
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
    }
}