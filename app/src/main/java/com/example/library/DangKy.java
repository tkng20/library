package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.library.model.Post;
import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {

    EditText name,email,pw,conf;
    APIService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        userService = ApiUtils.getAPIService();

        Button btnDangNhap = (Button) findViewById(R.id.signIn);
        Button btnDangKy = (Button) findViewById(R.id.dk_btnDangKy);
        Button btnQuenMK = (Button) findViewById(R.id.dk_forgotPass);

        name = findViewById(R.id.signupName);
        email= findViewById(R.id.signupEmail);
        pw = findViewById(R.id.signupPass);
        conf = findViewById(R.id.signupConf);

        // Handle click event
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(DangKy.this, DangNhap.class);
                startActivity(iSubActivity01);
            }
        });

        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(DangKy.this, QuenMatKhau.class);
                startActivity(iSubActivity01);
            }
        });


// fix
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            String str_pass = pw.getText().toString();
            @Override
            public void onClick(View view) {

                // fix
//                if(str_pass.length() < 8 ){
//                    pw.setError("too short");
//                    pw.requestFocus();
//                    return;
//                }


                User user = new User();
                user.setName(name.getText().toString().trim());
                user.setEmail(email.getText().toString().trim());
                user.setPassword(pw.getText().toString().trim());
                signup(user.getName(),user.getEmail(),user.getPassword());
            }
        });
    }

    public void signup(String name, String email, String pass){
        userService.register(name,email,pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // this method is called when we get response from our api.
                Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent iSubActivity01 = new Intent(DangKy.this, DangNhap.class);
                        startActivity(iSubActivity01);
                    }
                },700);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}