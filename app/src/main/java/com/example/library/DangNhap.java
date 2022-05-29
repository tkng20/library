package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {

    APIService userService;
    EditText email,password,email1,password1;
    User user;
    ListView listView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        userService = ApiUtils.getAPIService();
        onBackPressed();

        Button btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        Button btnDangKy = (Button) findViewById(R.id.signUp);
        Button btnQuenMK = (Button) findViewById(R.id.forgotPass);
        email = findViewById(R.id.signupName);
        password = findViewById(R.id.signupPass);

        email1 = findViewById(R.id.signupName);
        password1 = findViewById(R.id.signupPass);

        checkLoginExist();

        // Handle click event
        btnDangNhap.setOnClickListener(view -> {
            User user = new User();
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());
            login(user.getEmail(),user.getPassword());
        });

        btnDangKy.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(DangNhap.this, DangKy.class);
            startActivity(iSubActivity01);
        });

        btnQuenMK.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(DangNhap.this, QuenMatKhau.class);
            startActivity(iSubActivity01);
        });
    }

    public void login(String email, String password) {
        Call<User> loginResponseCall = userService.login(email,password);
        loginResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",response.body().getName().toString());
                    editor.putString("mail",response.body().getEmail().toString());
                    editor.putInt("id", response.body().getId());
                    editor.putString("password",password1.getText().toString());
                    editor.commit();
                    editor.apply();
                    new Handler().postDelayed(() -> {
                        Intent iSubActivity01 = new Intent(DangNhap.this, TrangChu.class);
                        startActivity(iSubActivity01);
                    },500);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DangNhap.this,"Tài khoản / Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkLoginExist(){
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("username")){
            startActivity(new Intent(getApplicationContext(),TrangChu.class));
        }
        else {
            Toast.makeText(getApplicationContext(),"Đăng nhập lại",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void findViewByIds(){

    }

    public void onBackPressed() {
    }

}