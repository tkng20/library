package com.example.library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookResponse {
//    @SerializedName("id")
//    @Expose
    private int id;

//    @SerializedName("tenSach")
//    @Expose
    private String tenSach;

//    @SerializedName("tacGia")
//    @Expose
    private String tacGia;

//    @SerializedName("categories_id")
//    @Expose
    private int categories_id;

//    @SerializedName("soLuong")
//    @Expose
    private String soLuong;

//    @SerializedName("soTrang")
//    @Expose
    private String soTrang;

//    @SerializedName("ngayXB")
//    @Expose
    private String ngayXB;

//    @SerializedName("moTa")
//    @Expose
    private String moTa;

//    @SerializedName("image")
//    @Expose
    private String image;

    public BookResponse(){}

    public BookResponse(int id, String tenSach, String tacGia, int categories_id, String soLuong, String soTrang, String ngayXB, String moTa) {
        this.id = id;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.categories_id = categories_id;
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.ngayXB = ngayXB;
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

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
