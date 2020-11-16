package com.example.accountbookssukssuk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_list, btn_home, btn_asset;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_list = (ImageButton)findViewById(R.id.btn_list);
        btn_home = (ImageButton)findViewById(R.id.btn_home);
        btn_asset = (ImageButton)findViewById(R.id.btn_asset);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frame, new Fragmenth());
        fragmentTransaction.commit();

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragmentl fragmentl = new Fragmentl();
                transaction.replace(R.id.frame, fragmentl);
                transaction.commit();
                btn_list.setImageDrawable(getResources().getDrawable(R.mipmap.sel_list, getApplicationContext().getTheme()));
                btn_home.setImageDrawable(getResources().getDrawable(R.mipmap.btn_home, getApplicationContext().getTheme()));
                btn_asset.setImageDrawable(getResources().getDrawable(R.mipmap.btn_asset, getApplicationContext().getTheme()));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragmenth fragmenth = new Fragmenth();
                transaction.replace(R.id.frame, fragmenth);
                transaction.commit();
                btn_list.setImageDrawable(getResources().getDrawable(R.mipmap.btn_list, getApplicationContext().getTheme()));
                btn_home.setImageDrawable(getResources().getDrawable(R.mipmap.sel_home, getApplicationContext().getTheme()));
                btn_asset.setImageDrawable(getResources().getDrawable(R.mipmap.btn_asset, getApplicationContext().getTheme()));
            }
        });

        btn_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragmenta fragmenta = new Fragmenta();
                transaction.replace(R.id.frame, fragmenta);
                transaction.commit();
                btn_list.setImageDrawable(getResources().getDrawable(R.mipmap.btn_list, getApplicationContext().getTheme()));
                btn_home.setImageDrawable(getResources().getDrawable(R.mipmap.btn_home, getApplicationContext().getTheme()));
                btn_asset.setImageDrawable(getResources().getDrawable(R.mipmap.sel_asset, getApplicationContext().getTheme()));
            }
        });

    }

}