package com.example.library;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.library.fragment.frag_choxacnhan;
import com.example.library.fragment.frag_damuon;
import com.example.library.fragment.frag_dangmuon;
import com.example.library.fragment.frag_denhan;

public class HistoryAdapter extends FragmentStateAdapter {

    public HistoryAdapter(@NonNull FragmentActivity fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new frag_choxacnhan();
            case 1:
                return new frag_dangmuon();
            case 2:
                return new frag_damuon();
            case 3:
                return new frag_denhan();
            default:
                return new frag_choxacnhan();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
