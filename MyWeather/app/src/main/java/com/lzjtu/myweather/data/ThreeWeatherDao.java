package com.lzjtu.myweather.data;

import android.content.Context;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class ThreeWeatherDao {

    public static final String TABLE_NAME = "three";
    public static final String COLUMN_NAME_FA1 = "fa1";
    public static final String COLUMN_NAME_FB1 = "fb1";
    public static final String COLUMN_NAME_FC1 = "fc1";
    public static final String COLUMN_NAME_FD1 = "fd1";
    public static final String COLUMN_NAME_FE1 = "fe1";
    public static final String COLUMN_NAME_FF1 = "ff1";
    public static final String COLUMN_NAME_FG1 = "fg1";
    public static final String COLUMN_NAME_FH1 = "fh1";
    public static final String COLUMN_NAME_FI1 = "fi1";

    public static final String COLUMN_NAME_FA2 = "fa2";
    public static final String COLUMN_NAME_FB2 = "fb2";
    public static final String COLUMN_NAME_FC2 = "fc2";
    public static final String COLUMN_NAME_FD2 = "fd2";
    public static final String COLUMN_NAME_FE2 = "fe2";
    public static final String COLUMN_NAME_FF2 = "ff2";
    public static final String COLUMN_NAME_FG2 = "fg2";
    public static final String COLUMN_NAME_FH2 = "fh2";
    public static final String COLUMN_NAME_FI2 = "fi2";

    public static final String COLUMN_NAME_FA3 = "fa3";
    public static final String COLUMN_NAME_FB3 = "fb3";
    public static final String COLUMN_NAME_FC3 = "fc3";
    public static final String COLUMN_NAME_FD3 = "fd3";
    public static final String COLUMN_NAME_FE3 = "fe3";
    public static final String COLUMN_NAME_FF3 = "ff3";
    public static final String COLUMN_NAME_FG3 = "fg3";
    public static final String COLUMN_NAME_FH3 = "fh3";
    public static final String COLUMN_NAME_FI3 = "fi3";

    public static final String COLUMN_NAME_TIME = "time";

    public ThreeWeatherDao(Context context){
        DBManager.getInstance().onInit(context);
    }

    public void saveThreeData(ThreeWeather datas){
        DBManager.getInstance().saveThreeWeather(datas);
    }

    public ThreeWeather getThreeWeather(){
        return DBManager.getInstance().getThreeWeather();
    }
}
