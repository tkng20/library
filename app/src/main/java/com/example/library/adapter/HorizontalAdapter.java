package com.example.library.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.ChiTietSach;
import com.example.library.R;
import com.example.library.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.PhoneViewHold> {

    ArrayList<Book> phoneLaocations;
    final private ListItemClickListener mOnClickListener;
    private int id;
    private String tenSach,tacGia,theLoai,soLuong,soTrang,ngayXB,moTa,image;

    public HorizontalAdapter(ArrayList<Book> phoneLaocations, ListItemClickListener listener) {
        this.phoneLaocations = phoneLaocations;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new PhoneViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


        Book phonehelper = phoneLaocations.get(position);

        id = phonehelper.getId();
        tacGia = phonehelper.getTacGia();
        soLuong = phonehelper.getSoLuong();
        soTrang = phonehelper.getSoTrang();
        theLoai = phonehelper.getCategories().getTenTheLoai();
        ngayXB = phonehelper.getNgayXB();
        moTa = phonehelper.getMoTa();
        image = phonehelper.getImage();

        holder.tv_tenSach.setText(phonehelper.getTenSach());
        holder.tv_theLoai.setText(phonehelper.getTacGia());
        holder.imageView.setImageResource(R.drawable.sach2);
    }

    @Override
    public int getItemCount() {
        return phoneLaocations.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView tv_tenSach,tv_theLoai;

        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks
            imageView = itemView.findViewById(R.id.tc_imageBook);
            tv_tenSach = itemView.findViewById(R.id.tc_tenSach);
            tv_theLoai = itemView.findViewById(R.id.tc_TacGia);
            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ChiTietSach.class);
                        intent.putExtra("book_id",id);
                        intent.putExtra("tenSach",tenSach);
                        intent.putExtra("tacGia",tacGia);
                        intent.putExtra("theLoai","test");
                        intent.putExtra("soLuong",soLuong);
                        intent.putExtra("soTrang",soTrang);
                        intent.putExtra("ngayXB",ngayXB);
                        intent.putExtra("moTa",moTa);
                        intent.putExtra("image",image);
                        view.getContext().startActivity(intent);
            });
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onphoneListClick(clickedPosition);
        }
    }
}

