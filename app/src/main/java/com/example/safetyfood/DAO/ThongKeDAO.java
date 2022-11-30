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
        db = dataBase.getReadableDatabase();
    }

    public List<Top> getTop() {
        List<Top> list = new ArrayList<>();
        String sql = "select ProductId, count(ProductId) as sum from ChiTietDatHang group by ProductId order by sum desc limit 10";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Top top = new Top();
            top.setProductId(cursor.getInt(cursor.getColumnIndex(ProductId)));
            top.setSum(cursor.getInt(cursor.getColumnIndex("sum")));
            list.add(top);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay) {
        String sql = "select SUM(TotalPrice) as doanhThu from DatHang where date(Created) between ? and ? and Status=5";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, new String[]{tuNgay, denNgay});
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }
}
