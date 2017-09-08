package com.lzjtu.myweather.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lzjtu.myweather.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haydd on 2016/5/7 0007.
 */
public class DBImportManager {

    private final int BUFFER_SIZE = 400000;
    public static final  String DB_NAME = "db_weather.db";
    public static final String PACKAGE_NAME = "com.lzjtu.myweather";
    public static final  String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath()
            +"/"+PACKAGE_NAME+"/databases";

    private SQLiteDatabase database;
    private Context context;

    public DBImportManager(Context context){
        this.context = context;
    }

    public void openDatabase(){
        this.database = this.openCityDatabase(DB_PATH
        +"/"+DB_NAME);
    }

    public List<String> getProSet(){
        List<String> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("select * from provinces",null);
        while (cursor.moveToNext()){
            String pro = cursor.getString(cursor.getColumnIndex("name"));
            list.add(pro);
        }
        cursor.close();
        return list;
    }

    public List<String> getcitySet(int pro_id){
        List<String> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("select * from citys where province_id="+pro_id,null);
        while (cursor.moveToNext()){
            String city = cursor.getString(cursor.getColumnIndex("name"));
            list.add(city);
        }
        cursor.close();
        return list;
    }

    public String getCityNum(int position,int pro_id){
        Cursor cursor = this.database.rawQuery("select * from citys where province_id="+pro_id,null);
        cursor.moveToPosition(position);
        String citynum = cursor.getString(cursor.getColumnIndex("city_num"));
        cursor.close();
        return citynum;
    }

    private SQLiteDatabase openCityDatabase(String dbfile){
        try{
            if (!(new File(dbfile).exists())){
                InputStream is = this.context.getResources().openRawResource(R.raw.db_weather);
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

    public void closeDatabase(){
        this.database.close();
    }

}
