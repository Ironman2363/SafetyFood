package com.example.safetyfood.Tab_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.R;

import java.util.List;

public class ChoXacNhanFragment extends Fragment {

    View view;
    RecyclerView Order_list;
    List<ChiTietDatHang> list;
    ChiTietDatHangDAO chiTietDatHangDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cho_xac_nhan, container, false);

        Order_list = view.findViewById(R.id.Order_list);
        chiTietDatHangDAO = new ChiTietDatHangDAO(getContext());

        getData();

        return view;
    }

    private void getData() {

    }
}