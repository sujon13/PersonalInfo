package com.example.sujon4002.personalinfo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantData;
import com.example.sujon4002.personalinfo.period_info.create_period_information.PeriodData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
        //Logger.addLogAdapter(new AndroidLogAdapter());
    }
    public long insertImportantInformation(ImportantData data){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_TYPE,data.getType());
        contentValues.put(Config.COLUMN_NAME, data.getName());
        contentValues.put(Config.COLUMN_RELATION, data.getRelation());
        contentValues.put(Config.COLUMN_DATE, data.getDate());
        contentValues.put(Config.COLUMN_DESCRIPTION, data.getDescription());

        try{
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_IMPORTANT_INFORMATION, null, contentValues);
        } catch (SQLiteException e){
            //Logger.d("Exception: " + e.getMessage());
            //Toast.makeText(context, "Operation failed: " + id, Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return id;
    }
    public long insertPeriodInformation(PeriodData data){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_PERIOD_START_DATE,data.getStartDate());
        contentValues.put(Config.COLUMN_PERIOD_END_DATE, data.getEndDate());
        contentValues.put(Config.COLUMN_DESCRIPTION, data.getDescription());

        try{
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_PERIOD_INFORMATION, null, contentValues);
        } catch (SQLiteException e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return id;
    }

    public List<ImportantData> getAllImportantInformation(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_IMPORTANT_INFORMATION, null, null, null, null, null, null, null);


              //If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             //String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             //cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<ImportantData> importantDataList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ID));
                        String type = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TYPE));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_NAME));
                        String relation = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RELATION));
                        String date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DATE));
                        String description = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DESCRIPTION));

                        importantDataList.add(new ImportantData(id, type, name, relation, date, description) );
                    }   while (cursor.moveToNext());
                    //Toast.makeText(context, "is empty: "+importantDataList.size(), Toast.LENGTH_SHORT).show();

                    return importantDataList;
                }

        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();

            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
    public List<PeriodData> getAllPeriodInformation(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_PERIOD_INFORMATION, null, null, null, null, null, null, null);


            //If you want to execute raw query then uncomment below 2 lines. And comment out above line.

            //String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
            //cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<PeriodData> periodDataList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PERIOD_ID));
                        String startDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PERIOD_START_DATE));
                        String endDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PERIOD_END_DATE));
                        String description = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PERIOD_DESCRIPTION));

                        periodDataList.add(new PeriodData(id, startDate,endDate, description) );
                    }   while (cursor.moveToNext());
                    //Toast.makeText(context, "is empty: "+importantDataList.size(), Toast.LENGTH_SHORT).show();

                    return periodDataList;
                }

        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();

            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    /*public ImportantData getImportantInformationByName(String Name){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        ImportantData student = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_IMPORTANT_INFORMATION, null,
                    Config.COLUMN_NAME + " = ? ", new String[]{Name},
                    null, null, null);


             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

             //String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_IMPORTANT_INFORMATION, Config.COLUMN_NAME, String.valueOf(Name));
             //cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ID));
                String type = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TYPE));
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_NAME));
                String relation = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RELATION));
                String date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DATE));
                String description = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DESCRIPTION));

                student = new ImportantData(id, type, name, relation, date, description);
            }
        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return student;
    }*/

    public long updateImportantInformation(ImportantData data){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_TYPE,data.getType());
        contentValues.put(Config.COLUMN_NAME, data.getName());
        contentValues.put(Config.COLUMN_RELATION, data.getRelation());
        contentValues.put(Config.COLUMN_DATE, data.getDate());
        contentValues.put(Config.COLUMN_DESCRIPTION, data.getDescription());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_IMPORTANT_INFORMATION, contentValues,
                    Config.COLUMN_ID + " = ? ",
                    new String[] {String.valueOf(data.getId())});
        } catch (SQLiteException e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    /*public long deleteStudentByRegNum(long registrationNum) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_STUDENT,
                    Config.COLUMN_STUDENT_REGISTRATION + " = ? ",
                    new String[]{ String.valueOf(registrationNum)});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }*/

    /*public boolean deleteAllStudents(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_IMPORTANT_INFORMATION, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_IMPORTANT_INFORMATION);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }*/

}
