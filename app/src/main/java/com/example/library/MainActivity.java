package com.example.library;

import static com.example.library.TrangChu.bookService;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.adapter.HomeAdapter;
import com.example.library.adapter.MainAdapter;
import com.example.library.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Object> objects = new ArrayList<>();
    ArrayList<Book> listBook = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        HomeAdapter adapter = new HomeAdapter(this, getObject());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<Object> getObject() {
        objects.add(getBookData().get(0));
        objects.add(getBookData().get(1));
        objects.add(getBookData().get(2));
        return objects;
    }

    public static ArrayList<Book> getBookData() {
        ArrayList<Book> singleHorizontals = new ArrayList<>();
        Call<List<Book>> call = bookService.getListBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {
                    singleHorizontals.clear();
                    singleHorizontals.addAll(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        return singleHorizontals;
    }
}
