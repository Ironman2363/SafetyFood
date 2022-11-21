package com.example.safetyfood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

public class SanPhamDetail extends AppCompatActivity {

    Toolbar SPDetail_toolbar;
    ImageView SPDetail_Img;
    TextView SPDetail_Ten,SPDetail_Gia,SpDetail_Created,SPDetail_Status;

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
    }

    private void setData(SanPham sanPham) {
        SPDetail_Img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        SPDetail_Ten.setText(sanPham.getNameSanpham());
        SPDetail_Gia.setText(sanPham.getPriceSanpham()+" VND");
        SpDetail_Created.setText(sanPham.getCreateSanpham());
        if(sanPham.getStatusSanpham()==1){
            SPDetail_Status.setTextColor(Color.GREEN);
            SPDetail_Status.setText("Còn hàng");
        }else {
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}