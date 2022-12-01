package com.example.safetyfood.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputEditText;

public class Sign_Up extends AppCompatActivity {
    TextInputEditText SU_edt_UserName;
    TextInputEditText SU_edt_PassWord;
    TextInputEditText SU_edt_RePassWord;
    TaikhoanDAO taikhoanDAO;

    Toolbar toolbar;
    TextView txt_toLogin;
    AppCompatButton appCompatButton;

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
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = SU_edt_UserName.getText().toString();
                String password = SU_edt_PassWord.getText().toString();
                String repass = SU_edt_RePassWord.getText().toString();

                DangKy(username, password, repass);
            }
        });

    }

    private void DangKy(String username, String password, String repass) {
        if (password.equals(repass)){
            TaiKhoan taiKhoan = new TaiKhoan(0, username, password, 2);
            taikhoanDAO.insertTaikhoan(taiKhoan);
            startActivity(new Intent(Sign_Up.this, DangKyThongTinActivity.class));
        }
        else{
            Toast.makeText(this, "Hai mật khẩu chưa trùng nhau", Toast.LENGTH_SHORT).show();
        }
    }

    private void toLoginListener() {
        startActivity(new Intent(this, Login.class));
    }

    private void anhXa() {
        toolbar = findViewById(R.id.SU_tool_bar);
        txt_toLogin = findViewById(R.id.txt_toLogin);
        appCompatButton = findViewById(R.id.appCompatButton);

        SU_edt_UserName = findViewById(R.id.SU_edt_UserName);
        SU_edt_PassWord = findViewById(R.id.SU_edt_PassWord);
        SU_edt_RePassWord = findViewById(R.id.SU_edt_RePassWord);
        taikhoanDAO = new TaikhoanDAO(this);
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