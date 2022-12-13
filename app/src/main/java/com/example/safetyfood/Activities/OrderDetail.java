package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.account_all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.safetyfood.ADAPTER.CartAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.DAO.ThongTinNguoiDungDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderDetail extends AppCompatActivity {

    Toolbar OrderDetail_Toolbar;
    LinearLayout Order_Done_Check;
    TextView Order_Done_Trang_Thai, Order_Done_Thanks, Order_Done_Address, Order_Done_TotalPrice;
    RecyclerView Order_Done_list;
    ImageView Order_Done_Img;
    DatHangDAO datHangDAO;
    ThongTinNguoiDungDAO thongTinNguoiDungDAO;
    ChiTietDatHangDAO chiTietDatHangDAO;
    SanPhamDAO sanPhamDAO;
    DatHang datHang;
    Button Btn_Admin_Huy,Btn_Admin_GiaoHang;
    Calendar calendar;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent( );
        Bundle bundle = intent.getBundleExtra("bundle");

        anhXa( );

        setSupportActionBar(OrderDetail_Toolbar);
        OrderDetail_Toolbar.setTitle("Thông tin đơn hàng");
        ActionBar actionBar = getSupportActionBar( );
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (bundle != null) {
            datHang = (DatHang) bundle.getSerializable("datHang");
            checkInfo( );
            checkStatus( );
            getData( );
        }

        if(account_all.getRole()==3){
            Btn_Admin_Huy.setVisibility(View.GONE);
            Btn_Admin_GiaoHang.setVisibility(View.GONE);
        }

        Btn_Admin_Huy.setOnClickListener(v -> {
            AdminHuy();
        });

        Btn_Admin_GiaoHang.setOnClickListener(v -> {
            AdminGiaoHang();
        });
    }

    private void AdminGiaoHang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Bạn có chắc muốn giao đơn hàng này không ?")
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
                        datHang.setStatusDathang(2);
                        datHangDAO.UpgradeDH(datHang);
                        checkStatus();
                    }
                });

        builder.create().show();
    }

    private void AdminHuy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Bạn có chắc muốn hủy đơn hàng này không ?")
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
                        datHang.setStatusDathang(4);
                        datHangDAO.UpgradeDH(datHang);
                        checkStatus();
                    }
                });

        builder.create().show();
    }

    private void checkInfo() {
        ThongTinNguoiDung info = thongTinNguoiDungDAO.getInfo(datHang.getIdtaikhoan( ));
        Order_Done_Address.setText(info.getFullname( ) + "\n"
                + info.getAddresNguoidung( ) + "\n" +
                info.getSdtNguoidung()
        );
    }

    private void getData() {
        List<ChiTietDatHang> list = chiTietDatHangDAO.getListCT(datHang.getId( ));
        setList(list);
    }

    private void setList(List<ChiTietDatHang> list) {
        CartAdapter adapter = new CartAdapter(list, getApplicationContext(),chiTietDatHangDAO,0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Order_Done_list.setLayoutManager(linearLayoutManager);
        Order_Done_list.setAdapter(adapter);
        setMoney( );
    }

    private void setMoney() {
        int money = (int) datHang.getTotalpriceDathang( );
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Order_Done_TotalPrice.setText("Thành tiền : " + decimalFormat.format(money) + "đ");
    }

    private void checkStatus() {
        int status = datHang.getStatusDathang( );
        String text1 = "";
        String text2 = "";
        String textGiao = "";
        int color = 0;
        int img_src = 0;
        switch (status) {
            case 1: {
                text1 = "Đơn hàng đang được xử lý";
                text2 = "Vui lòng xử lý đơn hàng";
                textGiao = "Giao hàng";
                color = R.color.Order_Done;
                img_src = R.drawable.order_done;
                break;
            }
            case 2: {
                text1 = "Đơn hàng đang được vận chuyển";
                text2 = "Shipper đang được vận chuyển";
                textGiao = "Đang giao hàng";
                Btn_Admin_GiaoHang.setVisibility(View.GONE);
                Btn_Admin_Huy.setVisibility(View.GONE);
                color = R.color.Order_Done;
                img_src = R.drawable.order_done;
                break;
            }
            case 3:
            case 4: {
                text1 = "Đơn hàng đã bị hủy";
                if(datHang.getStatusDathang()==3){
                    text2 = "Đơn đã bị hủy từ phía người dùng";
                }else {
                    text2 = "Đơn hàng bị hủy từ phía cửa hàng";
                }
                textGiao = "Đơn đã hủy";
                Btn_Admin_GiaoHang.setVisibility(View.GONE);
                Btn_Admin_Huy.setVisibility(View.GONE);
                img_src = R.drawable.order_fail;
                color = R.color.red;
                break;
            }
            case 5: {
                text1 = "Đơn hàng đã hoàn thành";
                text2 = "Khách hàng đã nhận được đơn hàng";
                textGiao = "Đã nhận";
                Btn_Admin_GiaoHang.setVisibility(View.GONE);
                Btn_Admin_Huy.setVisibility(View.GONE);
                color = R.color.Order_Done;
                img_src = R.drawable.order_done;
                break;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Order_Done_Check.setBackgroundColor(getColor(color));
        }
        Btn_Admin_GiaoHang.setText(textGiao);
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
        Btn_Admin_Huy = findViewById(R.id.Btn_Admin_Huy);
        Btn_Admin_GiaoHang = findViewById(R.id.Btn_Admin_GiaoHang);
        datHangDAO = new DatHangDAO(getApplicationContext( ));
        chiTietDatHangDAO = new ChiTietDatHangDAO(getApplicationContext( ));
        thongTinNguoiDungDAO = new ThongTinNguoiDungDAO(getApplicationContext( ));
        sanPhamDAO = new SanPhamDAO(getApplicationContext( ));
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId( )) {
            case android.R.id.home: {
                onBackPressed( );
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}