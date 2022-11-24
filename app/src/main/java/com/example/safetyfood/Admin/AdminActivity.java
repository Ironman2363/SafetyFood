package com.example.safetyfood.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.safetyfood.R;
import com.example.safetyfood.databinding.ActivityMainBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mainBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home1:
                    replaceFragment(new HomeAdminFragment());
                    break;
                case R.id.cart1:
                    replaceFragment(new OrderAdminFragment());
                    break;
                case R.id.order1:
                    replaceFragment(new ManagerAccountFragment());
                    break;
                case R.id.Statistical1:
                    replaceFragment(new ThongKeFragment());
                    break;
                case R.id.setting1:
                    replaceFragment(new SeingAdminFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout_admin, fragment);
        fragmentTransaction.commit();
    }
}