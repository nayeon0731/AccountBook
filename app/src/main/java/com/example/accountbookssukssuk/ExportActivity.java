package com.example.accountbookssukssuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.example.accountbookssukssuk.spend.SpendDB;
import com.example.accountbookssukssuk.spend.SpendData;
import com.example.accountbookssukssuk.total.TotalDB;
import com.example.accountbookssukssuk.total.TotalData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.amitshekhar.utils.Constants.NULL;

public class ExportActivity extends Fragment implements DatePickerDialog.OnDateSetListener {

    TextView date_text;
    String str;

    SpendDB spend_database;

    List<String> total_dataList = new ArrayList<String>();
    List<Integer> total_IdList = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_export, container, false);
        Spinner object_spinner = viewGroup.findViewById(R.id.object_category_text);
        Spinner category_spinner = viewGroup.findViewById(R.id.export_cate);
        Button save_btn = viewGroup.findViewById(R.id.add_btn2);
        EditText ex_price = viewGroup.findViewById(R.id.export_price);
        EditText ex_comment = viewGroup.findViewById(R.id.export_comment);
        final TextView array_text = viewGroup.findViewById(R.id.array_export_text);
        final TextView array_text2 = viewGroup.findViewById(R.id.array_export_cate);
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

        // 데이터베이스 초기화(생성)
        Context context = getContext();
        spend_database = SpendDB.getInstance(context);

        // 분류(sub_category) 스피너 값 totalDB 에서 가져옴.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, total_dataList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(adapter);

        SubViewModel mSubViewModel = new ViewModelProvider(this).get(SubViewModel.class);
        mSubViewModel.getAllTotal().observe(getViewLifecycleOwner(), new Observer<List<TotalData>>() {
            @Override
            public void onChanged(@Nullable final List<TotalData> totalData) {
                // 기존에 쌓여있던 데이터리스트에 값 지우기
                total_dataList.clear();
                for (TotalData totalData1 : totalData){
                    total_dataList.add(totalData1.getSubCategory());
                    total_IdList.add(totalData1.getID());
                }
                //notifyDataSetChanged after update termsList variable here
                adapter.notifyDataSetChanged();

            }
        });

        // 분류선택
        // 분류를 선택하면
        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 항목에서 받아온 string값을 array_text에 저장
                array_text2.setText(""+parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 저장 버튼 누르면 저장
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sComment = ex_comment.getText().toString();
                String sPrice = ex_price.getText().toString();
                String sCategory = array_text2.getText().toString();
                // 내용정보와 가격정보를 받아오면
                if (!sComment.equals("") & !sPrice.equals("")) {
                    // main data 초기화
                    SpendData data = new SpendData(NULL, NULL, sCategory, sPrice, sComment);
                    // main data에 받아온 정보 저장하기
                    spend_database.spendDao().insert(data);
                    // 저장된 후 내용정보와 가격정보를 적는 EditText 빈칸으로 만들기
                    ex_comment.setText("");
                    ex_price.setText("");
                }

            }
        });

        DebugDB.getAddressLog();

        return viewGroup;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // date_text에 날짜 string 정보 저장하기
        str = year + "/" + (month + 1) + "/" + dayOfMonth;
        date_text.setText(str);
    }
}
