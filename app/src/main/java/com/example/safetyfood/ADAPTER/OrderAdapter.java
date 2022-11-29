package com.example.safetyfood.ADAPTER;

import static com.example.safetyfood.MainActivity.account_all;
import static com.example.safetyfood.MainActivity.check_login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.Activities.OrderDetail;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.ChiTietDatHang;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderAdapterHolder> {

    List<DatHang> list;
    DatHangDAO datHangDAO;
    ChiTietDatHangDAO chiTietDatHangDAO;
    SanPhamDAO sanPhamDAO;
    Context context;


    public OrderAdapter(List<DatHang> list, Context context) {
        this.list = list;
        datHangDAO = new DatHangDAO(context);
        chiTietDatHangDAO = new ChiTietDatHangDAO(context);
        sanPhamDAO = new SanPhamDAO(context);
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterHolder holder, int position) {
        DatHang datHang = list.get(position);

        ChiTietDatHang chiTietDatHang = chiTietDatHangDAO.getListCT(datHang.getId()).get(0);
        SanPham sanPham = sanPhamDAO.getID(chiTietDatHang.getProductid());
        String textStatus = "";
        int statusDH = datHang.getStatusDathang();
        holder.Order_items_nameSP.setText(sanPham.getNameSanpham());
        holder.Order_items_Img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        holder.Order_items_Amount.setText("x " + chiTietDatHang.getAmount());
        holder.Order_items_Price.setText("Giá : " + sanPham.getPriceSanpham());
//        holder.Order_items_totalAmount.setText(chiTietDatHangDAO.getListCT(datHang.getId( )).size()+" sản phẩm");
        holder.Order_items_totalAmount.setText(chiTietDatHangDAO.getSum(datHang.getId()) + " sản phẩm");
        holder.Order_items_totalPrice.setText("Thành tiền : " + datHang.getTotalpriceDathang());
        holder.btnXacnhan.setVisibility(View.VISIBLE);
        if (account_all.getRole() != 1) {
            holder.btnXacnhan.setVisibility(View.GONE);
        }

        if (chiTietDatHangDAO.getListCT(datHang.getId()).size() > 1) {
            holder.Order_items_xemThem.setVisibility(View.VISIBLE);
        }
        switch (statusDH) {
            case 1: {
                textStatus = "Đang chờ xử lý";
                holder.Order_items_MuaLai.setText("Hủy");
                break;
            }
            case 2: {
                textStatus = "Đang giao hàng";
                if (textStatus.equals("Đang giao hàng")) {
                    holder.btnXacnhan.setVisibility(View.GONE);
                    holder.Order_items_MuaLai.setVisibility(View.GONE);
                }
                break;
            }
            case 3: {
                textStatus = "Bị hủy từ phía bạn";
                break;
            }
            case 4: {
                textStatus = "Bị hủy từ phía cửa hàng";
                break;
            }
            case 5: {
                textStatus = "Giao hàng thành công";
                break;
            }
        }
        holder.Order_items_status.setText(textStatus);
        holder.Order_items_MuaLai.setOnClickListener(v -> {
            if (statusDH == 3) {
                datHang.setStatusDathang(3);
                datHangDAO.UpgradeDH(datHang);
                holder.Order_items_View.setVisibility(View.GONE);
            }
        });
        holder.btnXacnhan.setOnClickListener(v -> {
            if (statusDH == 1) {
                datHang.setStatusDathang(2);
                datHangDAO.UpgradeDH(datHang);
                holder.Order_items_View.setVisibility(View.GONE);
            }
        });
        holder.Order_items_View.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("datHang", datHang);
            intent.putExtra("bundle", bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderAdapterHolder extends RecyclerView.ViewHolder {

        ImageView Order_items_Img;
        TextView Order_items_nameSP, Order_items_Amount, Order_items_Price, Order_items_totalAmount, Order_items_totalPrice, Order_items_status,
                Order_items_xemThem;
        Button Order_items_MuaLai, btnXacnhan;
        ConstraintLayout Order_items_View;

        public OrderAdapterHolder(@NonNull View itemView) {
            super(itemView);
            Order_items_Img = itemView.findViewById(R.id.Order_items_Img);
            Order_items_nameSP = itemView.findViewById(R.id.Order_items_nameSP);
            Order_items_Amount = itemView.findViewById(R.id.Order_items_Amount);
            Order_items_Price = itemView.findViewById(R.id.Order_items_Price);
            Order_items_totalAmount = itemView.findViewById(R.id.Order_items_totalAmount);
            Order_items_totalPrice = itemView.findViewById(R.id.Order_items_totalPrice);
            Order_items_status = itemView.findViewById(R.id.Order_items_status);
            Order_items_MuaLai = itemView.findViewById(R.id.Order_items_MuaLai);
            Order_items_View = itemView.findViewById(R.id.Order_items_View);
            Order_items_xemThem = itemView.findViewById(R.id.Order_items_xemThem);
            btnXacnhan = itemView.findViewById(R.id.btnxacnhan);
        }
    }
}
