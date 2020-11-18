package com.example.accountbookssukssuk.total;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TotalDao {

    // Insert query
    @Insert(onConflict = REPLACE)
    void insert(TotalData totalData);

    //Delete query
    @Delete
    void delete(TotalData totalData);

    // Update query
    @Query("UPDATE total_table SET main_category = :sMainCategory, sub_category = :sSubCategory, price = :sPrice  WHERE ID = :sID")
    void update(int sID, String sMainCategory, String sSubCategory, String sPrice);

    // Get all data query
    @Query("SELECT * FROM total_table")
    List<TotalData> getAll();
}
