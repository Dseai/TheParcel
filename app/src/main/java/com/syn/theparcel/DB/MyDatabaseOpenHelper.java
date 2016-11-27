package com.syn.theparcel.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 孙亚楠 on 2016/11/21.
 */

public class MyDatabaseOpenHelper extends SQLiteOpenHelper {
    private Context context;
    private final String CREATE_HISTORY="create table if not exists history(id integer primary key autoincrement,name text ,number text )";



    public MyDatabaseOpenHelper(Context context) {
        super(context,"history" , null, 1);
        this.context=context;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
