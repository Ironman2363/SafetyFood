package com.example.safetyfood.Service;

import static com.example.safetyfood.MainActivity.account_all;
import static com.example.safetyfood.MainActivity.cart_all;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.DatHang;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckCartService extends Service {

    DatHangDAO datHangDAO;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int check = -1;
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String text = simpleDateFormat.format(calendar.getTime());
        datHangDAO = new DatHangDAO(getApplicationContext());

        DatHang datHang = datHangDAO.getBuyingCart(account_all.getId());
        if (datHang == null) {
            DatHang datHang1 = new DatHang();
            datHang1.setIdtaikhoan(account_all.getId());
            datHang1.setStatusDathang(0);
            datHang1.setCreateDathang(text);
            datHang1.setUpdateDathang(text);
            datHang1.setTotalpriceDathang(0);
            datHangDAO.InsertDH(datHang1);
            check = 1;
        }

        if (check == 1) {
            Log.e("Put1", "onStartCommand: ");
            DatHang NewDH = datHangDAO.getBuyingCart(account_all.getId());
            Intent intent1 = new Intent();
            intent1.setAction("checkCart");
            Bundle bundle = new Bundle();
            bundle.putSerializable("cart", NewDH);
            intent1.putExtra("bundle", bundle);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
        } else {
            Log.e("Put-1", "onStartCommand: ");
            Intent intent1 = new Intent();
            intent1.setAction("checkCart");
            Bundle bundle = new Bundle();
            bundle.putSerializable("cart", datHang);
            intent1.putExtra("bundle", bundle);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
        }
        stopSelf();
        return START_NOT_STICKY;
    }
}
