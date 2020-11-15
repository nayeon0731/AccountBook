package com.example.accountbookssukssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImportActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        textView = findViewById(R.id.importText);
        textView = findViewById((R.id.dateView));
        textView = findViewById((R.id.itemView));
        textView = findViewById((R.id.categoryView));
        textView = findViewById((R.id.priceView));
        textView = findViewById((R.id.contentView));

        editText = findViewById(R.id.inputDate);
        editText = findViewById(R.id.inputPrice);
        editText = findViewById(R.id.inputContent);

        button = findViewById(R.id.btn_income);
        button = findViewById(R.id.btn_expenditure);
        button = findViewById(R.id.btn_save);

        //ExportActivity로 이동
        Intent intent = new Intent(this, ExportActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}