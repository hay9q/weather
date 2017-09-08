package com.lzjtu.myweather.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static DbOpenHelper instance;

    private static final String TODAY_TABLE_CREATE = "CREATE TABLE "
            + WeatherDao.TABLE_NAME + " ("
            + WeatherDao.COLUMN_NAME_I12 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I14 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I15 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I22 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I24 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I25 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I32 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I34 + " TEXT, "
            + WeatherDao.COLUMN_NAME_I35 + " TEXT, "
            + WeatherDao.COLUMN_NAME_TIME + " TEXT PRIMARY KEY); ";

    private static final String THREE_TABLE_CREATE = "CREATE TABLE "
            + ThreeWeatherDao.TABLE_NAME + " ("
            + ThreeWeatherDao.COLUMN_NAME_FA1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FB1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FC1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FD1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FE1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FF1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FG1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FH1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FI1 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FA2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FB2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FC2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FD2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FE2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FF2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FG2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FH2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FI2 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FA3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FB3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FC3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FD3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FE3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FF3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FG3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FH3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_FI3 + " TEXT, "
            + ThreeWeatherDao.COLUMN_NAME_TIME + " TEXT PRIMARY KEY); ";

    private static final String SETTING_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + UerSettingDao.TABLE_NAME + " ("
            + UerSettingDao.COLUMN_NAME_PROVINCE + " TEXT, "
            + UerSettingDao.COLUMN_NAME_CITY + " TEXT, "
            + UerSettingDao.COLUMN_NAME_LOCATION + " TEXT); ";

    private DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
    }

    public static DbOpenHelper getInstance(Context context){
        if (instance == null){
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TODAY_TABLE_CREATE);
        db.execSQL(THREE_TABLE_CREATE);
        db.execSQL(SETTING_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static String getUserDatabaseName() {
        return  "myWeather_demo.db";
    }

    public void closeDB()
    {
        if (instance != null){
            try{
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
