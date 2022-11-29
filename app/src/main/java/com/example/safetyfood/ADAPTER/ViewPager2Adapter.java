package com.example.safetyfood.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.safetyfood.Tab_Fragment.ChoXacNhanFragment;
import com.example.safetyfood.Tab_Fragment.DaNhanFragment;
import com.example.safetyfood.Tab_Fragment.GiaoHangFragment;
import com.example.safetyfood.Tab_Fragment.HuyFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new ChoXacNhanFragment();
            }
            case 1: {
                return new GiaoHangFragment();
            }
            case 2: {
                return new DaNhanFragment();
            }
            case 3: {
                return new HuyFragment();
            }
            default:
                return new ChoXacNhanFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
