package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.model.Book;
import com.example.library.model.Post;
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
    APIService bookService;
    List<Book> list = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);
        ImageView imgDSC = (ImageView) findViewById(R.id.imgTC_DanhSachCho);
        Button btnGiaHan = findViewById(R.id.btnTC_GiaHan);
        Button btnLichSu = findViewById(R.id.btnTC_LichSu);
        Button btnChoXacNhan = findViewById(R.id.btnTC_ChoXacNhan);
        Button btnTroGiup = findViewById(R.id.tc_TroGiup);

        listView = findViewById(R.id.listSachMuonNhieu);
        Button viewMore = findViewById(R.id.viewmore);
        ImageView tc_btnHome = findViewById(R.id.tc_btnHome);
        ImageView tc_btnNotice = findViewById(R.id.tc_btnNotice);
        FloatingActionButton tc_btnDiscover = findViewById(R.id.tc_btnDiscover);

        listView = findViewById(R.id.listSachMuonNhieu);
        bookService = ApiUtils.getAPIService();
        getBookList();

        heyName = findViewById(R.id.name);
        checkLogin();

        btnTroGiup.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, TroGiup.class);
            startActivity(iSubActivity01);
        });
        // Handle click event
        imgProfile.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, MainActivity.class);
            startActivity(iSubActivity01);
        });
        imgDSC.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, DanhSachCho.class);
            startActivity(iSubActivity01);
        });
        btnChoXacNhan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
        btnLichSu.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
        btnGiaHan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
        viewMore.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, XemThem.class);
            startActivity(iSubActivity01);
        });
        tc_btnHome.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, TrangChu.class);
            startActivity(iSubActivity01);
        });
        tc_btnNotice.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, ThongBao.class);
            startActivity(iSubActivity01);
        });
        tc_btnDiscover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu.this, DanhMucSach.class);
            startActivity(iSubActivity01);
        });
    }

    public void getBookList()
    {
        Call<List<Book>> call = bookService.getListBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new BookMuonNhieuAdapter(TrangChu.this, R.layout.list_sachmuonnhieu, list));
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void checkLogin(){
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("username")){
            heyName.setText("Hey "+sp.getString("username",""));
        }
        else {
            startActivity(new Intent(getApplicationContext(),DangNhap.class));
        }
    }
}