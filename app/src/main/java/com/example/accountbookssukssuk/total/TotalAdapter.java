package com.example.accountbookssukssuk.total;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accountbookssukssuk.R;

import java.util.List;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.ViewHolder> {

    // 변수 정의
    private List<TotalData> total_dataList;
    private Activity context;
    private TotalDB total_database;

    // constructor 만들기
    // constructor 호출시 같은 id 값을 갖는 요소들을 캡슐화해서 보내줌
    public TotalAdapter(Activity context, List<TotalData> total_dataList) {
        this.context = context;
        this.total_dataList = total_dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // view 초기화
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //main data 초기화
        TotalData data = total_dataList.get(position);
        //database 초기화
        total_database = TotalDB.getInstance(context);
        //TextView를 데이터베이스에 집어넣기
        holder.mainView.setText(data.getMainCategory());
        holder.textView.setText(data.getSubCategory());
        holder.priceView.setText(data.getPrice());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // main data 초기화
                TotalData td = total_dataList.get(holder.getAdapterPosition());
                // ID 값 얻기
                int sID = td.getID();
                // MainCategory 값 얻기
                String sMain = td.getMainCategory();
                // text 얻기
                String sSubCategory = td.getSubCategory();
                // price 얻기
                String sPrice = td.getPrice();


                // 수정창 띄우기기
                // Create dialog
                Dialog dialog = new Dialog(context);
                // Set content view
                dialog.setContentView(R.layout.dialog_update);
                // Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                // initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                // set layout
                dialog.getWindow().setLayout(width,height);
                // show dialog
                dialog.show();

                // 수정창의 변수들 정의
                // initialize and assign variable
                EditText editMain = dialog.findViewById(R.id.edit_main);
                EditText editText = dialog.findViewById(R.id.edit_text);
                EditText editPrice = dialog.findViewById(R.id.edit_price);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                // id에 맞는 데이터베이스에 저장된 값을 EditText에 받아오기
                // set text on edit text
                editMain.setText(sMain);
                editText.setText(sSubCategory);
                editPrice.setText(sPrice);

                // 수정 버튼을 누르면
                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 다이얼로그 끄기
                        dialog.dismiss();
                        // EditText에 적혀있는 수정된 string값을 받아오기
                        String uMain = editMain.getText().toString().trim();
                        String uText = editText.getText().toString().trim();
                        String uPrice = editPrice.getText().toString().trim();
                        // 수정된 값을 데이터베이스에 업데이트하기
                        total_database.totalDao().update(sID, uMain, uText, uPrice);
                        // 데이터베이스에 있는 모든 리스트 지우기
                        total_dataList.clear();
                        // 데이터베이스에 있는 모든 요소들 받아오기
                        total_dataList.addAll(total_database.totalDao().getAll());
                        // 리스트뷰 업데이트
                        notifyDataSetChanged();
                    }
                });
            }
        });


        // 삭제 버튼을 누르면
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택된 id 값을 갖는 리스트를 가지고 오기위한 변수
                //initialize main data
                TotalData td = total_dataList.get(holder.getAdapterPosition());
                // 데이터베이스에서 찾아서 삭제하기
                //delete text from database
                total_database.totalDao().delete(td);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                total_dataList.remove(position);
                // 리스트뷰에서 삭제된 요소 지우기
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,total_dataList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return total_dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Initialize variavle
        TextView mainView, textView, priceView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Assign variiable
            mainView = itemView.findViewById(R.id.category_view);
            textView = itemView.findViewById(R.id.text_view);
            priceView = itemView.findViewById(R.id.price_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
