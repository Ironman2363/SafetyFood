package com.example.safetyfood.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.SanPham;
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

    public List<SanPham> getTop() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT SanPham.*,sum(amount) AS sum FROM ChiTietDatHang " +
                "JOIN DatHang on DatHang.Id=ChiTietDatHang.orderid " +
                "JOIN SanPham on ChiTietDatHang.ProductId = SanPham.Id " +
                "WHERE DatHang.status=5 GROUP by productid ORDER BY sum DESC LIMIT 10";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new SanPham(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getInt(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Top> getTopSP(String tuNgay, String denNgay) {
        List<Top> list = new ArrayList<>();
        String sql = "SELECT ChiTietDatHang.ProductId,sum(ChiTietDatHang.Amount) as sum FROM ChiTietDatHang " +
                "JOIN DatHang on ChiTietDatHang.OrderId=DatHang.Id " +
                "JOIN SanPham on SanPham.Id=ChiTietDatHang.ProductId " +
                "WHERE DatHang.Status=5 and (date(DatHang.Updated) BETWEEN ? AND ?) " +
                "GROUP by productid ORDER BY sum DESC";
        Cursor cursor = db.rawQuery(sql, new String[]{tuNgay, denNgay});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Top(cursor.getInt(0), cursor.getInt(1)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay) {
        String sql = "select SUM(TotalPrice) as doanhThu from DatHang where (date(Created) between ? and ?) and Status=5";
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
