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
import com.example.library.model.BorrowResponse;

import java.util.List;

public class BookBorrow_DaMuon_Adapter extends ArrayAdapter<BorrowResponse> {

    private Context context;
    private List<BorrowResponse> borrowResponses;

    public BookBorrow_DaMuon_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BorrowResponse> objects) {
        super(context, resource, objects);
        this.context = context;
        this.borrowResponses = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.borrow_items_damuon, parent, false);

        TextView tenSach = (TextView) rowView.findViewById(R.id.borrow2_tenSach);
        TextView ngayMuon = (TextView) rowView.findViewById(R.id.borrow2_ngayMuon);
        TextView ngayTra = (TextView) rowView.findViewById(R.id.borrow2_ngayTra);
        TextView muonLai = (Button) rowView.findViewById(R.id.btnMuonLai);

        tenSach.setText(borrowResponses.get(pos).getBook().getTenSach());
        ngayMuon.setText(borrowResponses.get(pos).getDate_borrow());
        ngayTra.setText(borrowResponses.get(pos).getDate_return());
        muonLai.setOnClickListener(v -> {
//            start Activity;
            Intent intent = new Intent(context, ChiTietSach.class);
            intent.putExtra("book_id",borrowResponses.get(pos).getBook_id());
            intent.putExtra("tenSach",borrowResponses.get(pos).getBook().getTenSach());
            intent.putExtra("tacGia",borrowResponses.get(pos).getBook().getTacGia());
            intent.putExtra("theLoai",borrowResponses.get(pos).getBook().getCategories().getTenTheLoai());
            intent.putExtra("soLuong",borrowResponses.get(pos).getBook().getSoLuong());
            intent.putExtra("soTrang",borrowResponses.get(pos).getBook().getSoTrang());
            intent.putExtra("ngayXB",borrowResponses.get(pos).getBook().getNgayXB());
            intent.putExtra("moTa",borrowResponses.get(pos).getBook().getMoTa());
            context.startActivity(intent);
        });
        return rowView;
    }
}
