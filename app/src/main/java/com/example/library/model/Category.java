package com.example.library.model;

import java.util.List;

public class Category {
    private String nameCategory;
    private List<Book> books;

    public Category() {
    }

    public Category(String category, List<Book> books) {
        nameCategory = category;
        this.books = books;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String category) {
        nameCategory = category;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
