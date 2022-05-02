package com.example.library.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.library.DanhSachCho;
import com.example.library.R;

public class frag_damuon extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_damuon, container, false);
        Button button = (Button) view.findViewById(R.id.btnMuon1);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhSachCho.class);
            startActivity(intent);
        });

        Button button2 = (Button) view.findViewById(R.id.btnMuon2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhSachCho.class);
            startActivity(intent);
        });

        return view;
    }

    public void onClick(View v) {
        Intent iSubActivity01 = new Intent(getActivity(), DanhSachCho.class);
        startActivity(iSubActivity01);
    }
}