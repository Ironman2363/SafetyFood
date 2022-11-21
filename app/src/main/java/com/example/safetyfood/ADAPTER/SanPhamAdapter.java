package com.example.safetyfood.ADAPTER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamAdapterHolder>{

    List<SanPham> list;

    public SanPhamAdapter(List<SanPham> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SanPhamAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SanPhamAdapterHolder(LayoutInflater.from(parent.getContext( )).inflate(R.layout.sp_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapterHolder holder, int position) {
        SanPham sp = list.get(position);
        holder.SP_items_Img.setImageResource(Integer.parseInt(sp.getImgSanpham()));
        holder.SP_items_Gia.setText(sp.getPriceSanpham()+" VND");
        holder.SP_items_Ten.setText(sp.getNameSanpham());
    }

    @Override
    public int getItemCount() {
        return list.size( );
    }

    class SanPhamAdapterHolder extends RecyclerView.ViewHolder {
        ImageView SP_items_Img;
        TextView SP_items_Gia,SP_items_Ten;
        public SanPhamAdapterHolder(@NonNull View itemView) {
            super(itemView);
            SP_items_Img = itemView.findViewById(R.id.SP_items_Img);
            SP_items_Gia = itemView.findViewById(R.id.SP_items_Gia);
            SP_items_Ten = itemView.findViewById(R.id.SP_items_Ten);
        }
    }
}
