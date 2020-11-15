package com.example.accountbookssukssuk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_list, btn_home, btn_asset;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_list = (Button)findViewById(R.id.btn_list);
        btn_home = (Button)findViewById(R.id.btn_home);
        btn_asset = (Button)findViewById(R.id.btn_asset);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragmentl fragmentl = new Fragmentl();
                transaction.replace(R.id.frame, fragmentl);
                transaction.commit();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragmenth fragmenth = new Fragmenth();
                transaction.replace(R.id.frame, fragmenth);
                transaction.commit();
            }
        });

        btn_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragmenta fragmenta = new Fragmenta();
                transaction.replace(R.id.frame, fragmenta);
                transaction.commit();
            }
        });

    }

}