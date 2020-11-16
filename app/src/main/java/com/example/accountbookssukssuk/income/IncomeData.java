package com.example.accountbookssukssuk.income;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "income_table")
public class IncomeData implements Serializable {
    // id 컬럼 만들기
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // text 컬럼 만들기
    @ColumnInfo(name = "Income_date")
    private String incomeDate;

    @ColumnInfo(name = "main_category")
    private String mainCategory;

    @ColumnInfo(name = "sub_category")
    private String subCategory;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "comment")
    private String comment;

    public IncomeData(String incomeDate, String mainCategory, String subCategory, String price, String comment) {
        this.incomeDate = incomeDate;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.price = price;
        this.comment = comment;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }
}
