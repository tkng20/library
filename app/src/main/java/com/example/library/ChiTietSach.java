package com.example.library;

import static com.example.library.R.*;
import static com.example.library.R.drawable.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.library.adapter.BookAdapter;
import com.example.library.adapter.BookBorrow_CXN_Adapter;
import com.example.library.model.Book;
import com.example.library.model.Borrow;
import com.example.library.model.BorrowResponse;
import com.example.library.model.Favorite;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSach extends AppCompatActivity {

    APIService userService;
    int id_user;
    int id_book;
    ToggleButton heart;
    public int id_favorite;
    List<Favorite> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_chitietsach);
        userService = ApiUtils.getAPIService();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setBackgroundDrawable (new ColorDrawable(Color.parseColor ("#4977F3")));

        Button btnMuonSach = (Button) findViewById(id.cts_muonSach);
        TextView title = findViewById(id.tensach);
        TextView author = findViewById(id.tacgia);
        TextView cate = findViewById(id.theloai);
        TextView num = findViewById(id.soluong);
        TextView num_page = findViewById(id.sotrang);
        TextView publish_date = findViewById(id.ngayxb);
        TextView des = findViewById(id.mota);
        ImageView image = findViewById(id.imageBook);
        heart = findViewById(id.heartButton);

        Bundle extras = getIntent().getExtras();
        final String tenSach = extras.getString("tenSach");
        final String tacGia = extras.getString("tacGia");
        final String theLoai = extras.getString("theLoai");
        final String soLuong = extras.getString("soLuong");
        final String soTrang = extras.getString("soTrang");
        final String ngayXB = extras.getString("ngayXB");
        final String moTa = extras.getString("moTa");
        final String anhBia = extras.getString("image");
        final int book_id = extras.getInt("book_id");
        int maSach = book_id;
        id_book = book_id;

        title.setText(tenSach);
        author.setText(tacGia);
        cate.setText(theLoai);
        num.setText(soLuong);
        num_page.setText(soTrang);
        publish_date.setText(ngayXB);
        des.setText(moTa);
        Picasso.with(ChiTietSach.this).load("http://3.0.59.80/test/public/public/storage/"+anhBia).into(image);
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        id_user = sp.getInt("id", 0);

        // Handle click event
        btnMuonSach.setOnClickListener(view -> {
            Intent intent = new Intent(ChiTietSach.this, Muon.class);
            intent.putExtra("tenSach", tenSach);
            intent.putExtra("maSach", maSach);
            startActivity(intent);
        });

        getFavorite();

        heart.setOnClickListener(view -> {
            if(heart.isChecked()){
                favorite(id_user,id_book);
            }
            else unfavorite();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        } return true;
    }

    public void favorite(int user_id, int id_book) {
        userService.addFavorite(user_id,id_book).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                // this method is called when we get response from our api.
                if(response.isSuccessful()) {
                    Toast.makeText(ChiTietSach.this, "Đã thích", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void unfavorite() {
        userService.deleteFavorite(id_favorite).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                // this method is called when we get response from our api.
                if(response.isSuccessful()) {
                    heart.setChecked(false);
                    Toast.makeText(ChiTietSach.this, "Bỏ thích", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void getFavorite()
    {
        Call<List<Favorite>> call = userService.getFavorite(id_user);
        call.enqueue(new Callback<List<Favorite>>() {
            @Override
            public void onResponse(Call<List<Favorite>> call, Response<List<Favorite>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    for (int i =0; i< list.size();i++){
                        if(list.get(i).getUser_id()==id_user & list.get(i).getBook_id()==id_book) {
                            id_favorite = list.get(i).getId();
                            heart.setChecked(true);
                            break;
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Favorite>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}