package com.example.pro1121_duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pro1121_duan.Adapter.PhongListViewAdapter;
import com.example.pro1121_duan.DAO.DangTinDao;
import com.example.pro1121_duan.Model.DangTin;
import com.example.pro1121_duan.SQLite.MySQLite;

import java.util.List;

public class AllListActivity extends AppCompatActivity {

    MySQLite mySQLite;
    String [] fall={"Không Tìm Thấy Kết Quả Nào!! Sorry"};
    String[] loaiPhong = {"--- Loại Phòng ---","Kí Túc/Homestay","Phòng Trọ","Chung Cư", "Nhà Nguyên Căn"};
    String[] phongNgu = {"--- Số Phòng Ngủ ---" ,"1", "2", "3", "4", "6"};
    String[] dienTich = {"--- Diện Tích ---","20 - 30m²","30 - 50m²","> 50m²",};
    String[] mucgia ={"--- Mức Giá ---","Dưới 1.500.000VND","1.500.000 - 3.000.000VND","Trên 3.000.000VND"};
    String[] sapXep ={ "--- Sắp Xếp ---" ,"Giá thấp đến cao", "Giá cao đến thấp", "Diện tích nhỏ đến lớn", "Diện tích lớn đến nhỏ"};

    EditText edTimKiem;
    Spinner spLoaiPhong, spPhongNgu, spDienTich, spMucGia, spSapXep;
    ListView Lv;
    TextView tvHuy, tvSapXep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        mySQLite = new MySQLite(AllListActivity.this);
        Lv=findViewById(R.id.Lv);
        tvHuy = findViewById(R.id.tvHuyTimChiTiet);
        edTimKiem = findViewById(R.id.edTimKiemTimChiTiet);
        tvSapXep = findViewById(R.id.tvSapXep);
        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        spLoaiPhong = findViewById(R.id.spinner1);
        spPhongNgu = findViewById(R.id.spinner2);
        spDienTich = findViewById(R.id.spinner3);
        spMucGia = findViewById(R.id.spinner4);
        spSapXep = findViewById(R.id.spinner5);

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(AllListActivity.this,android.R.layout.simple_list_item_1,loaiPhong);
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(AllListActivity.this,android.R.layout.simple_list_item_1,phongNgu);
        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<>(AllListActivity.this,android.R.layout.simple_list_item_1,dienTich);
        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<>(AllListActivity.this,android.R.layout.simple_list_item_1,mucgia);
        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<>(AllListActivity.this,android.R.layout.simple_list_item_1,sapXep);

        spLoaiPhong.setAdapter(arrayAdapter1);
        spPhongNgu.setAdapter(arrayAdapter2);
        spDienTich.setAdapter(arrayAdapter3);
        spMucGia.setAdapter(arrayAdapter4);
        spSapXep.setAdapter(arrayAdapter5);

        DangTinDao dangTinDao=new DangTinDao(mySQLite);


    }
}