package com.example.accountbookssukssuk.income;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {IncomeData.class}, version = 1, exportSchema = false)
public abstract class IncomeDB extends RoomDatabase {
    // 인스턴스 만들기
    private static IncomeDB income_instance;
    // Create Dao
    public abstract IncomeDao incomeDao();

    // 데이터베이스 이름 정의
    private static String DATABASE_NAME = "income_database";

    public synchronized static IncomeDB getInstance(Context context) {
        // Check condition
        if (income_instance == null) {
            // When databa se is null
            // Initialize database
            income_instance = Room.databaseBuilder(context.getApplicationContext(),
                    IncomeDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return database
        return income_instance;
    }
}
