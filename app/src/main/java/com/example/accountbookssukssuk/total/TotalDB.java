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
    // Data Access Object. 실질적으로 DB에 접근하는 객체
    public abstract TotalDao totalDao();

    // 데이터베이스 이름 정의
    private static String DATABASE_NAME = "total_database";

    // 데이터베이스의 인스턴스들을 순차적으로 불러옴
    public synchronized static TotalDB getInstance(Context context) {
        // 데이터베이스 상태 확인
        if (total_database == null) {
            // 데이터베이스가 비어있을 때
            // 데이터베이스 초기화
            total_database = Room.databaseBuilder(context, TotalDB.class, DATABASE_NAME)
                    .allowMainThreadQueries() // => 이걸 추가해서 AsyncTask 를 사용안하고 간편하게할수있지만 오류가많아 실제 앱을 만들때 사용하면 안된다고함.
                   .fallbackToDestructiveMigration()
                    .build();
        }
        // 데이터베이스 반환
        return total_database;
    }

    //디비객체제거
    public static void destroyInstance() {
         total_database = null;
    }

}
