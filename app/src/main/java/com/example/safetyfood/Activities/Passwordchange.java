package com.example.safetyfood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safetyfood.R;

public class Passwordchange extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);

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
}