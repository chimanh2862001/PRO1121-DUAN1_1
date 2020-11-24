package com.example.pro1121_duan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    String us,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        us=intent.getExtras().getString("uss");
        pass=intent.getExtras().getString("pass");





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_Nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new FragmentTimKiemActivity()).commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.nav_timkiem:
                    fragment = new FragmentTimKiemActivity();
                    break;
                case R.id.nav_oghep:
                    fragment = new FragmentOGhepActivity();
                    break;
                case R.id.nav_profile:
                    fragment = new FragmentProfileActivity();
                    Bundle bundle=new Bundle();
                    String us2=us;
                    bundle.putString("us2",us2);
                    fragment.setArguments(bundle);
                    break;
                case R.id.nav_save:
                    fragment = new FragmentLuuActivity();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            return true;
        }

    };
}