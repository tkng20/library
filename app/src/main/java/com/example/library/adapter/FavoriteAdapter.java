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
        ImageView anhSach = rowView.findViewById(R.id.imageBook);
        Picasso.with(context).load("http://3.0.59.80/test/public/public/storage/"+books.get(pos).getBook().getImage()).into(anhSach);
        tenSach1.setText(books.get(pos).getBook().getTenSach());
        return rowView;
    }
}
