package com.example.safetyfood.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.safetyfood.ADAPTER.OrderHistoryAdapter;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;


public class SeingAdminFragment extends Fragment {

    RecyclerView Order_History_List;
    Spinner Order_History_Spn;
    View view;
    List<DatHang> list;
    List<String> listspn;
    DatHangDAO datHangDAO;
    int index,status,status1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_seing_admin, container, false);

        Order_History_List = view.findViewById(R.id.Order_History_List);
        Order_History_Spn = view.findViewById(R.id.Order_History_Spn);
        datHangDAO = new DatHangDAO(view.getContext());

        getDataSPN();

        Order_History_Spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        getData();
                        index = 0;
                        break;
                    }
                    case 1:{
                        getDataStatus(1,1);
                        index = 1;
                        break;
                    }
                    case 2:{
                        getDataStatus(2,2);
                        index = 2;
                        break;
                    }
                    case 3:{
                        getDataStatus(5,5);
                        index = 3;
                        break;
                    }
                    case 4:{
                        getDataStatus(3,4);
                        index = 4;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    private void getDataSPN() {
        listspn = new ArrayList<>(  );
        listspn.add("Toàn bộ");
        listspn.add("Đang chờ xử lý");
        listspn.add("Đang giao");
        listspn.add("Đã nhận");
        listspn.add("Đã hủy");
        setSPN();
    }

    private void getDataStatus(int status, int status1) {
        listspn = new ArrayList<>(  );
        list = datHangDAO.getAllOrderStatus(status,status1);
        setData();
    }

    private void getData() {
        list = new ArrayList<>(  );
        list = datHangDAO.getOrderHistory(0);
        setData();
    }

    private void setSPN() {
        ArrayAdapter<String> adapterspn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listspn);
        Order_History_Spn.setAdapter(adapterspn);
    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        Order_History_List.setLayoutManager(linearLayoutManager);
        OrderHistoryAdapter adapter = new OrderHistoryAdapter(list,getContext());
        Order_History_List.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume( );
        Order_History_Spn.setSelection(index);
        switch (index){
            case 0:{
                getData();
                break;
            }
            case 1:{
                getDataStatus(1,1);
                break;
            }
            case 2:{
                getDataStatus(2,2);
                break;
            }
            case 3:{
                getDataStatus(5,5);
                break;
            }
            case 4:{
                getDataStatus(3,4);
                break;
            }
        }
    }
}