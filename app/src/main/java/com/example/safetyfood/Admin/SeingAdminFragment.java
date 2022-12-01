package com.example.safetyfood.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safetyfood.ADAPTER.OrderHistoryAdapter;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;


public class SeingAdminFragment extends Fragment {

    RecyclerView Order_History_List;
    View view;
    List<DatHang> list;
    DatHangDAO datHangDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_seing_admin, container, false);

        Order_History_List = view.findViewById(R.id.Order_History_List);
        datHangDAO = new DatHangDAO(view.getContext());

        getData();

        return view;
    }

    private void getData() {
        list = new ArrayList<>(  );
        list = datHangDAO.getOrderHistory(0);
        setData();
    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        Order_History_List.setLayoutManager(linearLayoutManager);
        OrderHistoryAdapter adapter = new OrderHistoryAdapter(list,getContext());
        Order_History_List.setAdapter(adapter);
    }
}