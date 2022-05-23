package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ChiTietSach extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsach);
        Button btnMuonSach = (Button) findViewById(R.id.cts_muonSach);
        TextView title = findViewById(R.id.tensach);
        TextView author = findViewById(R.id.tacgia);
        TextView cate = findViewById(R.id.theloai);
        TextView num = findViewById(R.id.soluong);
        TextView num_page = findViewById(R.id.sotrang);
        TextView publish_date = findViewById(R.id.ngayxb);
        TextView des = findViewById(R.id.mota);

        Bundle extras = getIntent().getExtras();
        final String tenSach = extras.getString("tenSach");
        final String tacGia = extras.getString("tacGia");
        final String theLoai = extras.getString("theLoai");
        final String soLuong = extras.getString("soLuong");
        final String soTrang = extras.getString("soTrang");
        final String ngayXB = extras.getString("ngayXB");
        final String moTa = extras.getString("moTa");


        final int book_id = extras.getInt("book_id");

        int maSach = book_id;

        title.setText(tenSach);
        author.setText(tacGia);
        cate.setText(theLoai);
        num.setText(soLuong);
        num_page.setText(soTrang);
        publish_date.setText(ngayXB);
        des.setText(moTa);

        // Handle click event
        btnMuonSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSach.this, Muon.class);
                intent.putExtra("tenSach",tenSach);
                intent.putExtra("maSach",maSach);
                startActivity(intent);
            }
        });
    }
}