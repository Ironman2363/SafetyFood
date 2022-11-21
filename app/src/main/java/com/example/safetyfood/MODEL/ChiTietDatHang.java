package com.example.safetyfood.MODEL;

public class ChiTietDatHang {
    int id;
    int idDathang;
    int productid;
    float unitprice;
    float amount;

    public ChiTietDatHang() {
    }

    public ChiTietDatHang(int id, int idDathang, int productid, float unitprice, float amount) {
        this.id = id;
        this.idDathang = idDathang;
        this.productid = productid;
        this.unitprice = unitprice;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDathang() {
        return idDathang;
    }

    public void setIdDathang(int idDathang) {
        this.idDathang = idDathang;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ChiTietDatHang{" +
                "id=" + id +
                ", idDathang=" + idDathang +
                ", productid=" + productid +
                ", unitprice=" + unitprice +
                ", amount=" + amount +
                '}';
    }
}
