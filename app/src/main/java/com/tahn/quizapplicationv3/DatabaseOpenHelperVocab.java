package com.tahn.quizapplicationv3;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperVocab extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="vocabulary.db";
    private static final int DATABASE_VERSION=1;

    public DatabaseOpenHelperVocab(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
}
