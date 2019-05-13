package com.tahn.quizapplicationv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tahn.quizapplicationv3.objectClass.WordNote;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        this.context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String title, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.TITLE, title);
        contentValues.put(dbHelper.DESC, description);
        database.insert(dbHelper.TABLE_NAME, null, contentValues);
    }

    public List<WordNote> getAllWord(){
        List<WordNote> noteList = new ArrayList<WordNote>();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_NAME;

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WordNote note = new WordNote(cursor.getString(1), cursor.getString(2));
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        return noteList;
    }

    public void update(int id, String title, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.TITLE, title);
        contentValues.put(dbHelper.DESC, description);
        database.update(dbHelper.TABLE_NAME, contentValues, dbHelper._ID + " = " + id, null);
    }

    public void delete(int id) {
        database.delete(dbHelper.TABLE_NAME,dbHelper._ID + " ='" + id + "'",null);
    }

    public Cursor fetch() {
        String[] columns = new String[]{dbHelper._ID, dbHelper.TITLE, dbHelper.DESC};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
