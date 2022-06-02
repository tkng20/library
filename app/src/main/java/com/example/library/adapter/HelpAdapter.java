package com.example.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.library.R;

public class HelpAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public HelpAdapter(Context context) {
        this.context = context;
    }

    //Array
    public int[] slide_images = {
        R.drawable.slide1,
        R.drawable.giahan,
        R.drawable.muonsach,
        R.drawable.quydinh
    };

    public String[] slide_headings = {
            "Địa điểm lấy sách",
            "Gia hạn",
            "Mượn sách",
            "Quy định"
    };

    public String[] slide_descs = {
            "Sau khi bạn đã mượn sách thành công. Bạn hãy đến địa chỉ sau đây để nhận sách.\n" +
                    "Tòa nhà A phòng 113, thư viện trường đại học thông tin.",
            "Thời gian được gia hạn là trong vòng nhiều nhất 3 tháng. ",
            "Thời gian đến nhận sách sau 7 ngày kể từ ngày đăng ký. Trong cùng một thời điểm, bạn có thể mượn tối đa 3 cuốn sách.",
            "Sau khi hết thời gian mượn sách, bạn phải nộp phí 2 nghìn/ngày tính từ ngày hết hạn nếu vẫn chưa hoàn thành trả sách. Trong quá trình mượn có mất hay tổn thất đến sách, bạn phải hoàn tiền đúng với giá bán của sách."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService((context.LAYOUT_INFLATER_SERVICE));
        View view = layoutInflater.inflate(R.layout.tro_giup, container, false);

        ImageView slideImageView = (ImageView) view.findViewById((R.id.slide_image));
        TextView slideHeading = (TextView) view.findViewById((R.id.slide_heading));
        TextView slideDescription = (TextView) view.findViewById((R.id.slide_desc));
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}

