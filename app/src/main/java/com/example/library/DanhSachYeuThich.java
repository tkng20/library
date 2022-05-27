package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.library.adapter.FavoriteAdapter;
import com.example.library.model.Book;
import com.example.library.model.Favorite;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachYeuThich extends AppCompatActivity {

    ImageView imageView;
    List<Favorite> list = new ArrayList<>();
    ListView listView;
    APIService bookService;
    int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachyeuthich);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        bookService = ApiUtils.getAPIService();
        listView = findViewById(R.id.listFavorite);
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        id_user = sp.getInt("id", 0);

        getFavorite();
    }

    @Override
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

    public void getFavorite()
    {
        Call<List<Favorite>> call = bookService.getFavorite(id_user);
        call.enqueue(new Callback<List<Favorite>>() {
            @Override
            public void onResponse(Call<List<Favorite>> call, Response<List<Favorite>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new FavoriteAdapter(DanhSachYeuThich.this, R.layout.list_yeuthich, list));
                }
            }
            @Override
            public void onFailure(Call<List<Favorite>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}

