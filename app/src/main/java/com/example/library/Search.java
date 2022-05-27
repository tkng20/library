package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.adapter.BookAdapter;
import com.example.library.adapter.SearchAdapter;
import com.example.library.model.Book;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    ListView listView2;
    String[] name = {"em yêu","em yêu anh","em yêu anh siêu nhiều"};
    ArrayAdapter<String> arrayAdapter;

    private RecyclerView rcvsearch;
    private SearchAdapter searchAdapter;
    public List<Book> returnedList2 = new ArrayList<>();
    APIService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        bookService = ApiUtils.getAPIService();

        rcvsearch = findViewById(R.id.rcv_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvsearch.setLayoutManager(linearLayoutManager);

        Call<List<Book>> call = bookService.getListBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {
                    searchAdapter = new SearchAdapter(response.body());
                    rcvsearch.setAdapter(searchAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

//        searchAdapter = new SearchAdapter(getListBook());
//        rcvsearch.setAdapter(searchAdapter);


//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);
//        listView2.setAdapter(arrayAdapter);

    }

//    private List<Book> getListBook() {
////        List<Book> list = new ArrayList<>();
////        Book book = new Book();
////        book.setTenSach("AAA");
////        list.add(book);
//        return returnedList2;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type something");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
        return true;
    }
}
