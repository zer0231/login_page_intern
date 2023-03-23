package com.zero.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button upload_btn;
    TextView terms_and_condition;
    ImageView avatar;
    ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        terms_and_condition = findViewById(R.id.terms_nd_cond);
        close = findViewById(R.id.iv_close);
        avatar = findViewById(R.id.avatar_iv);
        upload_btn = findViewById(R.id.upload_btn);

        terms_and_condition.setMovementMethod(LinkMovementMethod.getInstance());

        avatar.setOnClickListener(view -> openCamera());

        close.setOnClickListener(view -> {
            finish();
        });

        upload_btn.setOnClickListener(view -> openGallery());
    }

    private void openGallery() {
        Intent gallery_open = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ActivityLauncherResult.launch(gallery_open);
    }

    private void openCamera() {
        Intent camera_open = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ActivityLauncherResult.launch(camera_open);
    }

    ActivityResultLauncher<Intent> ActivityLauncherResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
        }
    });
}