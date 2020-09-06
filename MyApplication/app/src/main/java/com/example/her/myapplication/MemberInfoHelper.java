package com.example.her.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DATABASE CLASS
 */
public class MemberInfoHelper  extends SQLiteOpenHelper{
    final static String TAG = "MemberInfoHelper";
    // 생성자에서 MemberInfo.db 라는 이름의 db생성
    public MemberInfoHelper(Context c) {
        super(c, "MemberInfo.db", null, 1); // Context, db name, cursor정보, version
    }

    // 초기에 한번 실행
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate()");
        // 질의 응답 만듬 table라는 이름의 Table을 생성하고 _id 값은 1씩 자동증가하게 하였고, unmame, uid, umoney를 key로 하는 value만듬
        String query = "CREATE TABLE member (_id INTEGER PRIMARY KEY AUTOINCREMENT, uname TEXT, uid TEXT, umoney INTEGER, jan INTEGER, feb INTEGER, mar INTEGER, " +
                "apr INTEGER, may INTEGER, jun INTEGER, jul INTEGER, agu INTEGER, sep INTEGER, oct INTEGER, nov INTEGER, dec INTEGER);";
        db.execSQL(query);  // 질의응답하겠다
    }
    // version 바뀌면 실행
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade()");
        String query = "DROP TABLE IF EXITS table";
        db.execSQL(query);
        onCreate(db);
    }
}

