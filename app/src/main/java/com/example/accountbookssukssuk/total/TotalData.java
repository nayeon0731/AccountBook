package com.example.accountbookssukssuk.total;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// 테이블 이름 정의
@Entity(tableName = "total_table")
public class TotalData  implements Serializable {

    // id 컬럼 만들기
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // text 컬럼 만들기
    @ColumnInfo(name = "main_category")
    private String mainCategory;

    @ColumnInfo(name = "sub_category")
    private String subCategory;

    @ColumnInfo(name = "price")
    private String price;

    public TotalData(String mainCategory, String subCategory, String price) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.price = price;
    }

    // Getter and Setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
