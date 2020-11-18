package com.example.accountbookssukssuk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accountbookssukssuk.total.TotalAdapter;
import com.example.accountbookssukssuk.total.TotalDB;
import com.example.accountbookssukssuk.total.TotalData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AssetsActivity extends AppCompatActivity {

    Spinner mainCategory;
    EditText subCategory, price;
    Button btAdd;
    RecyclerView recyclerView;

    List<TotalData> total_dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    TotalDB total_database;
    TotalAdapter total_adapter;

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
        recyclerView = findViewById(R.id.recycler_view);
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

        // 데이터베이스 초기화
        total_database = TotalDB.getInstance(this);
        // this로 받아온 인스턴스값을 데이터베이스에 저장
        total_dataList = total_database.totalDao().getAll();

        // linear layout manager 초기화
        linearLayoutManager = new LinearLayoutManager(this);

        // 리사이클러뷰에 layout manager 세팅하기
        recyclerView.setLayoutManager(linearLayoutManager);
        // 어댑터 초기화
        total_adapter = new TotalAdapter(AssetsActivity.this,total_dataList);
        // 리사이클러뷰에 어댑터 세팅하기
        recyclerView.setAdapter(total_adapter);

        // 저장하기 버튼을 누르면
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText로 string값 받아오기
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

                // 리스트 정보 모두 지우기
                total_dataList.clear();
                // 데이터베이스에서 받아온 정보들을 리스트에 다시채우기
                total_dataList.addAll(total_database.totalDao().getAll());
                // 리사이클러뷰에 리스트 다시 불러오기
                total_adapter.notifyDataSetChanged();

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