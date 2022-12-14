package com.example.safetyfood.Activities;

import static com.example.safetyfood.MainActivity.account_all;
import static com.example.safetyfood.MainActivity.cart_all;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyfood.ADAPTER.SanPhamAdapter;
import com.example.safetyfood.DAO.ChiTietDatHangDAO;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.util.List;

public class SearchItems extends AppCompatActivity {

    RecyclerView Search_List;
    SanPhamDAO sanPhamDAO;
    List<SanPham> list;
    Toolbar Search_toolbar;
    TextView sizeCart;
    ChiTietDatHangDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_items);

        Search_List = findViewById(R.id.Search_List);
        Search_toolbar = findViewById(R.id.Search_toolbar);
        
        sanPhamDAO = new SanPhamDAO(this);
        dao = new ChiTietDatHangDAO(this);

        setSupportActionBar(Search_toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent( );
        String search = intent.getStringExtra("search");

        getData(search);

    }

    private void getData(String search) {
        list = sanPhamDAO.getListName(search);
        setData();
    }

    private void setData() {
        SanPhamAdapter adapter = new SanPhamAdapter(list,this,0);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        Search_List.setLayoutManager(gridLayoutManager);
        Search_List.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.badge_cart:{
                startActivity(new Intent( SearchItems.this,BadgeCart.class ));
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
    protected void onResume() {
        super.onResume( );
        if (sizeCart!=null){
            setCartSize();
        }
    }
}