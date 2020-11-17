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


        // Assign variable
        mainCategory = findViewById(R.id.main_category_text);
        subCategory = findViewById(R.id.sub_category_text);
        price = findViewById(R.id.price_text);
        btAdd = findViewById(R.id.add_btn);
        recyclerView = findViewById(R.id.recycler_view);
        final TextView array_text = (TextView)findViewById(R.id.array_text);


        mainCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                array_text.setText(""+parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Initialize database
        total_database = TotalDB.getInstance(this);
        //store database value in data list
        total_dataList = total_database.totalDao().getAll();

        //initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);

        //set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
        total_adapter = new TotalAdapter(AssetsActivity.this,total_dataList);
        //set adapter
        recyclerView.setAdapter(total_adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from edit text
                String sMain = array_text.getText().toString();
                String sSub = subCategory.getText().toString();
                String sPrice = price.getText().toString();


                if (!sSub.equals("") & !sPrice.equals("")) {
                    //when text is not empty
                    //initialize main data
                    TotalData data = new TotalData(sMain, sSub, sPrice);
                    //set TEXT on main data
                    total_database.totalDao().insert(data);
                    subCategory.setText("");
                    price.setText("");
                }

                //notify when data is inserted
                total_dataList.clear();
                total_dataList.addAll(total_database.totalDao().getAll());
                total_adapter.notifyDataSetChanged();

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