package com.luncode.moetools;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView wechatImage;
    private ImageView logImage;
    private ImageView aboutImage;
    private ImageView updateImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wechatImage = findViewById(R.id.wechatImage);
        logImage = findViewById(R.id.logImage);
        aboutImage =  findViewById(R.id.aboutImage);
        updateImage = findViewById(R.id.updateImage);

        wechatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WeChetShare.class);
                startActivity(intent);
            }
        });

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://app.xiaomi.com/detail/467215");
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        logImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Update_Log.class);
                startActivity(intent);
            }
        });

        aboutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });

    }
}
