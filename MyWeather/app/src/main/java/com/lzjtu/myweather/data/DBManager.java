package com.lzjtu.myweather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class DBManager {
    private DbOpenHelper dbOpenHelper;
    static private DBManager dbManager = new DBManager();
    private Context context;

    void onInit(Context context){
        this.context = context;
        dbOpenHelper = DbOpenHelper.getInstance(context);
    }

    public static synchronized DBManager getInstance(){
        return dbManager;
    }

    synchronized public void saveLocation(String location,String pro,String city){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()){
            db.delete(UerSettingDao.TABLE_NAME,null,null);
            ContentValues values = new ContentValues();
            values.put(UerSettingDao.COLUMN_NAME_LOCATION,location);
            values.put(UerSettingDao.COLUMN_NAME_PROVINCE,pro);
            values.put(UerSettingDao.COLUMN_NAME_CITY,city);
            db.replace(UerSettingDao.TABLE_NAME,null,values);
        }
    }

    synchronized public String getPro(){
        String str = "";
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + UerSettingDao.TABLE_NAME, null);
            if (cursor.getColumnCount() == 0){
                return str;
            }
            cursor.moveToLast();
            try{
                str = cursor.getString(cursor.getColumnIndex(UerSettingDao.COLUMN_NAME_PROVINCE));
            }catch (Exception e){
                return str;
            }

            cursor.close();
        }
        return str;
    }

    synchronized public String getCity(){
        String str = "";
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + UerSettingDao.TABLE_NAME, null);
            if (cursor.getColumnCount() == 0){
                return str;
            }
            cursor.moveToLast();
            try{
                str = cursor.getString(cursor.getColumnIndex(UerSettingDao.COLUMN_NAME_CITY));
            }catch (Exception e){
                return str;
            }

            cursor.close();
        }
        return str;
    }

    synchronized public String getLocation(){
        String str = "";
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + UerSettingDao.TABLE_NAME, null);
            if (cursor.getColumnCount() == 0){
                return str;
            }
            cursor.moveToLast();
            try{
                str = cursor.getString(cursor.getColumnIndex(UerSettingDao.COLUMN_NAME_LOCATION));
            }catch (Exception e){
                return str;
            }

            cursor.close();
        }
        return str;
    }

    synchronized public void saveTodayWeather(TodayWeatherData today){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()){
            db.delete(WeatherDao.TABLE_NAME,null,null);
            ContentValues values = new ContentValues();

            values.put(WeatherDao.COLUMN_NAME_I12,today.getI12());
            values.put(WeatherDao.COLUMN_NAME_I14,today.getI14());
            values.put(WeatherDao.COLUMN_NAME_I15,today.getI15());
            values.put(WeatherDao.COLUMN_NAME_I22,today.getI22());
            values.put(WeatherDao.COLUMN_NAME_I24,today.getI24());
            values.put(WeatherDao.COLUMN_NAME_I25,today.getI25());
            values.put(WeatherDao.COLUMN_NAME_I32,today.getI32());
            values.put(WeatherDao.COLUMN_NAME_I34,today.getI34());
            values.put(WeatherDao.COLUMN_NAME_I35,today.getI35());

            db.replace(WeatherDao.TABLE_NAME,null,values);
        }
    }

    synchronized public void saveThreeWeather(ThreeWeather three){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (db.isOpen()){
            db.delete(ThreeWeatherDao.TABLE_NAME,null,null);
            ContentValues values = new ContentValues();
            values.put(ThreeWeatherDao.COLUMN_NAME_FA1,three.getOne().getFa());
            values.put(ThreeWeatherDao.COLUMN_NAME_FB1,three.getOne().getFb());
            values.put(ThreeWeatherDao.COLUMN_NAME_FC1,three.getOne().getFc());
            values.put(ThreeWeatherDao.COLUMN_NAME_FD1,three.getOne().getFd());
            values.put(ThreeWeatherDao.COLUMN_NAME_FE1,three.getOne().getFe());
            values.put(ThreeWeatherDao.COLUMN_NAME_FF1,three.getOne().getFf());
            values.put(ThreeWeatherDao.COLUMN_NAME_FG1,three.getOne().getFg());
            values.put(ThreeWeatherDao.COLUMN_NAME_FH1,three.getOne().getFh());
            values.put(ThreeWeatherDao.COLUMN_NAME_FI1,three.getOne().getFi());

            values.put(ThreeWeatherDao.COLUMN_NAME_FA2,three.getTwo().getFa());
            values.put(ThreeWeatherDao.COLUMN_NAME_FB2,three.getTwo().getFb());
            values.put(ThreeWeatherDao.COLUMN_NAME_FC2,three.getTwo().getFc());
            values.put(ThreeWeatherDao.COLUMN_NAME_FD2,three.getTwo().getFd());
            values.put(ThreeWeatherDao.COLUMN_NAME_FE2,three.getTwo().getFe());
            values.put(ThreeWeatherDao.COLUMN_NAME_FF2,three.getTwo().getFf());
            values.put(ThreeWeatherDao.COLUMN_NAME_FG2,three.getTwo().getFg());
            values.put(ThreeWeatherDao.COLUMN_NAME_FH2,three.getTwo().getFh());
            values.put(ThreeWeatherDao.COLUMN_NAME_FI2,three.getTwo().getFi());

            values.put(ThreeWeatherDao.COLUMN_NAME_FA3,three.getThree().getFa());
            values.put(ThreeWeatherDao.COLUMN_NAME_FB3,three.getThree().getFb());
            values.put(ThreeWeatherDao.COLUMN_NAME_FC3,three.getThree().getFc());
            values.put(ThreeWeatherDao.COLUMN_NAME_FD3,three.getThree().getFd());
            values.put(ThreeWeatherDao.COLUMN_NAME_FE3,three.getThree().getFe());
            values.put(ThreeWeatherDao.COLUMN_NAME_FF3,three.getThree().getFf());
            values.put(ThreeWeatherDao.COLUMN_NAME_FG3,three.getThree().getFg());
            values.put(ThreeWeatherDao.COLUMN_NAME_FH3,three.getThree().getFh());
            values.put(ThreeWeatherDao.COLUMN_NAME_FI3,three.getThree().getFi());

            values.put(ThreeWeatherDao.COLUMN_NAME_TIME,three.getTime());

            db.replace(ThreeWeatherDao.TABLE_NAME,null,values);
        }
    }

    synchronized public TodayWeatherData getTodayWeather(){
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen()){
            Cursor cursor = db.rawQuery("select * from " + WeatherDao.TABLE_NAME,null);
            if (cursor.getColumnCount() == 0){
                return null;
            }
            cursor.moveToLast();
            String i12 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I12));
            String i14 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I14));
            String i15 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I15));

            String i22 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I22));
            String i24 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I24));
            String i25 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I25));

            String i32 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I32));
            String i34 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I34));
            String i35 = cursor.getString(cursor.getColumnIndex(WeatherDao.COLUMN_NAME_I35));

            TodayWeatherData today = new TodayWeatherData(i12,i14,i15,i22,i24,i25,i32,i34,i35);
            cursor.close();
            return today;
        }
        return null;
    }

    synchronized public ThreeWeather getThreeWeather(){
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (db.isOpen())
        {
            Cursor cursor = db.rawQuery("select * from "+ ThreeWeatherDao.TABLE_NAME,null);
            if (cursor.getColumnCount() == 0){
                return null;
            }
            cursor.moveToLast();
            String fa1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FA1));
            String fb1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FB1));
            String fc1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FC1));
            String fd1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FD1));
            String fe1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FE1));
            String ff1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FF1));
            String fg1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FG1));
            String fh1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FH1));
            String fi1 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FI1));

            String fa2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FA2));
            String fb2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FB2));
            String fc2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FC2));
            String fd2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FD2));
            String fe2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FE2));
            String ff2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FF2));
            String fg2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FG2));
            String fh2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FH2));
            String fi2 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FI2));

            String fa3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FA3));
            String fb3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FB3));
            String fc3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FC3));
            String fd3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FD3));
            String fe3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FE3));
            String ff3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FF3));
            String fg3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FG3));
            String fh3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FH3));
            String fi3 = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_FI3));

            String time = cursor.getString(cursor.getColumnIndex(ThreeWeatherDao.COLUMN_NAME_TIME));

            ThreeWeather three = new ThreeWeather();
            DBImportDataManage managerData = new DBImportDataManage(context);
            managerData.openDatabase();
            boolean result = fb1.matches("[0-9]+");
            if (result){
                three.setWeather(new WeatherData(managerData.getAtmosphereName(fa1), managerData.getAtmosphereName(fb1),
                                fc1,fd1,managerData.getDirectionName(fe1),managerData.getDirectionName(ff1),managerData.getPowerName(fg1),managerData.getPowerName(fh1),fi1),
                        new WeatherData(managerData.getAtmosphereName(fa2), managerData.getAtmosphereName(fb2),
                                fc2,fd2,managerData.getDirectionName(fe2),managerData.getDirectionName(ff2),managerData.getPowerName(fg2),managerData.getPowerName(fh2),fi2),
                        new WeatherData(managerData.getAtmosphereName(fa3), managerData.getAtmosphereName(fb3),
                                fc3,fd3,managerData.getDirectionName(fe3),managerData.getDirectionName(ff3),managerData.getPowerName(fg3),managerData.getPowerName(fh3),fi3));

            }else {
                three.setWeather(new WeatherData(fa1,fb1,fc1,fd1,fe1,ff1,fg1,fh1,fi1),
                        new WeatherData(fa2,fb2,
                                fc2,fd2,(fe2),(ff2),(fg2),(fh2),fi2),
                        new WeatherData((fa3), (fb3),fc3,fd3,(fe3),(ff3),(fg3),(fh3),fi3));

            }

            three.setTime(time);

            cursor.close();
            return three;
        }
        return null;
    }
}
