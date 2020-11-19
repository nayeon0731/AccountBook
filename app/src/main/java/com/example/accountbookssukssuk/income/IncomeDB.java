package com.example.accountbookssukssuk.income;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {IncomeData.class}, version = 2, exportSchema = false)
@TypeConverters({Convert.class})
public abstract class IncomeDB extends RoomDatabase {

    // 인스턴스 만들기
    private static IncomeDB income_instance;
    // Create Dao
    // Data Access Object. 실질적으로 DB에 접근하는 객체
    public abstract IncomeDao incomeDao();

    // 데이터베이스 이름 정의
    private static String DATABASE_NAME = "income_database";

    // 데이터베이스의 인스턴스들을 순차적으로 불러옴
    public synchronized static IncomeDB getInstance(Context context) {
        // 데이터베이스 상태 확인
        if (income_instance == null) {
            // 데이터베이스가 비어있을 때
            // 데이터베이스 초기화
            income_instance = Room.databaseBuilder(context.getApplicationContext(),
                    IncomeDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        // 데이터베이스 반환
        return income_instance;
    }
}
