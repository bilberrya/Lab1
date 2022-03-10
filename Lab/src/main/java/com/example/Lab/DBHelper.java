package com.example.Lab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LabDb";
    public static final String TABLE_RESULTS = "Results";
    public static final String TABLE_USERS = "Users";

    public static final String KEY_ID_USER = "id_user";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DOLGNOST = "dolgnost";

    public static final String KEY_ID_RESULT = "id_result";
    public static final String KEY_NAME2 = "name2";
    public static final String KEY_SERVICE = "service";
    public static final String KEY_RESULT = "result";
    public static final String KEY_DATE = "data";
    public static final String KEY_PRICE = "price";

    public DBHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + " (" +
                KEY_ID_USER + " integer primary key," +
                KEY_NAME + " text," +
                KEY_LOGIN + " text," +
                KEY_PASSWORD + " text," +
                KEY_DOLGNOST + " text" + ")");

        db.execSQL("create table " + TABLE_RESULTS + " (" +
                KEY_ID_RESULT + " integer primary key," +
                KEY_NAME2  + " text," +
                KEY_SERVICE  + " text," +
                KEY_RESULT  + " text," +
                KEY_DATE  + " text," +
                KEY_PRICE + " float" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_RESULTS);
        db.execSQL("drop table if exists " + TABLE_USERS);
        onCreate(db);
    }
}
