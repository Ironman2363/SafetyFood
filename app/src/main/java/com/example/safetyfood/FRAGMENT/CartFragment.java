package com.example.safetyfood.FRAGMENT;

import static com.example.safetyfood.MainActivity.cart_all;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.safetyfood.ADAPTER.CartAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    View view;
    RecyclerView Cart_list;
    TextView Cart_TotalPrice;
    ChiTietDatHangDAO dao;
    List<ChiTietDatHang> chiTietDatHangList;
    Button Cart_Dat_Hang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        anhXa();

        getData();

        setAdapterToRCL();

        loadMoney();
        return view;
    }

    private void loadMoney() {
        int sum = 0;
        for(ChiTietDatHang x : chiTietDatHangList) {
            sum += x.getUnitprice();
        }
        Cart_TotalPrice.setText("Tổng tiền : "+sum+" VND");
    }

    private void setAdapterToRCL() {
        CartAdapter cartAdapter = new CartAdapter(chiTietDatHangList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Cart_list.setLayoutManager(linearLayoutManager);
        Cart_list.setAdapter(cartAdapter);
    }

    private void getData() {
        if (dao.getListCT(cart_all.getId()).isEmpty()){
            Cart_TotalPrice.setText("Bạn chưa có gì trong giỏ hàng");
        }
        chiTietDatHangList = dao.getListCT(cart_all.getId());
    }

    private void anhXa() {
        Cart_list = view.findViewById(R.id.Cart_list);
        Cart_TotalPrice = view.findViewById(R.id.Cart_TotalPrice);
        Cart_Dat_Hang = view.findViewById(R.id.Cart_Dat_Hang);
        chiTietDatHangList = new ArrayList<>(  );
        dao = new ChiTietDatHangDAO(getContext());
    }
}