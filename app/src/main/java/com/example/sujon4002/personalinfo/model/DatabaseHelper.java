package com.example.sujon4002.personalinfo.model;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.logging.Logger;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_PERSONAL_TABLE = "CREATE TABLE " + Config.TABLE_IMPORTANT_INFORMATION + "("
                + Config.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_TYPE + " TEXT NOT NULL, "
                + Config.COLUMN_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_RELATION + " TEXT, " //nullable
                + Config.COLUMN_DATE + " TEXT NOT NULL, "
                + Config.COLUMN_DESCRIPTION + " TEXT " //nullable
                + ")";

        //Logger.d("Table create SQL: " + CREATE_PERSONAL_TABLE);

        db.execSQL(CREATE_PERSONAL_TABLE);

        //Logger.d("DB created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_IMPORTANT_INFORMATION);

        // Create tables again
        onCreate(db);
    }

}