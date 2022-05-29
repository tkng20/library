package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.regex.Matcher;

public class DangKy extends AppCompatActivity {
    private boolean passwordVisible = false;
    EditText name,email,pw,conf;
    APIService userService;
    private static final String regex = "^(.+)@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        userService = ApiUtils.getAPIService();

        Button btnDangNhap = findViewById(R.id.signIn);
        Button btnDangKy = findViewById(R.id.dk_btnDangKy);
        Button btnQuenMK = findViewById(R.id.dk_forgotPass);

        name = findViewById(R.id.signupName);
        email= findViewById(R.id.signupEmail);
        pw = findViewById(R.id.signupPass);
        conf = findViewById(R.id.signupConf);

        // Handle click event
        btnDangNhap.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(DangKy.this, DangNhap.class);
            startActivity(iSubActivity01);
        });

        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSubActivity01 = new Intent(DangKy.this, QuenMatKhau.class);
                startActivity(iSubActivity01);
            }
        });

        // Handle switch between hide and view password

        pw.setOnTouchListener((view, event) -> {
            final int RIGHT = 2 ;
            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (pw.getRight() - pw.getCompoundDrawables()[RIGHT].getBounds().width() - 80)) {
                    int selection = pw.getSelectionEnd();
                    if(passwordVisible){
                        pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pw.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_lock,0,R.drawable.ic_eye_off,0);
                        passwordVisible = false;
                    }else {
                        pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pw.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_lock,0,R.drawable.ic_eye,0);
                        passwordVisible = true;
                    }
                    pw.setSelection(selection);
                    return true;
                }
            }
            return false;
        });

        conf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int RIGHT = 2 ;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (pw.getRight() - pw.getCompoundDrawables()[RIGHT].getBounds().width() - 80)) {
                        int selection = pw.getSelectionEnd();
                        if(passwordVisible){
                            conf.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            conf.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_lock,0,R.drawable.ic_eye_off,0);
                            passwordVisible = false;
                        }else {
                            conf.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            conf.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_lock,0,R.drawable.ic_eye,0);
                            passwordVisible = true;
                        }
                        conf.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        // check conditon pass and email condition
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_pass = pw.getText().toString();
                String str_pass_conf = conf.getText().toString();
                String str_email = email.getText().toString();
                String str_name = name.getText().toString();
                //initialize the Pattern object
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str_email);
                if(str_name.length() == 0){
                    Toast.makeText(DangKy.this, "Vui lòng tên toài khoản!", Toast.LENGTH_SHORT).show();
                }else if(str_name.length()<6){
                    Toast.makeText(DangKy.this, "Độ dài tài khoản phải lớn hơn 6!", Toast.LENGTH_SHORT).show();
                } else if(!Pattern.matches("[A-Za-z0-9]+",str_name)){
                    Toast.makeText(DangKy.this, "Tài khoản không được chứa kí tự đặc biệt!", Toast.LENGTH_SHORT).show();
                } else if(str_email.length() == 0){
                    Toast.makeText(DangKy.this, "Vui lòng email!", Toast.LENGTH_SHORT).show();
                } else if(!matcher.matches()){
                    Toast.makeText(DangKy.this, "Định dạng email chưa đúng!", Toast.LENGTH_SHORT).show();
                }else if(str_pass.length() == 0){
                    Toast.makeText(DangKy.this, "Vui lòng nhập mật khẩu!"  , Toast.LENGTH_SHORT).show();
                }else if(str_pass.length()<8){
                    Toast.makeText(DangKy.this, "Độ dài mật khẩu phải lớn hơn 8!", Toast.LENGTH_SHORT).show();
                } else if(!Pattern.matches("[A-Za-z0-9]+",str_pass)){
                    Toast.makeText(DangKy.this, "Mật khẩu không được chứa kí tự đặc biệt!", Toast.LENGTH_SHORT).show();
                }else if(!str_pass.equals(str_pass_conf)){
                    Toast.makeText(DangKy.this, "Mật khẩu nhập lại không trùng!", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    user.setName(name.getText().toString().trim());
                    user.setEmail(email.getText().toString().trim());
                    user.setPassword(pw.getText().toString().trim());
                    signup(user.getName(),user.getEmail(),user.getPassword());
                }
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