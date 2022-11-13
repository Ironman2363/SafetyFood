package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.LoaiSanPham;

import java.util.ArrayList;

public class LoaiSanPhamDAO {
    SafetyFoodDataBase safetyFoodDataBase;

    public LoaiSanPhamDAO(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
    }

    public ArrayList<LoaiSanPham> getDSLoaiSanPham() {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSanPham", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSanPham(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themLoaiSanPham(LoaiSanPham loaiSanPham){
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Idcuahang", loaiSanPham.getIdCuahang());
        contentValues.put("Name", loaiSanPham.getNameLoaisanpham());
        contentValues.put("Image", loaiSanPham.getImgLoaisanpham());
        contentValues.put("Created", loaiSanPham.getCreateLoaisanpham());
        contentValues.put("Updated", loaiSanPham.getUpdatedLoaisanpham());
        contentValues.put("Status", loaiSanPham.getStatusLoaisanpham());
        long check = sqLiteDatabase.insert("LoaiSanPham", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
    public boolean capnhatLoaiSanPham(LoaiSanPham loaiSanPham){
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Idcuahang", loaiSanPham.getIdCuahang());
        contentValues.put("Name", loaiSanPham.getNameLoaisanpham());
        contentValues.put("Image", loaiSanPham.getImgLoaisanpham());
        contentValues.put("Created", loaiSanPham.getCreateLoaisanpham());
        contentValues.put("Updated", loaiSanPham.getUpdatedLoaisanpham());
        contentValues.put("Status", loaiSanPham.getStatusLoaisanpham());
        long check = sqLiteDatabase.update("LoaiSanPham",contentValues,"Id = ?",new String[]{String.valueOf(loaiSanPham.getId())});
        if (check == -1)
            return false;
        return true;
    }

}
