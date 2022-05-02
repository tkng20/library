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
import com.example.library.model.User;

import java.util.List;

public class ProfileAdapter extends ArrayAdapter<User> {

    private Context context;
    private User users;

    public ProfileAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
