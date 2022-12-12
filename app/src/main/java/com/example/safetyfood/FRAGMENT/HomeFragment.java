package com.example.safetyfood.FRAGMENT;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safetyfood.ADAPTER.LoaiSanPhamAdapter;
import com.example.safetyfood.ADAPTER.PhotoAdapter;
import com.example.safetyfood.ADAPTER.SanPhamAdapter;
import com.example.safetyfood.Activities.SearchItems;
import com.example.safetyfood.Activities.ShowAll;
import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.DAO.ThongKeDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.MODEL.Photo;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {

    View view;
    Context context;
    EditText edt_findFood;
    TextInputLayout Home_TIL_findFood;
    RecyclerView RCL_loaiSP, RCL_SP, RCL_TSP;
    LoaiSanPhamDAO loaiSanPhamDAO;
    SanPhamDAO sanPhamDAO;
    List<LoaiSanPham> loaiSanPhamList;
    List<SanPham> sanPhamList, topSanPham;
    ThongKeDAO thongKeDAO;
    ViewPager viewPager;
    CircleIndicator indicator;
    List<Photo> listImage = getListPhoto();
    Timer timer;
    TextView Home_Show_All;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        anhXa();
        autoSidelImage();
        Home_TIL_findFood.setStartIconOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchItems.class);
            intent.putExtra("search", edt_findFood.getText().toString().trim());
            startActivity(intent);
        });
        Home_Show_All.setOnClickListener(v -> {
            startActivity(new Intent( getContext(), ShowAll.class));
        });

        getData();

        return view;
    }

    private void autoSidelImage() {
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int item = viewPager.getCurrentItem();
                        int totalitem = listImage.size() - 1;
                        if (item < totalitem) {
                            item++;
                            viewPager.setCurrentItem(item);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    private void setRCLAdapter() {
        LoaiSanPhamAdapter adapter = new LoaiSanPhamAdapter(loaiSanPhamList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RCL_loaiSP.setLayoutManager(linearLayoutManager);
        RCL_loaiSP.setAdapter(adapter);

        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(sanPhamList, getContext(),1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RCL_SP.setLayoutManager(layoutManager);
        RCL_SP.setAdapter(sanPhamAdapter);

        SanPhamAdapter sanPhamAdapter1 = new SanPhamAdapter(topSanPham, getContext(),1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RCL_TSP.setLayoutManager(layoutManager1);
        RCL_TSP.setAdapter(sanPhamAdapter1);

        PhotoAdapter photoAdapter = new PhotoAdapter(context, getListPhoto());
        viewPager.setAdapter(photoAdapter);
        indicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(indicator.getDataSetObserver());

    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.banner1));
        list.add(new Photo(R.drawable.banner2));
        list.add(new Photo(R.drawable.banner3));
        list.add(new Photo(R.drawable.banner4));
        list.add(new Photo(R.drawable.banner5));
        return list;
    }
// cho nay da sua

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
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Trà sữa", String.valueOf(R.drawable.trasua)
                    , "19/11/2022", "19/11/2022", 1));
            loaiSanPhamDAO.themLoaiSanPham(new LoaiSanPham(0, 0, "Tráng miệng", String.valueOf(R.drawable.trangmieng)
                    , "19/11/2022", "19/11/2022", 1));
        }
        if (sanPhamDAO.getDSSanPham().isEmpty()) {
            sanPhamDAO.themSanpham(new SanPham(0, "Coca-cola", String.valueOf(R.drawable.cocanoback), 25000
                    , "1", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Cơm tấm", String.valueOf(R.drawable.comtam), 25000
                    , "4", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Cơm rang", String.valueOf(R.drawable.comrangduabo), 25000
                    , "4", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Cơm gia đình", String.valueOf(R.drawable.comgiadinh), 25000
                    , "4", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Phở bò", String.valueOf(R.drawable.phobo), 35000
                    , "2", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Mì cay", String.valueOf(R.drawable.micay), 35000
                    , "2", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Khoai tây chiên", String.valueOf(R.drawable.khaitaychiennoback), 35000
                    , "3", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Hot dog", String.valueOf(R.drawable.hotdog), 35000
                    , "3", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Pepsi", String.valueOf(R.drawable.pesinobg), 25000
                    , "1", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Phở", String.valueOf(R.drawable.phoooo), 40000
                    , "2", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Bánh bao", String.valueOf(R.drawable.banhbaonoback), 40000
                    , "5", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Bánh mì", String.valueOf(R.drawable.banhminoback), 40000
                    , "5", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Bánh cuốn", String.valueOf(R.drawable.banhcuonnoback), 40000
                    , "5", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Bánh xèo", String.valueOf(R.drawable.banhxeonoback), 40000
                    , "5", "19/11/2022", "19/11/2022", 1));
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
            sanPhamDAO.themSanpham(new SanPham(0, "Sườn Nướng", String.valueOf(R.drawable.suongnuongno), 25000
                    , "6", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Bánh Flan", String.valueOf(R.drawable.banhplan), 25000
                    , "8", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Kem", String.valueOf(R.drawable.kem), 25000
                    , "8", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Sữa chua", String.valueOf(R.drawable.suachua), 25000
                    , "8", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Trái cây", String.valueOf(R.drawable.diahoaqua), 25000
                    , "8", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Heo quay", String.valueOf(R.drawable.heoquay), 25000
                    , "6", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Vịt quay", String.valueOf(R.drawable.vitquay), 25000
                    , "6", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Gà nướng", String.valueOf(R.drawable.ganuong), 25000
                    , "6", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Sữa tươi trân châu", String.valueOf(R.drawable.suatuoitranchau), 25000
                    , "7", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Trà sữa việt quất", String.valueOf(R.drawable.trasuavietquat), 25000
                    , "7", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Trà sữa dâu tây", String.valueOf(R.drawable.trausuadautay), 25000
                    , "7", "19/11/2022", "19/11/2022", 1));
            sanPhamDAO.themSanpham(new SanPham(0, "Trà sữa matcha", String.valueOf(R.drawable.matchakem), 25000
                    , "7", "19/11/2022", "19/11/2022", 1));
        }
        loaiSanPhamList = loaiSanPhamDAO.getDSLoaiSanPham();
        topSanPham = thongKeDAO.getTop();
        if (topSanPham.isEmpty() || topSanPham.size() < 10) {
            topSanPham = sanPhamDAO.getDSSanPham();
        }
        sanPhamList = sanPhamDAO.getDSSanPham();
        setRCLAdapter();
    }

    private void anhXa() {
        context = view.getContext();
        edt_findFood = view.findViewById(R.id.edt_findFood);
        Home_TIL_findFood = view.findViewById(R.id.Home_TIL_findFood);
        RCL_loaiSP = view.findViewById(R.id.RCL_loaiSP);
        RCL_SP = view.findViewById(R.id.RCL_SP);
        RCL_TSP = view.findViewById(R.id.RCL_TSP);
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
        sanPhamDAO = new SanPhamDAO(context);
        thongKeDAO = new ThongKeDAO(context);
        viewPager = view.findViewById(R.id.viewpager);
        indicator = view.findViewById(R.id.circle);
        Home_Show_All = view.findViewById(R.id.Home_Show_All);
    }

}