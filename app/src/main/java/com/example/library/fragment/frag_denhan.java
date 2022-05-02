package com.example.library.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.library.Giahan;
import com.example.library.R;

public class frag_denhan extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denhan, container, false);
        Button button = (Button) view.findViewById(R.id.btnGH1);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Giahan.class);
            startActivity(intent);
        });
        return view;
    }
}