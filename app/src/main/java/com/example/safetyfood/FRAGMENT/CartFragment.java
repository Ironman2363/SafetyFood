package com.example.safetyfood.FRAGMENT;

import static com.example.safetyfood.MainActivity.cart_all;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.ADAPTER.CartAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.R;
import com.example.safetyfood.Service.CheckCartService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    View view;
    RecyclerView Cart_list;
    TextView Cart_TotalPrice;
    ChiTietDatHangDAO chiTietDatHangDAO;
    DatHangDAO datHangDAO;
    List<ChiTietDatHang> chiTietDatHangList;
    Button Cart_Dat_Hang;
    int totalPrice = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        anhXa();

        getData();

        Cart_Dat_Hang.setOnClickListener(v -> {
            MuaHang();
        });


        return view;
    }

    private void MuaHang() {
        if (!chiTietDatHangList.isEmpty()) {
            cart_all.setTotalpriceDathang(totalPrice);
            cart_all.setStatusDathang(1);
            datHangDAO.UpgradeDH(cart_all);

            getContext().startService(new Intent(getContext(), CheckCartService.class));
            replaceFragment(new OrderFragment());
        }
    }

    private int loadMoney() {
        int sum = 0;
        for (ChiTietDatHang x : chiTietDatHangList) {
            sum += x.getUnitprice() * x.getAmount();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        Cart_TotalPrice.setText("Tổng thanh toán : " + decimalFormat.format(sum) + "đ");
        return sum;
    }

    private void setAdapterToRCL() {
        CartAdapter cartAdapter = new CartAdapter(chiTietDatHangList, getContext(), chiTietDatHangDAO, 1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
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

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void anhXa() {
        Cart_list = view.findViewById(R.id.Cart_list);
        Cart_TotalPrice = view.findViewById(R.id.Cart_TotalPrice);
        Cart_Dat_Hang = view.findViewById(R.id.Cart_Dat_Hang);
        chiTietDatHangList = new ArrayList<>();
        chiTietDatHangDAO = new ChiTietDatHangDAO(getContext());
        datHangDAO = new DatHangDAO(getContext());
        IntentFilter intentFilter = new IntentFilter("checkCart");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

        BottomNavigationView view = getActivity().findViewById(R.id.bottomNavigationView);

        Menu menu = view.getMenu();
        menu.findItem(R.id.order).setChecked(true);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra("type", 0);
            switch (type) {
                case 1: {
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