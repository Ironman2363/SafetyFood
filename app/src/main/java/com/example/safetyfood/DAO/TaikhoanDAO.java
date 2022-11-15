package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class TaikhoanDAO {
    SQLiteDatabase db;
    SafetyFoodDataBase safetyFoodDataBase;

    public TaikhoanDAO(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
        db = safetyFoodDataBase.getWritableDatabase();
    }
    public List<TaiKhoan>getAllTaikhoan(String sql , String...select){
        List<TaiKhoan>list = new ArrayList<>();
        db = safetyFoodDataBase.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,select);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setId(cursor.getInt(0));
//            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    " UserName TEXT NOT NULL," +
//                    "Password TEXT NOT NULL,"+
//                    "Roled INTEGER NOT NULL)";
            taiKhoan.setUsername(cursor.getString(1));
            taiKhoan.setPassword(cursor.getString(2));
            taiKhoan.setRole(cursor.getInt(3));
            cursor.moveToNext();
            list.add(taiKhoan);
        }
        cursor.close();
        return list;
    }
    public boolean insertTaikhoan(TaiKhoan taiKhoan){
       db = safetyFoodDataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id",taiKhoan.getId());
        values.put("UserName",taiKhoan.getUsername());
        values.put("Password",taiKhoan.getPassword());
        values.put("Roled",taiKhoan.getRole());
        Long row = db.insert("TaiKhoan",null,values);
        return row >0;
    }
    public boolean updateTaikhoan(TaiKhoan taiKhoan){
        db = safetyFoodDataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id",taiKhoan.getId());
        values.put("UserName",taiKhoan.getUsername());
        values.put("Password",taiKhoan.getPassword());
        values.put("Roled",taiKhoan.getRole());
        int row = db.update("TaiKhoan",values,"Id=?",new String[]{taiKhoan.getId()+""});
        return row >0;
    }
    public boolean deleteTaikhoan(String id){
        db = safetyFoodDataBase.getWritableDatabase();
        int row = db.delete("TaiKhoan","Id=?",new String[]{id});
        return row >0;
    }
    public List<TaiKhoan>getAll(){
        String sql = "SELECT * FROM TaiKhoan";
        return getAllTaikhoan(sql);
    }
    public TaiKhoan getID(){
        String sql = "SELECT * FROM TaiKhoan WHERE Id=?";
        List<TaiKhoan>list = getAllTaikhoan(sql);
        return list.get(0);
    }
}