package com.example.accountbookssukssuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.ColumnInfo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class InsertActivity extends AppCompatActivity {

    Button btn_import, btn_export;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btn_import = (Button)findViewById(R.id.btn_import);
        btn_export = (Button)findViewById(R.id.btn_export);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //툴바 뒤로가기 버튼
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.arrow); //뒤로가기 버튼 이미지 변경

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frame, new ImportActivity());
        fragmentTransaction.commit();

        // 수입이랑 지출 버튼 선택시 색 바꾸기
        btn_export.setBackgroundColor(Color.LTGRAY);
        // 수입 버튼 누르면 색 바꿈
        btn_import.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_import.setBackgroundColor(Color.TRANSPARENT);
                btn_export.setBackgroundColor(Color.LTGRAY);
                return false;
            }
        });
        // 지출 버튼 누르면 색 바꿈
        btn_export.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_import.setBackgroundColor(Color.LTGRAY);
                btn_export.setBackgroundColor(Color.TRANSPARENT);
                return false;
            }
        });

        // 수입 버튼 누르면
        // 수입 플래그먼트(ImportActivity)창으로 이동하기
        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ImportActivity fragment_import = new ImportActivity();
                transaction.replace(R.id.frame, fragment_import);
                transaction.commit();
            }
        });

        // 지출 버튼 누르면
        // 지출 플래그먼트(ExportActivity)창으로 이동하기
        btn_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ExportActivity fragment_export = new ExportActivity();
                transaction.replace(R.id.frame, fragment_export);
                transaction.commit();
            }
        });
    }

    //툴바 뒤로가기 실행
    @Override
    public boolean onOptionsItemSelected(MenuItem item ){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}