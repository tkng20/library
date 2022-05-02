package com.example.library;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Muon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btn;
    TextView title,ten,email,phone;
    APIService userService;
    int id_sach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muon_2);
        btn = findViewById(R.id.btnChoose);
        FloatingActionButton btnDiscover = findViewById(R.id.discover);
        View btnHome = findViewById(R.id.home);
        View btnNotice = findViewById(R.id.notice);
        title = findViewById(R.id.muon_title);
        ten = findViewById(R.id.m_ten);
        email = findViewById(R.id.m_email);
        phone = findViewById(R.id.m_phone);

        btn.setOnClickListener(v -> showDatePickerDialog());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Bundle extras = getIntent().getExtras();
        final String tenSach = extras.getString("tenSach");
        final int maSach = extras.getInt("maSach");

        title.setText(tenSach);
        id_sach =maSach;

        btnHome.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(Muon.this, TrangChu.class);
            startActivity(iSubActivity01);
        });

        btnDiscover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(Muon.this, DanhMucSach.class);
            startActivity(iSubActivity01);
        });

        btnNotice.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(Muon.this, ThongBao.class);
            startActivity(iSubActivity01);
        });

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        int id = sp.getInt("id",0);

        userService = ApiUtils.getAPIService();
        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();
                    ten.setText(user.getName().toString());
                    email.setText(user.getEmail().toString());
                    if(user.getPhone() != null) {
                        phone.setText(user.getPhone().toString());
                    }
                    else phone.setText("chưa có");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
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

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Intent intent = new Intent(Muon.this, XacNhanMuon.class);
        intent.putExtra("maSach",id_sach);
        intent.putExtra("tenSach",title.getText());
        intent.putExtra("ten",ten.getText());
        intent.putExtra("email",email.getText());
        intent.putExtra("phone",phone.getText());
        intent.putExtra("year",i);
        intent.putExtra("month",i1+1);
        intent.putExtra("date",i2);
        startActivity(intent);
    }
}
