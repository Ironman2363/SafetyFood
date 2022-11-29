package com.example.safetyfood.FRAGMENT;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safetyfood.ADAPTER.LoaiSanPhamAdapter;
import com.example.safetyfood.ADAPTER.SanPhamAdapter;
import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class HomeFragment extends Fragment {

    View view;
    Context context;
    EditText edt_findFood;
    TextInputLayout Home_TIL_findFood;
    RecyclerView RCL_loaiSP, RCL_SP,RCL_TSP;
    LoaiSanPhamDAO loaiSanPhamDAO;
    SanPhamDAO sanPhamDAO;
    List<LoaiSanPham> loaiSanPhamList;
    List<SanPham> sanPhamList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        anhXa();

        Home_TIL_findFood.setStartIconOnClickListener(v -> {
            Toast.makeText(context, "" + edt_findFood.getText().toString(), Toast.LENGTH_SHORT).show();
        });

        getData();

        setRCLAdapter();

        return view;
    }

    private void setRCLAdapter() {
        LoaiSanPhamAdapter adapter = new LoaiSanPhamAdapter(loaiSanPhamList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RCL_loaiSP.setLayoutManager(linearLayoutManager);
        RCL_loaiSP.setAdapter(adapter);

        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(sanPhamList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RCL_SP.setLayoutManager(layoutManager);
        RCL_SP.setAdapter(sanPhamAdapter);
        SanPhamAdapter sanPhamAdapter1 = new SanPhamAdapter(sanPhamList,getContext());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        RCL_TSP.setLayoutManager(layoutManager1);
        RCL_TSP.setAdapter(sanPhamAdapter1);

    }

    private void getData() {
        if (loaiSanPhamDAO.getDSLoaiSanPham().isEmpty()) {
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Nước ngọt", String.valueOf(R.drawable.nuocoga)
                    , "19/11/2022", "19/11/2022", 1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Phở", String.valueOf(R.drawable.pho)
                    , "19/11/2022", "19/11/2022", 1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Đồ ăn nhanh", String.valueOf(R.drawable.doananh)
                    , "19/11/2022", "19/11/2022", 1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Cơm", String.valueOf(R.drawable.com)
                    , "19/11/2022", "19/11/2022", 1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Bánh", String.valueOf(R.drawable.bread)
                    , "19/11/2022", "19/11/2022", 1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Thịt", String.valueOf(R.drawable.meat)
                    , "19/11/2022", "19/11/2022", 1));
        }
        if (sanPhamDAO.getDSSanPham().isEmpty()) {
            sanPhamDAO.themSanpham(new SanPham(0, "Coca-cola", String.valueOf(R.drawable.cocanoback), 25000
                    , "1", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Cơm tấm", String.valueOf(R.drawable.comtam), 25000
                    , "4", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Cơm rang", String.valueOf(R.drawable.comrangduabo), 25000
                    , "4", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Phở bò", String.valueOf(R.drawable.phobo), 35000
                    , "2", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Khoai tây chiên", String.valueOf(R.drawable.khaitaychiennoback), 35000
                    , "3", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Pepsi", String.valueOf(R.drawable.pesinobg), 25000
                    , "1", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Phở", String.valueOf(R.drawable.phoooo), 40000
                    , "2", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Hamburger", String.valueOf(R.drawable.hamburgernobackk), 45000
                    , "3", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Cơm văn phòng", String.valueOf(R.drawable.comvanphong), 25000
                    , "4", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Monter", String.valueOf(R.drawable.monter), 27000
                    , "1", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Bún bò", String.valueOf(R.drawable.bunbonoback), 30000
                    , "2", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Gà Rán", String.valueOf(R.drawable.garannoback), 35000
                    , "3", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Nước cam", String.valueOf(R.drawable.nccamnoback), 25000
                    , "1", "19/11/2022", "19/11/2022", 1));
        }
        loaiSanPhamList = loaiSanPhamDAO.getDSLoaiSanPham();
        Log.e("ZZZZZ", "getData: " + loaiSanPhamList.get(0));
        sanPhamList = sanPhamDAO.getDSSanPham();
    }

    private void anhXa() {
        context = view.getContext();
//        edt_findFood = view.findViewById(R.id.edt_findFood);
        Home_TIL_findFood = view.findViewById(R.id.Home_TIL_findFood);
        RCL_loaiSP = view.findViewById(R.id.RCL_loaiSP);
        RCL_SP = view.findViewById(R.id.RCL_SP);
        RCL_TSP = view.findViewById(R.id.RCL_TSP);
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
        sanPhamDAO = new SanPhamDAO(context);
    }
}