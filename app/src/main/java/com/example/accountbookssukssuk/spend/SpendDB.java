package com.example.accountbookssukssuk.spend;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SpendData.class}, version = 1, exportSchema = false)
public abstract class SpendDB extends RoomDatabase {

    // 인스턴스 만들기
    private static SpendDB spend_instance;
    // Create Dao
    public abstract SpendDao spendDao();

    // 데이터베이스 이름 정의
    private static String DATABASE_NAME = "spend_database";

    public synchronized static SpendDB getInstance(Context context) {
        // Check condition
        if (spend_instance == null) {
            // When databa se is null
            // Initialize database
            spend_instance = Room.databaseBuilder(context.getApplicationContext(),
                    SpendDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return database
        return spend_instance;
    }
}
