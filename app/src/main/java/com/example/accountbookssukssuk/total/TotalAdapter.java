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

    // Create constructor
    public TotalAdapter(Activity context, List<TotalData> total_dataList) {
        this.context = context;
        this.total_dataList = total_dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize main data
        TotalData data = total_dataList.get(position);
        //Initialize database
        total_database = TotalDB.getInstance(context);
        //Set text on text view
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

                //Create dialog
                Dialog dialog = new Dialog(context);
                //Set content view
                dialog.setContentView(R.layout.dialog_update);
                //Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();

                //initialize and assign variable
                EditText editMain = dialog.findViewById(R.id.edit_main);
                EditText editText = dialog.findViewById(R.id.edit_text);
                EditText editPrice = dialog.findViewById(R.id.edit_price);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                //set text on edit text
                editMain.setText(sMain);
                editText.setText(sSubCategory);
                editPrice.setText(sPrice);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dismiss dialog
                        dialog.dismiss();
                        //get update text from edit text
                        String uMain = editMain.getText().toString().trim();
                        String uText = editText.getText().toString().trim();
                        String uPrice = editPrice.getText().toString().trim();
                        //update text in database
                        total_database.totalDao().update(sID, uMain, uText, uPrice);
                        //NOTIFY when data is updated
                        total_dataList.clear();
                        total_dataList.addAll(total_database.totalDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                TotalData td = total_dataList.get(holder.getAdapterPosition());
                //delete text from database
                total_database.totalDao().delete(td);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                total_dataList.remove(position);
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
