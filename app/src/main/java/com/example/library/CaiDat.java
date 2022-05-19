package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CaiDat extends AppCompatActivity {

    Button btnXoaTK;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        Button btnDoiMK = findViewById(R.id.btnDoiMatKhau);
        btnXoaTK = findViewById(R.id.btnXoaTK);
        builder = new AlertDialog.Builder(this);
        Button btnCaiDatNhacNho = findViewById(R.id.btnCaiDatNhacNho);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);

        btnDoiMK.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(CaiDat.this, TaoMKMoi.class);
            startActivity(iSubActivity01);
        });

        btnXoaTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Bạn có chắc chắn muốn xóa tài khoản không ?")
                        .setCancelable(false)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Tài khoản đã xóa thành công",
                                        Toast.LENGTH_SHORT).show();
                                Intent iSubActivity01 = new Intent(CaiDat.this, DangNhap.class);
                                startActivity(iSubActivity01);
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Xóa tài khoản không thành công",
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
}