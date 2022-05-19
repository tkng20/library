package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.model.Book;

import java.util.List;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.Book2ViewHolder> {

    private List<Book> mBooks;
    private int id;
    private String tacGia,theLoai,soLuong,soTrang,ngayXB,moTa;
    public void setData(List<Book> list){
        this.mBooks = list;
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
        Book book = mBooks.get(position);
        id = book.getId();
        tacGia = book.getTacGia();
        theLoai = book.getTheLoai();
        soLuong = book.getSoLuong();
        soTrang = book.getSoTrang();
        ngayXB = book.getNgayXuatBan();
        moTa = book.getMoTa();
        if(book == null){
            return;
        }
        holder.imageView.setImageResource(R.drawable.sach2);
        holder.textTitle.setText(book.getTenSach());
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
        private TextView textTitle;

        public Book2ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageBook);
            textTitle = itemView.findViewById(R.id.tenSach);
            imageView.setOnClickListener(view -> {
                Intent myIntent = new Intent(view.getContext(),ChiTietSach.class);
                myIntent.putExtra("book_id",id);
                myIntent.putExtra("tenSach",textTitle.getText());
                myIntent.putExtra("tacGia",tacGia);
                myIntent.putExtra("theLoai",theLoai);
                myIntent.putExtra("soLuong",soLuong);
                myIntent.putExtra("soTrang",soTrang);
                myIntent.putExtra("ngayXB",ngayXB);
                myIntent.putExtra("moTa",moTa);
                view.getContext().startActivity(myIntent);
            });
        }
    }
}
