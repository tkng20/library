package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMatKhau extends AppCompatActivity {

    EditText email;
    APIService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mat_khau);

        email = findViewById(R.id.email);
        Button btn_sendEmail = (Button) findViewById(R.id.btn_sendEmail);
        Button btnDangKy = (Button) findViewById(R.id.qmk_dangky);
        Button btnQuenMK = (Button) findViewById(R.id.qmk_dangnhap);
        userService = ApiUtils.getAPIService();

        // Handle click event
        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(email.getText().toString());
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau.this, DangKy.class);
                startActivity(iSubActivity01);
            }
        });

        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(QuenMatKhau.this, DangNhap.class);
                startActivity(iSubActivity01);
            }
        });
    }

    public void  sendEmail(String email){
        Call<String> call = userService.sendEmail(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"hãy kiểm tra hộp thư của bạn",
                            Toast.LENGTH_SHORT).show();
                    Intent iSubActivity01 = new Intent(QuenMatKhau.this, DangNhap.class);
                    startActivity(iSubActivity01);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}