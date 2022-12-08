package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.DatHang;

import java.util.ArrayList;
import java.util.List;

public class DatHangDAO {
    SafetyFoodDataBase dataBase;
    SQLiteDatabase db;

    public static String TagZZZ = "ZZZZZZ";

    String table_name = "DatHang";
    String DH_ID = "Id";
    String AccountId = "AccountId";
    String TotalPrice = "TotalPrice";
    String Created = "Created";
    String Updated = "Updated";
    String Status = "Status";

    public DatHangDAO(Context context) {
        dataBase = new SafetyFoodDataBase(context);
        db = dataBase.getReadableDatabase();
    }

    public void InsertDH(DatHang datHang) {
        ContentValues values = new ContentValues();
        values.put(AccountId, datHang.getIdtaikhoan());
        values.put(TotalPrice, datHang.getTotalpriceDathang());
        values.put(Created, datHang.getCreateDathang());
        values.put(Updated, datHang.getUpdateDathang());
        values.put(Status, datHang.getStatusDathang());
        long kq = db.insert(table_name, null, values);
        Log.e(TagZZZ, "InsertDH: " + kq);
    }

    public void DeleteDH(DatHang datHang) {
        long kq = db.delete(table_name, DH_ID + "=?", new String[]{String.valueOf(datHang.getId())});
        Log.e(TagZZZ, "InsertDH: " + kq);
    }

    public void UpgradeDH(DatHang datHang) {
        ContentValues values = new ContentValues();
        values.put(AccountId, datHang.getIdtaikhoan());
        values.put(TotalPrice, datHang.getTotalpriceDathang());
        values.put(Created, datHang.getCreateDathang());
        values.put(Updated, datHang.getUpdateDathang());
        values.put(Status, datHang.getStatusDathang());
        long kq = db.update(table_name, values, DH_ID + "=?", new String[]{String.valueOf(datHang.getId())});
        Log.e(TagZZZ, "UpgradeDH: " + kq);
    }

    public List<DatHang> getAllList() {
        String sql = "select * from " + table_name;
        return getData(sql);
    }

    public DatHang getID(int ID) {
        String sql = "select * from " + table_name + " where " + DH_ID + "=?";
        return getData(sql, String.valueOf(ID)).get(0);
    }

    public DatHang getBuyingCart(int accountID) {
        String sql = "Select * from " + table_name + " where " + AccountId + "=? and " + Status + " = 0";
        List<DatHang> list = new ArrayList<>();
        list = getData(sql, String.valueOf(accountID));
        if (list.size() <= 0)
            return null;
        return list.get(list.size() - 1);
    }

    public List<DatHang> getAllOrderStatus(int status, int status1) {
        String sql = "Select * from " + table_name + " where  " + Status + " = ? or "+Status+" = ? order by "+Created+" desc";
        return getData(sql, new String[]{String.valueOf(status),String.valueOf(status1)});
    }

    public List<DatHang> getAllOrderDate(int status,String tuNgay, String denNgay) {
        String sql = "Select * from " + table_name + " where  " + Status + " = ? and date(Updated) between ? and ?";
        return getData(sql, new String[]{String.valueOf(status),tuNgay,denNgay});
    }

    public List<DatHang> getOrderHistory(int status){
        String sql = "Select * from " + table_name + " where  " + Status + " != ? order by "+Created+" desc";
        return getData(sql, new String[]{String.valueOf(status)});
    }

    public List<DatHang> getCartStatus(int ID, int status3,int status4) {
        String sql = "Select * from " + table_name + " where " + AccountId + "=? and (" + Status + " = ? or "+Status+" = ?) Order by created desc";
        return getData(sql, new String[]{String.valueOf(ID), String.valueOf(status3),String.valueOf(status4)});
    }

    public List<DatHang> getData(String sql, String... selectionArgs) {
        List<DatHang> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            DatHang datHang = new DatHang();
            datHang.setId(cursor.getInt(cursor.getColumnIndex(DH_ID)));
            datHang.setIdtaikhoan(cursor.getInt(cursor.getColumnIndex(AccountId)));
            datHang.setTotalpriceDathang(cursor.getFloat(cursor.getColumnIndex(TotalPrice)));
            datHang.setCreateDathang(cursor.getString(cursor.getColumnIndex(Created)));
            datHang.setUpdateDathang(cursor.getString(cursor.getColumnIndex(Updated)));
            datHang.setStatusDathang(cursor.getInt(cursor.getColumnIndex(Status)));
            list.add(datHang);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

//    Trạng thái của status :
//    0: Đang đặt hàng
//    1: Đã đặt hàng đang chờ xử lý
//    2: Đã xử lý đang giao hàng
//    3: Hủy từ phía người dùng
//    4: Hủy từ phía cửa hàng
//    5: Giao hàng thành công

}
