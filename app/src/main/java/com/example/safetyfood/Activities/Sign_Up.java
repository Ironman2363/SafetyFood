package com.example.safetyfood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.safetyfood.R;

public class Sign_Up extends AppCompatActivity {

    Toolbar toolbar;
    TextView txt_toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(null);

        txt_toLogin.setOnClickListener(v -> {
            toLoginListener();
        });
    }

    private void toLoginListener() {
        startActivity(new Intent(this,Login.class));
    }

    private void anhXa() {
        toolbar = findViewById(R.id.SU_tool_bar);
        txt_toLogin = findViewById(R.id.txt_toLogin);
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