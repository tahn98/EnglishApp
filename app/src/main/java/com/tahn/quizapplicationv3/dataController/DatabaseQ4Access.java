package com.tahn.quizapplicationv3.dataController;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tahn.quizapplicationv3.objectClass.Question4;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQ4Access {
    private SQLiteOpenHelper db4hp;
    private SQLiteDatabase database;
    private static DatabaseQ4Access instance;

    private DatabaseQ4Access(Context context){this.db4hp=new DatabaseQ4OpenHealper(context);}
    public static DatabaseQ4Access getInstance(Context context){
        if(instance==null){
            instance=new DatabaseQ4Access(context);
        }
        return instance;

    }
    public void open() {this.database=db4hp.getWritableDatabase();}

    public void close(){if (database!=null) this.database.close();}
    public List<Question4> get2question()
    {
        List<Question4> listq4= new ArrayList<>();
        Cursor cursor= database.rawQuery("SELECT * FROM Question ",null);
        while(cursor.moveToNext())
        {
            Question4 q4=new Question4(cursor.getString(5),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(6));
            listq4.add(q4);
        }
        cursor.close();
        return listq4;
    }


}
