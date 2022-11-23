package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.ChiTietDatHang;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDatHangDAO {

    private SQLiteDatabase db;
    SafetyFoodDataBase dataBase;

    public ChiTietDatHangDAO(Context context) {
        dataBase = new SafetyFoodDataBase(context);
    }

    public ArrayList<ChiTietDatHang> getDSChiTietDatHang() {
        ArrayList<ChiTietDatHang> list = new ArrayList<>( );
        db = dataBase.getReadableDatabase( );
        Cursor cursor = db.rawQuery("SELECT * FROM ChiTietDatHang", null);

        if (cursor.getCount( ) != 0) {
            cursor.moveToFirst( );
            do {
                list.add(new ChiTietDatHang(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext( ));
        }
        return list;
    }

    public boolean ThemChiTietDatHang(ChiTietDatHang chiTietDatHang) {
        db = dataBase.getWritableDatabase( );
        ContentValues contentValues = new ContentValues( );
        contentValues.put("OrderId", chiTietDatHang.getIdDathang( ));
        contentValues.put("ProductId", chiTietDatHang.getProductid( ));
        contentValues.put("UnitPrice", chiTietDatHang.getUnitprice( ));
        contentValues.put("Amount", chiTietDatHang.getAmount( ));
        long check = db.insert("ChiTietDatHang", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean CapNhapChiTietDatHang(ChiTietDatHang chiTietDatHang) {
        db = dataBase.getWritableDatabase( );
        ContentValues contentValues = new ContentValues( );
        contentValues.put("OrderId", chiTietDatHang.getIdDathang( ));
        contentValues.put("ProductId", chiTietDatHang.getProductid( ));
        contentValues.put("UnitPrice", chiTietDatHang.getUnitprice( ));
        contentValues.put("Amount", chiTietDatHang.getAmount( ));
        long check = db.update("ChiTietDatHang", contentValues, "Id =?", new String[]{String.valueOf(chiTietDatHang.getId( ))});

        if (check == -1)
            return false;
        return true;
    }

    public int XoaChiTietDatHang(int Id) {
        db = dataBase.getWritableDatabase( );
        Cursor cursor = db.rawQuery("SELECT*FROM ChiTietDatHang WHERE Id = ?", new String[]{String.valueOf(Id)});
        if (cursor.getCount( ) != 0) {
            return -1;
        }
        long check = db.delete("ChiTietDatHang", "Id = ?", new String[]{String.valueOf(Id)});
        if (check == -1)
            return 0;
        return 1;
    }

    public List<ChiTietDatHang> getListCT(int idDatHang) {
        List<ChiTietDatHang> list = new ArrayList<>( );
        db = dataBase.getWritableDatabase( );
        String sql = "select * from ChiTietDatHang where OrderId =?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(idDatHang)});
        cursor.moveToFirst( );
        while (!cursor.isAfterLast( )) {

            int id = cursor.getInt(0);
            int idDathang = cursor.getInt(1);
            int productid = cursor.getInt(2);
            float unitprice = cursor.getFloat(3);
            float amount = cursor.getFloat(4);

            ChiTietDatHang chiTietDatHang = new ChiTietDatHang(id,idDathang,productid,unitprice,amount);
            list.add(chiTietDatHang);

            cursor.moveToNext( );
        }
        cursor.close( );
        return list;
    }

    public List<ChiTietDatHang> checkCart(int idDatHang, int IdSP){
        List<ChiTietDatHang> list = new ArrayList<>( );
        db = dataBase.getWritableDatabase( );
        String sql = "select * from ChiTietDatHang where OrderId =? and ProductId =?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(idDatHang),String.valueOf(IdSP)});
        cursor.moveToFirst( );
        while (!cursor.isAfterLast( )) {

            int id = cursor.getInt(0);
            int idDathang = cursor.getInt(1);
            int productid = cursor.getInt(2);
            float unitprice = cursor.getFloat(3);
            float amount = cursor.getFloat(4);

            ChiTietDatHang chiTietDatHang = new ChiTietDatHang(id,idDathang,productid,unitprice,amount);
            list.add(chiTietDatHang);

            cursor.moveToNext( );
        }
        cursor.close( );
        return list;
    }
}
