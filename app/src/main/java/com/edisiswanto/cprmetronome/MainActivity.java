package com.edisiswanto.cprmetronome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton bpmSatu, bpmDua;
    TextView title, text1, text2, text3, text4, text5;
    Animation ttb, ttb1, ttb2, ttb3, ttb4, ttb5, ttb6, ttb7;
    private Metronome metronome;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // load animation
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        ttb1 = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb2 = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb3 = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb4 = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb5 = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb6 = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb7 = AnimationUtils.loadAnimation(this, R.anim.btt);

        // load button
        bpmSatu = (ToggleButton) findViewById(R.id.bpmSatu);
        bpmDua = (ToggleButton) findViewById(R.id.bpmDua);

        title = findViewById(R.id.title);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);

        // run animation
        ttb1.setStartOffset(600);
        ttb2.setStartOffset(800);
        ttb3.setStartOffset(1000);
        ttb4.setStartOffset(1200);
        ttb5.setStartOffset(1400);
        ttb6.setStartOffset(1600);
        ttb7.setStartOffset(1800);

        title.startAnimation(ttb);
        text1.startAnimation(ttb1);
        text2.startAnimation(ttb2);
        text3.startAnimation(ttb3);
        text4.startAnimation(ttb4);
        text5.startAnimation(ttb5);

        bpmSatu.startAnimation(ttb6);
        bpmDua.startAnimation(ttb7);

        metronome = new Metronome(this);
        metronome.setBeatsPerMeasure(0);

        bpmSatu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                checkStart(1);
                metronome.changeBpm(100);

            }
        });

        bpmDua.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                checkStart(2);
                metronome.changeBpm(120);
            }
        });

    }

    public void checkStart(int bpm) {
        if (bpm == 1 && bpmSatu.isChecked()) {
            metronome.restart();
            bpmDua.setChecked(false);
            metronome.play();
        } else if (bpm == 2 && bpmDua.isChecked()) {
            metronome.restart();
            bpmSatu.setChecked(false);
            metronome.play();
        } else {
            metronome.pause();
        }
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            metronome.pause();
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }


}