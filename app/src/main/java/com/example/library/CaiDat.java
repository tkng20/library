package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaiDat extends AppCompatActivity {

    Button btnXoaTK;

    AlertDialog.Builder builder;
    SharedPreferences sp;
    ImageView avatar;
    APIService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Button btnDoiMK = findViewById(R.id.btnDoiMatKhau);
        btnXoaTK = findViewById(R.id.btnXoaTK);
        builder = new AlertDialog.Builder(this);
        Button btnCaiDatNhacNho = findViewById(R.id.btnCaiDatNhacNho);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);
        TextView profile = findViewById(R.id.profile);
        avatar = findViewById(R.id.cd_image);

        sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("username")) {
            profile.setText(sp.getString("username", ""));
        }

        if(!sp.getString("dp","").equals("")){
            byte[] decodedString = Base64.decode(sp.getString("dp", ""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            avatar.setImageBitmap(decodedByte);
        }

        int id = sp.getInt("id",0);
        userService = ApiUtils.getAPIService();
        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response){
                if(response.isSuccessful()) {
                    final User user = response.body();
                    if(user.getAvatar() != null) {
                        byte[] decodedString = Base64.decode(user.getAvatar(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        avatar.setImageBitmap(decodedByte);
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

            btnDoiMK.setOnClickListener(view -> {
                Intent iSubActivity01 = new Intent(CaiDat.this, TaoMKMoi.class);
                startActivity(iSubActivity01);
            });

            profile.setOnClickListener(view -> {
                Intent iSubActivity01 = new Intent(CaiDat.this, TrangChu.class);
                startActivity(iSubActivity01);
            });

            btnXoaTK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                    //Setting message manually and performing action on button click
                    builder.setMessage("Bạn có chắc chắn muốn xóa tài khoản không ?")
                            .setCancelable(false)
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    Toast.makeText(getApplicationContext(), "Tài khoản đã xóa thành công",
                                            Toast.LENGTH_SHORT).show();
                                    Intent iSubActivity01 = new Intent(CaiDat.this, DangNhap.class);
                                    startActivity(iSubActivity01);
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Xóa tài khoản không thành công",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Thông báo xác nhận");
                    alert.setIcon(R.drawable.delete);
                    alert.show();
                }
            });

            btnCaiDatNhacNho.setOnClickListener(view -> {
                Intent iSubActivity01 = new Intent(CaiDat.this, NhacNho.class);
                startActivity(iSubActivity01);
            });

            btnGioiThieu.setOnClickListener(view -> {
                Intent iSubActivity01 = new Intent(CaiDat.this, GioiThieu.class);
                startActivity(iSubActivity01);
            });
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    super.onBackPressed();
                    break;
            }
            return true;
        }
}