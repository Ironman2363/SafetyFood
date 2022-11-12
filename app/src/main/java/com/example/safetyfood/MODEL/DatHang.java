package com.example.safetyfood.MODEL;

public class DatHang {
    int id;
    int idtaikhoan;
    float totalpriceDathang;
    String createDathang;
    String updateDathang;
    int statusDathang;

    public DatHang() {
    }

    public DatHang(int id, int idtaikhoan, float totalpriceDathang, String createDathang, String updateDathang, int statusDathang) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
        this.totalpriceDathang = totalpriceDathang;
        this.createDathang = createDathang;
        this.updateDathang = updateDathang;
        this.statusDathang = statusDathang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public float getTotalpriceDathang() {
        return totalpriceDathang;
    }

    public void setTotalpriceDathang(float totalpriceDathang) {
        this.totalpriceDathang = totalpriceDathang;
    }

    public String getCreateDathang() {
        return createDathang;
    }

    public void setCreateDathang(String createDathang) {
        this.createDathang = createDathang;
    }

    public String getUpdateDathang() {
        return updateDathang;
    }

    public void setUpdateDathang(String updateDathang) {
        this.updateDathang = updateDathang;
    }

    public int getStatusDathang() {
        return statusDathang;
    }

    public void setStatusDathang(int statusDathang) {
        this.statusDathang = statusDathang;
    }
}
