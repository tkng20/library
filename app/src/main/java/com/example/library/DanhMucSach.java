package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.library.model.Book;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhMucSach extends AppCompatActivity {

    APIService bookService;
    List<Book> list = new ArrayList<>();
    ListView listView;

    String arr[]={
            "Sách mới nhất",
            "Số lượng mượn giảm dần",
            "Số lượng mượn tăng dần",
            };
    TextView selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_danhmucsach);
        selection =(TextView) findViewById(R.id.selection);

        listView = findViewById(R.id.listBook);
        bookService = ApiUtils.getAPIService();

        getBookList();

        ImageView tc_btnHome = findViewById(R.id.dms_btnHome);
        ImageView tc_btnNotice = findViewById(R.id.dms_btnNotice);
        FloatingActionButton tc_btnDiscover = findViewById(R.id.dms_btnDiscover);


        tc_btnHome.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(DanhMucSach.this, TrangChu.class);
            startActivity(iSubActivity01);
        });

        tc_btnNotice.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(DanhMucSach.this, ThongBao.class);
            startActivity(iSubActivity01);
        });

        tc_btnDiscover.setOnClickListener(view -> {
            Intent iSubActivity01 = new Intent(DanhMucSach.this, DanhMucSach.class);
            startActivity(iSubActivity01);
        });

        //Lấy đối tượng Spinner ra
        Spinner spin=(Spinner) findViewById(R.id.spinner1);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());

    }

    //Class tạo sự kiện
    private class MyProcessEvent implements OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            selection.setText(arr[arg2]);
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            selection.setText("");
        }
    }

    public void getBookList()
    {
        Call<List<Book>> call = bookService.getListBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new BookAdapter(DanhMucSach.this, R.layout.list_book, list));
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
