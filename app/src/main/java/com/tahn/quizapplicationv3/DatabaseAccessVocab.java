package com.tahn.quizapplicationv3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccessVocab {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccessVocab instance;
    Cursor c = null;

    private DatabaseAccessVocab(Context context){
        this.openHelper= new DatabaseOpenHelperVocab(context);
    }
    public static DatabaseAccessVocab getInstance(Context context){
        if(instance==null){
            instance= new DatabaseAccessVocab(context);
        }
        return instance;
    }
    public void open(){
        this.db= openHelper.getWritableDatabase();
    }
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    public String getWord(int id,String table){
        c=db.rawQuery("select word from "+table+" where id = "+id,new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String word = c.getString(0);
            buffer.append(""+word);
        }
        return buffer.toString();
    }
    public String getMean(int id,String table){
        c=db.rawQuery("select mean from "+table+" where id = "+id,new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String mean = c.getString(0);
            buffer.append(""+mean);
        }
        return buffer.toString();
    }

}
