package com.example.safetyfood.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.safetyfood.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class getImg extends AppCompatActivity {

    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_img);

        Intent intent = getIntent();
        int type = intent.getIntExtra("type",-1);
        switch (type){
            case 0 :{
                openImage();
                break;
            }
            case 1 :{
                imageChoose();
                break;
            }
        }

    }

    private void openImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(this.getPackageManager( )) != null) {
            activityResultLauncher.launch(intent);
        } else {
            Toast.makeText(getApplicationContext( ), "app ko ho tro action", Toast.LENGTH_SHORT).show( );
        }
    }

    private void imageChoose() {
        Intent i = new Intent( );
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        LauncherSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> LauncherSomeActivity =
            registerForActivityResult(new ActivityResultContracts
                            .StartActivityForResult( ), result -> {
                        if (result.getResultCode( ) == RESULT_OK) {
                            Intent data = result.getData( );
                            if (data != null && data.getData( ) != null) {
                                String path = null;
                                Uri selectImageUri = data.getData( );
                                Bitmap SeclectImageBitmap = null;
                                try {
                                    SeclectImageBitmap = MediaStore.Images.Media.getBitmap(getApplicationContext( ).getContentResolver( ), selectImageUri);

                                } catch (IOException e) {
                                    e.printStackTrace( );
                                }
                                link = String.valueOf(getImageUri(getApplicationContext(),SeclectImageBitmap));
                                Intent sendBack = new Intent(  );
                                sendBack.setAction("sendBack");
                                sendBack.putExtra("link",link);
                                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(sendBack);
                                finish();
//                               Log.e("zzz",link);
                            }
                        }
                    }
            );

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult( ), new ActivityResultCallback<ActivityResult>( ) {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode( ) == RESULT_OK && result.getData( ) != null) {
                Bundle bundle = result.getData( ).getExtras( );
                Bitmap bitmap = (Bitmap) bundle.get("data");
                link = String.valueOf(getImageUri(getApplicationContext(),bitmap));
                Intent sendBack = new Intent(  );
                sendBack.setAction("sendBack");
                sendBack.putExtra("link",link);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(sendBack);
                finish();
            }
        }
    });

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "IMG_" + Calendar.getInstance().getTime(),null);
        return Uri.parse(path);
    }
}