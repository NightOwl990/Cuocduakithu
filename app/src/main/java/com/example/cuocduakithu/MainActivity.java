package com.example.cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvDiem;
    private Button btnStart;
    private SeekBar seekBar1, seekBar2, seekBar3;
    private CheckBox chk1, chk2, chk3;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDiem = findViewById(R.id.tv_diem);
        btnStart = findViewById(R.id.btn_start);
        seekBar1 = findViewById(R.id.seek_bar_1);
        seekBar2 = findViewById(R.id.seek_bar_2);
        seekBar3 = findViewById(R.id.seek_bar_3);
        chk1 = findViewById(R.id.chk_1);
        chk2 = findViewById(R.id.chk_2);
        chk3 = findViewById(R.id.chk_3);

        Random random = new Random();
        int one = random.nextInt(5);
        int two = random.nextInt(5);
        int three = random.nextInt(5);

        seekBar1.setEnabled(false);
        seekBar2.setEnabled(false);
        seekBar3.setEnabled(false);

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    chk2.setChecked(false);
                    chk3.setChecked(false);
                }
            }
        });
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    chk1.setChecked(false);
                    chk3.setChecked(false);
                }
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    chk2.setChecked(false);
                    chk1.setChecked(false);
                }
            }
        });

        tvDiem.setText(String.valueOf(score));
        CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                if (seekBar1.getProgress() >= seekBar1.getMax()){
                    this.cancel();
                    btnStart.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "One win", Toast.LENGTH_SHORT).show();
                    if (chk1.isChecked()){
                        score = score + 5;
                    } else {
                        score = score - 5;
                    }
                    tvDiem.setText(String.valueOf(score));
                    EnableCheckBox();
                }
                if (seekBar2.getProgress() >= seekBar2.getMax()){
                    this.cancel();
                    btnStart.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Two win", Toast.LENGTH_SHORT).show();
                    if (chk2.isChecked()){
                        score = score + 5;
                    } else {
                        score = score - 5;
                    }
                    tvDiem.setText(String.valueOf(score));
                    EnableCheckBox();
                }
                if (seekBar3.getProgress() >= seekBar3.getMax()){
                    this.cancel();
                    btnStart.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Three win", Toast.LENGTH_SHORT).show();
                    if (chk3.isChecked()){
                        score = score + 5;
                    } else {
                        score = score - 5;
                    }
                    tvDiem.setText(String.valueOf(score));
                    EnableCheckBox();
                }
                seekBar1.setProgress(seekBar1.getProgress() + one);
                seekBar2.setProgress(seekBar2.getProgress() + two);
                seekBar3.setProgress(seekBar3.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk1.isChecked() || chk2.isChecked() || chk3.isChecked()){
                    seekBar1.setProgress(0);
                    seekBar2.setProgress(0);
                    seekBar3.setProgress(0);
                    countDownTimer.start();
                    btnStart.setVisibility(View.INVISIBLE);
                    DisableCheckBox();
                } else Toast.makeText(MainActivity.this, "Vui lòng đặt cược", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void EnableCheckBox(){
        chk1.setEnabled(true);
        chk2.setEnabled(true);
        chk3.setEnabled(true);
    }
    private void DisableCheckBox(){
        chk1.setEnabled(false);
        chk2.setEnabled(false);
        chk3.setEnabled(false);
    }
}