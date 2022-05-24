package com.example.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.library.R;
import com.example.library.model.BorrowResponse;

import java.util.List;

public class BookBorrow_DangMuon_Adapter extends ArrayAdapter<BorrowResponse> {

    private Context context;
    private List<BorrowResponse> borrowResponses;

    public BookBorrow_DangMuon_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BorrowResponse> objects) {
        super(context, resource, objects);
        this.context = context;
        this.borrowResponses = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.borrow_items_dangmuon, parent, false);

        TextView tenSach = (TextView) rowView.findViewById(R.id.borrow1_tenSach);
        TextView ngayMuon = (TextView) rowView.findViewById(R.id.borrow1_ngayMuon);

        tenSach.setText(borrowResponses.get(pos).getBook().getTenSach());
        ngayMuon.setText(borrowResponses.get(pos).getDate_borrow());
//        rowView.setOnClickListener(v -> {
////            start Activity;
//            Intent intent = new Intent(context, ChiTietSach.class);
//            intent.putExtra("book_id",books.get(pos).getId());
//            intent.putExtra("tenSach",books.get(pos).getTenSach());
//            intent.putExtra("tacGia",books.get(pos).getTacGia());
//            intent.putExtra("theLoai",books.get(pos).getTheLoai());
//            intent.putExtra("soLuong",books.get(pos).getSoLuong());
//            intent.putExtra("soTrang",books.get(pos).getSoTrang());
//            intent.putExtra("ngayXB",books.get(pos).getNgayXuatBan());
//            intent.putExtra("moTa",books.get(pos).getMoTa());
//            context.startActivity(intent);
//        });
        return rowView;
    }
}
