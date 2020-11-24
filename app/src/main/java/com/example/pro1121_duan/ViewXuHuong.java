package com.example.pro1121_duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.pro1121_duan.Adapter.PhongAdapter;
import com.example.pro1121_duan.DAO.DangTinDao;
import com.example.pro1121_duan.Model.DangTin;
import com.example.pro1121_duan.SQLite.MySQLite;

import java.util.ArrayList;
import java.util.List;

public class ViewXuHuong extends AppCompatActivity {
    String quan;
    TextView tvQuan;
    GridView gridView;
    MySQLite mySQLite;
    String [] fall={"Không tìm thấy nhà trọ nào"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_xu_huong);
        Intent intent=getIntent();
        quan=intent.getExtras().getString("quan");
        System.out.println(quan);
        tvQuan=findViewById(R.id.tvQuan);
        gridView=findViewById(R.id.grid);
        tvQuan.setText(quan);
        mySQLite=new MySQLite(ViewXuHuong.this);
        DangTinDao dangTinDao=new DangTinDao(mySQLite);

        List<DangTin> dangTinList=dangTinDao.timKiem(quan);
        if (dangTinList.size()==0){
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,fall);
            gridView.setAdapter(arrayAdapter);
        }else {
            PhongAdapter phongAdapter=new PhongAdapter(dangTinList);
            gridView.setAdapter(phongAdapter);

             gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     DangTin dangTin = dangTinList.get(position);
                     Intent intent= new Intent(ViewXuHuong.this,ChiTietTro_Googlemap.class);

                     intent.putExtra("Tieude",dangTin.TieuDe);
                     intent.putExtra("Dientich",dangTin.DienTich);
                     intent.putExtra("Phongngu",dangTin.PhongNgu);
                     intent.putExtra("Vesinh",dangTin.VeSinh);
                     intent.putExtra("Kinhdo",dangTin.KinhDo);
                     intent.putExtra("Vido",dangTin.ViDo);
                     intent.putExtra("LoaiPhong", dangTin.LoaiPhong);
                     intent.putExtra("ThanhPho", dangTin.ThanhPho);
                     intent.putExtra("QuanHuyen", dangTin.QuanHuyen);
                     intent.putExtra("TenDiaDiem", dangTin.TenDiaDiem);
                     intent.putExtra("TenDuong", dangTin.TenDuong);
                     intent.putExtra("SoNha", dangTin.SoNha);
                     intent.putExtra("Gia", dangTin.Gia);
                     intent.putExtra("MoTa", dangTin.MoTa);
                     intent.putExtra("LienHe", dangTin.LienHe);
                     startActivity(intent);
                 }
             });
        }



    }
}