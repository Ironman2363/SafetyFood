package com.example.safetyfood.MODEL;

public class VaiTro {
    int id;
    String nameVaitro;
    String deschiptionVaitro;
    String createVaitro;
    String updateVaitro;

    public VaiTro() {
    }

    public VaiTro(int id, String nameVaitro, String deschiptionVaitro, String createVaitro, String updateVaitro) {
        this.id = id;
        this.nameVaitro = nameVaitro;
        this.deschiptionVaitro = deschiptionVaitro;
        this.createVaitro = createVaitro;
        this.updateVaitro = updateVaitro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameVaitro() {
        return nameVaitro;
    }

    public void setNameVaitro(String nameVaitro) {
        this.nameVaitro = nameVaitro;
    }

    public String getDeschiptionVaitro() {
        return deschiptionVaitro;
    }

    public void setDeschiptionVaitro(String deschiptionVaitro) {
        this.deschiptionVaitro = deschiptionVaitro;
    }

    public String getCreateVaitro() {
        return createVaitro;
    }

    public void setCreateVaitro(String createVaitro) {
        this.createVaitro = createVaitro;
    }

    public String getUpdateVaitro() {
        return updateVaitro;
    }

    public void setUpdateVaitro(String updateVaitro) {
        this.updateVaitro = updateVaitro;
    }
}
