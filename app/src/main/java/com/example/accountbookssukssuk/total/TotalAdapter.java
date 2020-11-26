package com.example.accountbookssukssuk.total;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accountbookssukssuk.R;

import java.util.List;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.ViewHolder> {
    private List<TotalData> dataList;
    private Activity context;
    private TotalDB totalDB;

    // constructor 만들기
    public TotalAdapter(Activity context, List<TotalData> totalData) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TotalData data = dataList.get(position);
        totalDB = TotalDB.getInstance(context);
        holder.textView.setText(data.getMainCategory());
        holder.categoryView.setText(data.getSubCategory());
        holder.priceView.setText(data.getPrice());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalData d = dataList.get(holder.getAdapterPosition());
                // id, string 얻어오기
                int sID = d.getID();
                String sMain = d.getMainCategory();
                String sSub = d.getSubCategory();
                String sPrice = d.getPrice();

                // 수정 dialog 만들기
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 변수 초기화
        TextView textView, categoryView, priceView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
            categoryView = itemView.findViewById(R.id.category_view);
            priceView = itemView.findViewById(R.id.price_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);

        }
    }
}
