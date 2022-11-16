package com.example.safetyfood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.safetyfood.R;

public class ThongTinCuaHang extends AppCompatActivity {
    WebView wed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_cua_hang);
        wed = findViewById(R.id.map);
        wed.loadUrl("https://www.google.com/maps/@21.0283695,105.7473378,15.81z");
        wed.getSettings().setJavaScriptEnabled(true);
        wed.setWebViewClient(new WebViewClient());

    }
}