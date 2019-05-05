package com.tahn.quizapplicationv3.dataController;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseQ4OpenHealper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="question.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseQ4OpenHealper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
