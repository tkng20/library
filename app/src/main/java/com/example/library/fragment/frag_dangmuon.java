package com.example.library.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.library.R;
import com.example.library.model.BorrowResponse;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

public class frag_dangmuon extends Fragment {
    int id_user;
    SharedPreferences sp;
    APIService userService;
    List<BorrowResponse> borrowResponse = new ArrayList<>();
    ListView listView;
    public frag_dangmuon() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.borrow_items_dangmuon,container, false);
        userService = ApiUtils.getAPIService();
        sp=this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        id_user = sp.getInt("id",0);
        return rootView;
    }
}