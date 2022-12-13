package com.example.safetyfood.ADAPTER;

import static com.example.safetyfood.MainActivity.account_all;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.safetyfood.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        holder.Order_items_nameSP.setText(sanPham.getNameSanpham());
        try{
            holder.Order_items_Img.setImageResource(Integer.parseInt(sanPham.getImgSanpham()));
        }catch (Exception e){
            Uri uri = Uri.parse(sanPham.getImgSanpham());
            holder.Order_items_Img.setImageURI(uri);
        }
        holder.Order_items_Amount.setText("x " + chiTietDatHang.getAmount());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.Order_items_Price.setText(decimalFormat.format(sanPham.getPriceSanpham()) + " đ");
        holder.Order_items_totalAmount.setText(chiTietDatHangDAO.getSum(datHang.getId()) + " sản phẩm");
        holder.Order_items_totalPrice.setText("Thành tiền : " + decimalFormat.format(datHang.getTotalpriceDathang())+"đ");


        if (chiTietDatHangDAO.getListCT(datHang.getId()).size() > 1) {
            holder.Order_items_xemThem.setVisibility(View.VISIBLE);
        }
        switch (statusDH) {
            case 1: {
                textStatus = "Đang chờ xử lý";
                holder.Order_items_MuaLai.setText("Hủy");
                holder.btnxacnhandagiahang.setVisibility(View.GONE);
                break;
            }
            case 2: {
                textStatus = "Đang giao hàng";
                if (textStatus.equals("Đang giao hàng")) {
                    holder.Order_items_MuaLai.setVisibility(View.GONE);
                    holder.btnxacnhandagiahang.setVisibility(View.VISIBLE);
                }
                break;
            }
            case 3: {
                textStatus = "Bị hủy từ phía bạn";
                holder.btnxacnhandagiahang.setVisibility(View.GONE);
                break;
            }
            case 4: {
                textStatus = "Bị hủy từ phía cửa hàng";
                holder.btnxacnhandagiahang.setVisibility(View.GONE);
                break;
            }
            case 5: {
                textStatus = "Giao hàng thành công";
                holder.btnxacnhandagiahang.setVisibility(View.GONE);
                holder.Order_items_MuaLai.setVisibility(View.GONE);
                break;
            }
        }
        holder.Order_items_status.setText(textStatus);
        holder.Order_items_MuaLai.setOnClickListener(v -> {
            datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
            if (statusDH == 1 ){
                if (account_all.getRole()==3){
                    datHang.setStatusDathang(3);
                }else {
                    datHang.setStatusDathang(4);
                }
                datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
                datHangDAO.UpgradeDH(datHang);
            }
            else if (statusDH == 3) {
                datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
                datHang.setStatusDathang(1);
                datHangDAO.UpgradeDH(datHang);
            }
            list.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });

        holder.btnxacnhandagiahang.setOnClickListener(v ->{
            datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
            if (statusDH == 2) {
                datHang.setUpdateDathang(simpleDateFormat.format(calendar.getTime()));
                datHang.setStatusDathang(5);
                datHangDAO.UpgradeDH(datHang);
            }
            list.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
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
        Button Order_items_MuaLai,btnxacnhandagiahang;
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
            btnxacnhandagiahang = itemView.findViewById(R.id.btnxacnhandagiahang);
        }
    }
}
