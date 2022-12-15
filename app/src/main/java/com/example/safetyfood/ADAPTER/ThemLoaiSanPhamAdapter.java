package com.example.safetyfood.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.Activities.AllLoaiSP;
import com.example.safetyfood.Activities.getImg;
import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ThemLoaiSanPhamAdapter extends RecyclerView.Adapter<ThemLoaiSanPhamAdapter.ThemLoaiSanPhamHolder> {
    Context context;
    List<LoaiSanPham>list;
    LoaiSanPhamDAO sanPhamDAO;
    String link;
    ImageView anh_sp;

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
//          Bitmap bit = StringToBitMap(sanPham.getImgLoaisanpham());
//          holder.anh_loai.setImageBitmap(bit);
      }
      holder.ten_sp.setText(sanPham.getNameLoaisanpham());
      holder.ngay_tao.setText(sanPham.getCreateLoaisanpham());
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
                          XoaSanPham(sanPham,position,holder);
                      } else if (item.getItemId() == R.id.sua) {
                          Toast.makeText(context, "SỬA", Toast.LENGTH_SHORT).show();
                          SuaSanPham(sanPham,holder);
                      }
                      return true;
                  }
              });
              popupMenu.show();
              return true;
          }
      });
    }

    private void SuaSanPham(LoaiSanPham sanPham, ThemLoaiSanPhamHolder holder) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_loai_san_pham,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver( ) {
            @Override
            public void onReceive(Context context, Intent intent) {
                link = intent.getStringExtra("link");
                sanPham.setImgLoaisanpham(link);
                Log.e("ZZZZZZ", "onReceive: "+sanPham );
                try {
                    anh_sp.setImageResource(Integer.parseInt(sanPham.getImgLoaisanpham()));
                } catch (Exception e) {
                    Uri uri = Uri.parse(sanPham.getImgLoaisanpham());
                    anh_sp.setImageURI(uri);
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter("sendBack");
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver,intentFilter);
        anh_sp = view.findViewById(R.id.update_image_loai_sp);
        EditText ten_sp = view.findViewById(R.id.update_edit_ten_loai_sp);
        Button sua_sp = view.findViewById(R.id.update_btn_add_loai_sp);
        ten_sp.setText(sanPham.getNameLoaisanpham());
        anh_sp.setOnClickListener(v -> {
            final CharSequence[] items = {"Chụp ảnh", "Chọn ảnh từ thư viện"};
            android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
            builder1.setIcon(R.drawable.img_image);
            builder1.setItems(items, new DialogInterface.OnClickListener( ) {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (items[which].equals("Chụp ảnh")) {
                        Intent intent = new Intent(context, getImg.class);
                        intent.putExtra("type",0);
                        context.startActivity(intent);
                    } else if (items[which].equals("Chọn ảnh từ thư viện")) {
                        Intent intent = new Intent(context,getImg.class);
                        intent.putExtra("type",1);
                        context.startActivity(intent);
                    }
                }
            });
            builder1.show( );
        });

        try {
            anh_sp.setImageResource(Integer.parseInt(sanPham.getImgLoaisanpham()));
        } catch (Exception e) {
            Uri uri = Uri.parse(sanPham.getImgLoaisanpham());
            anh_sp.setImageURI(uri);
        }
        sua_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham.setNameLoaisanpham(ten_sp.getText().toString());
                sanPhamDAO.capnhatLoaiSanPham(sanPham);
                dialog.dismiss();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    private void XoaSanPham(LoaiSanPham sanPham, int position, ThemLoaiSanPhamHolder holder) {
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
