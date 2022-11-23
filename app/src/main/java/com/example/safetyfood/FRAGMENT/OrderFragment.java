package com.example.safetyfood.FRAGMENT;

import static com.example.safetyfood.MainActivity.account_all;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safetyfood.ADAPTER.OrderAdapter;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    View view;
    RecyclerView Order_list;
    List<DatHang> datHangList;
    DatHangDAO datHangDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_order, container, false);

        anhXa();

        getData();

        return view;
    }

    private void setRCL() {
        OrderAdapter adapter = new OrderAdapter(datHangList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Order_list.setLayoutManager(linearLayoutManager);
        Order_list.setAdapter(adapter);
    }

    private void getData() {
        datHangList = datHangDAO.getCartStatus(account_all.getId(),1);
        setRCL();
        Log.e("ZZZZ", "getData: "+datHangList.size() );
    }

    private void anhXa() {
        Order_list = view.findViewById(R.id.Order_list);
        datHangDAO = new DatHangDAO(getContext());
        datHangList = new ArrayList<>(  );
    }
}