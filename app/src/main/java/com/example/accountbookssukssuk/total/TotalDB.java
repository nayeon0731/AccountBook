package com.example.accountbookssukssuk.total;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TotalData.class}, version = 1, exportSchema = false)
public abstract class TotalDB extends RoomDatabase {
    // 인스턴스 만들기
    private static TotalDB total_database;
    // Create Dao
    public abstract TotalDao totalDao();

    // 데이터베이스 이름 정의
    private static String DATABASE_NAME = "total_database";

    public synchronized static TotalDB getInstance(Context context) {
        // Check condition
        if (total_database == null) {
            // When databa se is null
            // Initialize database
            total_database = Room.databaseBuilder(context.getApplicationContext(),
                    TotalDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return database
        return total_database;
    }

}
