package com.example.safetyfood.MODEL;

public class LoaiSanPham {
    int id;
    String nameLoaisanpham;
    String imgLoaisanpham;
    String createLoaisanpham;
    String updatedLoaisanpham;
    int statusLoaisanpham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, String nameLoaisanpham, String imgLoaisanpham, String createLoaisanpham, String updatedLoaisanpham, int statusLoaisanpham) {
        this.id = id;
        this.nameLoaisanpham = nameLoaisanpham;
        this.imgLoaisanpham = imgLoaisanpham;
        this.createLoaisanpham = createLoaisanpham;
        this.updatedLoaisanpham = updatedLoaisanpham;
        this.statusLoaisanpham = statusLoaisanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLoaisanpham() {
        return nameLoaisanpham;
    }

    public void setNameLoaisanpham(String nameLoaisanpham) {
        this.nameLoaisanpham = nameLoaisanpham;
    }

    public String getImgLoaisanpham() {
        return imgLoaisanpham;
    }

    public void setImgLoaisanpham(String imgLoaisanpham) {
        this.imgLoaisanpham = imgLoaisanpham;
    }

    public String getCreateLoaisanpham() {
        return createLoaisanpham;
    }

    public void setCreateLoaisanpham(String createLoaisanpham) {
        this.createLoaisanpham = createLoaisanpham;
    }

    public String getUpdatedLoaisanpham() {
        return updatedLoaisanpham;
    }

    public void setUpdatedLoaisanpham(String updatedLoaisanpham) {
        this.updatedLoaisanpham = updatedLoaisanpham;
    }

    public int getStatusLoaisanpham() {
        return statusLoaisanpham;
    }

    public void setStatusLoaisanpham(int statusLoaisanpham) {
        this.statusLoaisanpham = statusLoaisanpham;
    }
}
