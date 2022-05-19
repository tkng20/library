package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.library.model.Post;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {

    private Context context;
    private List<Post> users;

    public PostAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.p_list_post, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("#ID: %d", users.get(pos).getId()));
        txtUsername.setText(String.format("USER NAME: %s", users.get(pos).getName()));

        rowView.setOnClickListener(v -> {
            //start Activity User Form
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra("user_id", String.valueOf(users.get(pos).getId()));
            intent.putExtra("post", users.get(pos).getName());
            context.startActivity(intent);
        });
        return rowView;
    }
}
