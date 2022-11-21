package com.example.safetyfood.FRAGMENT;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView RCL_loaiSP,RCL_SP;
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
            Toast.makeText(context, "find", Toast.LENGTH_SHORT).show( );
        });

        getData();

        setRCLAdapter();

        return view;
    }

    private void setRCLAdapter() {
        LoaiSanPhamAdapter adapter = new LoaiSanPhamAdapter(loaiSanPhamList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        RCL_loaiSP.setLayoutManager(linearLayoutManager);
        RCL_loaiSP.setAdapter(adapter);

        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(sanPhamList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        RCL_SP.setLayoutManager(layoutManager);
        RCL_SP.setAdapter(sanPhamAdapter);
    }

    private void getData() {
        if(loaiSanPhamDAO.getDSLoaiSanPham().isEmpty()){
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0,0,"Nước uống",String.valueOf(R.drawable.loaisp_coca)
                    ,"19/11/2022","19/11/2022",1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0,0,"Phở",String.valueOf(R.drawable.loaisp_pho)
                    ,"19/11/2022","19/11/2022",1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0,0,"Gà rán",String.valueOf(R.drawable.loaisp_garan)
                    ,"19/11/2022","19/11/2022",1));
        }
        if(sanPhamDAO.getDSSanPham().isEmpty()){
            sanPhamDAO.themSanpham(new SanPham(0,"Coca-cola",String.valueOf(R.drawable.loaisp_coca),25000f
                    ,"Nước uống","19/11/2022","19/11/2022",1));
            sanPhamDAO.themSanpham(new SanPham(0,"Phở bò",String.valueOf(R.drawable.loaisp_pho),35000f
                    ,"Phở","19/11/2022","19/11/2022",1));
            sanPhamDAO.themSanpham(new SanPham(0,"Gàn rán",String.valueOf(R.drawable.loaisp_garan),35000f
                    ,"Gà rán","19/11/2022","19/11/2022",1));
        }
        loaiSanPhamList = loaiSanPhamDAO.getDSLoaiSanPham();
        sanPhamList = sanPhamDAO.getDSSanPham();
    }

    private void anhXa() {
        context = view.getContext();
        edt_findFood = view.findViewById(R.id.edt_findFood);
        Home_TIL_findFood = view.findViewById(R.id.Home_TIL_findFood);
        RCL_loaiSP = view.findViewById(R.id.RCL_loaiSP);
        RCL_SP = view.findViewById(R.id.RCL_SP);
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
        sanPhamDAO = new SanPhamDAO(context);
    }
}