package com.lzjtu.myweather.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lzjtu.myweather.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by haydd on 2016/5/7 0007.
 */
public class DBImportDataManage {

    private final int BUFFER_SIZE = 400000;
    public static final  String DB_NAME = "data.db";
    public static final String PACKAGE_NAME = "com.lzjtu.myweather";
    public static final  String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath()
            +"/"+PACKAGE_NAME+"/databases";

    private SQLiteDatabase db;
    private Context context;

    public DBImportDataManage(Context context){
        this.context = context;
    }

    public void openDatabase(){
        this.db = this.openDataDatabase(DB_PATH
                +"/"+DB_NAME);
    }

    private SQLiteDatabase openDataDatabase(String dbfile){
        try{
            if (!(new File(dbfile).exists())){
                InputStream is = this.context.getResources().openRawResource(R.raw.data);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while((count = is.read(buffer))>0){
                    fos.write(buffer,0,count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return db;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    synchronized public String getAtmosphereName(String num){
        if (num.equals("")){
            return "";
        }
        String str = "";
        if (db.isOpen()){
            Cursor cursor = db.rawQuery("select * from atmosphere where id="+num,null);
            cursor.moveToFirst();
            str = cursor.getString(cursor.getColumnIndex("name"));
            cursor.close();
        }
        return str;
    }

    synchronized public String getDirectionName(String num){
        if (num.equals("")){
            return "";
        }
        String str = "";
        if (db.isOpen()){
            Cursor cursor = db.rawQuery("select * from direction where id="+num,null);
            cursor.moveToFirst();
            str = cursor.getString(cursor.getColumnIndex("name"));
            cursor.close();
        }
        return str;
    }

    synchronized public String getPowerName(String num){
        if (num.equals("")){
            return "";
        }
        String str = "";
        if (db.isOpen()){
            Cursor cursor = db.rawQuery("select * from power where id="+num,null);
            cursor.moveToFirst();
            str = cursor.getString(cursor.getColumnIndex("name"));
            cursor.close();
        }
        return str;
    }
}
