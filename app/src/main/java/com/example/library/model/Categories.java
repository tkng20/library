package com.example.library.model;

import java.util.List;

public class Categories {
    private String tenTheLoai;
    private List<Book> books;

    public Categories() {
    }

    public Categories(String tenTheLoai, List<Book> books) {
        this.tenTheLoai = tenTheLoai;
        this.books = books;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
