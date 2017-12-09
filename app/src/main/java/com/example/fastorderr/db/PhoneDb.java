package com.example.fastorderr.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 2/12/2560.
 */

public class PhoneDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 9;

    public static final String TABLE_NAME_REC = "phone_number1";
    public static final String TABLE_NAME_FAV = "phone_number2";

    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_NUMBER = "number";
    public static final String COL_PICTURE = "picture";

    private static final String CREATE_TABLE_REC = "CREATE TABLE "+ TABLE_NAME_REC + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_NUMBER + " TEXT, "
            + COL_PICTURE + " TEXT)";
    private static final String CREATE_TABLE_FAV = "CREATE TABLE "+ TABLE_NAME_FAV + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_NUMBER + " TEXT, "
            + COL_PICTURE + " TEXT)";

    public PhoneDb(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAV );
        db.execSQL(CREATE_TABLE_REC);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, "ร้านป้าจ๋า(23ชม.)");
        cv.put(COL_NUMBER, "08xxxxxxxx");
        cv.put(COL_PICTURE, "pajar.PNG");
        db.insert(TABLE_NAME_REC, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "ล้านกระติ๊บ");
        cv.put(COL_NUMBER, "08xxxxxxxx");
        cv.put(COL_PICTURE, "lankratib.PNG");
        db.insert(TABLE_NAME_REC, null, cv);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAV);
        onCreate(db);
    }
}