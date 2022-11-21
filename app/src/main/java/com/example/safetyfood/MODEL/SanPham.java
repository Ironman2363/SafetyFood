package com.example.safetyfood.MODEL;

public class SanPham {
    int id;
    String nameSanpham;
    String imgSanpham;
    Float priceSanpham;
    String loaiSanpham;
    String createSanpham;
    String updatedSanpham;
    int statusSanpham;

    public SanPham() {
    }

    public SanPham(int id, String nameSanpham, String imgSanpham, Float priceSanpham,
                   String loaiSanpham, String createSanpham, String updatedSanpham, int statusSanpham) {
        this.id = id;
        this.nameSanpham = nameSanpham;
        this.imgSanpham = imgSanpham;
        this.priceSanpham = priceSanpham;
        this.loaiSanpham = loaiSanpham;
        this.createSanpham = createSanpham;
        this.updatedSanpham = updatedSanpham;
        this.statusSanpham = statusSanpham;
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

    public Float getPriceSanpham() {
        return priceSanpham;
    }

    public void setPriceSanpham(Float priceSanpham) {
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
}
