package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.CuaHang;

import java.util.ArrayList;

public class CuaHangDAO {


    private SQLiteDatabase db;
    SafetyFoodDataBase safetyFoodDataBase;

    public CuaHangDAO(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
    }

    public ArrayList<CuaHang> getDSCuahang() {
        ArrayList<CuaHang> list = new ArrayList<>();
        db = safetyFoodDataBase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CuaHang", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new CuaHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean CapNhapCuaHang(CuaHang cuaHang) {
        db = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", cuaHang.getNameCuahang());
        contentValues.put("Image", cuaHang.getImgCuahang());
        contentValues.put("Phone", cuaHang.getPhoneCuahang());
        contentValues.put("Email", cuaHang.getEmailCuahang());
        contentValues.put("Addres", cuaHang.getAddresCuahang());
        contentValues.put("Created", cuaHang.getCretaeCuahang());
        contentValues.put("Updated", cuaHang.getUpdateCuahang());
        contentValues.put("Status", cuaHang.getStatusCuahang());
        long check = db.update("CuaHang", contentValues, "Id = ?", new String[]{String.valueOf(cuaHang.getId())});
        if (check == -1)
            return false;
        return true;
    }

    public boolean ThemCuaHang(CuaHang cuaHang) {
        db = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", cuaHang.getNameCuahang());
        contentValues.put("Image", cuaHang.getImgCuahang());
        contentValues.put("Phone", cuaHang.getPhoneCuahang());
        contentValues.put("Email", cuaHang.getEmailCuahang());
        contentValues.put("Addres", cuaHang.getAddresCuahang());
        contentValues.put("Created", cuaHang.getCretaeCuahang());
        contentValues.put("Updated", cuaHang.getUpdateCuahang());
        contentValues.put("Status", cuaHang.getStatusCuahang());
        long check = db.insert("CuaHang", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
}
