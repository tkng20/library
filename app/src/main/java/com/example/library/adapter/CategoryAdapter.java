package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.ChiTietSach;
import com.example.library.R;
import com.example.library.Search;
import com.example.library.XemThem;
import com.example.library.model.Categories;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context mContext;
    private List<Categories> mListCategories;

    public CategoryAdapter (Context context) {
        this.mContext = context;
    }

    public void setData(List<Categories> list){
        this.mListCategories = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,final int position) {
        Categories categories = mListCategories.get(position);
        if(categories == null){
            return;
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);
        holder.tvNameCategory.setText(categories.getTenTheLoai());
        MainAdapter bookAdapter = new MainAdapter();
        bookAdapter.setData(mContext,categories.getBooks());
        holder.rcvBook.setAdapter(bookAdapter);
    }

    @Override
    public int getItemCount() {
        if(mListCategories != null){
            return mListCategories.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameCategory;
        private Button viewmore;
        private RecyclerView rcvBook;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
            rcvBook = itemView.findViewById(R.id.rcv_book);
            viewmore = itemView.findViewById(R.id.viewmore);
            viewmore.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), XemThem.class);
                view.getContext().startActivity(intent);
            });
        }
    }
}
