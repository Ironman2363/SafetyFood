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
    TaiKhoan tk;

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

        login.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                String mail = email.getText( ).toString( );
                String mk = pass.getText( ).toString( );
                if (mail.isEmpty( ) || mk.isEmpty( )) {
                    Toast.makeText(Login.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show( );
                } else {
                    if(checkTK(mail,mk)){
                        Intent intent;
                        if(tk.getRole()==3){
                             intent = new Intent(getApplicationContext(),MainActivity.class);
                        }else {
                            intent =new Intent( getApplicationContext(), AdminActivity.class);
                        }
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show( );
                        Bundle bundle = new Bundle(  );
                        bundle.putSerializable("tk",dao.getName(mail));
                        intent.putExtra("bundle",bundle);
                        startActivity(intent);
                    }
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Sign_Up.class));
            }
        });
        skip.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
                cart_all = new DatHang(  );
                account_all = new TaiKhoan(  );
            }
        });
    }

    private boolean checkTK(String mail, String mk) {
        if(dao.getName(mail)==null){
            Toast.makeText(Login.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show( );
            return false;
        }else {
            tk = dao.getName(mail);
            if(!mk.equals(tk.getPassword())){
                Toast.makeText(Login.this, "Mật khẩu sai", Toast.LENGTH_SHORT).show( );
                return false;
            }
        }
        return true;
    }
}