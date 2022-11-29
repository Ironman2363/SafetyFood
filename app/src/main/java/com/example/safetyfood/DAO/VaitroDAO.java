package com.example.safetyfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;
import com.example.safetyfood.MODEL.VaiTro;

import java.util.ArrayList;
import java.util.List;

public class VaitroDAO {
    SQLiteDatabase db;
    SafetyFoodDataBase safetyFoodDataBase;

    public VaitroDAO(Context context) {
        safetyFoodDataBase = new SafetyFoodDataBase(context);
        db = safetyFoodDataBase.getWritableDatabase();
    }

    public List<VaiTro> getAllVaitro(String sql, String... select) {
        List<VaiTro> list = new ArrayList<>();
        db = safetyFoodDataBase.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, select);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            VaiTro vaiTro = new VaiTro();
//            "Id INTEGER REFERENCES TaiKhoan(Roled)," +
//                    "Name TEXT NOT NULL," +
//                    "Description TEXT NOT NULL,"+
//                    "Created datatime not null,"+
//
//                    datatime nay la gi va tai sao lai phai
//                    create va update (datatime nay co dung dc getString khong)


//                    "Updated datatime not null)";
            vaiTro.setId(cursor.getInt(0));
            vaiTro.setNameVaitro(cursor.getString(1));
            vaiTro.setDeschiptionVaitro(cursor.getString(2));
            vaiTro.setCreateVaitro(cursor.getString(3));
            vaiTro.setUpdateVaitro(cursor.getString(4));
            cursor.moveToNext();
            list.add(vaiTro);
        }
        cursor.close();
        return list;
    }

    public boolean insertVaitro(VaiTro vaiTro) {
        db = safetyFoodDataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", vaiTro.getNameVaitro());
        values.put("Description", vaiTro.getDeschiptionVaitro());
        values.put("Created", vaiTro.getCreateVaitro());
        values.put("Updated", vaiTro.getUpdateVaitro());
        Long row = db.insert("VaiTro", null, values);
        return row > 0;
    }

    public boolean updateVaitro(VaiTro vaiTro) {
        db = safetyFoodDataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", vaiTro.getId());
        values.put("Name", vaiTro.getNameVaitro());
        values.put("Description", vaiTro.getDeschiptionVaitro());
        values.put("Created", vaiTro.getCreateVaitro());
        values.put("Updated", vaiTro.getUpdateVaitro());
        int row = db.update("VaiTro", values, "Id=?", new String[]{vaiTro.getId() + ""});
        return row > 0;
    }

    public boolean deleteVaitro(String id) {
        db = safetyFoodDataBase.getWritableDatabase();
        int row = db.delete("VaiTro", "Id=?", new String[]{id});
        return row > 0;
    }

    public List<VaiTro> getAll() {
        String sql = "SELECT * FROM VaiTro";
        return getAllVaitro(sql);
    }

    public VaiTro getID(String id) {
        String sql = "SELECT * FROM VaiTro where Id=?";
        List<VaiTro> list = getAllVaitro(sql);
        return list.get(0);
    }
}
