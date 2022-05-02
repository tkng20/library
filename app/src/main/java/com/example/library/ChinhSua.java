package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.model.Post;
import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSua extends AppCompatActivity {

    APIService userService;
    RadioGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_chinhsua);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        Button btnUpdate = (Button) findViewById(R.id.btn_Update);

        gender = findViewById(R.id.gender);

        RadioButton nu = findViewById(R.id.nu);
        RadioButton nam= findViewById(R.id.nam);

        userService = ApiUtils.getAPIService();

        TextView name = findViewById(R.id.tvName);
        TextView editName = findViewById(R.id.editName);
        TextView editEmail = findViewById(R.id.editEmail);
        TextView editPhone = findViewById(R.id.editPhone);

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        if(sp.contains("id")){
            name.setText(String.valueOf(id));
        }

        Bundle extras = getIntent().getExtras();
        String hoten = extras.getString("hoten");
        String email = extras.getString("email");
        String phone = extras.getString("phone");

        editName.setText(hoten);
        editEmail.setText(email);
        editPhone.setText(phone);

        btnUpdate.setOnClickListener(v -> {
            User u = new User ();
            u.setName(editName.getText().toString());
            u.setEmail(editEmail.getText().toString());
            u.setPhone(editPhone.getText().toString());
            int radId = gender.getCheckedRadioButtonId();
            if (radId == R.id.nu) {
                u.setGender("Nữ");
            } else {
                u.setGender("Nam");
            }
            updateUser(id, u);
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

    public void updateUser(int id, User u){
        Call<User> call = userService.updateUser(id, u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ChinhSua.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent iSubActivity01 = new Intent(ChinhSua.this, Thongtincanhan.class);
                            startActivity(iSubActivity01);
                        }
                    }, 50);
                }
                finish();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
