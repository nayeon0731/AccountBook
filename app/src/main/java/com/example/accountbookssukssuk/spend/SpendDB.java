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
    // Data Access Object. 실질적으로 DB에 접근하는 객체
    public abstract SpendDao spendDao();

    // 데이터베이스 이름 정의
    private static String DATABASE_NAME = "spend_database";

    // 데이터베이스의 인스턴스들을 순차적으로 불러옴
    public synchronized static SpendDB getInstance(Context context) {
        // 데이터베이스 상태 확인
        if (spend_instance == null) {
            // 데이터베이스가 비어있을 때
            // 데이터베이스 초기화
            spend_instance = Room.databaseBuilder(context.getApplicationContext(),
                    SpendDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        // 데이터베이스 반환
        return spend_instance;
    }
}
