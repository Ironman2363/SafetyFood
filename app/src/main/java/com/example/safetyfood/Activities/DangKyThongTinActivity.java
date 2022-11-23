package com.example.safetyfood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.safetyfood.MainActivity;
import com.example.safetyfood.R;

public class DangKyThongTinActivity extends AppCompatActivity {

    Button hoantat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_thong_tin);
        hoantat = findViewById(R.id.btnDangkythongtin);
        hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKyThongTinActivity.this, MainActivity.class));
                Toast.makeText(DangKyThongTinActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}