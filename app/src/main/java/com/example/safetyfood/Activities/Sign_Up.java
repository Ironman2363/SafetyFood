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
import com.google.android.material.textfield.TextInputLayout;

public class Sign_Up extends AppCompatActivity {
    TextInputEditText SU_edt_UserName,SU_edt_PassWord,SU_edt_RePassWord;
    TextInputLayout SU_til_UserName,SU_til_PassWord,SU_til_RePassWord;
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
        SU_til_UserName.setErrorEnabled(false);
        SU_til_PassWord.setErrorEnabled(false);
        SU_til_RePassWord.setErrorEnabled(false);
        if(username.isEmpty()){
            SU_til_UserName.setError("Không được để trống username");
            return;
        }
        else if (taikhoanDAO.getName(username)!=null){
            SU_til_UserName.setError("Tài khoản đã tồn tại");
            return;
        }
        if(password.isEmpty()){
            SU_til_PassWord.setError("Không được để trống password");
            return;
        }else if(repass.isEmpty()){
            SU_til_RePassWord.setError("Không được để trống repass");
            return;
        }
        else if (password.equals(repass)){
            TaiKhoan taiKhoan = new TaiKhoan(0, username, password, 3);
            taikhoanDAO.insertTaikhoan(taiKhoan);
            Intent intent = new Intent(this,DangKyThongTinActivity.class);
            intent.putExtra("user",username);
            intent.putExtra("pass",password);
            startActivity(intent);
        }
        else{
            SU_til_PassWord.setError("Password không khớp");
            SU_til_RePassWord.setError("Password không khớp");
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
        SU_til_UserName = findViewById(R.id.SU_til_UserName);
        SU_til_PassWord = findViewById(R.id.SU_til_PassWord);
        SU_til_RePassWord = findViewById(R.id.SU_til_RePassWord);
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