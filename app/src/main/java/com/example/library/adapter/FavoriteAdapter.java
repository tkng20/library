package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.library.ChiTietSach;
import com.example.library.Muon;
import com.example.library.R;
import com.example.library.model.Book;
import com.example.library.model.Favorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<Favorite> {

    private Context context;
    private List<Favorite> books;

    public FavoriteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Favorite> objects) {
        super(context, resource, objects);
        this.context = context;
        this.books = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_yeuthich, parent, false);
        TextView tenSach1 = (TextView) rowView.findViewById(R.id.ten_Sach_1);
        TextView theLoai1 = (TextView) rowView.findViewById(R.id.fav_theLoai);
        TextView tacGia1 = (TextView) rowView.findViewById(R.id.fav_tacGia);
        ImageView anhSach = rowView.findViewById(R.id.imageBook);
        TextView muonNgay = (TextView) rowView.findViewById(R.id.fav_MuonNgay);
        TextView soLuong = rowView.findViewById(R.id.fav_soLuong);

        tenSach1.setText(books.get(pos).getBook().getTenSach());
        Picasso.with(context).load("http://3.0.59.80/test/public/public/storage/"+books.get(pos).getBook().getImage()).into(anhSach);
        theLoai1.setText(books.get(pos).getBook().getCategories().getTenTheLoai());
        tacGia1.setText(books.get(pos).getBook().getTacGia());
        soLuong.setText(books.get(pos).getBook().getSoLuong());

        anhSach.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChiTietSach.class);
            intent.putExtra("book_id",books.get(pos).getId());
            intent.putExtra("tenSach",books.get(pos).getBook().getTenSach());
            intent.putExtra("tacGia",books.get(pos).getBook().getTacGia());
            intent.putExtra("theLoai",books.get(pos).getBook().getCategories().getTenTheLoai());
            intent.putExtra("soLuong",books.get(pos).getBook().getSoLuong());
            intent.putExtra("soTrang",books.get(pos).getBook().getSoTrang());
            intent.putExtra("ngayXB",books.get(pos).getBook().getNgayXB());
            intent.putExtra("moTa",books.get(pos).getBook().getMoTa());
            intent.putExtra("image",books.get(pos).getBook().getImage());
            context.startActivity(intent);
        });

        muonNgay.setOnClickListener(v -> {
            //start Activity
            Intent intent = new Intent(context, Muon.class);
            intent.putExtra("tenSach", books.get(pos).getBook().getTenSach());
            intent.putExtra("maSach",books.get(pos).getBook_id());
            context.startActivity(intent);
        });
        return rowView;
    }
}
