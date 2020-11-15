package com.example.accountbookssukssuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.accountbookssukssuk.total.TotalAdapter;
import com.example.accountbookssukssuk.total.TotalDB;
import com.example.accountbookssukssuk.total.TotalData;

import java.util.ArrayList;
import java.util.List;

public class AssetsActivity extends AppCompatActivity {

    EditText mainCategory, subCategory, price;
    Button btAdd;
    RecyclerView recyclerView;
    ImageView back1;

    List<TotalData> total_dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    TotalDB total_database;
    TotalAdapter total_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        // Assign variable
        mainCategory = findViewById(R.id.main_category_text);
        subCategory = findViewById(R.id.sub_category_text);
        price = findViewById(R.id.price_text);
        btAdd = findViewById(R.id.add_btn);
        recyclerView = findViewById(R.id.recycler_view);
        back1 = findViewById(R.id.back1);

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
                String sMain = mainCategory.getText().toString();
                String sSub = subCategory.getText().toString();
                String sPrice = price.getText().toString();


                if (!sSub.equals("") & !sPrice.equals("")) {
                    //when text is not empty
                    //initialize main data
                    TotalData data = new TotalData(sMain, sSub, sPrice);
                    //set TEXT on main data
                    total_database.totalDao().insert(data);
                    mainCategory.setText("");
                    subCategory.setText("");
                    price.setText("");
                }

                //notify when data is inserted
                total_dataList.clear();
                total_dataList.addAll(total_database.totalDao().getAll());
                total_adapter.notifyDataSetChanged();

            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AssetsActivity.this,Fragmenta.class));
                finish();
            }
        });
    }
}