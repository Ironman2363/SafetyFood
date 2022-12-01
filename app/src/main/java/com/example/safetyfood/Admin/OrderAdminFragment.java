package com.example.safetyfood.Admin;

import static com.example.safetyfood.MainActivity.account_all;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safetyfood.ADAPTER.OrderAdapter;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;

import java.util.List;


public class OrderAdminFragment extends Fragment {

    View view;
    RecyclerView Order_list;
    List<DatHang> list;
    DatHangDAO datHangDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_admin, container, false);


        Order_list = view.findViewById(R.id.Order_list);
        datHangDAO = new DatHangDAO(getContext());

        getData();

        return view;
    }

    private void getData() {
        list = datHangDAO.getAllOrderStatus(1,1);
        setList();
    }

    private void setList() {
        OrderAdapter adapter = new OrderAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Order_list.setLayoutManager(linearLayoutManager);
        Order_list.setAdapter(adapter);
    }
}