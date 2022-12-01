package com.example.safetyfood.ADAPTER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.Activities.AllLoaiSP;
import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.R;

import java.util.List;

public class ThemLoaiSanPhamAdapter extends RecyclerView.Adapter<ThemLoaiSanPhamAdapter.ThemLoaiSanPhamHolder> {
    Context context;
    List<LoaiSanPham>list;
    LoaiSanPhamDAO sanPhamDAO;

    public ThemLoaiSanPhamAdapter(Context context, List<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
        sanPhamDAO = new LoaiSanPhamDAO(context);
    }

    @NonNull
    @Override
    public ThemLoaiSanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loai_san_pham,null);

        return new ThemLoaiSanPhamHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ThemLoaiSanPhamHolder holder, int position) {
      LoaiSanPham sanPham = list.get(position);
      try{
          holder.anh_loai.setImageResource(Integer.parseInt(sanPham.getImgLoaisanpham()));
      }catch (Exception e){
          Uri uri = Uri.parse(sanPham.getImgLoaisanpham());
          holder.anh_loai.setImageURI(uri);
      }
      holder.ten_sp.setText(sanPham.getNameLoaisanpham());
      holder.ngay_tao.setText(sanPham.getCreateLoaisanpham());
      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) { 
              AlertDialog.Builder dialog = new AlertDialog.Builder(context);
              dialog.setTitle("Xác nhận");
              dialog.setMessage("Bạn có chắc chắn muốn xóa không ?");
              dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      if (sanPhamDAO.deleteLoaiSanPham(sanPham.getId()+"")) {
                          dialog.dismiss();
                          list.clear();
                          list.addAll(sanPhamDAO.getDSLoaiSanPham());
                          notifyDataSetChanged();
                          Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                      } else {
                          Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                      }
                  }
              });
              dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.cancel();
                  }
              });
              AlertDialog b = dialog.create();
              b.show();
              return true;
          }
      });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThemLoaiSanPhamHolder extends RecyclerView.ViewHolder {
        ImageView anh_loai;
        TextView ten_sp , ngay_tao ;
        public ThemLoaiSanPhamHolder(@NonNull View itemView) {
            super(itemView);
            anh_loai = itemView.findViewById(R.id.txt_anh_loai_sp);
            ten_sp = itemView.findViewById(R.id.txt_ten_loai);
            ngay_tao = itemView.findViewById(R.id.txt_ngay_tao);

        }
    }
}
