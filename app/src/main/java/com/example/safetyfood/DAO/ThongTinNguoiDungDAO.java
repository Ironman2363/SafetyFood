package com.example.safetyfood.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ThongTinNguoiDungDAO {
    SafetyFoodDataBase safetyFoodDataBase;
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;

    public ThongTinNguoiDungDAO(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
        db = safetyFoodDataBase.getReadableDatabase();
        sharedPreferences = context.getSharedPreferences("OKLuon",MODE_PRIVATE);

    }

    public List<ThongTinNguoiDung> getThongTinNguoiDungs() {
        String sql = "SELECT * FROM ThongTinNguoiDung";
        return getData(sql);
    }

    public boolean themThongTinNguoiDung(ThongTinNguoiDung info) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccountId", info.getIdtaikhoan());
        contentValues.put("FullName", info.getFullname());
        contentValues.put("Email", info.getEmailNguoidung());
        contentValues.put("SDT", info.getEmailNguoidung());
        contentValues.put("Addres", info.getAddresNguoidung());
        contentValues.put("Avatar", info.getAvatarNguoidung());
        contentValues.put("Birthday", info.getBirthdayNguoidung());
        contentValues.put("Gender", info.getGender());
        contentValues.put("Created", info.getCreateNguoidung());
        contentValues.put("Updated", info.getUpdateNguoidung());
        long check = db.insert("ThongTinNguoiDung", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinNguoiDung(ThongTinNguoiDung info) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("AccountId", info.getIdtaikhoan());
        contentValues.put("FullName", info.getFullname());
        contentValues.put("Email", info.getEmailNguoidung());
        contentValues.put("SDT", info.getSdtNguoidung());
        contentValues.put("Addres", info.getAddresNguoidung());
        contentValues.put("Birthday", info.getBirthdayNguoidung());
        contentValues.put("Gender", info.getGender());
        contentValues.put("Created", info.getCreateNguoidung());
        contentValues.put("Updated", info.getUpdateNguoidung());
        long check = db.update("ThongTinNguoiDung", contentValues, "id = ?", new String[]{String.valueOf(info.getId())});
        if (check == -1)
            return false;
        return true;
    }
    public boolean capNhatAnh(ThongTinNguoiDung info) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Avatar", info.getAvatarNguoidung());
        long check = db.update("ThongTinNguoiDung", contentValues, "Id = ?", new String[]{String.valueOf(info.getId())});
        if (check == -1)
            return false;
        return true;
    }

    public int XoaThongTinNguoiDung(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM ThongTinNguoiDung WHERE Id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("ThongTinNguoiDung", "id = ?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }
    public List<ThongTinNguoiDung> getData(){
        List<ThongTinNguoiDung> list = new ArrayList<>(  );
        Cursor cursor = db.rawQuery("select*from ThongTinNguoiDung",null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThongTinNguoiDung(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getInt(8), cursor.getString(9),cursor.getString(10)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public List<ThongTinNguoiDung> getData(String sql,String... selectionArgs){
        List<ThongTinNguoiDung> list = new ArrayList<>(  );
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThongTinNguoiDung(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getInt(8), cursor.getString(9),cursor.getString(10)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ThongTinNguoiDung getInfo(int id){
        String sql = "SELECT * FROM ThongTinNguoiDung WHERE AccountId=?";
        return getData(sql,new String[]{String.valueOf(id)}).get(0);
    }

}
