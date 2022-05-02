package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.model.Book;

import java.util.List;

public class BookMuonNhieuAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> books;

    public BookMuonNhieuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.books = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_sachmuonnhieu, parent, false);

        TextView tenSach = (TextView) rowView.findViewById(R.id.tenSach);
        TextView theLoai = (TextView) rowView.findViewById(R.id.theLoai);
        tenSach.setText(books.get(pos).getTenSach().toString());
        theLoai.setText(books.get(pos).getTheLoai().toString());

        rowView.setOnClickListener(v -> {
            //start Activity
            Intent intent = new Intent(context, ChiTietSach.class);
            intent.putExtra("book_id",books.get(pos).getId());
            intent.putExtra("tenSach",books.get(pos).getTenSach());
            intent.putExtra("tacGia",books.get(pos).getTacGia());
            intent.putExtra("theLoai",books.get(pos).getTheLoai());
            intent.putExtra("soLuong",books.get(pos).getSoLuong());
            intent.putExtra("soTrang",books.get(pos).getSoTrang());
            intent.putExtra("ngayXB",books.get(pos).getNgayXuatBan());
            intent.putExtra("moTa",books.get(pos).getMoTa());
            context.startActivity(intent);
        });
        return rowView;
    }
}
