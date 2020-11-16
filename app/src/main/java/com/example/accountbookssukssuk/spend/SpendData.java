package com.example.accountbookssukssuk.spend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "spend_table")
public class SpendData implements Serializable {
    // id 컬럼 만들기
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // text 컬럼 만들기
    @ColumnInfo(name = "spend_date")
    private String spendDate;

    @ColumnInfo(name = "main_category")
    private String mainCategory;

    @ColumnInfo(name = "sub_category")
    private String subCategory;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "comment")
    private String comment;

    public SpendData(String spendDate, String mainCategory, String subCategory, String price, String comment) {
        this.spendDate = spendDate;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.price = price;
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    public String getSpendDate() {
        return spendDate;
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
