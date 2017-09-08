package com.lzjtu.myweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzjtu.myweather.data.WeatherData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends ArrayAdapter<WeatherData>{

    private Context context;
    private List<WeatherData> copyList;
    private List<WeatherData> list;
    private LayoutInflater layoutInflater;
    private int res;

    public ForecastAdapter(Context context, int resource, List<WeatherData> objects){
        super(context,resource,objects);
        this.res = resource;
        copyList = new ArrayList<WeatherData>();
        this.list = objects;
        this.context = context;
        copyList.addAll(objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public WeatherData getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        convertView = layoutInflater.inflate(res,null);
        holder = new ViewHolder(convertView);
        final WeatherData data = copyList.get(position);
        if (position != 0){
            String fa = data.getFa();
            String fc = data.getFc();
            String fd = data.getFd();
            holder.descriptionView.setText(fa);
            holder.highTempView.setText(fc);
            holder.lowTempView.setText(fd);
            if (!fa.equals("")){
                holder.iconView.setImageResource(Utility.getIconResourceForWeatherCondition(fa));
            }

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
            holder.dateView.setText(time);
        }else {
            convertView = layoutInflater.inflate(R.layout.list_item_forecast_today,null);
            TextView date = (TextView) convertView.findViewById(R.id.list_item_date_textview);
            TextView high = (TextView) convertView.findViewById(R.id.list_item_high_textview);
            TextView low = (TextView) convertView.findViewById(R.id.list_item_low_textview);
            TextView atmo = (TextView) convertView.findViewById(R.id.list_item_forecast_textview);
            ImageView ico = (ImageView)convertView.findViewById(R.id.list_item_icon);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
            String time = dateFormat.format(new Date());

            date.setText("Today "+ time);
            high.setText(data.getFc()+"℃");
            low.setText(data.getFd()+"℃");
            if (data.getFa().equals("")){
                ico.setImageResource(Utility.getArtResourceForWeatherCondition(data.getFb()));
                atmo.setText(data.getFb());
            }else {
                ico.setImageResource(Utility.getArtResourceForWeatherCondition(data.getFa()));
                atmo.setText(data.getFa());
            }

        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        copyList.clear();
        copyList.addAll(list);
    }

    public static class ViewHolder {
        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        public final TextView highTempView;
        public final TextView lowTempView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }
    }

}