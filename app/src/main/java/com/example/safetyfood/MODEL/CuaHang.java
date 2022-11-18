package com.example.safetyfood.MODEL;

public class CuaHang {
    int id;
    String nameCuahang;
    String imgCuahang;
    String phoneCuahang;
    String emailCuahang;
    String addresCuahang;
    String cretaeCuahang;
    String updateCuahang;
    int statusCuahang;

    public CuaHang(int anInt, String string, String cursorString, String s, String string1, String cursorString1, String s1, String string2, String cursorInt, String cursorString2, int i) {
    }

    public CuaHang(int id, String nameCuahang, String imgCuahang, String phoneCuahang, String emailCuahang, String addresCuahang, String cretaeCuahang, String updateCuahang, int statusCuahang) {
        this.id = id;
        this.nameCuahang = nameCuahang;
        this.imgCuahang = imgCuahang;
        this.phoneCuahang = phoneCuahang;
        this.emailCuahang = emailCuahang;
        this.addresCuahang = addresCuahang;
        this.cretaeCuahang = cretaeCuahang;
        this.updateCuahang = updateCuahang;
        this.statusCuahang = statusCuahang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCuahang() {
        return nameCuahang;
    }

    public void setNameCuahang(String nameCuahang) {
        this.nameCuahang = nameCuahang;
    }

    public String getImgCuahang() {
        return imgCuahang;
    }

    public void setImgCuahang(String imgCuahang) {
        this.imgCuahang = imgCuahang;
    }

    public String getPhoneCuahang() {
        return phoneCuahang;
    }

    public void setPhoneCuahang(String phoneCuahang) {
        this.phoneCuahang = phoneCuahang;
    }

    public String getEmailCuahang() {
        return emailCuahang;
    }

    public void setEmailCuahang(String emailCuahang) {
        this.emailCuahang = emailCuahang;
    }

    public String getAddresCuahang() {
        return addresCuahang;
    }

    public void setAddresCuahang(String addresCuahang) {
        this.addresCuahang = addresCuahang;
    }

    public String getCretaeCuahang() {
        return cretaeCuahang;
    }

    public void setCretaeCuahang(String cretaeCuahang) {
        this.cretaeCuahang = cretaeCuahang;
    }

    public String getUpdateCuahang() {
        return updateCuahang;
    }

    public void setUpdateCuahang(String updateCuahang) {
        this.updateCuahang = updateCuahang;
    }

    public int getStatusCuahang() {
        return statusCuahang;
    }

    public void setStatusCuahang(int statusCuahang) {
        this.statusCuahang = statusCuahang;
    }
}
