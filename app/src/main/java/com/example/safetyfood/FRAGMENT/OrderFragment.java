package com.example.safetyfood.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safetyfood.ADAPTER.ViewPager2Adapter;
import com.example.safetyfood.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class OrderFragment extends Fragment {

    View view;
    TabLayout Order_Tab;
    ViewPager2 Order_ViewPager2;
    ViewPager2Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);

        anhXa();
        Order_ViewPager2.setAdapter(adapter);
        new TabLayoutMediator(Order_Tab, Order_ViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("Chờ xử lý");
                        break;
                    }
                    case 1: {
                        tab.setText("Đang giao");
                        break;
                    }
                    case 2: {
                        tab.setText("Đã giao");
                        break;
                    }
                    case 3: {
                        tab.setText("Đã hủy");
                        break;
                    }
                }
            }
        }).attach();

        return view;
    }

    private void anhXa() {
        Order_Tab = view.findViewById(R.id.Order_Tab);
        Order_ViewPager2 = view.findViewById(R.id.Order_ViewPager2);
        adapter = new ViewPager2Adapter(getActivity());
    }
}