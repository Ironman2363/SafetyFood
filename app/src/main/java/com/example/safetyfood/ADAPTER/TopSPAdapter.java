package com.example.safetyfood.ADAPTER;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.MODEL.Top;
import com.example.safetyfood.R;

import java.util.List;

public class TopSPAdapter extends RecyclerView.Adapter<TopSPAdapter.TopSPAdapterHolder>{

    List<Top> list;
    Context context;
    SanPhamDAO sanPhamDAO;

    public TopSPAdapter(List<Top> list, Context context) {
        this.list = list;
        this.context = context;
        sanPhamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public TopSPAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext( )).inflate(R.layout.topsp_items,parent,false);
        return new TopSPAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSPAdapterHolder holder, int position) {
        Top top = list.get(position);
        SanPham sanPham = sanPhamDAO.getID(top.getProductId());
        switch (position){
            case 0 :{
                holder.topsp_items_trophy.setImageResource(R.drawable.gold_trophy);
                break;
            }
            case 1 :{
                holder.topsp_items_trophy.setImageResource(R.drawable.silver_trophy);
                break;
            }
            case 2 :{
                holder.topsp_items_trophy.setImageResource(R.drawable.bronze_trophy);
                break;
            }
            default:{
                holder.topsp_items_trophy.setVisibility(View.GONE);
                holder.topsp_items_top.setVisibility(View.VISIBLE);
                holder.topsp_items_top.setText(String.valueOf(position));
                break;
            }
        }
        holder.topsp_items_name.setText("Sản phẩm :"+sanPham.getNameSanpham());
        holder.topsp_items_count.setText("Số lượng :"+top.getSum());
        try {
            holder.topsp_items_img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        } catch (Exception e) {
            Uri uri = Uri.parse(sanPham.getImgSanpham());
            holder.topsp_items_img.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TopSPAdapterHolder extends RecyclerView.ViewHolder {

        ImageView topsp_items_trophy,topsp_items_img;
        TextView topsp_items_name,topsp_items_count,topsp_items_top;

        public TopSPAdapterHolder(@NonNull View itemView) {
            super(itemView);
            topsp_items_trophy = itemView.findViewById(R.id.topsp_items_trophy);
            topsp_items_img = itemView.findViewById(R.id.topsp_items_img);
            topsp_items_name = itemView.findViewById(R.id.topsp_items_name);
            topsp_items_count = itemView.findViewById(R.id.topsp_items_count);
            topsp_items_top = itemView.findViewById(R.id.topsp_items_top);
        }
    }
}
