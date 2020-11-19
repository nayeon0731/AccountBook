package com.example.accountbookssukssuk.income;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.accountbookssukssuk.spend.SpendData;
import com.example.accountbookssukssuk.total.TotalData;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface IncomeDao {

    // Insert query
    @Insert(onConflict = REPLACE)
    void insert(IncomeData incomeData);

    //Delete query
    @Delete
    void delete(IncomeData incomeData);

    // Update query
    @Query("UPDATE income_table SET Income_date = :sIncomeDate, sub_category = :sSubCategory, price = :sPrice, comment = :sComment  WHERE ID = :sID")
    void update(int sID, Date sIncomeDate, String sSubCategory, String sPrice, String sComment);

    // Get all data query
    @Query("SELECT * FROM income_table")
    List<IncomeData> getAll();

}


