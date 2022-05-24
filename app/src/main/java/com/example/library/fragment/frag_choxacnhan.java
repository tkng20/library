package com.example.library.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.library.adapter.BookBorrow_CXN_Adapter;
import com.example.library.R;
import com.example.library.model.BorrowResponse;
import com.example.library.remote.APIService;
import com.example.library.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_choxacnhan extends Fragment {
    int id_user;
    SharedPreferences sp;
    APIService userService;
    List<BorrowResponse> borrowResponse = new ArrayList<>();
    ListView listView;
    public frag_choxacnhan() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_items,container, false);
        userService = ApiUtils.getAPIService();
        sp=this.getActivity().getSharedPreferences("credentials",Context.MODE_PRIVATE);
        id_user = sp.getInt("id",0);
        listView = (ListView) rootView.findViewById(R.id.listBorrow);
        getBookBorrow();
        return rootView;
    }

    public void getBookBorrow()
    {
        Call<List<BorrowResponse>> call = userService.borrowResponse(id_user);
        call.enqueue(new Callback<List<BorrowResponse>>() {
            @Override
            public void onResponse(Call<List<BorrowResponse>> call, Response<List<BorrowResponse>> response) {
                if(response.isSuccessful()) {
                    borrowResponse = response.body();
                    listView.setAdapter(new BookBorrow_CXN_Adapter(getActivity(), R.layout.borrow_items_cxn, borrowResponse));
                }
            }
            @Override
            public void onFailure(Call<List<BorrowResponse>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}