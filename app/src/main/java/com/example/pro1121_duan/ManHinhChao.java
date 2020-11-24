package com.example.pro1121_duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        setTitle("Hello World");
        Thread thread=new Thread(){
            @Override
            public void run(){
                super.run();
                int waited = 0;
                while(waited < 1000){
                    try {
                        sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    waited+=200;
                }
                Intent intent=new Intent(ManHinhChao.this,DangNhap.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
            }
        };
        thread.start();
    }
}