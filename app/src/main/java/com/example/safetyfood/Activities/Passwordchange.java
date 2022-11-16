package com.example.safetyfood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safetyfood.R;

public class Passwordchange extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        EditText edtOldPass = findViewById(R.id.edtOldPass);
        EditText edtNewPass = findViewById(R.id.edtNewPass);
        EditText edtReNewPass = findViewById(R.id.edtReNewPass);

        String oldPass = edtOldPass.getText().toString();
        String newPass = edtNewPass.getText().toString();
        String renewPass = edtReNewPass.getText().toString();
        if (oldPass.equals("") || newPass.equals("") || renewPass.equals("")) {
            Toast.makeText(Passwordchange.this, "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}