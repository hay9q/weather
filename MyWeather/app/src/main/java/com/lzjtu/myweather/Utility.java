package com.lzjtu.myweather;

import com.lzjtu.myweather.data.ThreeWeather;
import com.lzjtu.myweather.data.TodayWeatherData;
import com.lzjtu.myweather.data.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class Utility {

    public static TodayWeatherData data;
    public static ThreeWeather threeData = new ThreeWeather();
    public static List<WeatherData> list = new ArrayList<>();
    public static String cityNum = "101160101";

    public static int getIconResourceForWeatherCondition(String fa) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (fa.contains("暴") && fa.contains("雨")) {
            return R.drawable.ic_storm;
        } else if (fa.contains("雷") && fa.contains("雨")) {
            return R.drawable.ic_light_rain;
        } else if (fa.contains("雨")) {
            return R.drawable.ic_rain;
        } else if (fa.contains("雪")) {
            return R.drawable.ic_snow;
        } else if (fa.contains("雾") || fa.equals("沙")) {
            return R.drawable.ic_fog;
        } else if (fa.equals("晴")) {
            return R.drawable.ic_clear;
        } else if (fa.equals("多云")) {
            return R.drawable.ic_cloudy;
        }
        return -1;
    }

    public static int getArtResourceForWeatherCondition(String fa) {
        if (fa.contains("暴") && fa.contains("雨")) {
            return R.drawable.art_storm;
        } else if (fa.contains("雷") && fa.contains("雨")) {
            return R.drawable.art_light_rain;
        } else if (fa.contains("雨")) {
            return R.drawable.art_rain;
        } else if (fa.contains("雪")) {
            return R.drawable.art_snow;
        } else if (fa.contains("雾") || fa.equals("沙")) {
            return R.drawable.art_fog;
        } else if (fa.equals("晴")) {
            return R.drawable.art_clear;
        } else if (fa.equals("多云")) {
            return R.drawable.art_clouds;
        } else {
            return R.drawable.no;
        }
    }

}
