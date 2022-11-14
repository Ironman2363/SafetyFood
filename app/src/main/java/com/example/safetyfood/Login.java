package com.example.safetyfood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
   EditText email , pass;
   Button login;
   TextView account , quenpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.edit_sdt);
        pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_login);
        account = findViewById(R.id.taotk);
        quenpass = findViewById(R.id.quenpass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String mk = pass.getText().toString();
                if (mail.isEmpty()|| mk.isEmpty()){
                    Toast.makeText(Login.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}