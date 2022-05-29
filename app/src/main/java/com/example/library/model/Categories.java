package com.example.library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    private String tenTheLoai;
    private List<BookResponse> book;

    public Categories() {
    }

    public Categories(String tenTheLoai, List<BookResponse> books) {
        this.tenTheLoai = tenTheLoai;
        this.book = books;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public List<BookResponse> getBooks() {
        return book;
    }

    public void setBooks(List<BookResponse> books) {
        this.book = books;
    }
}
