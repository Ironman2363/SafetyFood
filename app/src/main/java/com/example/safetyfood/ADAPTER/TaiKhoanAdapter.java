package com.example.safetyfood.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.MODEL.Itemclick;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TaiKhoan> list;
    private Itemclick itemclick;

    public TaiKhoanAdapter(Context context, ArrayList<TaiKhoan> list, Itemclick itemclick) {
        this.context = context;
        this.list = list;
        this.itemclick = itemclick;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_taikhoan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        TaiKhoan taiKhoan = list.get(position);
        holder.txt_tknv.setText("Tài khoản : " + taiKhoan.getUsername());
        holder.txt_mknv.setText("Mật khẩu : " + taiKhoan.getPassword());
        holder.btn_deletenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaikhoanDAO taikhoanDAO = new TaikhoanDAO(context);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                        .setMessage("Bạn có muốn xóa tài khoản này không ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int check = taikhoanDAO.deleteTaikhoan(String.valueOf(taiKhoan.getId()));
                                switch (check) {
                                    case 1:

                                        list.clear();
                                        list = taikhoanDAO.getDSNV();
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -1:
                                        Toast.makeText(context, "Không thể xóa tài khoản này", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 0:
                                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.create();
                builder.show();
            }

        });
        holder.btn_editnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaiKhoan taiKhoan = list.get(holder.getAdapterPosition());
                itemclick.onCickLoaiSach(taiKhoan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tknv, txt_mknv;
        ImageView btn_editnv, btn_deletenv;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_tknv = itemView.findViewById(R.id.txt_tknv);
            txt_mknv = itemView.findViewById(R.id.txt_mknv);
            btn_editnv = itemView.findViewById(R.id.btn_editnv);
            btn_deletenv = itemView.findViewById(R.id.btn_deletenv);
        }
    }
}
