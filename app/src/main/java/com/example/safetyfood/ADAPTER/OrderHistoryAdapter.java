package com.example.safetyfood.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.Activities.OrderDetail;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.ThongTinNguoiDungDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryAdapterHolder>{

    List<DatHang> list;
    Context context;
    ChiTietDatHangDAO chiTietDatHangDAO;
    ThongTinNguoiDungDAO thongTinNguoiDungDAO;

    public OrderHistoryAdapter(List<DatHang> list, Context context) {
        this.list = list;
        this.context = context;
        chiTietDatHangDAO = new ChiTietDatHangDAO(context);
        thongTinNguoiDungDAO = new ThongTinNguoiDungDAO(context);
    }

    @NonNull
    @Override
    public OrderHistoryAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderHistoryAdapterHolder(LayoutInflater.from(parent.getContext( )).inflate(R.layout.order_history_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapterHolder holder, int position) {
        DatHang datHang = list.get(position);
        Log.e("ZZZZ", "onBindViewHolder: "+datHang.getIdtaikhoan() );
        ThongTinNguoiDung info = thongTinNguoiDungDAO.getInfo(datHang.getIdtaikhoan());
        Log.e("Adapter", "onBindViewHolder: "+datHang );
        String status = "";
        holder.Order_History_items_ID.setText("M?? ????n :"+datHang.getId());
        holder.Order_History_items_Buyer.setText("Kh??ch h??ng : "+info.getFullname());
        holder.Order_History_items_Address.setText("?????a ch??? : "+info.getAddresNguoidung());
        holder.Order_History_items_Amount.setText(chiTietDatHangDAO.getSum(datHang.getId( ))+" s???n ph???m");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.Order_History_items_TotalPrice.setText(decimalFormat.format(datHang.getTotalpriceDathang()) + " ??");
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("datHang", datHang);
            intent.putExtra("bundle", bundle);
            context.startActivity(intent);
        });
        switch (datHang.getStatusDathang()){
            case 1:{
                status = "??ang ch??? x??? l??";
                holder.Order_History_items_Status.setTextColor(Color.GREEN);
                break;
            }
            case 2:{
                status = "??ang giao";
                holder.Order_History_items_Status.setTextColor(Color.GREEN);
                break;
            }
            case 3:{
            }
            case 4:{
                status = "???? h???y";
                holder.Order_History_items_Status.setTextColor(Color.RED);
                break;
            }
            case 5:{
                status = "???? nh???n";
                holder.Order_History_items_Status.setTextColor(Color.GREEN);
                break;
            }
        }
        holder.Order_History_items_Status.setText("Tr???ng th??i :"+status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderHistoryAdapterHolder extends RecyclerView.ViewHolder {

        TextView Order_History_items_ID,Order_History_items_Buyer,Order_History_items_Address,Order_History_items_Amount,
                Order_History_items_TotalPrice,Order_History_items_Status;

        public OrderHistoryAdapterHolder(@NonNull View itemView) {
            super(itemView);
            Order_History_items_ID = itemView.findViewById(R.id.Order_History_items_ID);
            Order_History_items_Buyer = itemView.findViewById(R.id.Order_History_items_Buyer);
            Order_History_items_Address = itemView.findViewById(R.id.Order_History_items_Address);
            Order_History_items_Amount = itemView.findViewById(R.id.Order_History_items_Amount);
            Order_History_items_TotalPrice = itemView.findViewById(R.id.Order_History_items_TotalPrice);
            Order_History_items_Status = itemView.findViewById(R.id.Order_History_items_Status);
        }
    }
}
