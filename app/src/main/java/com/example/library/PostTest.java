package com.example.library;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.Post;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostTest extends AppCompatActivity {

    APIService userService;
    EditText edtName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_add_post);

        setTitle("Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = (EditText) findViewById(R.id.edt_name);
        btnSave = (Button) findViewById(R.id.save);

        userService = ApiUtils.getAPIService();

        btnSave.setOnClickListener(v -> {
            Post u = new Post();
            u.setName(edtName.getText().toString().trim());
            postData(u.getName());
            }
        );
    }

    public void postData(String name) {

        userService.addPost(name).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                // this method is called when we get response from our api.
                Toast.makeText(PostTest.this, "Data added successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}