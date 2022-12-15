package com.example.safetyfood.Tab_Fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.safetyfood.ADAPTER.LoaiSanPhamSpinnerAdapter;
import com.example.safetyfood.ADAPTER.ThemSanPhamAdapter;
import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SanPhamFragment extends Fragment {
    FloatingActionButton add_sp;
    RecyclerView list_sp;
    SanPham sanPham;
    ThemSanPhamAdapter phamAdapter;
    List<SanPham> list = new ArrayList<>();
    List<LoaiSanPham> list_loai_sp;
    LoaiSanPhamDAO phamDAO;
    SanPhamDAO sanPhamDAO;
    LoaiSanPhamSpinnerAdapter spinnerAdapter;
    ActivityResultLauncher<Intent> activityResultLauncher;
    int position;
    String link;
    ImageView anh;
    int maLoaiSanPham;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        add_sp = view.findViewById(R.id.add_sp);
        list_sp = view.findViewById(R.id.list_sp);
        sanPhamDAO = new SanPhamDAO(getContext());
//        add();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        list_sp.setLayoutManager(manager);
        list = sanPhamDAO.getDSSanPham();
        phamAdapter = new ThemSanPhamAdapter(getContext(), list);
        list_sp.setAdapter(phamAdapter);
        registerForContextMenu(list_sp);
        add_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPham();
            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    if (bitmap != null) {
                        anh.setImageBitmap(bitmap);
                        anh.setVisibility(View.VISIBLE);
                    }
                    link = String.valueOf(getImageUri(getContext(), bitmap));
                }
            }
        });
        return view;
    }

    //    private void add() {
//        list.add(new SanPham("pepsi",String.valueOf(R.drawable.pepsi),10000,1,"20/22/2022"));
//        list.add(new SanPham("coca-cola",String.valueOf(R.drawable.pepsi),10000,2,"20/22/2022"));
//        list.add(new SanPham("pepsi",String.valueOf(R.drawable.pepsi),20000,2,"20/22/2022"));
//        list.add(new SanPham("pepsi",String.valueOf(R.drawable.pepsi),30000,1,"20/22/2022"));
//        list.add(new SanPham("pepsi",String.valueOf(R.drawable.pepsi),400000,3,"20/22/2022"));
//
//    }
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "IMG_" + Calendar.getInstance().getTime(),null);
        return Uri.parse(path);
    }

    private void themSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_san_pham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        LinearLayout them_anh = view.findViewById(R.id.them_anh);
        Spinner loai_sp = view.findViewById(R.id.spinner_loai_sp);
        EditText ten_sp = view.findViewById(R.id.edit_ten_sp);
        EditText gia_sp = view.findViewById(R.id.edit_gia_sp);
        Button them = view.findViewById(R.id.btn_add_sp);
        anh = view.findViewById(R.id.img_loai_sp);

        list_loai_sp = new ArrayList<>();
        phamDAO = new LoaiSanPhamDAO(getContext());

        them_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });
        list_loai_sp = phamDAO.getDSLoaiSanPham();
        spinnerAdapter = new LoaiSanPhamSpinnerAdapter(getContext(), list_loai_sp);
        loai_sp.setAdapter(spinnerAdapter);

        loai_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSanPham = list_loai_sp.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        sanPham = new SanPham();
//        int maSanPham = Integer.parseInt(sanPham.getLoaiSanpham());
//        for (int i = 0;i<list_loai_sp.size();i++){
//            if(maSanPham==(list_loai_sp.get(i).getId())){
//                position = i;
//            }
//        }
//        loai_sp.setSelection(position);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham = new SanPham();
                String name = ten_sp.getText().toString();
                String price = gia_sp.getText().toString();
                String image = anh.getResources().toString();
                sanPham.setLoaiSanpham(String.valueOf(maLoaiSanPham));
                sanPham.setNameSanpham(name);
                sanPham.setPriceSanpham(Integer.parseInt(price));
                sanPham.setImgSanpham(link);
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
                String text = simpleDateFormat.format(calendar.getTime());
                sanPham.setCreateSanpham(text);
                sanPham.setUpdatedSanpham(text);
                if (sanPhamDAO.themSanpham(sanPham)) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(sanPhamDAO.getDSSanPham());
                    phamAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.option_sua_xoa, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(getContext(), "ban da xoa", Toast.LENGTH_SHORT).show();
                return true;
            case 1:
                Toast.makeText(getContext(), "ban da xoa", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void openImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            activityResultLauncher.launch(intent);
        } else {
            Toast.makeText(getContext(), "app ko ho tro action", Toast.LENGTH_SHORT).show();
        }
    }
}