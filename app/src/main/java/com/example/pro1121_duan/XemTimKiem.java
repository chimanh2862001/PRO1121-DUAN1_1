package com.example.pro1121_duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pro1121_duan.Adapter.PhongAdapter;
import com.example.pro1121_duan.Adapter.PhongListViewAdapter;
import com.example.pro1121_duan.DAO.DangTinDao;
import com.example.pro1121_duan.Model.DangTin;
import com.example.pro1121_duan.SQLite.MySQLite;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class XemTimKiem extends AppCompatActivity {
   MySQLite mySQLite;
   String [] fall={"Không Tìm Thấy Kết Quả Nào!! Sorry"};
    String[] sapXep ={ "--- Sắp Xếp ---" ,"Giá thấp đến cao", "Giá cao đến thấp", "Diện tích nhỏ đến lớn", "Diện tích lớn đến nhỏ"};
    EditText edTimKiem;
    Spinner spSapXep;
    ListView Lv;
    TextView tvHuy, tvKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_tim_kiem);
        Lv=findViewById(R.id.Lv);
        tvHuy = findViewById(R.id.tvHuyTimChiTiet);
        edTimKiem = findViewById(R.id.edTimKiemTimChiTiet);
        tvKetQua = findViewById(R.id.tvKetQua);

        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        spSapXep = findViewById(R.id.spinner5);

        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<>(XemTimKiem.this,android.R.layout.simple_list_item_1,sapXep);

        spSapXep.setAdapter(arrayAdapter5);

        mySQLite=new MySQLite(XemTimKiem.this);
        Intent intent=getIntent();
        String tim=intent.getExtras().getString("timkiem");
        edTimKiem.setText(tim);

        DangTinDao dangTinDao=new DangTinDao(mySQLite);

        if (true){
            List<DangTin> dangTinList = dangTinDao.timKiem(tim);
            if (dangTinList.size() == 0){
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(XemTimKiem.this, android.R.layout.simple_list_item_1,fall);
                Lv.setAdapter(arrayAdapter);
            }else {
                PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                Lv.setAdapter(phongAdapter);
                Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangTin dangTin = dangTinList.get(position);
                        Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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

        edTimKiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (true){
                    List<DangTin> dangTinList = dangTinDao.timKiem(edTimKiem.getText().toString());
                    if (dangTinList.size() == 0){
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(XemTimKiem.this, android.R.layout.simple_list_item_1,fall);
                        Lv.setAdapter(arrayAdapter);
                    }else {
                        PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                        Lv.setAdapter(phongAdapter);
                        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                DangTin dangTin = dangTinList.get(position);
                                Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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
                return true;
            }
        });

        spSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    List<DangTin> dangTinList = dangTinDao.timKiem(tim);
                    PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                    Lv.setAdapter(phongAdapter);
                    Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DangTin dangTin = dangTinList.get(position);
                            Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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

                if (position == 1){
                    List<DangTin> dangTinList = dangTinDao.timKiem(tim);
                    Collections.sort(dangTinList, new Comparator<DangTin>() {
                        @Override
                        public int compare(DangTin o1, DangTin o2) {
                            return o1.Gia - o2.Gia;
                        }
                    });
                    PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                    Lv.setAdapter(phongAdapter);
                    Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DangTin dangTin = dangTinList.get(position);
                            Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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

                if (position == 2){
                    List<DangTin> dangTinList = dangTinDao.timKiem(tim);
                    Collections.sort(dangTinList, new Comparator<DangTin>() {
                        @Override
                        public int compare(DangTin o1, DangTin o2) {
                            return o2.Gia - o1.Gia;
                        }
                    });
                    PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                    Lv.setAdapter(phongAdapter);
                    Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DangTin dangTin = dangTinList.get(position);
                            Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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

                if (position == 3){
                    List<DangTin> dangTinList = dangTinDao.timKiem(tim);
                    Collections.sort(dangTinList, new Comparator<DangTin>() {
                        @Override
                        public int compare(DangTin o1, DangTin o2) {
                            return o1.DienTich - o2.DienTich;
                        }
                    });
                    PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                    Lv.setAdapter(phongAdapter);
                    Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DangTin dangTin = dangTinList.get(position);
                            Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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

                if (position == 4){
                    List<DangTin> dangTinList = dangTinDao.timKiem(tim);
                    Collections.sort(dangTinList, new Comparator<DangTin>() {
                        @Override
                        public int compare(DangTin o1, DangTin o2) {
                            return o2.DienTich - o1.DienTich;
                        }
                    });
                    PhongListViewAdapter phongAdapter=new PhongListViewAdapter(dangTinList);
                    Lv.setAdapter(phongAdapter);
                    Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DangTin dangTin = dangTinList.get(position);
                            Intent intent= new Intent(getApplicationContext(),ChiTietTro_Googlemap.class);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvKetQua.setText("Kết Quả: " + Lv.getAdapter().getCount());

    }
}