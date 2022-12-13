package com.example.safetyfood.Admin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.safetyfood.ADAPTER.OrderHistoryAdapter;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.ThongKeDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;
import com.example.safetyfood.Tab_Fragment.ChoXacNhanFragment;
import com.example.safetyfood.Tab_Fragment.DaNhanFragment;
import com.example.safetyfood.Tab_Fragment.GiaoHangFragment;
import com.example.safetyfood.Tab_Fragment.HuyFragment;
import com.example.safetyfood.Tab_Fragment.ThongKeDoanhThuFragment;
import com.example.safetyfood.Tab_Fragment.ThongKeSoSPDaBanFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class ThongKeFragment extends Fragment {

    TabLayout ThongKe_TabLayout;
    ViewPager2 ThongKe_ViewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        ThongKe_TabLayout = view.findViewById(R.id.ThongKe_TabLayout);
        ThongKe_ViewPager2 = view.findViewById(R.id.ThongKe_ViewPager2);
        ThongKeAdapter adapter = new ThongKeAdapter(getActivity());
        ThongKe_ViewPager2.setAdapter(adapter);
        new TabLayoutMediator(ThongKe_TabLayout, ThongKe_ViewPager2, new TabLayoutMediator.TabConfigurationStrategy( ) {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("Doanh thu");
                        break;
                    }
                    case 1: {
                        tab.setText("Sản phẩm bán chạy");
                        break;
                    }
                }
            }
        }).attach();

        return view;
    }

    class ThongKeAdapter extends FragmentStateAdapter {
        public ThongKeAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: {
                    return new ThongKeDoanhThuFragment();
                }
                case 1: {
                    return new ThongKeSoSPDaBanFragment();
                }
                default:
                    return new ThongKeDoanhThuFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}