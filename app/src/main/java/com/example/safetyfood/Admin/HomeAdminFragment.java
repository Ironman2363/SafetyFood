package com.example.safetyfood.Admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.safetyfood.ADAPTER.ViewSanPhamAdapter;
import com.example.safetyfood.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class HomeAdminFragment extends Fragment {
   View view;
   ViewSanPhamAdapter phamAdapter;
   TabLayout layout;
   ViewPager2 pager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home_admin, container, false);
        layout = view.findViewById(R.id.Sanpham_Tab);
        pager2 = view.findViewById(R.id.Sanpham_ViewPager2);
        phamAdapter = new ViewSanPhamAdapter(getActivity());
        pager2.setAdapter(phamAdapter);
        new TabLayoutMediator(layout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("Thêm loại sản phẩm");
                        break;
                    }
                    case 1:{
                        tab.setText("Thêm sản phẩm");
                        break;
                    }
                }
            }
        }).attach();
        return view;
    }
}