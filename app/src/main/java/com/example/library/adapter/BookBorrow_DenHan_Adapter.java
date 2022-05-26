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

import com.example.library.Giahan;
import com.example.library.R;
import com.example.library.model.BorrowResponse;

import java.util.List;

public class BookBorrow_DenHan_Adapter extends ArrayAdapter<BorrowResponse> {

    private Context context;
    private List<BorrowResponse> borrowResponses;

    public BookBorrow_DenHan_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BorrowResponse> objects) {
        super(context, resource, objects);
        this.context = context;
        this.borrowResponses = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.borrow_items_denhan, parent, false);

        TextView tenSach = (TextView) rowView.findViewById(R.id.dh_tenSach);
        TextView ngayMuon = (TextView) rowView.findViewById(R.id.dh_ngayMuon);
        TextView ngayTra = (TextView) rowView.findViewById(R.id.dh_ngayTra);
        TextView giaHan = (Button) rowView.findViewById(R.id.btnGH1);

        tenSach.setText(borrowResponses.get(pos).getBook().getTenSach());
        ngayMuon.setText(borrowResponses.get(pos).getDate_borrow());
        ngayTra.setText(borrowResponses.get(pos).getReturn_expect());
        int maMuon = borrowResponses.get(pos).getId();
        giaHan.setOnClickListener(view -> {
            Intent intent = new Intent(context, Giahan.class);
            intent.putExtra("maMuon",maMuon);
            intent.putExtra("tenSach",tenSach.getText());
            intent.putExtra("ngayMuon",ngayMuon.getText());
            intent.putExtra("ngayTra",ngayTra.getText());
            context.startActivity(intent);
        });
        return rowView;
    }
}
