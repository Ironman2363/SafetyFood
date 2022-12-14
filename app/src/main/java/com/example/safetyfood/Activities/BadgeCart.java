package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.cart_all;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safetyfood.ADAPTER.CartAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.FRAGMENT.CartFragment;
import com.example.safetyfood.FRAGMENT.OrderFragment;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.R;
import com.example.safetyfood.Service.CheckCartService;
import com.example.safetyfood.Tab_Fragment.ChoXacNhanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BadgeCart extends AppCompatActivity {

    RecyclerView Cart_list;
    TextView Cart_TotalPrice;
    ChiTietDatHangDAO chiTietDatHangDAO;
    DatHangDAO datHangDAO;
    List<ChiTietDatHang> chiTietDatHangList;
    Button Cart_Dat_Hang;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_cart);

        anhXa();

        getData();

        Cart_Dat_Hang.setOnClickListener(v -> {
            MuaHang();
        });

    }

    private void MuaHang() {
        if (!chiTietDatHangList.isEmpty()) {
            cart_all.setTotalpriceDathang(totalPrice);
            cart_all.setStatusDathang(1);
            datHangDAO.UpgradeDH(cart_all);

            startService(new Intent( this,CheckCartService.class ));
            finish();
            Toast.makeText(this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();

        }
    }


    private int loadMoney() {
        int sum = 0;
        for (ChiTietDatHang x : chiTietDatHangList) {
            sum += x.getUnitprice()*x.getAmount();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        Cart_TotalPrice.setText("Tổng thanh toán : " + decimalFormat.format(sum) + "đ");
        return sum;
    }

    private void setAdapterToRCL() {
        CartAdapter cartAdapter = new CartAdapter(chiTietDatHangList, getApplicationContext(),chiTietDatHangDAO,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        Cart_list.setLayoutManager(linearLayoutManager);
        Cart_list.setAdapter(cartAdapter);
    }

    private void getData() {
        chiTietDatHangList = chiTietDatHangDAO.getListCT(cart_all.getId());
        if (chiTietDatHangList.isEmpty()) {
            Cart_TotalPrice.setText("Bạn chưa có gì trong giỏ hàng");
            setAdapterToRCL();
            return;
        }
        totalPrice = loadMoney();
        setAdapterToRCL();
    }

    private void anhXa() {
        Cart_list = findViewById(R.id.Cart_list);
        Cart_TotalPrice = findViewById(R.id.Cart_TotalPrice);
        Cart_Dat_Hang = findViewById(R.id.Cart_Dat_Hang);
        chiTietDatHangList = new ArrayList<>();
        chiTietDatHangDAO = new ChiTietDatHangDAO(getApplicationContext());
        datHangDAO = new DatHangDAO(getApplicationContext());
        IntentFilter intentFilter = new IntentFilter("checkCart");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra("type",0);
            switch (type){
                case 1:{
                    chiTietDatHangList = chiTietDatHangDAO.getListCT(cart_all.getId());
                    totalPrice = loadMoney();
                    break;
                }
                default:
                    getData();
            }
        }
    };

}