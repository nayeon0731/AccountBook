package com.example.accountbookssukssuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.ColumnInfo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
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

        btn_import.setBackgroundColor(Color.LTGRAY);
        btn_export.setBackgroundColor(Color.LTGRAY);

        btn_import.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_import.setBackgroundColor(Color.TRANSPARENT);
                btn_export.setBackgroundColor(Color.LTGRAY);
                return false;
            }
        });

        btn_export.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_import.setBackgroundColor(Color.LTGRAY);
                btn_export.setBackgroundColor(Color.TRANSPARENT);
                return false;
            }
        });

        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ImportActivity fragment_import = new ImportActivity();
                transaction.replace(R.id.frame, fragment_import);
                transaction.commit();
            }
        });

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
}