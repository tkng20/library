package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu2 extends AppCompatActivity {

    TextView heyName;
    APIService bookService;
    List<Book> list, list2 = new ArrayList<>();
    ListView listView;
    Book book1;
    CategoryAdapter categoryAdapter;
    RecyclerView rcv_cate;

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

        bookService = ApiUtils.getAPIService();
        Call<List<Book>> call = bookService.getListBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    Log.e("test",list.get(2).getTenSach().toString());
                    book1.setTenSach(list.get(2).getTenSach().toString());
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

//        listView = findViewById(R.id.listSachMuonNhieu);
//        Button viewMore = findViewById(R.id.viewmore);
        ImageView tc_btnHome = findViewById(R.id.tc_btnHome);
        ImageView tc_btnNotice = findViewById(R.id.tc_btnNotice);
        FloatingActionButton tc_btnDiscover = findViewById(R.id.tc_btnDiscover);

//        listView = findViewById(R.id.listSachMuonNhieu);
        categoryAdapter = new CategoryAdapter(this);
        rcv_cate = findViewById(R.id.rcv_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_cate.setLayoutManager(linearLayoutManager);
        categoryAdapter.setData(getListCategory());
        rcv_cate.setAdapter(categoryAdapter);

        heyName = findViewById(R.id.name);
        checkLogin();

        btnTroGiup.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, TroGiup.class);
            startActivity(iSubActivity01);
        });
        // Handle click event
        imgProfile.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, MainActivity.class);
            startActivity(iSubActivity01);
        });
        imgDSC.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, DanhSachCho.class);
            startActivity(iSubActivity01);
        });
        btnChoXacNhan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
        btnLichSu.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
        btnGiaHan.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, Lichsu.class);
            startActivity(iSubActivity01);
        });
//        viewMore.setOnClickListener(view -> {
//            Intent iSubActivity01 = new Intent(TrangChu.this, XemThem.class);
//            startActivity(iSubActivity01);
//        });
        tc_btnHome.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, TrangChu2.class);
            startActivity(iSubActivity01);
        });
        tc_btnNotice.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, ThongBao.class);
            startActivity(iSubActivity01);
        });
        tc_btnDiscover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(TrangChu2.this, DanhMucSach.class);
            startActivity(iSubActivity01);
        });
    }

   // cần làm

    public void getBookList() {
        Call<List<Book>> call = bookService.getListBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
//                    listView.setAdapter(new BookMuonNhieuAdapter(TrangChu.this, R.layout.list_sachmuonnhieu, list));
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

    public List<Category> getListCategory(){

        List<Category> listCategory = new ArrayList<>();

        List<Book> listBook = new ArrayList<>();
        listBook.add(book1);

        listCategory.add(new Category("loai1",listBook));
        listCategory.add(new Category("loai2",listBook));
        listCategory.add(new Category("loai3",listBook));
        listCategory.add(new Category("loai4",listBook));

        return listCategory;
    }
}