package com.example.safetyfood.MODEL;

import java.io.Serializable;

public class LoaiSanPham implements Serializable {
    int id;
    int idCuahang;
    String nameLoaisanpham;
    String imgLoaisanpham;
    String createLoaisanpham;
    String updatedLoaisanpham;
    int statusLoaisanpham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, int idCuahang, String nameLoaisanpham, String imgLoaisanpham, String createLoaisanpham, String updatedLoaisanpham, int statusLoaisanpham) {
        this.id = id;
        this.idCuahang = idCuahang;
        this.nameLoaisanpham = nameLoaisanpham;
        this.imgLoaisanpham = imgLoaisanpham;
        this.createLoaisanpham = createLoaisanpham;
        this.updatedLoaisanpham = updatedLoaisanpham;
        this.statusLoaisanpham = statusLoaisanpham;
    }

    public LoaiSanPham(String nameLoaisanpham, String imgLoaisanpham, String createLoaisanpham) {
        this.nameLoaisanpham = nameLoaisanpham;
        this.imgLoaisanpham = imgLoaisanpham;
        this.createLoaisanpham = createLoaisanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuahang() {
        return idCuahang;
    }

    public void setIdCuahang(int idCuahang) {
        this.idCuahang = idCuahang;
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

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "id=" + id +
                ", idCuahang=" + idCuahang +
                ", nameLoaisanpham='" + nameLoaisanpham + '\'' +
                ", imgLoaisanpham='" + imgLoaisanpham + '\'' +
                ", createLoaisanpham='" + createLoaisanpham + '\'' +
                ", updatedLoaisanpham='" + updatedLoaisanpham + '\'' +
                ", statusLoaisanpham=" + statusLoaisanpham +
                '}';
    }
}
