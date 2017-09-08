package com.lzjtu.myweather;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzjtu.myweather.data.GetData;
import com.lzjtu.myweather.data.ThreeWeatherDao;
import com.lzjtu.myweather.data.UerSettingDao;
import com.lzjtu.myweather.data.WeatherDao;
import com.lzjtu.myweather.data.WeatherData;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private GetData data;
    private WeatherDao today;
    private ThreeWeatherDao three;
    private UerSettingDao setting;

    private ListView mListView;
    private ForecastAdapter adapter;
    private boolean flag = false;
    private String oldNum;

    private List<WeatherData> list;
    private boolean internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internet = isInternet();
        flag = test();
        if (flag){
            mListView = (ListView)findViewById(R.id.listview_forecast);
            adapter = new ForecastAdapter(this,R.layout.list_item_forecast,list);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });
        }else {
            TextView textView = (TextView)findViewById(R.id.internet);
            textView.setVisibility(View.VISIBLE);
            textView.setText("无网络");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStart(){
        super.onStart();
        if (!Utility.cityNum.equals(oldNum)){
            test();
            refresh();
        }
    }

    protected void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isAvailable())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean test(){

        today = new WeatherDao(this);
        three = new ThreeWeatherDao(this);
        setting = new UerSettingDao(this);
        String num = setting.getLocation();
        if (!num.equals(""))
        {
            Utility.cityNum = num;
            oldNum = Utility.cityNum;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                data = new GetData();
            }
        };

        if (internet){
            try{
                Thread thread = new Thread(runnable);
                thread.start();
                thread.join();
                today.saveWeatherData(Utility.data);
                three.saveThreeData(Utility.threeData);
                //将天气编号修改为天气描述
                Utility.data = today.getTodayWeather();
                Utility.threeData = three.getThreeWeather();
            }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }else {
            Utility.data = today.getTodayWeather();
            Utility.threeData = three.getThreeWeather();
            Toast.makeText(this,"无网络连接。。。",Toast.LENGTH_LONG).show();
            if (Utility.data == null){
                return false;
            }
        }

/*        while(flag){
            try{
                Utility.data = today.getTodayWeather();
                Utility.threeData = three.getThreeWeather();
            }catch (Exception e){
                Toast.makeText(this,"请稍后，网络慢",Toast.LENGTH_SHORT).show();
            }

            flag = false;
        }*/

        Utility.list.clear();

        Utility.list.add(Utility.threeData.getOne());
        Utility.list.add(Utility.threeData.getTwo());
        Utility.list.add(Utility.threeData.getThree());
        Utility.list.add(Utility.threeData.getFour());
        Utility.list.add(Utility.threeData.getFive());
        Utility.list.add(Utility.threeData.getSix());
        Utility.list.add(Utility.threeData.getSeven());
        list = Utility.list;

        return true;
    }

    // 刷新ui
    public void refresh() {
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
