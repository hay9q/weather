package com.lzjtu.myweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzjtu.myweather.data.TodayWeatherData;
import com.lzjtu.myweather.data.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DetailActivity extends ActionBarActivity {

    private ImageView mIconView;
    private TextView mFriendlyDateView;
    private TextView mDateView;
    private TextView mDescriptionView;
    private TextView mHighTempView;
    private TextView mLowTempView;
    private TextView mHumidityView;
    private TextView mWindView;
    private TextView mPressureView;
    private TextView mSuggest1;
    private TextView mSuggest2;
    private TextView mSuggest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIconView = (ImageView)findViewById(R.id.detail_icon);
        mDateView = (TextView) findViewById(R.id.detail_date_textview);
        mFriendlyDateView = (TextView) findViewById(R.id.detail_day_textview);
        mDescriptionView = (TextView) findViewById(R.id.detail_forecast_textview);
        mHighTempView = (TextView) findViewById(R.id.detail_high_textview);
        mLowTempView = (TextView) findViewById(R.id.detail_low_textview);
        mHumidityView = (TextView) findViewById(R.id.detail_humidity_textview);
        mWindView = (TextView) findViewById(R.id.detail_wind_textview);
        mPressureView = (TextView) findViewById(R.id.detail_pressure_textview);

        mSuggest1 = (TextView)findViewById(R.id.suggest1);
        mSuggest2 = (TextView)findViewById(R.id.suggest2);
        mSuggest3 = (TextView)findViewById(R.id.suggest3);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
//        Date date = new Date();

        WeatherData data = Utility.list.get(position);

        if (position == 0){
            mSuggest1.setVisibility(View.VISIBLE);
            mSuggest2.setVisibility(View.VISIBLE);
            mSuggest3.setVisibility(View.VISIBLE);

            TodayWeatherData today = Utility.data;
            String time = dateFormat.format(getDate(position));
            mFriendlyDateView.setText(time);
            mDateView.setText("Today");
            if (data.getFa().equals("")){ //晚上
                mDescriptionView.setText(data.getFb());
                mIconView.setImageResource(Utility.getArtResourceForWeatherCondition(data.getFb()));
            }else {
                mDescriptionView.setText(data.getFa());
                mIconView.setImageResource(Utility.getArtResourceForWeatherCondition(data.getFa()));
            }
            mHumidityView.setText(today.getI12()+"："+today.getI14());
            mWindView.setText(today.getI22()+"："+today.getI24());
            mPressureView.setText(today.getI32()+"："+today.getI34());
            mSuggest1.setText(today.getI15());
            mSuggest2.setText(today.getI25());
            mSuggest3.setText(today.getI35());
            mHighTempView.setText(data.getFc()+"℃");  //晚上没有白天温度
            mLowTempView.setText(data.getFd()+"℃");
        }else if (position > 0 && position <3){

            mSuggest1.setVisibility(View.GONE);
            mSuggest2.setVisibility(View.GONE);
            mSuggest3.setVisibility(View.GONE);

            mDateView.setText(getWeek(position));

            mFriendlyDateView.setText(dateFormat.format(getDate(position)));

            mHighTempView.setText(data.getFc()+"℃");
            mLowTempView.setText(data.getFd()+"℃");

            mDescriptionView.setText(data.getFa());
            mHumidityView.setText("风向："+data.getFf());
            mPressureView.setText("风力："+data.getFh());
            mWindView.setText("日出日落："+data.getFi());
            mIconView.setImageResource(Utility.getArtResourceForWeatherCondition(data.getFa()));
        }else{
            mSuggest1.setVisibility(View.GONE);
            mSuggest2.setVisibility(View.GONE);
            mSuggest3.setVisibility(View.GONE);

            mDateView.setText(getWeek(position));
            mFriendlyDateView.setText(dateFormat.format(getDate(position)));
            mHighTempView.setText("HIGH");
            mLowTempView.setText("LOW");
            mDescriptionView.setText("无");
            mHumidityView.setText("APP使用中国天气网所提供的接口");
            mPressureView.setText("该接口只提供了三天的天气数据");
            mWindView.setText("建议：想得到更多的天气数据，换个天气接口（网上会有很多）");
            mIconView.setImageResource(R.drawable.no);
        }


    }

    public Date getDate(int position){
        Date date = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(c.DATE,position);
        date = c.getTime();
        return date;
    }


    public String getWeek(int position){
        Calendar c;
        c = Calendar.getInstance();
        int todayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String time = "";
        switch ((todayOfWeek+position)%7){
            case 1:
                time = "星期天";
                break;
            case 2:
                time = "星期一";
                break;
            case 3:
                time = "星期二";
                break;
            case 4:
                time = "星期三";
                break;
            case 5:
                time = "星期四";
                break;
            case 6:
                time = "星期五";
                break;
            case 0:
                time = "星期六";
                break;
        }
        return time;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
