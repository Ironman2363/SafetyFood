package com.example.safetyfood.Tab_Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.safetyfood.ADAPTER.OrderHistoryAdapter;
import com.example.safetyfood.ADAPTER.TopSPAdapter;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.ThongKeDAO;
import com.example.safetyfood.MODEL.DatHang;
import com.example.safetyfood.MODEL.Top;
import com.example.safetyfood.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ThongKeSoSPDaBanFragment extends Fragment {

    List<Top> list;
    RecyclerView TK_List;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_ke_so_s_p_da_ban, container, false);
        Button btnStart = view.findViewById(R.id.btnStart);
        Button btnEnd = view.findViewById(R.id.btnEnd);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TK_List = view.findViewById(R.id.TK_List);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###");
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String text = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR,1);
        String text1 = simpleDateFormat.format(calendar.getTime());
        btnStart.setText(text);
        btnEnd.setText(text1);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10) {
                                    ngay = "0" + dayOfMonth;
                                } else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month < 9) {
                                    thang = "0" + (month + 1);
                                } else {
                                    thang = String.valueOf(month + 1);
                                }

                                btnStart.setText(year + "-" + thang + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10) {
                                    ngay = "0" + dayOfMonth;
                                } else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month < 9) {
                                    thang = "0" + (month + 1);
                                } else {
                                    thang = String.valueOf(month + 1);
                                }

                                btnEnd.setText(year + "-" + thang + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                String tuNgay = btnStart.getText().toString();
                String denNgay = btnEnd.getText().toString();
                list = thongKeDAO.getTopSP(tuNgay, denNgay);
                setData();
            }
        });

        return view;
    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        TK_List.setLayoutManager(linearLayoutManager);
        TopSPAdapter adapter = new TopSPAdapter(list,getContext());
        TK_List.setAdapter(adapter);
    }
}