package com.allandroidprojects.ecomsample.startup;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class dbManager extends SQLiteOpenHelper {
    public static final String dbname ="Login,db";

    public dbManager( Context context) {
        super(context,"Login.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create table users (username TEXT primary Key,Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {
        mydb.execSQL("drop table if exists users");
    }
    public Boolean insertData(String username, String password){
            SQLiteDatabase mydb= this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username",username);
            contentValues.put("password",password);
            long result =mydb.insert("users",null,contentValues);
            if(result==-1) return false;
            else
                return true;


     }

    public boolean checkusername(String username)
    {
     SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor =mydb.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public boolean checkusernamePassword(String username, String Password)
    {
    SQLiteDatabase mydb = this.getWritableDatabase();
    Cursor cursor =mydb.rawQuery("select * from users where username = ? and Password = ?",new String[] {username,Password});
    if (cursor.getCount() > 0 )
        return true;
    else
        return false;
    }



}
