package com.example.safetyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.FRAGMENT.CartFragment;
import com.example.safetyfood.FRAGMENT.HomeFragment;
import com.example.safetyfood.FRAGMENT.OrderFragment;
import com.example.safetyfood.FRAGMENT.SettingFragment;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static boolean check_login = false;
    public static TaiKhoan account_all = new TaiKhoan(  );
    public static DatHang cart_all = new DatHang(  );
    DatHangDAO datHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        datHangDAO = new DatHangDAO(this);

        Intent intent = getIntent();
        if (intent!=null){
            Bundle bundle = intent.getBundleExtra("bundle");
            account_all = (TaiKhoan) bundle.getSerializable("tk");
            check_login = true;
            checkLastCart();
        }

        binding.bottomNavigationView.setItemIconTintList(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.cart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.order:
                    replaceFragment(new OrderFragment());
                    break;
                case R.id.setting:
                    replaceFragment(new SettingFragment());
                    break;

            }


            return true;
        });
    }

    private void checkLastCart() {
        DatHang datHang = datHangDAO.getLastCart(account_all.getId());
        if(datHang==null){
            DatHang datHang1 = new DatHang(  );
            datHang1.setIdtaikhoan(account_all.getId());
            datHang1.setStatusDathang(0);
            datHang1.setCreateDathang("21/11/2022");
            datHang1.setUpdateDathang("21/11/2022");
            datHang1.setTotalpriceDathang(0);
            datHangDAO.InsertDH(datHang1);
        }else if(datHang.getStatusDathang()==1 ||datHang.getStatusDathang()==2){
            DatHang datHang1 = new DatHang(  );
            datHang1.setIdtaikhoan(account_all.getId());
            datHang1.setStatusDathang(0);
            datHang1.setCreateDathang("21/11/2022");
            datHang1.setUpdateDathang("21/11/2022");
            datHang1.setTotalpriceDathang(0);
            datHangDAO.InsertDH(datHang1);
        }else {
            cart_all = datHang;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right,R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}