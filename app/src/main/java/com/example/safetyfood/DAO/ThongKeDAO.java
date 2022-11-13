package com.example.safetyfood.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;

public class ThongKeDAO {
    SafetyFoodDataBase dataBase;
    SQLiteDatabase db;

    public ThongKeDAO(Context context) {
        dataBase = new SafetyFoodDataBase(context);
        db = dataBase.getReadableDatabase( );
    }

}
