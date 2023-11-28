package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.shoppingcart.views.RecyclerActivity;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img=findViewById(R.id.imageView);
        img.animate().alpha(4000).setDuration(0);
        handler = new Handler();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },
                4000);

    }
}