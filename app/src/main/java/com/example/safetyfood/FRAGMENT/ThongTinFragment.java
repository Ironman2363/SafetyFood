package com.example.safetyfood.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.ADAPTER.ThongTinNguoiDungAdapter;
import com.example.safetyfood.DAO.ThongTinNguoiDungDAO;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.R;

import java.util.List;

public class ThongTinFragment extends Fragment {
    ThongTinNguoiDungDAO dao;
    RecyclerView recyclerView;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_thong_tin, container, false);

            back = view.findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   replaceFragment(new SettingFragment());
                }
            });
            recyclerView = view.findViewById(R.id.recycledThongtin);
            dao = new ThongTinNguoiDungDAO(getContext());
        List<ThongTinNguoiDung> list = dao.getThongTinNguoiDungs();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ThongTinNguoiDungAdapter thongTinNguoiDungAdapter = new ThongTinNguoiDungAdapter(getContext(),list,dao);
        recyclerView.setAdapter(thongTinNguoiDungAdapter);
        return view;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager( );
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction( )
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit( );
    }
}