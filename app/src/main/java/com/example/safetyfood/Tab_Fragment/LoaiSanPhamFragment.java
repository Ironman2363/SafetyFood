package com.example.safetyfood.Tab_Fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.safetyfood.ADAPTER.ThemLoaiSanPhamAdapter;
import com.example.safetyfood.DAO.LoaiSanPhamDAO;
import com.example.safetyfood.MODEL.LoaiSanPham;
import com.example.safetyfood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LoaiSanPhamFragment extends Fragment {
    LoaiSanPhamDAO sanPhamDAO;
    FloatingActionButton add_loai;
    RecyclerView list_sp;
    ThemLoaiSanPhamAdapter phamAdapter;
    ImageView anh_loai;
    String link;
    int SELECT_PICTURE = 200;
    ActivityResultLauncher<Intent> activityResultLauncher;
    List<LoaiSanPham> list = new ArrayList<>();
    Uri selectImageUri;
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        add_loai = view.findViewById(R.id.add_loai_sp);
        list_sp = view.findViewById(R.id.list_loai_sp);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        list_sp.setLayoutManager(manager);
        sanPhamDAO = new LoaiSanPhamDAO(getContext());
        list = sanPhamDAO.getDSLoaiSanPham();
        phamAdapter = new ThemLoaiSanPhamAdapter(getContext(), list);
        list_sp.setAdapter(phamAdapter);

        add_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themLoaiSanPham();
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData()!=null){
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    if (bitmap != null){
                        anh_loai.setImageBitmap(bitmap);
                        anh_loai.setVisibility(View.VISIBLE);
                    }
                    link = String.valueOf(BitMapToString(bitmap));
                }
            }
        });
        return view;
    }

    private void imageChoose() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        LauncherSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> LauncherSomeActivity =
            registerForActivityResult(new ActivityResultContracts
                            .StartActivityForResult(), result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.getData() != null) {
                                String path = null;
                                Uri selectImageUri = data.getData();
                                Bitmap SeclectImageBitmap = null;
                                try {
                                SeclectImageBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectImageUri);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                               if (SeclectImageBitmap != null){
                                   anh_loai.setImageBitmap(SeclectImageBitmap);
                                   anh_loai.setVisibility(View.VISIBLE);
                               }
                             link = String.valueOf(getImageUri(getContext(),SeclectImageBitmap));
//
                            }
                        }
                    }
            );

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void themLoaiSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loai_sp, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        ImageView add_anh = view.findViewById(R.id.add_anh);
        anh_loai = view.findViewById(R.id.anh_loai_sp);
        EditText edit_ten = view.findViewById(R.id.edit_ten_loai_sp);
        Button them = view.findViewById(R.id.btn_loai_sp);

        add_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"Chụp ảnh" , "Chọn ảnh từ thư viện"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setIcon(R.drawable.img_image);
                builder1.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("Chụp ảnh")){
                           openImgea();
                        }else if (items[which].equals("Chọn ảnh từ thư viện")){
                            imageChoose();
                        }
                    }
                });
                builder1.show();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSanPham sanPham = new LoaiSanPham();
                String ten = edit_ten.getText().toString();
                String anh = anh_loai.getResources().toString();
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
                String text = simpleDateFormat.format(calendar.getTime());
                sanPham.setNameLoaisanpham(ten);
                sanPham.setCreateLoaisanpham(text);
                sanPham.setImgLoaisanpham(link);

                sanPham.setUpdatedLoaisanpham(text);
                sanPham.setIdCuahang(0);

                if (sanPhamDAO.themLoaiSanPham(sanPham)) {
                    Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(sanPhamDAO.getDSLoaiSanPham());
                    phamAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectImageUri = data.getData();
                if (null != selectImageUri) {
                    anh_loai.setImageURI(selectImageUri);
                }
            }
        }
    }

    private void openImgea() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            activityResultLauncher.launch(intent);
        } else {
            Toast.makeText(getContext(), "app ko ho tro action", Toast.LENGTH_SHORT).show();
        }
    }
}