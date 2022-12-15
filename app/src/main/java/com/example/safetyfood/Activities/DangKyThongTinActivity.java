package com.example.safetyfood.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.DAO.ThongTinNguoiDungDAO;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DangKyThongTinActivity extends AppCompatActivity {
    ThongTinNguoiDungDAO nguoiDungDAO;
    Button hoantat;
    ImageView avatar;
    TextInputLayout namelayout,sdtlayout,emaillayout,diachilayout,ngaysinhlayout;
    EditText sdt,email, dia_chi, name, ngay_sinh;
    RadioButton GT_nam, GT_nu;
    String userDK, passDK;
    TaikhoanDAO taikhoanDAO;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_thong_tin);

        Intent intent = getIntent();
        userDK = intent.getStringExtra("user");
        passDK = intent.getStringExtra("pass");

        namelayout = findViewById(R.id.namelayout);
        sdtlayout = findViewById(R.id.sdtlayout);
        emaillayout = findViewById(R.id.emaillayout);
        diachilayout = findViewById(R.id.diachilayout);
        ngaysinhlayout = findViewById(R.id.ngaysinhlayout);

        hoantat = findViewById(R.id.btnDangkythongtin);
        sdt = findViewById(R.id.edtSdt);
        email = findViewById(R.id.edtEmail);
        dia_chi = findViewById(R.id.edtDiachi);
        name = findViewById(R.id.edtHoten);
        ngay_sinh = findViewById(R.id.edtNgaysinh);
        GT_nam = findViewById(R.id.rdo_GT_Nam);
        GT_nu = findViewById(R.id.rdo_BTN_Nu);
        avatar = findViewById(R.id.edtAvatar);
        nguoiDungDAO = new ThongTinNguoiDungDAO(this);
        taikhoanDAO = new TaikhoanDAO(this);
        if (taikhoanDAO.getName(userDK).getPassword().equals(passDK)){
            taiKhoan=taikhoanDAO.getName(userDK);
        }
        hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = name.getText().toString();
                String phone = sdt.getText().toString();
                String mail = email.getText().toString();
                String address = dia_chi.getText().toString();
                String ngay = ngay_sinh.getText().toString();

                if(check(ten,phone,mail,address,ngay)){
                    ThongTinNguoiDung nguoiDung = new ThongTinNguoiDung();
                    nguoiDung.setEmailNguoidung(mail);
                    nguoiDung.setAddresNguoidung(address);
                    nguoiDung.setBirthdayNguoidung(ngay);
                    nguoiDung.setFullname(ten);
                    nguoiDung.setSdtNguoidung(phone);

                    if (GT_nu.isChecked()) {
                        nguoiDung.setGender(0);
                    } else if (GT_nam.isChecked()) {
                        nguoiDung.setGender(1);
                    }
                    Calendar calendar = Calendar.getInstance();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String text = simpleDateFormat.format(calendar.getTime());
                    nguoiDung.setCreateNguoidung(text);
                    nguoiDung.setAvatarNguoidung(String.valueOf(R.drawable.avatar));
                    nguoiDung.setUpdateNguoidung(text);
                    nguoiDung.setIdtaikhoan(taiKhoan.getId());
                    if (nguoiDungDAO.themThongTinNguoiDung(nguoiDung)) {
                        Toast.makeText(DangKyThongTinActivity.this, "Them thong tin thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    } else {
                        Toast.makeText(DangKyThongTinActivity.this, "Them thong tin that bai", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
    public boolean emailcheck(CharSequence e){
        return !TextUtils.isEmpty(e) && Patterns.EMAIL_ADDRESS.matcher(e).matches();
    }
    public boolean phonecheck(CharSequence e){
        return !TextUtils.isEmpty(e) && Patterns.PHONE.matcher(e).matches();
    }
    
    public boolean check(String ten, String phone, String mail, String address, String ngay){
        namelayout.setError("");
        sdtlayout.setError("");
        emaillayout.setError("");
        ngaysinhlayout.setError("");
        diachilayout.setError("");
        if (ten.equals("")){
            namelayout.setError("Không được để trống họ tên");
            return false;
        }if (!phonecheck(phone)){
            sdtlayout.setError("Số điện thoại không đúng định dạng");
            return false;
        }else if(phone.length()<11){
            sdtlayout.setError("Số điện thoại không đúng định dạng");
            return false;
        }
        if (mail.equals("")){
            emaillayout.setError("Không được để trống email ");

            return false;
        } if (!emailcheck(email.getEditableText().toString())) {
            emaillayout.setError("Không đúng định dạng email");
            return false;
        } if (address.equals("")){
            diachilayout.setError("Không để trống địa chỉ");
            return false;
        }  if (ngay.equals("")){
            ngaysinhlayout.setError("Không để trống ngày sinh");
            return false;
        }
        
        return true;
    }
}