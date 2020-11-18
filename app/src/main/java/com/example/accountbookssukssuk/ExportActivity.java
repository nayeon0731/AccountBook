package com.example.accountbookssukssuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class ExportActivity extends Fragment implements DatePickerDialog.OnDateSetListener {

    TextView date_text;
    String str;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_export, container, false);
        Spinner object_spinner = viewGroup.findViewById(R.id.object_category_text);
        final TextView array_text = viewGroup.findViewById(R.id.array_export_text);
        Button export_date_picker = viewGroup.findViewById(R.id.export_date_picker);
        date_text = viewGroup.findViewById(R.id.export_date_text);

        // 날짜선택
        // 날자 다이얼로그에서 년, 월, 일 정보 받아오기위한 초기화
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        // 날짜선택 버튼을 누르면 날짜 다이얼로그 불러오기
        export_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // 항목선택
        // 항목을 선택하면
        object_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 항목에서 받아온 string값을 array_text에 저장
                array_text.setText(""+parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return viewGroup;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // date_text에 날짜 string 정보 저장하기
        str = year + "/" + (month + 1) + "/" + dayOfMonth;
        date_text.setText(str);
    }
}
