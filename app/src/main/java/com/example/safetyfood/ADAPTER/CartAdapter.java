package com.example.safetyfood.ADAPTER;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.safetyfood.MainActivity.account_all;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
    ChiTietDatHangDAO chiTietDatHangDAO;
    int type;

    public CartAdapter(List<ChiTietDatHang> list, Context context, ChiTietDatHangDAO chiTietDatHangDAO,int type) {
        this.list = list;
        this.context = context;
        sanPhamDAO = new SanPhamDAO(context);
        this.chiTietDatHangDAO = chiTietDatHangDAO;
        this.type = type;
    }

    @NonNull
    @Override
    public CartAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterHolder holder, int position) {
        ChiTietDatHang chiTietDatHang = list.get(position);
        Intent CheckMN = new Intent(  );
        CheckMN.setAction("checkCart");
        CheckMN.putExtra("type",1);
        SanPham sanPham = sanPhamDAO.getID(chiTietDatHang.getProductid());
        try {
            holder.Cart_items_img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        } catch (Exception e) {
            Uri uri = Uri.parse(sanPham.getImgSanpham());
            holder.Cart_items_img.setImageURI(uri);
        }

        if(type==0){
            holder.Cart_items_amount_edt.setVisibility(View.GONE);
            holder.Cart_items_plus.setVisibility(View.GONE);
            holder.Cart_items_remove.setVisibility(View.GONE);
            holder.Cart_items_amount_txt.setVisibility(View.VISIBLE);
        }

        holder.Cart_items_name.setText(sanPham.getNameSanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.Cart_items_amount_edt.setText(decimalFormat.format(chiTietDatHang.getAmount()));
        holder.Cart_items_amount_txt.setText("Số sản phẩm :"+decimalFormat.format(chiTietDatHang.getAmount()));
        holder.Cart_items_amount_edt.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()<=0){
                    return;
                }
                String amount = s.toString();
                try {
                    if (Integer.parseInt(amount)<=0){
                        list.remove(holder.getAdapterPosition());
                        chiTietDatHangDAO.XoaChiTietDatHang(chiTietDatHang);
                        notifyItemRemoved(holder.getAdapterPosition());
                    }else {
                        chiTietDatHang.setAmount(Float.parseFloat(amount));
                        chiTietDatHangDAO.CapNhapChiTietDatHang(chiTietDatHang);
                    }
                    LocalBroadcastManager.getInstance(context).sendBroadcast(CheckMN);
                }catch (Exception e){

                }
            }
        });

        holder.Cart_items_plus.setOnClickListener(v -> {
            chiTietDatHang.setAmount(chiTietDatHang.getAmount()+1);
            chiTietDatHangDAO.CapNhapChiTietDatHang(chiTietDatHang);
            holder.Cart_items_amount_edt.setText(decimalFormat.format(chiTietDatHang.getAmount()));
            LocalBroadcastManager.getInstance(context).sendBroadcast(CheckMN);
        });

        holder.Cart_items_remove.setOnClickListener(v -> {
            chiTietDatHang.setAmount(chiTietDatHang.getAmount()-1);
            chiTietDatHangDAO.CapNhapChiTietDatHang(chiTietDatHang);
            if(chiTietDatHang.getAmount()<=0){
                list.remove(holder.getAdapterPosition());
                chiTietDatHangDAO.XoaChiTietDatHang(chiTietDatHang);
                notifyItemRemoved(holder.getAdapterPosition());
            }
            holder.Cart_items_amount_edt.setText(decimalFormat.format(chiTietDatHang.getAmount()));
            LocalBroadcastManager.getInstance(context).sendBroadcast(CheckMN);
        });

        holder.Cart_items_price.setText(decimalFormat.format(sanPham.getPriceSanpham()) + " đ");
//        holder.Cart_items_price.setText(String.valueOf(sanPham.getPriceSanpham()));


        holder.Cart_items_View.setOnClickListener(v -> {
            if (account_all.getRole()!=3){
                return;
            }
            if (type==1){
                return;
            }
            Intent intent = new Intent(context, SanPhamDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sp", sanPham);
            intent.putExtra("bundle", bundle);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CartAdapterHolder extends RecyclerView.ViewHolder {
        ImageView Cart_items_img;
        TextView Cart_items_name, Cart_items_price,Cart_items_amount_txt;
        CardView Cart_items_View;
        ImageButton Cart_items_remove,Cart_items_plus;
        EditText Cart_items_amount_edt;

        public CartAdapterHolder(@NonNull View itemView) {
            super(itemView);
            Cart_items_img = itemView.findViewById(R.id.Cart_items_img);
            Cart_items_name = itemView.findViewById(R.id.Cart_items_name);
            Cart_items_amount_edt = itemView.findViewById(R.id.Cart_items_amount_edt);
            Cart_items_amount_txt = itemView.findViewById(R.id.Cart_items_amount_txt);
            Cart_items_price = itemView.findViewById(R.id.Cart_items_price);
            Cart_items_View = itemView.findViewById(R.id.Cart_items_View);
            Cart_items_remove = itemView.findViewById(R.id.Cart_items_remove);
            Cart_items_plus = itemView.findViewById(R.id.Cart_items_plus);
        }
    }
}
