package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.DatHang;

import java.util.ArrayList;
import java.util.List;

public class DatHangDAO {
    SafetyFoodDataBase dataBase;
    SQLiteDatabase db;

    public static String TagZZZ = "ZZZZZZ";

    String table_name = "DatHang";
    String DH_ID = "Id";
    String AccountId = "AccountId";
    String TotalPrice = "TotalPrice";
    String Created = "Created";
    String Updated = "Updated";
    String Status = "Status";

    public DatHangDAO(Context context) {
        dataBase = new SafetyFoodDataBase(context);
        db = dataBase.getReadableDatabase( );
    }

    public void InsertDH(DatHang datHang) {
        ContentValues values = new ContentValues( );
        values.put(DH_ID, datHang.getId( ));
        values.put(AccountId, datHang.getIdtaikhoan( ));
        values.put(TotalPrice, datHang.getTotalpriceDathang( ));
        values.put(Created, datHang.getCreateDathang( ));
        values.put(Updated, datHang.getUpdateDathang( ));
        values.put(Status, datHang.getStatusDathang( ));
        long kq = db.insert(table_name, null, values);
        Log.e(TagZZZ, "InsertDH: " + kq);
    }

    public void DeleteDH(DatHang datHang) {
        long kq = db.delete(table_name, DH_ID + "=?", new String[]{String.valueOf(datHang.getId( ))});
        Log.e(TagZZZ, "InsertDH: " + kq);
    }

    public List<DatHang> getAllList(){
        String sql = "select * from "+table_name;
        return getData(sql);
    }

    public DatHang getID(int ID){
        String sql = "select * from "+table_name +" where "+DH_ID+"=?";
        return getData(sql, String.valueOf(ID)).get(0);
    }

    public DatHang getLastCart(int accountID){
        String sql = "Select * from "+table_name+" where "+AccountId+"=?";
        List<DatHang> list = new ArrayList<>(  );
        list = getData(sql,String.valueOf(accountID));
        if(list.size()==0)
            return null;
        return list.get(list.size( )-1);
    }

    public List<DatHang> getData(String sql, String... selectionArgs) {
        List<DatHang> list = new ArrayList<>( );

        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst( );
        while (!cursor.isAfterLast( )) {

            DatHang datHang = new DatHang( );
            datHang.setId(cursor.getInt(cursor.getColumnIndex(DH_ID)));
            datHang.setIdtaikhoan(cursor.getInt(cursor.getColumnIndex(AccountId)));
            datHang.setTotalpriceDathang(cursor.getFloat(cursor.getColumnIndex(TotalPrice)));
            datHang.setCreateDathang(cursor.getString(cursor.getColumnIndex(Created)));
            datHang.setUpdateDathang(cursor.getString(cursor.getColumnIndex(Updated)));
            datHang.setStatusDathang(cursor.getInt(cursor.getColumnIndex(Status)));
            list.add(datHang);
            cursor.moveToNext( );
        }
        cursor.close( );
        return list;
    }
}
