package com.example.accountbookssukssuk.spend;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.accountbookssukssuk.income.IncomeData;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SpendDao {

    // Insert query
    @Insert(onConflict = REPLACE)
    void insert(SpendData spendData);

    //Delete query
    @Delete
    void delete(SpendData spendData);

    // Update query
    @Query("UPDATE spend_table SET spend_date = :sSpendDate, main_category = :sMainCategory, sub_category = :sSubCategory, price = :sPrice, comment = :sComment  WHERE ID = :sID")
    void update(int sID, String sSpendDate, String sMainCategory, String sSubCategory, String sPrice, String sComment);

    // Get all data query
    @Query("SELECT * FROM spend_table")
    List<IncomeData> getAll();
}
