package com.example.library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorite {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("book_id")
    @Expose
    private int book_id;

    @SerializedName("book")
    @Expose
    private Book book;

    public Favorite() {
    }

    public Favorite(int id, int user_id, int book_id, Book book) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
