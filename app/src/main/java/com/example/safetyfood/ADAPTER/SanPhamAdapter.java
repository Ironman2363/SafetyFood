package com.example.safetyfood.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.Activities.Login;
import com.example.safetyfood.Activities.SanPhamDetail;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamAdapterHolder> {

    List<SanPham> list;
    Context context;

    public SanPhamAdapter(List<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SanPhamAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SanPhamAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sp_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapterHolder holder, int position) {
        SanPham sp = list.get(position);
        holder.SP_items_Img.setImageResource(Integer.parseInt(sp.getImgSanpham()));
        holder.SP_items_Gia.setText(sp.getPriceSanpham() + "đ");
        holder.SP_items_Ten.setText(sp.getNameSanpham());
        holder.SP_items_View.setOnClickListener(v -> {
            Intent intent = new Intent(context, SanPhamDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sp", sp);
            intent.putExtra("bundle", bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SanPhamAdapterHolder extends RecyclerView.ViewHolder {
        ImageView SP_items_Img;
        TextView SP_items_Gia, SP_items_Ten;
        LinearLayout SP_items_View;

        public SanPhamAdapterHolder(@NonNull View itemView) {
            super(itemView);
            SP_items_Img = itemView.findViewById(R.id.SP_items_Img);
            SP_items_Gia = itemView.findViewById(R.id.SP_items_Gia);
            SP_items_Ten = itemView.findViewById(R.id.SP_items_Ten);
            SP_items_View = itemView.findViewById(R.id.SP_items_View);
        }
    }
}
