package com.example.safetyfood.FRAGMENT;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.safetyfood.Activities.Login;
import com.example.safetyfood.Activities.Passwordchange;
import com.example.safetyfood.Activities.ThongTinCuaHang;
import com.example.safetyfood.DAO.ThongTinNguoiDungDAO;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.MODEL.ThongTinNguoiDung;
import com.example.safetyfood.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {
    ThongTinNguoiDung thongTinNguoiDung;
    TextView doiten;
    ImageView Anhnen,Anh_loai;
    CardView btndangxuat, ThayDoiMk, txtThongtinchitiet, txtthongtinshop,btnDangNhap;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ThongTinNguoiDungDAO dao;
    List<ThongTinNguoiDung> list;
    String link;
    public static TaiKhoan account_all = new TaiKhoan( );
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btndangxuat = view.findViewById(R.id.btnDangXuat);
        txtthongtinshop = view.findViewById(R.id.thongtinshop);
        txtThongtinchitiet = view.findViewById(R.id.txtThongtinchitiet);
        ThayDoiMk = view.findViewById(R.id.ThayDoiMk);
        doiten = view.findViewById(R.id.doiten);
        Anhnen = view.findViewById(R.id.Anhnen);
        dao = new ThongTinNguoiDungDAO(getContext( ));
        thongTinNguoiDung = new ThongTinNguoiDung( );
        list = new ArrayList<>( );
        SharedPreferences sharedPreferences = getContext( ).getSharedPreferences("OKLuon", Context.MODE_PRIVATE);
        String ten = sharedPreferences.getString("FullName", "Chưa Đăng Nhập");

        doiten.setText(ten);
        if (ten == "Chưa Đăng Nhập"){
            btndangxuat.setVisibility(View.GONE);
            btnDangNhap.setVisibility(View.VISIBLE);
        }else {
            btndangxuat.setVisibility(View.VISIBLE);
            btnDangNhap.setVisibility(View.GONE);
        }

//Code Anh
        Anhnen.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                if (ten == "Chưa Đăng Nhập"){
                    Toast.makeText(getContext(), "Cần đăng nhập để sử dụng", Toast.LENGTH_SHORT).show();
                }else {
                    ThemAnh( );
                }
            }
        });
//Thong Tin Chi Tiet
        txtThongtinchitiet.setOnClickListener(v -> {
            replaceFragment(new ThongTinFragment( ));
        });
//Doi Mat Khau
        ThayDoiMk.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext( ), Passwordchange.class);
                startActivity(intent);
                getActivity( ).finish( );
            }
        });
//Thong Tin Cua Hang
        txtthongtinshop.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext( ), ThongTinCuaHang.class);
                startActivity(intent);
            }
        });
//Dang Xuat/DangNhap
        btndangxuat.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext( ), Login.class);
                startActivity(intent);

                getActivity( ).finish( );

            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext( ), Login.class);
                startActivity(intent);

                getActivity( ).finish( );

            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult( ), new ActivityResultCallback<ActivityResult>( ) {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode( ) == RESULT_OK && result.getData( ) != null) {
                    Bundle bundle = result.getData( ).getExtras( );
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    if (bitmap != null) {
                        Anh_loai.setImageBitmap(bitmap);
                        Anh_loai.setVisibility(View.VISIBLE);
                    }
                    link = String.valueOf(getImageUri(getContext( ), bitmap));
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume( );

    }

    //Chuyen Man
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity( ).getSupportFragmentManager( );
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction( )
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit( );
    }

    //    //Code Them Anh
    private void ThemAnh() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext( ));
        View view = LayoutInflater.from(getContext( )).inflate(R.layout.dialog_themanh, null);
        builder.setView(view);
        Dialog dialog = builder.create( );
        dialog.show( );
        ImageView add_anh = view.findViewById(R.id.add_anh);
        Anh_loai = view.findViewById(R.id.anh_loai_sp);
        Button them = view.findViewById(R.id.btn_loai_sp);

        add_anh.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                openIm( );
            }
        });
        them.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                ThongTinNguoiDung thongTinNguoiDung = new ThongTinNguoiDung( );
                thongTinNguoiDung.setAvatarNguoidung(link);
                if (dao.capNhatAnh(thongTinNguoiDung)) {
                    try {
                        Anhnen.setImageResource(Integer.parseInt(thongTinNguoiDung.getAvatarNguoidung( )));
                    } catch (Exception e) {
                        Uri uri = Uri.parse(thongTinNguoiDung.getAvatarNguoidung( ));
                        Anhnen.setImageURI(uri);
                    }
                    Toast.makeText(getContext( ), "Cập nhập thành công", Toast.LENGTH_SHORT).show( );
                    dialog.dismiss( );
                    list.clear( );

                } else {
                    Toast.makeText(getContext( ), "Sửa Thất Bại", Toast.LENGTH_SHORT).show( );
                }

            }
        });
    }

    private void openIm() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity( ).getPackageManager( )) != null) {
            activityResultLauncher.launch(intent);

        } else {
            Toast.makeText(getActivity( ), "Lỗi", Toast.LENGTH_SHORT).show( );
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream( );
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver( ), inImage, "Title", null);
        return Uri.parse(path);
    }

}