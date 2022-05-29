package com.example.library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaoMKMoi extends AppCompatActivity {
    Button closeButton;
    AlertDialog.Builder builder;

    APIService userService;
    int id_user;
    String mail;
    boolean check;
    SharedPreferences sp;
    EditText pw_new,pw_old,conf;
    private static final String regex = "^(.+)@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        userService = ApiUtils.getAPIService();

        sp=getSharedPreferences("credentials",MODE_PRIVATE);
        id_user = sp.getInt("id",0);
        mail = sp.getString("mail","");

        closeButton = findViewById(R.id.btn_DoiMatKhau);
        pw_new = findViewById(R.id.pw_new);
        pw_old = findViewById(R.id.pw_old);
        conf = findViewById(R.id.pw_cf);

        builder = new AlertDialog.Builder(this);
        closeButton.setOnClickListener(v -> {

            String str_pass_current = pw_old.getText().toString();
            String str_pass = pw_new.getText().toString();
            String str_pass_conf = conf.getText().toString();
            //initialize the Pattern object
            Pattern pattern = Pattern.compile(regex);
            if(str_pass.length() == 0){
                Toast.makeText(TaoMKMoi.this, "Vui lòng nhập mật khẩu!"  , Toast.LENGTH_SHORT).show();
            }else if(str_pass.length()<8){
                Toast.makeText(TaoMKMoi.this, "Độ dài mật khẩu phải lớn hơn 8!", Toast.LENGTH_SHORT).show();
            } else if(!Pattern.matches("[A-Za-z0-9]+",str_pass)){
                Toast.makeText(TaoMKMoi.this, "Mật khẩu không được chứa kí tự đặc biệt!", Toast.LENGTH_SHORT).show();
            }else if(!str_pass.equals(str_pass_conf)){
                Toast.makeText(TaoMKMoi.this, "Mật khẩu nhập lại không trùng!", Toast.LENGTH_SHORT).show();
            }
            else if(!checkPassword(mail,str_pass_current)){
                Toast.makeText(TaoMKMoi.this, "Mật khẩu hiện tại k đúng!", Toast.LENGTH_SHORT).show();
            }
            else {

//                Toast.makeText(TaoMKMoi.this, "Success", Toast.LENGTH_SHORT).show();

//            Uncomment the below code to Set the message and title from the strings.xml file
            builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

            //Setting message manually and performing action on button click
            builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu không ?")
                    .setCancelable(false)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            User u = new User ();
                            u.setPassword(pw_new.getText().toString());
                            updatePassword(id_user,u);
                        }
                    })
                    .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"Đổi mật không thành công",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Thông báo xác nhận");
            alert.setIcon(R.drawable.alert);
            alert.show();
            }
        });
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

    public void updatePassword(int id, User u){
        Call<User> call = userService.updatePassword(id, u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    finish();
                    Toast.makeText(getApplicationContext(),"Mật khẩu đã thay đổi thành công",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public boolean checkPassword(String email, String password){
        Call<String> call = userService.checkPassword(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if (response.body().contains("true")) {
                        check = true;
                    } else check = false;
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        return check;
    }
}
