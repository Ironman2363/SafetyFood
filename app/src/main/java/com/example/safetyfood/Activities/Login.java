package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.account_all;
import static com.example.safetyfood.MainActivity.cart_all;
import static com.example.safetyfood.MainActivity.check_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetyfood.Admin.AdminActivity;
import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.MainActivity;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputEditText email, pass;
    Button login;
    TextView skip, signUp;
    TaikhoanDAO dao;
    TaiKhoan tk;
    TextInputLayout textInputName, textInputPass;
    public static ThongTinNguoiDung settingFragment = new ThongTinNguoiDung();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.edit_sdt);
        pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_login);
        skip = findViewById(R.id.skip);
        signUp = findViewById(R.id.signUp);
        textInputName = findViewById(R.id.Login_Til_Name);
        textInputPass = findViewById(R.id.Login_Til_Pass);
        dao = new TaikhoanDAO(this);

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", -1);
        if (key == 0) {
            email.setText("");
            pass.setText("");
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputPass.setError("");
                textInputName.setError("");
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
                    textInputName.setError("Vui lòng nhập tài khoản");
                } else if (mk.equalsIgnoreCase("")) {
                    textInputPass.setError("Vui lòng nhập mật khẩu");
                } else if (dao.checkDangNhapkhNVAD(mail, mk) == true) {
                    intent = new Intent(getApplicationContext(), AdminActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tk", dao.getName(mail));
                    intent.putExtra("bundle", bundle);
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    textInputName.setError("Tài khoản hoặc mật khẩu sai");
                    textInputPass.setError("Tài khoản hoặc mật khẩu sai");
                }


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
                sharedPreferences = getSharedPreferences("OKLuon", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                startActivity(new Intent(Login.this, MainActivity.class));
                cart_all = new DatHang();
                check_login = false;
                account_all = new TaiKhoan();
                settingFragment = new ThongTinNguoiDung();
                Log.e("ZZZZZ", "onClick: " + account_all + "." + check_login);
                editor.putString("FullName", settingFragment.getFullname());
                editor.putString("Avatar",settingFragment.getAvatarNguoidung());
                editor.commit();
            }
        });
    }

    private boolean checkTK(String mail, String mk) {
        if (dao.getName(mail) == null) {
            textInputName.setError("Tai khoan khong ton tai");
            return false;
        } else {
            tk = dao.getName(mail);
            if (!mk.equals(tk.getPassword())) {
                textInputPass.setError("Mật khẩu sai");
                return false;
            }
        }
        return true;
    }
}