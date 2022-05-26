package com.example.library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class BorrowResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("book_id")
    @Expose
    private int book_id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date_borrow")
    @Expose
    private String date_borrow;

    @SerializedName("date_return")
    @Expose
    private String date_return;

    @SerializedName("return_expect")
    @Expose
    private String return_expect;

    @SerializedName("book")
    @Expose
    private Book book;

    public BorrowResponse() {
    }

    public BorrowResponse(int id, int user_id, int book_id, String status, String date_borrow, String date_return, Book book) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.status = status;
        this.date_borrow = date_borrow;
        this.date_return = date_return;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_borrow() {
        return date_borrow;
    }

    public void setDate_borrow(String date_borrow) {
        this.date_borrow = date_borrow;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getReturn_expect() {
        return return_expect;
    }

    public void setReturn_expect(String return_expect) {
        this.return_expect = return_expect;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BorrowResponse{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", status='" + status + '\'' +
                ", date_borrow='" + date_borrow + '\'' +
                ", date_return='" + date_return + '\'' +
                ", book=" + book +
                '}';
    }
}
