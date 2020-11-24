package com.example.pro1121_duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pro1121_duan.DAO.NguoiDungDao;
import com.example.pro1121_duan.Model.NguoiDung;
import com.example.pro1121_duan.SQLite.MySQLite;

import java.util.List;

public class ThongTinTaiKhoan extends AppCompatActivity {
    MySQLite mySQLite;
    TextView textView12,textView13,textView14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        Intent intent=getIntent();
        String user=intent.getExtras().getString("tk");
        mySQLite=new MySQLite(ThongTinTaiKhoan.this);
        NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQLite);
        textView12=findViewById(R.id.textView12);
        textView13=findViewById(R.id.textView13);
        textView14=findViewById(R.id.textView14);

        List<NguoiDung> nguoiDungList=nguoiDungDao.getAll();

        for (NguoiDung s:nguoiDungList){
            if (s.username.equals(user)){
                textView12.setText(s.name);
                textView13.setText(user);
                StringBuilder phoen=new StringBuilder(s.phone);
                phoen.setCharAt(4,'*');
                phoen.setCharAt(5,'*');
                phoen.setCharAt(6,'*');

                textView14.setText(phoen);
            }
        }

    }
}