package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.Post;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {
    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    APIService userService;
    List<Post> list = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_main);

        setTitle("Retrofit 2 CRUD Demo");

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);
        userService = ApiUtils.getAPIService();

        btnGetUsersList.setOnClickListener(v -> {
            getUsersList();
        });

        btnAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(Main.this, PostTest.class);
            startActivity(intent);
        });
    }

    public void getUsersList(){
        Call<List<Post>> call = userService.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new PostAdapter(Main.this, R.layout.p_list_post, list));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}