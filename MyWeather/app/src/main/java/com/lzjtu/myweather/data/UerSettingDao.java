package com.lzjtu.myweather.data;

import android.content.Context;

/**
 * Created by haydd on 2016/5/7 0007.
 */
public class UerSettingDao {

    public static final String TABLE_NAME = "setting";
    public static final String COLUMN_NAME_LOCATION = "location";
    public static final String COLUMN_NAME_PROVINCE = "province";
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_NOTIFICATION = "notificationS";

    public UerSettingDao(Context context){
        DBManager.getInstance().onInit(context);
    }

    public void saveLocation(String location,String pro,String city){
        DBManager.getInstance().saveLocation(location,pro,city);
    }

    public String getLocation(){
        return DBManager.getInstance().getLocation();
    }

    public String getPro(){
        return DBManager.getInstance().getPro();
    }

    public String getCity(){
        return DBManager.getInstance().getCity();
    }

}
