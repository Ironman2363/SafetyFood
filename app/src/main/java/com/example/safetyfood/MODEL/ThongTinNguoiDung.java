package com.example.safetyfood.MODEL;

public class ThongTinNguoiDung {
    int id;
    int idtaikhoan;
    String fullname;
    String emailNguoidung;
    String sdtNguoidung;
    String addresNguoidung;
    String avatarNguoidung;
    String birthdayNguoidung;
    int gender;
    String createNguoidung;
    String updateNguoidung;

    public ThongTinNguoiDung() {
    }

    public ThongTinNguoiDung(int id, int idtaikhoan, String fullname, String emailNguoidung, String sdtNguoidung, String addresNguoidung, String avatarNguoidung, String birthdayNguoidung, int gender, String createNguoidung, String updateNguoidung) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
        this.fullname = fullname;
        this.emailNguoidung = emailNguoidung;
        this.sdtNguoidung = sdtNguoidung;
        this.addresNguoidung = addresNguoidung;
        this.avatarNguoidung = avatarNguoidung;
        this.birthdayNguoidung = birthdayNguoidung;
        this.gender = gender;
        this.createNguoidung = createNguoidung;
        this.updateNguoidung = updateNguoidung;
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

    public String getFullname() {
        return fullname;
    }

    public String setFullname(String fullname) {
        this.fullname = fullname;
        return fullname;
    }

    public String getEmailNguoidung() {
        return emailNguoidung;
    }

    public void setEmailNguoidung(String emailNguoidung) {
        this.emailNguoidung = emailNguoidung;
    }

    public String getAddresNguoidung() {
        return addresNguoidung;
    }

    public void setAddresNguoidung(String addresNguoidung) {
        this.addresNguoidung = addresNguoidung;
    }

    public String getAvatarNguoidung() {
        return avatarNguoidung;
    }

    public void setAvatarNguoidung(String avatarNguoidung) {
        this.avatarNguoidung = avatarNguoidung;
    }

    public String getBirthdayNguoidung() {
        return birthdayNguoidung;
    }

    public void setBirthdayNguoidung(String birthdayNguoidung) {
        this.birthdayNguoidung = birthdayNguoidung;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCreateNguoidung() {
        return createNguoidung;
    }

    public void setCreateNguoidung(String createNguoidung) {
        this.createNguoidung = createNguoidung;
    }

    public String getUpdateNguoidung() {
        return updateNguoidung;
    }

    public void setUpdateNguoidung(String updateNguoidung) {
        this.updateNguoidung = updateNguoidung;
    }

    public String getSdtNguoidung() {
        return sdtNguoidung;
    }

    public void setSdtNguoidung(String sdtNguoidung) {
        this.sdtNguoidung = sdtNguoidung;
    }

    @Override
    public String toString() {
        return "ThongTinNguoiDung{" +
                "id=" + id +
                ", idtaikhoan=" + idtaikhoan +
                ", fullname='" + fullname + '\'' +
                ", emailNguoidung='" + emailNguoidung + '\'' +
                ", sdtNguoidung='" + sdtNguoidung + '\'' +
                ", addresNguoidung='" + addresNguoidung + '\'' +
                ", avatarNguoidung='" + avatarNguoidung + '\'' +
                ", birthdayNguoidung='" + birthdayNguoidung + '\'' +
                ", gender=" + gender +
                ", createNguoidung='" + createNguoidung + '\'' +
                ", updateNguoidung='" + updateNguoidung + '\'' +
                '}';
    }


}
