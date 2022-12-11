package com.example.safetyfood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.safetyfood.ADAPTER.SanPhamAdapter;
import com.example.safetyfood.DAO.SanPhamDAO;
import com.example.safetyfood.MODEL.SanPham;
import com.example.safetyfood.R;

import java.util.List;

public class SearchItems extends AppCompatActivity {

    RecyclerView Search_List;
    SanPhamDAO sanPhamDAO;
    List<SanPham> list;
    Toolbar Search_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_items);

        Search_List = findViewById(R.id.Search_List);
        Search_toolbar = findViewById(R.id.Search_toolbar);
        sanPhamDAO = new SanPhamDAO(this);

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
        }
        return super.onOptionsItemSelected(item);
    }
}