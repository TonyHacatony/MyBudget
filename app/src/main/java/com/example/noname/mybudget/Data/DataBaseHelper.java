package com.example.noname.mybudget.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Money.myDb";
    public static final String TABLE_NAME = "Money_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "MARK";
    public static final String COL_3 = "SUMMARY";
    public static final String COL_4 = "TYPE";
    public static final String COL_5 = "DATE";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,MARK TEXT,SUMMARY LONG,TYPE TEXT,DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String type,String sum,String mark,String date){

        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,mark);
        cv.put(COL_3,sum);
        cv.put(COL_4,type);
        cv.put(COL_5,date);
        long result = myDb.insert(TABLE_NAME,null,cv);
        myDb.close();

        if(result ==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("Select * from " + TABLE_NAME,null);
        return res;
    }

    public Cursor getSumOfType() {
        SQLiteDatabase myDb = this.getWritableDatabase();
        String[] columns = {"TYPE", "sum(SUMMARY) as SUMMARY"};
        Cursor res1 = myDb.query(TABLE_NAME, columns, null, null, "TYPE", null, null);
        return res1;

    }

    public Cursor getSumOfMark() {
        SQLiteDatabase myDb = this.getWritableDatabase();
        String[]  columns = {"MARK","sum(SUMMARY) as SUMMARY"};
        Cursor res2 = myDb.query(TABLE_NAME, columns,null,null,"MARK",null,null);
        return res2;
    }


}