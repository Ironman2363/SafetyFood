package com.example.safetyfood.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.Activities.SanPhamDetail;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterHolder> {

    List<ChiTietDatHang> list;
    SanPhamDAO sanPhamDAO;
    Context context;

    public CartAdapter(List<ChiTietDatHang> list, Context context) {
        this.list = list;
        this.context = context;
        sanPhamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public CartAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterHolder holder, int position) {
        ChiTietDatHang chiTietDatHang = list.get(position);
        SanPham sanPham = sanPhamDAO.getID(chiTietDatHang.getProductid());
        try {
            holder.Cart_items_img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        } catch (Exception e) {
            Uri uri = Uri.parse(sanPham.getImgSanpham());
            holder.Cart_items_img.setImageURI(uri);
        }

        holder.Cart_items_name.setText(sanPham.getNameSanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.Cart_items_amount.setText("Số lượng : " + decimalFormat.format(chiTietDatHang.getAmount()));


        holder.Cart_items_price.setText(decimalFormat.format(sanPham.getPriceSanpham()) + " đ");
//        holder.Cart_items_price.setText(String.valueOf(sanPham.getPriceSanpham()));


        holder.Cart_items_View.setOnClickListener(v -> {
            Intent intent = new Intent(context, SanPhamDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sp", sanPham);
            intent.putExtra("bundle", bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CartAdapterHolder extends RecyclerView.ViewHolder {
        ImageView Cart_items_img;
        TextView Cart_items_name, Cart_items_amount, Cart_items_price;
        CardView Cart_items_View;

        public CartAdapterHolder(@NonNull View itemView) {
            super(itemView);
            Cart_items_img = itemView.findViewById(R.id.Cart_items_img);
            Cart_items_name = itemView.findViewById(R.id.Cart_items_name);
            Cart_items_amount = itemView.findViewById(R.id.Cart_items_amount);
            Cart_items_price = itemView.findViewById(R.id.Cart_items_price);
            Cart_items_View = itemView.findViewById(R.id.Cart_items_View);
        }
    }
}
