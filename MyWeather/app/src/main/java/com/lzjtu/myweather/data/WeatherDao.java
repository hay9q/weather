package com.lzjtu.myweather.data;

import android.content.Context;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class WeatherDao {

    public static final String TABLE_NAME = "today";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_I12 = "i12";
    public static final String COLUMN_NAME_I14 = "i14";
    public static final String COLUMN_NAME_I15 = "i15";
    public static final String COLUMN_NAME_I22 = "i22";
    public static final String COLUMN_NAME_I24 = "i24";
    public static final String COLUMN_NAME_I25 = "i25";
    public static final String COLUMN_NAME_I32 = "i32";
    public static final String COLUMN_NAME_I34 = "i34";
    public static final String COLUMN_NAME_I35 = "i35";

    public WeatherDao(Context context){
        DBManager.getInstance().onInit(context);
    }

    public void saveWeatherData(TodayWeatherData data){
        DBManager.getInstance().saveTodayWeather(data);
    }

    public TodayWeatherData getTodayWeather(){
        return DBManager.getInstance().getTodayWeather();
    }

}
