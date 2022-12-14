package com.example.safetyfood.ADAPTER;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;
import com.example.safetyfood.Tab_Fragment.LoaiSanPhamFragment;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ThemSanPhamAdapter extends RecyclerView.Adapter<ThemSanPhamAdapter.ThemSanPhamHolder> {
    Context context;
    List<SanPham> list;
    SanPhamDAO phamDAO;
    int position;
    String link;
    ImageView anh_sp;
    ActivityResultLauncher<Intent> activityResultLauncher;
    LoaiSanPhamSpinnerAdapter loaiSpinnerAdapter;
    List<LoaiSanPham> listLoaiSanPham = new ArrayList<>();
    public ThemSanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
        phamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ThemSanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, null);
//        activityResultLauncher = new ActivityResultContracts.StartActivityForResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == RESULT_OK && result.getData()!=null){
//                    Bundle bundle = result.getData().getExtras();
//                    Bitmap bitmap = (Bitmap) bundle.get("data");
//                    if (bitmap != null){
//                        anh_sp.setImageBitmap(bitmap);
//
//                    }
//                    link = String.valueOf(getImageUri(getContext(),bitmap));
//                }
//            }
//        });
        return new ThemSanPhamHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ThemSanPhamHolder holder, int position) {
        SanPham sanPham = list.get(position);
        if (sanPham != null) {
            try {
                holder.anh.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
            } catch (Exception e) {
                Uri uri = Uri.parse(sanPham.getImgSanpham());
                holder.anh.setImageURI(uri);
            }
            LoaiSanPhamDAO sanPhamDAO = new LoaiSanPhamDAO(context);

            LoaiSanPham loaiSanPham = sanPhamDAO.getID(sanPham.getLoaiSanpham() + "");
            holder.ten_sp.setText(sanPham.getNameSanpham());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            holder.gia_sp.setText(decimalFormat.format(sanPham.getPriceSanpham())+"đ");
            holder.ngay_sp.setText(sanPham.getCreateSanpham());
            holder.loai_sp.setText("Loại sản phẩm: " + loaiSanPham.getNameLoaisanpham());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, holder.itemView);
                    popupMenu.getMenuInflater().inflate(R.menu.option_sua_xoa, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.xoa) {
                                Toast.makeText(context, "XÓA", Toast.LENGTH_SHORT).show();
                                XoaSanPham();
                            } else if (item.getItemId() == R.id.sua) {
                                Toast.makeText(context, "SỬA", Toast.LENGTH_SHORT).show();
                                SuaSanPham();
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });
        }
    }



    private void SuaSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_san_pham,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        SanPham sanPham = list.get(position);
        LoaiSanPhamDAO sanPhamDAO = new LoaiSanPhamDAO(context);
        anh_sp = view.findViewById(R.id.update_image_sp);
        EditText ten_sp = view.findViewById(R.id.update_edit_ten_sp);
        EditText gia_sp = view.findViewById(R.id.update_edit_gia_sp);
        Spinner loai_sp = view.findViewById(R.id.update_pinner_loai_sp);
        Button sua_sp = view.findViewById(R.id.update_btn_add_sp);
        ten_sp.setText(sanPham.getNameSanpham());
        gia_sp.setText(sanPham.getPriceSanpham()+"");
        listLoaiSanPham = sanPhamDAO.getDSLoaiSanPham();
        loaiSpinnerAdapter = new LoaiSanPhamSpinnerAdapter(context,listLoaiSanPham);
        loai_sp.setAdapter(loaiSpinnerAdapter);
        for (int i = 0 ; i<listLoaiSanPham.size();i++){
            if (Integer.parseInt(sanPham.getLoaiSanpham()) == (listLoaiSanPham.get(i).getId())){
                loai_sp.setSelection(i);
            }
        }
        try {
            anh_sp.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        } catch (Exception e) {
            Uri uri = Uri.parse(sanPham.getImgSanpham());
            anh_sp.setImageURI(uri);
        }
        sua_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void XoaSanPham() {
        SanPham sanPham = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (phamDAO.xoaSanPham(sanPham.getId() + "")) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(phamDAO.getDSSanPham());
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThemSanPhamHolder extends RecyclerView.ViewHolder {
        ImageView anh;
        TextView ten_sp, gia_sp, ngay_sp, loai_sp;

        public ThemSanPhamHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.anh_sp);
            ten_sp = itemView.findViewById(R.id.txt_ten_sp);
            gia_sp = itemView.findViewById(R.id.txt_gia_sp);
            ngay_sp = itemView.findViewById(R.id.txt_ngay_sp);
            loai_sp = itemView.findViewById(R.id.txt_loai_sp);
        }
    }
}
