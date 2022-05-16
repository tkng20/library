package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Thongtincanhan extends AppCompatActivity {

    APIService userService;
    TextView hoTen,name,email,phone,gioitinh;
    int id;
    User user;
    SharedPreferences sp;
    ImageView imageViewAvatar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        imageViewAvatar2 = findViewById(R.id.imageViewAvatar2);

        sp=getSharedPreferences("credentials",MODE_PRIVATE);

        if(!sp.getString("dp","").equals("")){
            byte[] decodedString = Base64.decode(sp.getString("dp", ""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewAvatar2.setImageBitmap(decodedByte);
        }


        name = findViewById(R.id.tvName);
        hoTen = findViewById(R.id.tvHoTen);
        email = findViewById(R.id.tvEmail);
        phone = findViewById(R.id.tvPhone);
        gioitinh = findViewById(R.id.tvGioiTinh);

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        if(sp.contains("id")){
            name.setText(String.valueOf(id));
        }

        userService = ApiUtils.getAPIService();
        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    final User user = response.body();
                    hoTen.setText(user.getName());
                    email.setText(user.getEmail());
                    if(user.getPhone() != null) {
                        phone.setText(user.getPhone());
                    }
                    else phone.setText("chưa có");
                    if(user.getGender() != null) {
                        gioitinh.setText(user.getGender());
                    }
                    else gioitinh.setText("chưa có");
                    if(user.getAvatar() != null) {
                        byte[] decodedString = Base64.decode(user.getAvatar(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageViewAvatar2.setImageBitmap(decodedByte);
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });


        Button btnEdit = findViewById(R.id.btn_Update);
        // Handle click event
        btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChinhSua.class);
            intent.putExtra("hoten",hoTen.getText());
            intent.putExtra("email",email.getText());
            intent.putExtra("phone",phone.getText());
            this.startActivity(intent);
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
}
