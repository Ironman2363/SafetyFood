package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;

import java.util.ArrayList;

public class thongtinnguoidungDao {
    SafetyFoodDataBase safetyFoodDataBase;

    public thongtinnguoidungDao(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
    }

    public ArrayList<ThongTinNguoiDung> getThongTinNguoiDungs() {
        ArrayList<ThongTinNguoiDung> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThongTinNguoiDung", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThongTinNguoiDung(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themThongTinNguoiDung(int AccountId, String FullName, String Email, String Addres, String Avatar, int Birthday, int Gender, int Created, int Updated) {
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccountId", AccountId);
        contentValues.put("FullName", FullName);
        contentValues.put("Email", Email);
        contentValues.put("Addres", Addres);
        contentValues.put("Avatar", Avatar);
        contentValues.put("Birthday", Birthday);
        contentValues.put("Gender", Gender);
        contentValues.put("Created", Created);
        contentValues.put("Updated", Updated);
        long check = sqLiteDatabase.insert("ThongTinNguoiDung", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinNguoiDung(int id, int AccountId, String FullName, String Email, String Addres, String Avatar, int Birthday, int Gender, int Created, int Updated) {
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("AccountId", AccountId);
        contentValues.put("FullName", FullName);
        contentValues.put("Email", Email);
        contentValues.put("Addres", Addres);
        contentValues.put("Avatar", Avatar);
        contentValues.put("Birthday", Birthday);
        contentValues.put("Gender", Gender);
        contentValues.put("Created", Created);
        contentValues.put("Updated", Updated);
        long check = sqLiteDatabase.update("ThongTinNguoiDung", contentValues, "id = ?", new String[]{String.valueOf(id)});
        if (check == -1)
            return false;
        return true;
    }

    public int XoaThongTinNguoiDung(int id) {
        SQLiteDatabase sqLiteDatabase = safetyFoodDataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThongTinNguoiDung WHERE Id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("ThongTinNguoiDung", "id = ?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }
}
