package com.example.safetyfood.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.safetyfood.DATABASE.SafetyFoodDataBase;

import java.util.ArrayList;

public class CuaHangDAO {

    private SQLiteDatabase db;
    SafetyFoodDataBase safetyFoodDataBase;
    public CuaHangDAO(Context context){safetyFoodDataBase = new SafetyFoodDataBase(context);}


}
