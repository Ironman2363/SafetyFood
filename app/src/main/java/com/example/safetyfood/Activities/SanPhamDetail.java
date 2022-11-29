package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.cart_all;
import static com.example.safetyfood.MainActivity.check_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safetyfood.ADAPTER.SanPhamAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDetail extends AppCompatActivity {

    Toolbar SPDetail_toolbar;
    ImageView SPDetail_Img;
    TextView SPDetail_Ten, SPDetail_Gia, SpDetail_Created, SPDetail_Status;
    ImageButton SPDetail_Buy;
    ChiTietDatHangDAO dao;
    RecyclerView SPDetail_List_Tuong_Tu, SPDetail_List_Goi_Y;
    List<SanPham> sanPhamListTT, sanPhamListGY;
    SanPhamDAO sanPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_detail);

        anhXa();

        setSupportActionBar(SPDetail_toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        SanPham sanPham = (SanPham) bundle.getSerializable("sp");
        setData(sanPham);
        setList(sanPham);
        Log.e("ZZZZZ", "onCreate: " + sanPham);

        SPDetail_Buy.setOnClickListener(v -> {
            if (check_login) {
                addToCart(sanPham);
            } else {
                Toast.makeText(this, "Bạn cần đăng nhập để sử dụng chức năng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setList(SanPham sanPham) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        getDataList(sanPham);
        SPDetail_List_Tuong_Tu.setLayoutManager(linearLayoutManager);
        SanPhamAdapter adapterTT = new SanPhamAdapter(sanPhamListTT, getApplicationContext());

        SPDetail_List_Tuong_Tu.setAdapter(adapterTT);
    }

    private void getDataList(SanPham sanPham) {
        sanPhamListTT = sanPhamDAO.getSpTT(Integer.parseInt(sanPham.getLoaiSanpham()), sanPham.getId());

    }

    private ChiTietDatHang CheckCart(SanPham sanPham) {
        List<ChiTietDatHang> list = new ArrayList<>();
        list = dao.checkCart(cart_all.getId(), sanPham.getId());
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private void addToCart(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_add_to_cart, null);
        builder.setView(view);

        AlertDialog dialog;
        ImageButton dialog_atc_remove, dialog_atc_add;
        EditText dialog_atc_amount;
        Button dialog_atc_cancle, dialog_atc_ok;

        dialog_atc_amount = view.findViewById(R.id.dialog_atc_amount);
        dialog_atc_remove = view.findViewById(R.id.dialog_atc_remove);
        dialog_atc_add = view.findViewById(R.id.dialog_atc_add);
        dialog_atc_cancle = view.findViewById(R.id.dialog_atc_cancle);
        dialog_atc_ok = view.findViewById(R.id.dialog_atc_ok);

        dialog = builder.create();

        dialog_atc_add.setOnClickListener(v -> {
            dialog_atc_amount.setText(String.valueOf(Integer.parseInt(dialog_atc_amount.getText().toString()) + 1));
        });

        dialog_atc_remove.setOnClickListener(v -> {
            if (dialog_atc_amount.getText().toString().equals("1")) {
                Toast.makeText(this, "Số lượng phải lớn hơn hoặc bằng 1", Toast.LENGTH_SHORT).show();
                return;
            }
            dialog_atc_amount.setText(String.valueOf(Integer.parseInt(dialog_atc_amount.getText().toString()) - 1));
        });

        ChiTietDatHang checkCart = CheckCart(sanPham);

        dialog_atc_ok.setOnClickListener(v -> {
            if (checkCart == null) {
                ChiTietDatHang chiTietDatHang = new ChiTietDatHang();
                chiTietDatHang.setAmount(Integer.parseInt(dialog_atc_amount.getText().toString()));
                chiTietDatHang.setProductid(sanPham.getId());
                chiTietDatHang.setUnitprice(Integer.parseInt(dialog_atc_amount.getText().toString()) * sanPham.getPriceSanpham());
                chiTietDatHang.setIdDathang(cart_all.getId());
                dao.ThemChiTietDatHang(chiTietDatHang);
                Toast.makeText(this, "Đã đặt thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                checkCart.setAmount(checkCart.getAmount() + Integer.parseInt(dialog_atc_amount.getText().toString()));
                checkCart.setUnitprice(checkCart.getUnitprice() +
                        (Integer.parseInt(dialog_atc_amount.getText().toString()) * sanPham.getPriceSanpham()));
                dao.CapNhapChiTietDatHang(checkCart);
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog_atc_cancle.setOnClickListener(v -> {
            dialog.dismiss();
        });


        dialog.show();
    }

    private void setData(SanPham sanPham) {
        SPDetail_Img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        SPDetail_Ten.setText(sanPham.getNameSanpham());
        SPDetail_Gia.setText(sanPham.getPriceSanpham() + " VND");
        SpDetail_Created.setText(sanPham.getCreateSanpham());
        if (sanPham.getStatusSanpham() == 1) {
            SPDetail_Status.setTextColor(Color.GREEN);
            SPDetail_Status.setText("Còn hàng");
        } else {
            SPDetail_Status.setTextColor(Color.RED);
            SPDetail_Status.setText("Hết hàng");
        }
    }

    private void anhXa() {
        SPDetail_toolbar = findViewById(R.id.SPDetail_toolbar);
        SPDetail_Img = findViewById(R.id.SPDetail_Img);
        SPDetail_Ten = findViewById(R.id.SPDetail_Ten);
        SPDetail_Gia = findViewById(R.id.SPDetail_Gia);
        SpDetail_Created = findViewById(R.id.SpDetail_Created);
        SPDetail_Status = findViewById(R.id.SPDetail_Status);
        SPDetail_Buy = findViewById(R.id.SPDetail_Buy);
        SPDetail_List_Tuong_Tu = findViewById(R.id.SPDetail_List_Tuong_Tu);
        SPDetail_List_Goi_Y = findViewById(R.id.SPDetail_List_Goi_Y);
        dao = new ChiTietDatHangDAO(getApplicationContext());
        sanPhamDAO = new SanPhamDAO(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}