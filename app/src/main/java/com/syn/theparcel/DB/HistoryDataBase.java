package com.syn.theparcel.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.syn.theparcel.enty.Express;

import java.util.ArrayList;

/**
 * Created by 孙亚楠 on 2016/11/21.
 */

public class HistoryDataBase {
    Context context;
    MyDatabaseOpenHelper myDatabaseOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public HistoryDataBase(Context context){
        this.context=context;
        myDatabaseOpenHelper=new MyDatabaseOpenHelper(context);
    }
    public ArrayList<Express> getArray(){
        ArrayList<Express > arrayList=new ArrayList<Express>();
        ArrayList<Express> arrayList1=new ArrayList<Express>();
        sqLiteDatabase=myDatabaseOpenHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from history ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
           int id=cursor.getInt(cursor.getColumnIndex("id"));
            String number=cursor.getString(cursor.getColumnIndex("number"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            Express express=new Express(id,name,number);
            arrayList.add(express);
            cursor.moveToNext();
        }cursor.close();
        sqLiteDatabase.close();
        for(int i=arrayList.size();i>0;i--){
            arrayList1.add(arrayList.get(i-1));
        }
        return arrayList1;
    }
    public void toInsert(Express express){
        sqLiteDatabase=myDatabaseOpenHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("insert into history(name,number) values('"+express.getExpressName()+"','"+express.getExpressNumber()+"')");
        sqLiteDatabase.close();
    }
    //删除一条数据
    public void deleteNote(int id){
        sqLiteDatabase=myDatabaseOpenHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("delete  from history where id=" + id+ "" );
        sqLiteDatabase.close();
    }
}
