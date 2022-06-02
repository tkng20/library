package com.example.library;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.library.model.Borrow;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacNhanMuon extends AppCompatActivity {

    Button btnXacNhanMuon,btnChonNgay;
    APIService userService;
    int id_user;
    int id_book;
    String date;
    Date ngaydk;
    TextView title;

    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhanmuon);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("My Channel", "My Channel", importance);
            channel.setDescription("aaa");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        btnChonNgay = findViewById(R.id.chonngay);
        btnXacNhanMuon = findViewById(R.id.btnXNM1);
        btnXacNhanMuon.setOnClickListener(this::onClick);
        userService = ApiUtils.getAPIService();

        ImageView home = findViewById(R.id.home);
        ImageView notification = findViewById(R.id.notification);
        FloatingActionButton discover = findViewById(R.id.discover);
        TextView ten = findViewById(R.id.m_ten);
        TextView email = findViewById(R.id.m_email);
        TextView phone = findViewById(R.id.m_phone);
        TextView title = findViewById(R.id.m_title);

        Bundle extras = getIntent().getExtras();
        final int sach_id = extras.getInt("maSach");
        final String tenSach = extras.getString("tenSach");
        final String i_ten = extras.getString("ten");
        final String i_email = extras.getString("email");
        final String i_phone = extras.getString("phone");
        final int i_year = extras.getInt("year");
        final int i_month = extras.getInt("month");
        final int i_date = extras.getInt("date");


        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        id_user = sp.getInt("id",0);
        id_book = sach_id;
        date = String.valueOf(i_date)+"/"+String.valueOf(i_month)+"/"+String.valueOf(i_year);
//        String str = "2017-09-11";
        String str = String.valueOf(i_year)+"-"+String.valueOf(i_month)+"-"+String.valueOf(i_date);
        // converting string into sql date
        ngaydk = Date.valueOf(str);

        title.setText(tenSach);
        ten.setText(i_ten);
        email.setText(i_email);
        phone.setText(i_phone);

        btnChonNgay.setText(i_date+"/"+i_month+"/"+i_year);

        home.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(XacNhanMuon.this, TrangChu.class);
            startActivity(iSubActivity01);
        });

        discover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(XacNhanMuon.this, DanhMucSach.class);
            startActivity(iSubActivity01);
        });

        notification.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(XacNhanMuon.this, ThongBao.class);
            startActivity(iSubActivity01);
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

    public void onClick(View view) {
        MaterialAlertDialogBuilder test = new MaterialAlertDialogBuilder(this);
        test.setTitle("Xác nhận mượn")
                .setMessage("Bạn sẽ đến lấy vào ngày "+date)
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        borrow(id_user,id_book,ngaydk);
                    }
                })
                .show();
    }

    public void borrow(int user_id, int book_id,Date ngaydk) {
        userService.addBorrow(user_id,book_id,"0",ngaydk).enqueue(new Callback<Borrow>() {
            @Override
            public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                // this method is called when we get response from our api.
                if (response.isSuccessful()){
                        Intent intent = new Intent(XacNhanMuon.this, TrangChu.class);
                        startActivity(intent);
                        Toast.makeText(XacNhanMuon.this, "Đăng ký mượn thành công", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Borrow> call, Throwable t) {
                Toast.makeText(XacNhanMuon.this, "Bạn đang mượn quyển này", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
