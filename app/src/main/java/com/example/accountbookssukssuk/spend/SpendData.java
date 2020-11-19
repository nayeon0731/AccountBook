package com.example.accountbookssukssuk.spend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "spend_table")
public class SpendData implements Serializable {
    // id 컬럼 만들기
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // 컬럼 만들기
    @ColumnInfo(name = "spend_date")
    private Date spendDate;

    @ColumnInfo(name = "main_category")
    private String mainCategory;

    @ColumnInfo(name = "sub_category")
    private String subCategory;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "comment")
    private String comment;

    // 정보를 한번에 저장해서 캡슐화
    // getter값이다.
    public SpendData(Date spendDate, String mainCategory, String subCategory, String price, String comment) {
        this.spendDate = spendDate;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.price = price;
        this.comment = comment;
    }

    // Getter and Setter
    // 외부에서 직접적으로 데이터에 접근하는 것을 막기 위해 사용. 객체의 무결성 보장
    // Getter : 본 필드의 값을 숨긴 채 내부에서 가공된 값을 꺼낼 때 사용.(출력)
    // Setter : 전달받은 값을 내부에서 가공해 필드에 넣어줄 때 사용.(입력)
    // ID는 개별적으로 사용되기 때문에 getter, setter 둘다 따로 존재
    // 나머지 값들은 입력할 때 한번에 받아들이므로 setter를 하나로 묶었음.

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Date getSpendDate() {
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
