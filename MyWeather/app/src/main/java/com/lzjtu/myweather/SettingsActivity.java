package com.lzjtu.myweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lzjtu.myweather.data.DBImportManager;
import com.lzjtu.myweather.data.UerSettingDao;

public class SettingsActivity extends ActionBarActivity {

    public DBImportManager manager;
    private Spinner province;
    private Spinner city;
    private int pro_id;
    private int city_id;
    private UerSettingDao setting;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setting = new UerSettingDao(getApplication());
        String ss = setting.getPro();
        if (ss.equals("")){
            pro_id = 15;
            city_id = 0;
        }else {
            pro_id = Integer.parseInt(ss);
            city_id = Integer.parseInt(setting.getCity());
        }


        button = (Button)findViewById(R.id.okbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.saveLocation(Utility.cityNum,pro_id+"",city_id+"");
            }
        });

        //导入数据库
        manager = new DBImportManager(this);
        manager.openDatabase();

        province = (Spinner)findViewById(R.id.provinces);
        ArrayAdapter<String> pro_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,manager.getProSet());
        province.setAdapter(pro_adapter);
        province.setSelection(pro_id);
        province.setOnItemSelectedListener(new SelectProvince());
        city=(Spinner)findViewById(R.id.city);
        city.setOnItemSelectedListener(new SelectCity());

    }

    //选择改变状态
    class SelectProvince implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {

            pro_id = position;
            city.setAdapter(getAdapter());
            city.setSelection(city_id);
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    //城市 选择改变状态
    class SelectCity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            Utility.cityNum = manager.getCityNum(position,pro_id);
            city_id = position;
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    /**
     * 返回适配器
     */
    public ArrayAdapter<String> getAdapter(){
        ArrayAdapter<String> adapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,manager.getcitySet(pro_id));
        return adapter1;
    }
}
