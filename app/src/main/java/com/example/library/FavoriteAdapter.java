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

import com.example.library.model.Book;

import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> books;

    public FavoriteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.books = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_yeuthich, parent, false);

        TextView tenSach1 = (TextView) rowView.findViewById(R.id.ten_Sach_1);
        TextView tenSach2 = (TextView) rowView.findViewById(R.id.ten_Sach_2);
        if(pos % 2 == 0 ){
            tenSach1.setText(books.get(pos).getTenSach());
        }
        else{
            tenSach2.setText(books.get(pos).getTenSach());
        }
        return rowView;
    }
}
