package com.example.library;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.model.User;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSua extends AppCompatActivity {

    APIService userService;
    RadioGroup gender;
    ImageView imageViewAvatar;
    ImageView imageViewChange;
    int id;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_chinhsua);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Button btnUpdate = (Button) findViewById(R.id.btn_Update);
        sp=getSharedPreferences("credentials",MODE_PRIVATE);
        userService = ApiUtils.getAPIService();
        id = sp.getInt("id",0);

// Change Avatar
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        imageViewChange = findViewById(R.id.imageViewChange);
        if(!sp.getString("dp","").equals("")){
            byte[] decodedString = Base64.decode(sp.getString("dp", ""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewAvatar.setImageBitmap(decodedByte);
        }

        imageViewChange.setOnClickListener(view -> ImagePicker.Companion.with(ChinhSua.this)
                .crop()
                .cropSquare()//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(100, 100)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        );

        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response){
                if(response.isSuccessful()) {
                    final User user = response.body();
                    if(user.getAvatar() != null) {
                        byte[] decodedString = Base64.decode(user.getAvatar(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageViewAvatar.setImageBitmap(decodedByte);
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });


//Update profile
        gender = findViewById(R.id.gender);

        RadioButton nu = findViewById(R.id.nu);
        RadioButton nam= findViewById(R.id.nam);

        TextView name = findViewById(R.id.tvName);
        TextView editName = findViewById(R.id.editName);
        TextView editEmail = findViewById(R.id.editEmail);
        TextView editPhone = findViewById(R.id.editPhone);
        TextView editBirthday = findViewById(R.id.birthday);

        if(sp.contains("id")){
            name.setText(String.valueOf(id));
        }

        Bundle extras = getIntent().getExtras();
        String hoten = extras.getString("hoten");
        String email = extras.getString("email");
        String phone = extras.getString("phone");
        String birthday = extras.getString("birthday");

        editName.setText(hoten);
        editEmail.setText(email);
        editPhone.setText(phone);
        editBirthday.setText(birthday);

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
            u.setBirthday(editBirthday.getText().toString());
            updateUser(id, u);

            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username_after", editName.getText().toString());
            editor.commit();
            editor.apply();
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
                    finish();
                    Toast.makeText(ChinhSua.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent iSubActivity01 = new Intent(ChinhSua.this, ThongTinCaNhan.class);
                            startActivity(iSubActivity01);
                        }
                    }, 50);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateAvatarUser(int id, User u){
        Call<User> call = userService.updateAvatarUser(id, u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ChinhSua.this, "User updated avatar successfully!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent iSubActivity01 = new Intent(ChinhSua.this, ThongTinCaNhan.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            User user = new User();
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            sp.edit().putString("dp", encodedImage).commit();
            imageViewAvatar.setImageBitmap(selectedImage);
            user.setAvatar(encodedImage);
            updateAvatarUser(id, user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
