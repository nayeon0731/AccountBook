package com.example.accountbookssukssuk;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accountbookssukssuk.total.TotalDB;
import com.example.accountbookssukssuk.total.TotalData;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class AssetsActivity extends AppCompatActivity {

    Spinner mainCategory;
    EditText subCategory, price;
    Button btAdd;

    TotalDB total_database;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //툴바 뒤로가기 버튼
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.arrow); //뒤로가기 버튼 이미지 변경


        // 변수정의
        mainCategory = findViewById(R.id.main_category_text);
        subCategory = findViewById(R.id.sub_category_text);
        price = findViewById(R.id.price_text);
        btAdd = findViewById(R.id.add_btn);

        final TextView array_text = (TextView)findViewById(R.id.array_text);

        // 분류 Spinner를 선택하면
        mainCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // array_text에 선택한 값을 string으로 받아옴
                array_text.setText(""+parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // 데이터베이스 초기화(생성)
        total_database = TotalDB.getInstance(this);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sMain = array_text.getText().toString();
                String sSub = subCategory.getText().toString();
                String sPrice = price.getText().toString();

                // 이름정보와 가격정보를 받아오면
                if (!sSub.equals("") & !sPrice.equals("")) {
                    // main data 초기화
                    TotalData data = new TotalData(sMain, sSub, sPrice);
                    // main data에 받아온 정보 저장하기
                    total_database.totalDao().insert(data);
                    // 저장된 후 이름정보와 가격정보를 적는 EditText 빈칸으로 만들기
                    subCategory.setText("");
                    price.setText("");
                }
            }
        });


    }

    // 툴바 뒤로가기 실행
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