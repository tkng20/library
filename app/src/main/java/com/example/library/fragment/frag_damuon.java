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

public class frag_damuon extends Fragment{
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_damuon, container, false);
//        Button button = (Button) view.findViewById(R.id.btnMuon1);
//        button.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), DanhSachCho.class);
//            startActivity(intent);
//        });
//
//        Button button2 = (Button) view.findViewById(R.id.btnMuon2);
//        button2.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), DanhSachCho.class);
//            startActivity(intent);
//        });
//
//        return view;
//    }
//
//    public void onClick(View v) {
//        Intent iSubActivity01 = new Intent(getActivity(), DanhSachCho.class);
//        startActivity(iSubActivity01);
//    }
    int id_user;
    SharedPreferences sp;
    APIService userService;
    List<BorrowResponse> borrowResponse = new ArrayList<>();
    ListView listView;
    public frag_damuon() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.borrow_items_damuon,container, false);
        userService = ApiUtils.getAPIService();
        sp=this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        id_user = sp.getInt("id",0);
//        listView = (ListView) rootView.findViewById(R.id.listBorrow);
//        listView = rootView.findViewById(R.id.listBorrow);
//        getBookBorrow();
        return rootView;
    }
}