package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.account_all;
import static com.example.safetyfood.MainActivity.cart_all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.safetyfood.ADAPTER.SanPhamAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.DatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.util.ArrayList;
import java.util.List;

public class ShowAll extends AppCompatActivity {

    Toolbar SA_toolbar;
    RecyclerView SA_List;
    Spinner SA_Spinner;
    SanPhamDAO sanPhamDAO;
    ChiTietDatHangDAO dao;
    List<SanPham> list;
    List<String> listspn;
    TextView sizeCart;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        SA_List = findViewById(R.id.SA_List);
        SA_Spinner = findViewById(R.id.SA_Spinner);
        SA_toolbar = findViewById(R.id.SA_toolbar);
        sanPhamDAO = new SanPhamDAO(this);
        dao = new ChiTietDatHangDAO(this);

        getDataSPN();
        setSupportActionBar(SA_toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SA_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        getData();
                        index = 0;
                        break;
                    }
                    case 1:{
                        getDataMoney(0);
                        index = 1;
                        break;
                    }
                    case 2:{
                        getDataMoney(1);
                        index = 2;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void getDataSPN() {
        listspn = new ArrayList<>(  );
        listspn.add("Toàn bộ");
        listspn.add("Giá tăng dần");
        listspn.add("Giá giảm dần");
        setSPN();
    }

    private void setSPN() {
        ArrayAdapter<String> adapterspn = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,listspn);
        SA_Spinner.setAdapter(adapterspn);
    }

    private void getDataMoney(int type) {
        listspn = new ArrayList<>(  );
        list = sanPhamDAO.getListMoney(type);
        setData();
    }

    private void getData() {
        list = new ArrayList<>(  );
        list = sanPhamDAO.getDSSanPham();
        setData();
    }

    private void setData() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        SA_List.setLayoutManager(linearLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(list,this,0);
        SA_List.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.badge_cart:{
                startActivity(new Intent( this,BadgeCart.class ));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.badge_cart_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.badge_cart);
        if (account_all.getRole()!=3){
            menuItem.setVisible(false);
        }
        View actionMenu = menuItem.getActionView();
        sizeCart = actionMenu.findViewById(R.id.number_size_cart);

        setCartSize();

        actionMenu.setOnClickListener(v -> {
            onOptionsItemSelected(menuItem);
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void setCartSize(){
        sizeCart.setText(String.valueOf(dao.getSum(cart_all.getId( ))));
    }


    @Override
    public void onResume() {
        super.onResume( );
        if (sizeCart!=null){
            setCartSize();
        }
    }
}