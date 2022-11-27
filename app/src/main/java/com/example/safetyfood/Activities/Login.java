package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.account_all;
import static com.example.safetyfood.MainActivity.cart_all;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetyfood.Admin.AdminActivity;
import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.MainActivity;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextInputEditText email, pass;
    Button login;
    TextView skip, signUp;
    TaikhoanDAO dao;
    TaiKhoan taiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.edit_sdt);
        pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_login);
        skip = findViewById(R.id.skip);
        signUp = findViewById(R.id.signUp);
        dao = new TaikhoanDAO(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                String mail = email.getText().toString();
                String mk = pass.getText().toString();
                if (dao.checkDangNhapkh(mail, mk) == true) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tk", dao.getName(mail));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else if (mail.equalsIgnoreCase("")) {
                    Toast.makeText(Login.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (mk.equalsIgnoreCase("")) {
                    Toast.makeText(Login.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else if (dao.checkDangNhapkhNVAD(mail,mk) == true){
                    intent = new Intent(getApplicationContext(), AdminActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tk", dao.getName(mail));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else{


                    Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();

                }
                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                if (checkTK(mail,mk)){
//                    Intent intent;
//                    if (taiKhoan.getRole() == 3){
//                        intent = new Intent(getApplicationContext(),MainActivity.class);
//                    }else {
//                        intent = new Intent(getApplicationContext(),AdminActivity.class);
//                    }
//                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("tk", dao.getName(mail));
//                    intent.putExtra("bundle", bundle);
//                    startActivity(intent);
//                }

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Sign_Up.class));
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
                cart_all = new DatHang();
                account_all = new TaiKhoan();
            }
        });
    }

    private boolean checkTK(String mail, String mk) {
        if (dao.getName(mail) == null) {
            Toast.makeText(Login.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            TaiKhoan tk = dao.getName(mail);
            if (!mk.equals(tk.getPassword())) {
                Toast.makeText(Login.this, "Mật khẩu sai", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}