package com.example.safetyfood.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputEditText;

public class Passwordchange extends AppCompatActivity {

    TextInputEditText edtOldPass, edtNewPass, edtReNewPass;
    Button btn_passchage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);

        btn_passchage = findViewById(R.id.btn_passchage);
        edtOldPass = findViewById(R.id.edtOldPass);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtReNewPass = findViewById(R.id.edtReNewPass);


        btn_passchage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String renewPass = edtReNewPass.getText().toString();
                if (oldPass.equals("") || newPass.equals("")|| renewPass.equals("")){
                    Toast.makeText(Passwordchange.this, "Nhập Đầy Đu Thông Tin Cần Thiết", Toast.LENGTH_SHORT).show();
                }else {
                    if (newPass.equals(renewPass)){
                        SharedPreferences sharedPreferences = getSharedPreferences("OKLuon",MODE_PRIVATE);
                        String Id = sharedPreferences.getString("Id","");
                        TaikhoanDAO taikhoanDAO = new TaikhoanDAO(Passwordchange.this);
                        int check = taikhoanDAO.capNhapMatKhau(Id,oldPass,newPass);
                        if (check ==1){
                            Toast.makeText(Passwordchange.this, "Cập Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Passwordchange.this,Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if (check == 0){
                            Toast.makeText(Passwordchange.this, "Mật Khẩu Cũ Không Đúng", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Passwordchange.this, "Cập Nhập Mật Khẩu Cũ Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Passwordchange.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}