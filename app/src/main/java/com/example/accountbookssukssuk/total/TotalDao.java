package com.example.accountbookssukssuk.total;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
    @Update
    void update(TotalData totalData);

    // Get all data query
    @Query("SELECT * FROM total_table")
    LiveData<List<TotalData>> getAll(); //LiveData => TotalData 테이블에 있는 모든 객체를 계속 관찰하고있다가 변경이 일어나면 그것을 자동으로 업데이트.
                                        //getAll() 은 관찰 가능한 객체.(디비변경시 반응하는)
}
