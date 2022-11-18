package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {
    SafetyFoodDataBase safetyFoodDataBase;

    public SanPhamDAO(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
    }

    public ArrayList<SanPham> getDSSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SanPham", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSanpham(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", sanPham.getNameSanpham());
        contentValues.put("Image", sanPham.getImgSanpham());
        contentValues.put("Price", sanPham.getPriceSanpham());
        contentValues.put("TypeproDuct", sanPham.getLoaiSanpham());
        contentValues.put("Created", sanPham.getCreateSanpham());
        contentValues.put("Updated", sanPham.getUpdatedSanpham());
        contentValues.put("Status", sanPham.getStatusSanpham());
        long check = sqLiteDatabase.insert("SanPham", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capnhatSanpham(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", sanPham.getNameSanpham());
        contentValues.put("Image", sanPham.getImgSanpham());
        contentValues.put("Price", sanPham.getPriceSanpham());
        contentValues.put("TypeproDuct", sanPham.getLoaiSanpham());
        contentValues.put("Created", sanPham.getCreateSanpham());
        contentValues.put("Updated", sanPham.getUpdatedSanpham());
        contentValues.put("Status", sanPham.getStatusSanpham());
        long check = sqLiteDatabase.update("SanPham", contentValues, "Id = ?", new String[]{String.valueOf(sanPham.getId())});
        if (check == -1)
            return false;
        return true;
    }

    public int xoaSanPham(int Id) {
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SanPham WHERE id = ?", new String[]{String.valueOf(Id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("SanPham", "Id = ?", new String[]{String.valueOf(Id)});
        if (check == -1)
            return 0;
        return 1;

    }

}
