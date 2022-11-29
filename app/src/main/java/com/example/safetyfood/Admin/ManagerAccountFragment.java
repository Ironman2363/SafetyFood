package com.example.safetyfood.Admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.safetyfood.ADAPTER.TaiKhoanAdapter;
import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.MODEL.Itemclick;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class ManagerAccountFragment extends Fragment {
    RecyclerView recycledsNhanVien;
    TaikhoanDAO taikhoanDAO;
    EditText edtTK, edtMK;
    Button btnthem, btnSua;
    int manv;

    public ManagerAccountFragment() {

    }

    public static ManagerAccountFragment newInstance(String param1, String param2) {
        ManagerAccountFragment fragment = new ManagerAccountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_account, container, false);
        recycledsNhanVien = view.findViewById(R.id.recycledsNhanVien);
        edtTK = view.findViewById(R.id.edt_tk_nv);
        edtMK = view.findViewById(R.id.edt_mk_nv);
        btnthem = view.findViewById(R.id.btn_dktk_nv);
        btnSua = view.findViewById(R.id.btn_udtk_nv);
        loaddata();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtTK.getText().toString();
                String pass = edtMK.getText().toString();
                TaiKhoan taiKhoan = new TaiKhoan(0, user, pass, 2);
                if (taikhoanDAO.insertTaikhoan(taiKhoan) == true) {
                    if (user.equalsIgnoreCase("") || pass.equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Bạn cần nhập tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Đăng ký nhân viên thành công", Toast.LENGTH_SHORT).show();
                        loaddata();
                        edtTK.setText("");
                        edtMK.setText("");
                    }

                } else {
                    Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtTK.getText().toString();
                String pass = edtMK.getText().toString();
                TaiKhoan taiKhoan = new TaiKhoan(0, user, pass, 2);
                if (taikhoanDAO.thayDoiLoaiSach(taiKhoan)) {

                    loaddata();
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }


    private void loaddata() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycledsNhanVien.setLayoutManager(linearLayoutManager);
        taikhoanDAO = new TaikhoanDAO(getContext());
        ArrayList<TaiKhoan> list = taikhoanDAO.getDSNV();
        TaiKhoanAdapter adapter = new TaiKhoanAdapter(getContext(), list, new Itemclick() {
            @Override
            public void onCickLoaiSach(TaiKhoan taiKhoan) {
                edtTK.setText(taiKhoan.getUsername());

                edtMK.setText(taiKhoan.getPassword());
                manv = taiKhoan.getId();
            }
        });
        recycledsNhanVien.setAdapter(adapter);
    }

}