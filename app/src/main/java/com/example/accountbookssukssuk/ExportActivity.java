package com.example.accountbookssukssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ExportActivity extends AppCompatActivity {

    ImageView back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        back2 = findViewById(R.id.back2);

    }

    public void go_import(View view) {
        Intent intent = new Intent(this, ImportActivity.class);
        startActivity(intent);
    }
}