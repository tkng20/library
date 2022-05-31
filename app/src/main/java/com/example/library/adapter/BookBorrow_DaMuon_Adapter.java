package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.library.ChiTietSach;
import com.example.library.Muon;
import com.example.library.R;
import com.example.library.TrangCaNhan;
import com.example.library.XacNhanMuon;
import com.example.library.model.BorrowResponse;
import com.squareup.picasso.Picasso;

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
        ImageView imgBook = rowView.findViewById(R.id.imageBook);
        Button muonLai = (Button) rowView.findViewById(R.id.btnMuonLai);

        ImageView anhSach = rowView.findViewById(R.id.imageBook);
        Picasso.with(context).load("http://3.0.59.80/test/public/public/storage/"+borrowResponses.get(pos).getBook().getImage()).into(anhSach);

        tenSach.setText(borrowResponses.get(pos).getBook().getTenSach());
        ngayMuon.setText(borrowResponses.get(pos).getDate_borrow());
        ngayTra.setText(borrowResponses.get(pos).getDate_return());
        imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSach.class);
                intent.putExtra("book_id",borrowResponses.get(pos).getId());
                intent.putExtra("tenSach",borrowResponses.get(pos).getBook().getTenSach());
                intent.putExtra("tacGia",borrowResponses.get(pos).getBook().getTacGia());
                intent.putExtra("theLoai","theLoai");
                intent.putExtra("soLuong",borrowResponses.get(pos).getBook().getSoLuong());
                intent.putExtra("soTrang",borrowResponses.get(pos).getBook().getSoTrang());
                intent.putExtra("ngayXB",borrowResponses.get(pos).getBook().getNgayXB());
                intent.putExtra("moTa",borrowResponses.get(pos).getBook().getMoTa());
                intent.putExtra("image",borrowResponses.get(pos).getBook().getImage());
                context.startActivity(intent);
            }
        });
        muonLai.setOnClickListener(v -> {
//            start Activity;
            Intent intent = new Intent(context, Muon.class);
            intent.putExtra("maSach",borrowResponses.get(pos).getBook_id());
            intent.putExtra("tenSach",borrowResponses.get(pos).getBook().getTenSach());
            context.startActivity(intent);
        });
        return rowView;
    }
}
