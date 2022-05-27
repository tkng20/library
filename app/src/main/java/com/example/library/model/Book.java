package com.example.library.model;

public class Book {

    private int id;
    private String tenSach;
    private String tacGia;
    private Categories categories;
    private String theLoai;
    private String soLuong;
    private String soTrang;
    private String ngayXB;
    private String moTa;

    public Book(){}

    public Book(int id, String tenSach, String tacGia, Categories categories, String soLuong, String soTrang, String ngayXuatBan, String moTa) {
        this.id = id;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.categories = categories;
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.ngayXB = ngayXuatBan;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getNgayXB() {
        return ngayXB;
    }

    public void setNgayXB(String ngayXB) {
        this.ngayXB = ngayXB;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
}
