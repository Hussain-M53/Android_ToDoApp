package com.bootcamp.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context,"ToDoDatabase.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ToDoList (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS ToDoList");
    }

    public boolean add(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATA",data);
        long insert = db.insert("ToDoList",null,cv);
        db.close();
        return insert!=-1 ;
    }

    public Cursor retrieve(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM ToDoList",null);
    }

    public boolean delete(String Task) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ToDoList where DATA=?", new String[]{ Task });
        long deleted;
        if (cursor.moveToFirst()) {
            deleted = db.delete("TODoList", "DATA =?", new String[]{Task});
        } else {
            return false;
        }
        cursor.close();
        return deleted != -1;
    }
    public boolean deleteAll() {
       SQLiteDatabase db = this.getWritableDatabase();
        long deleted = db.delete("ToDoList",null,null);
        return deleted == 1;
    }
}
