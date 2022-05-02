package com.example.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.model.Book;

import java.util.List;

// The adapter class which
// extends RecyclerView Adapter
public class HorizontalAdapter extends RecyclerView.Adapter {
        //Dữ liệu hiện thị là danh sách sinh viên
        private List<Book> mStutents;
        // Lưu Context để dễ dàng truy cập
        private Context mContext;

        public HorizontalAdapter(List _student, Context mContext) {
            this.mStutents = _student;
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Nạp layout cho View biểu diễn phần tử sinh viên
            View studentView =
                    inflater.inflate(R.layout.list_book, parent, false);

            ViewHolder viewHolder = new ViewHolder(studentView);
            return viewHolder;

        }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
            Book student = mStutents.get(position);

            holder.studentname.setText(student.getTenSach());
            holder.birthyear.setText(student.getTheLoai()+"");
        }

        @Override
        public int getItemCount() {
            return mStutents.size();
        }

        /**
         * Lớp nắm giữ cấu trúc view
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private View itemview;
            public TextView studentname;
            public TextView birthyear;
            public Button detail_button;

            public ViewHolder(View itemView) {
                super(itemView);
                itemview = itemView;
                studentname = itemView.findViewById(R.id.tenSach);

                //Xử lý khi nút Chi tiết được bấm
                detail_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(),
                                studentname.getText() +" | "
                                        + " Demo function", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        }
}

