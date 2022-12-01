package com.example.safetyfood.MODEL;

import java.io.Serializable;

public class SanPham implements Serializable {
    int id , maLoaiSanPham;

    String nameSanpham;
    String imgSanpham;
    int priceSanpham;
    String loaiSanpham;
    String createSanpham;
    String updatedSanpham;
    int statusSanpham;

    public SanPham() {
    }

    public SanPham(int id, String nameSanpham, String imgSanpham, int priceSanpham, String loaiSanpham, String createSanpham, String updatedSanpham, int statusSanpham) {
        this.id = id;
        this.nameSanpham = nameSanpham;
        this.imgSanpham = imgSanpham;
        this.priceSanpham = priceSanpham;
        this.loaiSanpham = loaiSanpham;
        this.createSanpham = createSanpham;
        this.updatedSanpham = updatedSanpham;
        this.statusSanpham = statusSanpham;

    }

    public SanPham(String nameSanpham, String imgSanpham, int priceSanpham, String loaiSanpham, String createSanpham) {
        this.nameSanpham = nameSanpham;
        this.imgSanpham = imgSanpham;
        this.priceSanpham = priceSanpham;
        this.loaiSanpham = loaiSanpham;
        this.createSanpham = createSanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSanpham() {
        return nameSanpham;
    }

    public void setNameSanpham(String nameSanpham) {
        this.nameSanpham = nameSanpham;
    }

    public String getImgSanpham() {
        return imgSanpham;
    }

    public void setImgSanpham(String imgSanpham) {
        this.imgSanpham = imgSanpham;
    }

    public int getPriceSanpham() {
        return priceSanpham;
    }

    public void setPriceSanpham(int priceSanpham) {
        this.priceSanpham = priceSanpham;
    }

    public String getLoaiSanpham() {
        return loaiSanpham;
    }

    public void setLoaiSanpham(String loaiSanpham) {
        this.loaiSanpham = loaiSanpham;
    }

    public String getCreateSanpham() {
        return createSanpham;
    }

    public void setCreateSanpham(String createSanpham) {
        this.createSanpham = createSanpham;
    }

    public String getUpdatedSanpham() {
        return updatedSanpham;
    }

    public void setUpdatedSanpham(String updatedSanpham) {
        this.updatedSanpham = updatedSanpham;
    }

    public int getStatusSanpham() {
        return statusSanpham;
    }

    public void setStatusSanpham(int statusSanpham) {
        this.statusSanpham = statusSanpham;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", nameSanpham='" + nameSanpham + '\'' +
                ", imgSanpham='" + imgSanpham + '\'' +
                ", priceSanpham=" + priceSanpham +
                ", loaiSanpham='" + loaiSanpham + '\'' +
                ", createSanpham='" + createSanpham + '\'' +
                ", updatedSanpham='" + updatedSanpham + '\'' +
                ", statusSanpham=" + statusSanpham +
                '}';
    }
}
