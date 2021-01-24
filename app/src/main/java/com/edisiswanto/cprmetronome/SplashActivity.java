package com.edisiswanto.cprmetronome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation btt1, btt2, splash;
    ImageView image;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        btt1 = AnimationUtils.loadAnimation(this, R.anim.btt);
        btt2 = AnimationUtils.loadAnimation(this, R.anim.btt);
        splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);

        // view
        image = findViewById(R.id.image);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        // animation
        text1.startAnimation(btt1);
        text2.startAnimation(btt2);
        image.startAnimation(splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}