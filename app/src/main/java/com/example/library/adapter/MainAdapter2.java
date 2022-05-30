package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.ChiTietSach;
import com.example.library.R;
import com.example.library.model.Book;
import com.example.library.model.BookResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter2 extends RecyclerView.Adapter<MainAdapter2.Book2ViewHolder> {

    private List<BookResponse> mBooks;
    private Context context;
    private int id;
    private String tacGia,soLuong,soTrang,ngayXB,moTa,image;
    public void setData(@NonNull Context context, @LayoutRes int resource,@NonNull List<BookResponse> list){
//        super(context, resource, list);
        this.mBooks = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Book2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new Book2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Book2ViewHolder holder, int position) {
        BookResponse book = mBooks.get(position);
        id = book.getId();
        tacGia = book.getTacGia();
        soLuong = book.getSoLuong();
        soTrang = book.getSoTrang();
        ngayXB = book.getNgayXB();
        moTa = book.getMoTa();
        image = book.getImage();
        if(book == null){
            return;
        }
        holder.imageView.setImageResource(R.drawable.sach2);
        holder.tv_tenSach.setText(book.getTenSach());
        Picasso.with(context).load("http://3.0.59.80/test/public/public/storage/"+image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(mBooks != null){
            return mBooks.size();
        }
        return 0;
    }

    public class Book2ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tv_tenSach,tv_theLoai;

        public Book2ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tc_imageBook);
            tv_tenSach = itemView.findViewById(R.id.tc_tenSach);
            tv_theLoai = itemView.findViewById(R.id.tc_theLoai);
            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ChiTietSach.class);
                intent.putExtra("book_id",id);
                intent.putExtra("tenSach",tv_tenSach.getText());
                intent.putExtra("tacGia",tacGia);
                intent.putExtra("theLoai","theLoai");
                intent.putExtra("soLuong",soLuong);
                intent.putExtra("soTrang",soTrang);
                intent.putExtra("ngayXB",ngayXB);
                intent.putExtra("moTa",moTa);
                intent.putExtra("image",image);
                view.getContext().startActivity(intent);
            });
        }
    }
}
