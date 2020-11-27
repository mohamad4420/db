package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class sqlLite extends SQLiteOpenHelper {
    public  static  final  String dbname="empl.db";
    public sqlLite(@Nullable Context context) {
        super(context, dbname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table empo ( id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT ,sex TEXT ,BaseSalary FLOAT,TotalSales FLOAT,CommissionRate FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS empo");
        onCreate(db);
    }

    public boolean insert (String name , String sex, float BaseSalary, float TotalSales, float CommissionRate){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("sex",sex);
        contentValues.put("BaseSalary",BaseSalary);
        contentValues.put("TotalSales",TotalSales);
        contentValues.put("CommissionRate",CommissionRate);

        long result = db.insert("empo",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }


    public  boolean modify(String id ,String name , String sex, float BaseSalary, float TotalSales, float CommissionRate){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("sex",sex);
        contentValues.put("BaseSalary",BaseSalary);
        contentValues.put("TotalSales",TotalSales);
        contentValues.put("CommissionRate",CommissionRate);
        db.update("empo",contentValues,"id= ?",new String[]{id});

        return true;
    }



    int delete (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete("empo","id= ?",new String[]{id});

    }


    public ArrayList Select(String name){
        ArrayList list = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery( "select *from embo where name = '"+name+"'",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            String id = res.getString(0);
            String name1 = res.getString(1);
            String  sex = res.getString(2);
            String BaseSalary  = res.getString(3);
            String TotalSales = res.getString(4);
            String CommissionRate = res.getString(5);
            list.add("Id"+id+"    Name"+name1+"     Sex"+sex+"      Base Salary"+BaseSalary+"     Total Sales"+TotalSales+"     Commission Rate"
                    +CommissionRate);
            res.moveToNext();
        }
        return list;
    }








}

