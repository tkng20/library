package com.example.library.model;

import java.sql.Date;

public class Book {

    private int id;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String soLuong;
    private String soTrang;
    private String ngayXB;
    private String moTa;

    public Book(int id, String tenSach, String tacGia, String theLoai, String soLuong, String soTrang, String ngayXuatBan, String moTa) {
        this.id = id;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
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

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
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

    public String getNgayXuatBan() {
        return ngayXB;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        this.ngayXB = ngayXuatBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
