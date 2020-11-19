package com.example.accountbookssukssuk.income;

import androidx.room.TypeConverter;

import java.util.Date;

// 유형 변환기
// Room DB의 기본 자료형인 String, int 이외에 Date를 사용하기 위해 유형 변환기가 필요
public class Convert {
    @TypeConverter
    // Date 객체를 Long 객체로 변환하고
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    // Long에서 Date로의 역변환을 실행
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
    // Room은 Long 객체를 유지하는 방법을 이미 알고 있으므로 이 변환기를 사용하여 Date 유형의 값을 유지.
}
