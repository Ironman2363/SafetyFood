package com.example.safetyfood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.safetyfood.ADAPTER.CartAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;

import java.util.List;

public class OrderDetail extends AppCompatActivity {

    Toolbar OrderDetail_Toolbar;
    LinearLayout Order_Done_Check;
    TextView Order_Done_Trang_Thai, Order_Done_Thanks, Order_Done_Address, Order_Done_TotalPrice;
    RecyclerView Order_Done_list;
    ImageView Order_Done_Img;
    DatHangDAO datHangDAO;
    ChiTietDatHangDAO chiTietDatHangDAO;
    SanPhamDAO sanPhamDAO;
    DatHang datHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        anhXa();

        setSupportActionBar(OrderDetail_Toolbar);
        OrderDetail_Toolbar.setTitle("Thông tin đơn hàng");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (bundle != null) {
            datHang = (DatHang) bundle.getSerializable("datHang");
            checkStatus();
            getData();
        } else {

        }
    }

    private void getData() {
        List<ChiTietDatHang> list = chiTietDatHangDAO.getListCT(datHang.getId());
        setList(list);
    }

    private void setList(List<ChiTietDatHang> list) {
        CartAdapter adapter = new CartAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Order_Done_list.setLayoutManager(linearLayoutManager);
        Order_Done_list.setAdapter(adapter);
        setMoney();
    }

    private void setMoney() {
        int money = (int) datHang.getTotalpriceDathang();
        Order_Done_TotalPrice.setText("Thành tiền : " + money + " VNĐ");
    }

    private void checkStatus() {
        int status = datHang.getStatusDathang();
        String text1 = "";
        String text2 = "";
        int color = 0;
        int img_src = 0;
        switch (status) {
            case 1: {
                text1 = "Đơn hàng đang được xử lý";
                text2 = "Cảm ơn bạn đã mua hàng tại SafetyFood";
                color = R.color.Order_Done;
                img_src = R.drawable.order_done;
                break;
            }
            case 2: {
                text1 = "Đơn hàng đang được vận chuyển";
                text2 = "Cảm ơn bạn đã mua hàng tại SafetyFood";
                color = R.color.Order_Done;
                img_src = R.drawable.order_done;
                break;
            }
            case 3:
            case 4: {
                text1 = "Đơn hàng đã bị hủy";
                text2 = "Rất xin lỗi vì sự bất tiện của SafetyFood";
                img_src = R.drawable.order_fail;
                color = R.color.red;
                break;
            }
            case 5: {
                text1 = "Đơn hàng đã hoàn thành";
                text2 = "Cảm ơn bạn đã mua hàng tại SafetyFood";
                color = R.color.Order_Done;
                img_src = R.drawable.order_done;
                break;
            }
        }
        Order_Done_Check.setBackgroundColor(getColor(color));
        Order_Done_Trang_Thai.setText(text1);
        Order_Done_Thanks.setText(text2);
        Order_Done_Img.setImageResource(img_src);
    }

    private void anhXa() {
        OrderDetail_Toolbar = findViewById(R.id.OrderDetail_Toolbar);
        Order_Done_Check = findViewById(R.id.Order_Done_Check);
        Order_Done_Trang_Thai = findViewById(R.id.Order_Done_Trang_Thai);
        Order_Done_Thanks = findViewById(R.id.Order_Done_Thanks);
        Order_Done_Address = findViewById(R.id.Order_Done_Address);
        Order_Done_TotalPrice = findViewById(R.id.Order_Done_TotalPrice);
        Order_Done_list = findViewById(R.id.Order_Done_list);
        Order_Done_Img = findViewById(R.id.Order_Done_Img);
        datHangDAO = new DatHangDAO(getApplicationContext());
        chiTietDatHangDAO = new ChiTietDatHangDAO(getApplicationContext());
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