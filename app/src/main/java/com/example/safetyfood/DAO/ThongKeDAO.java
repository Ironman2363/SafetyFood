package com.example.safetyfood.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.Top;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    SafetyFoodDataBase dataBase;
    SQLiteDatabase db;

    String ProductId = "ProductId";
    String Table_ChiTietDH = "ChiTietDatHang";
    String Table_DatHang = "DatHang";
    String Created = "Created";
    String TotalPrice = "TotalPrice";

    public ThongKeDAO(Context context) {
        dataBase = new SafetyFoodDataBase(context);
        db = dataBase.getReadableDatabase( );
    }

    public List<Top> getTop(){
        List<Top> list = new ArrayList<>(  );
        String sql = "select "+ProductId+", count("+ProductId+") as sum from "+ Table_ChiTietDH +" group by "+ProductId+
                " order by sum desc limit 10";
        Cursor cursor = db.rawQuery(sql,null);
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "select SUM("+TotalPrice+") as doanhThu from "+Table_DatHang+" where "+Created+" between ? and ?";
        List<Integer> list = new ArrayList<>(  );
        Cursor cursor = db.rawQuery(sql,new String[]{tuNgay,denNgay});
        cursor.moveToFirst();
        while (!cursor.isAfterLast( )){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
            cursor.moveToNext();
        }
        cursor.close( );
        return list.get(0);
    }

}
