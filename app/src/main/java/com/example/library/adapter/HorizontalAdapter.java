package com.example.library.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.library.ChiTietSach;
import com.example.library.R;
import com.example.library.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private ArrayList<Book> data = new ArrayList<>();
    private int id,cate_id;
    private String tenSach,tacGia,theLoai,soLuong,soTrang,ngayXB,moTa;

        public HorizontalAdapter(ArrayList<Book> data) {
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_single_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            id = data.get(position).getId();
            tenSach= data.get(position).getTenSach();
            tacGia =data.get(position).getTacGia();
            theLoai = data.get(position).getCategories().getTenTheLoai();
            soLuong = data.get(position).getSoLuong();
            soTrang = data.get(position).getSoTrang();
            ngayXB = data.get(position).getNgayXB();
            moTa = data.get(position).getMoTa();

            holder.description.setText(data.get(position).getCategories().getTenTheLoai());
            holder.title.setText(data.get(position).getTenSach());
            holder.pubDate.setText(data.get(position).getNgayXB());
//            Picasso.with(this).load("http://3.0.59.80/test/public/public/storage/"+data.get(position).getImage()).into(holder.image);

            holder.image.setImageResource(R.drawable.sach2);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title, description, pubDate;
            ImageView image;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);
                description = (TextView) itemView.findViewById(R.id.description);
                pubDate = (TextView) itemView.findViewById(R.id.published_date);
                image = (ImageView) itemView.findViewById(R.id.image_view);
                // truyền dữ liệu sang chi tiết sách
                itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(view.getContext(), ChiTietSach.class);
                    intent.putExtra("book_id",id);
                    intent.putExtra("tenSach",tenSach);
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
}

