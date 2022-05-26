package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.library.ChiTietSach;
import com.example.library.R;
import com.example.library.model.Book;
import com.example.library.model.Post;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> books;

    public BookAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.books = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_book, parent, false);

        TextView tenSach = (TextView) rowView.findViewById(R.id.tenSach);
        TextView theLoai = (TextView) rowView.findViewById(R.id.theLoai);
        TextView soLuong = (TextView) rowView.findViewById(R.id.soLuong);
        TextView muonNgay = (TextView) rowView.findViewById(R.id.btnMuonNgay);

        tenSach.setText(books.get(pos).getTenSach().toString());
        theLoai.setText(books.get(pos).getCategories().getTenTheLoai().toString());
        soLuong.setText(books.get(pos).getSoLuong().toString());

        rowView.setOnClickListener(v -> {
            //start Activity
            Intent intent = new Intent(context, ChiTietSach.class);
            intent.putExtra("book_id",books.get(pos).getId());
            intent.putExtra("tenSach",books.get(pos).getTenSach());
            intent.putExtra("tacGia",books.get(pos).getTacGia());
            intent.putExtra("theLoai",books.get(pos).getCategories().getTenTheLoai());
            intent.putExtra("soLuong",books.get(pos).getSoLuong());
            intent.putExtra("soTrang",books.get(pos).getSoTrang());
            intent.putExtra("ngayXB",books.get(pos).getNgayXB());
            intent.putExtra("moTa",books.get(pos).getMoTa());
            context.startActivity(intent);
        });
        return rowView;
    }
}
