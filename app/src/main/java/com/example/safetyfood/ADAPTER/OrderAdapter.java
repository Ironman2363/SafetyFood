package com.example.safetyfood.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderAdapterHolder>{

    List<DatHang> list;
    DatHangDAO datHangDAO;

    public OrderAdapter(List<DatHang> list, Context context) {
        this.list = list;
        datHangDAO = new DatHangDAO(context);
    }

    @NonNull
    @Override
    public OrderAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderAdapterHolder(LayoutInflater.from(parent.getContext( )).inflate(R.layout.order_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterHolder holder, int position) {
        DatHang datHang = list.get(position);
        String textStatus = "";
        int statusDH = datHang.getStatusDathang();
        holder.test_ID_Order.setText("ID Order : "+datHang.getId());
        holder.test_UserID_Order.setText("ID User : "+datHang.getIdtaikhoan());
        holder.test_TotalPrice_Order.setText("Tổng : "+datHang.getTotalpriceDathang());
        holder.test_Create_Order.setText("Ngày đặt : "+datHang.getCreateDathang());
        switch (statusDH){
            case 1 : {
                textStatus = "Đang chờ xử lý";
                break;
            }
            case 2 :{
                textStatus = "Đang giao hàng";
                break;
            }
            case 3 :{
                textStatus = "Bị hủy từ phía bạn";
                break;
            }
            case 4 :{
                textStatus = "Bị hủy từ phía cửa hàng";
                break;
            }
            case 5 :{
                textStatus = "Giao hàng thành công";
                break;
            }
        }
        holder.test_Status_Order.setText(textStatus);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderAdapterHolder extends RecyclerView.ViewHolder {
        TextView test_ID_Order,test_UserID_Order,test_TotalPrice_Order,test_Status_Order,test_Create_Order;
        public OrderAdapterHolder(@NonNull View itemView) {
            super(itemView);
            test_ID_Order = itemView.findViewById(R.id.test_ID_Order);
            test_UserID_Order = itemView.findViewById(R.id.test_UserID_Order);
            test_TotalPrice_Order = itemView.findViewById(R.id.test_TotalPrice_Order);
            test_Status_Order = itemView.findViewById(R.id.test_Status_Order);
            test_Create_Order = itemView.findViewById(R.id.test_Create_Order);
        }
    }
}
