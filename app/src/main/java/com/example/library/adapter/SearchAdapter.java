package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.ChiTietSach;
import com.example.library.R;
import com.example.library.Search;
import com.example.library.TrangChu;
import com.example.library.model.Book;
import com.example.library.model.Categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {

//    private Context mContext;
    private List<Book> mListBooks;
    private List<Book> mListBooksOld;

    private int id;
    private String tacGia,theLoai,soLuong,soTrang,ngayXB,moTa;

    public SearchAdapter (List<Book> list) {
        this.mListBooks = list;
        this.mListBooksOld = list;
    }

    public void setData(List<Book> list){
        this.mListBooks = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_search,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Book books = mListBooks.get(position);
        id = books.getId();
        tacGia = books.getTacGia();
        theLoai = books.getCategories().getTenTheLoai();
        soLuong = books.getSoLuong();
        soTrang = books.getSoTrang();
        ngayXB = books.getNgayXB();
        moTa = books.getMoTa();
        if(books == null){
            return;
        }
        holder.tenSach.setText(books.getTenSach());
    }

    @Override
    public int getItemCount() {
        if(mListBooks != null){
            return mListBooks.size();
        }
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        private TextView tenSach;
        private ImageView anhSach;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSach= itemView.findViewById(R.id.tenSach);
            anhSach= itemView.findViewById(R.id.anhSach);
            anhSach.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ChiTietSach.class);
                intent.putExtra("book_id",id);
                intent.putExtra("tenSach",tenSach.getText());
                intent.putExtra("tacGia",tacGia);
                intent.putExtra("theLoai",theLoai);
                intent.putExtra("soLuong",soLuong);
                intent.putExtra("soTrang",soTrang);
                intent.putExtra("ngayXB",ngayXB);
                intent.putExtra("moTa",moTa);
                view.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mListBooks = mListBooksOld;
                } else {
                    List<Book> list = new ArrayList<>();
                    for(Book book : mListBooksOld){
                        if(book.getTenSach().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(book);
                        }
                    }
                    mListBooks = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListBooks;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListBooks = (List<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
