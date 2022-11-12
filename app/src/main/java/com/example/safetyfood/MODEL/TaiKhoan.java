package com.example.safetyfood.MODEL;

public class TaiKhoan {
    int id;
    String username;
    String password;
    int role;
    int statusTaikhoan;

    public TaiKhoan() {
    }

    public TaiKhoan(int id, String username, String password, int role, int statusTaikhoan) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.statusTaikhoan = statusTaikhoan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatusTaikhoan() {
        return statusTaikhoan;
    }

    public void setStatusTaikhoan(int statusTaikhoan) {
        this.statusTaikhoan = statusTaikhoan;
    }
}
