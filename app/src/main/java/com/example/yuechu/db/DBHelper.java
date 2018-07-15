package com.example.yuechu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "db";
    public static final int DB_VERSION = 1;
    private static final String PERSON="t_person";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建数据库表
        sqLiteDatabase.execSQL("create table "+PERSON+" (name varchar(20) primary key, password varchar, age integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 数据库版本更新
        Log.i("PersonDAOTest", "UpGrade!");
    }
}
