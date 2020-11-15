package com.example.accountbookssukssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ImportActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button add3;
    ImageView back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        add3 = findViewById(R.id.btn_expenditure);
        back3 = findViewById(R.id.back3);
        
    }

    public void export(View view) {
        Intent intent = new Intent(this, ExportActivity.class);
        startActivity(intent);
    }
}