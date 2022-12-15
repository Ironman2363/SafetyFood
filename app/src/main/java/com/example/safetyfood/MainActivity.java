package com.example.safetyfood;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.ThongTinNguoiDungDAO;
import com.example.safetyfood.FRAGMENT.CartFragment;
import com.example.safetyfood.FRAGMENT.HomeFragment;
import com.example.safetyfood.FRAGMENT.OrderFragment;
import com.example.safetyfood.FRAGMENT.SettingFragment;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.Service.CheckCartService;
import com.example.safetyfood.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    public static boolean check_login = false;
    public static TaiKhoan account_all = new TaiKhoan( );
    public static DatHang cart_all = new DatHang( );
    DatHangDAO datHangDAO;
    ThongTinNguoiDungDAO thongTinNguoiDungDAO;


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater( ));
        setContentView(binding.getRoot( ));
        replaceFragment(new HomeFragment( ));
        datHangDAO = new DatHangDAO(this);
        thongTinNguoiDungDAO = new ThongTinNguoiDungDAO(this);
        IntentFilter intentFilter = new IntentFilter("checkCart");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);

        Intent intent = getIntent( );
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            account_all = (TaiKhoan) bundle.getSerializable("tk");
            check_login = true;
            checkLastCart( );
        }

        Log.e("ZZZZZ", "onClick: "+account_all+"."+check_login );

        binding.bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId( )) {

                case R.id.home:
                    replaceFragment(new HomeFragment( ));
                    break;
                case R.id.cart:
                    replaceFragment(new CartFragment( ));
                    break;
                case R.id.order:
                    replaceFragment(new OrderFragment( ));
                    break;
                case R.id.setting:
                    replaceFragment(new SettingFragment( ));
                    break;
            }

            return true;
        });
    }

    private void checkLastCart() {
        startService(new Intent( this,CheckCartService.class ));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager( );
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction( )
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit( );
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver( ) {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                DatHang datHang = (DatHang) bundle.getSerializable("cart");
                cart_all = datHang;
            }
        }
    };
}