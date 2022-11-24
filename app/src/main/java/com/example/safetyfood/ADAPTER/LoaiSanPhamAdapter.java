package com.example.safetyfood.ADAPTER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.R;

import java.util.List;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.LoaiSanPhamHolder> {

    List<LoaiSanPham> list;

    public LoaiSanPhamAdapter(List<LoaiSanPham> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public LoaiSanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LoaiSanPhamHolder(LayoutInflater.from(parent.getContext( )).inflate(R.layout.loaisp_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSanPhamHolder holder, int position) {
        LoaiSanPham loaiSanPham = list.get(position);
        holder.loaiSP_items_Img.setImageResource(Integer.parseInt(loaiSanPham.getImgLoaisanpham()));
        holder.loaiSP_items_txt.setText(loaiSanPham.getNameLoaisanpham());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LoaiSanPhamHolder extends RecyclerView.ViewHolder {
        ImageView loaiSP_items_Img;
        CardView loaiSP_items_View;
        TextView loaiSP_items_txt;
        public LoaiSanPhamHolder(@NonNull View itemView) {
            super(itemView);
            loaiSP_items_txt = itemView.findViewById(R.id.loaiSP_items_txt);
            loaiSP_items_Img = itemView.findViewById(R.id.loaiSP_items_Img);
            loaiSP_items_View = itemView.findViewById(R.id.loaiSP_items_View);
        }
    }
}
