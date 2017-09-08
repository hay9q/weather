package com.lzjtu.myweather.data;

import android.util.Base64;

import com.lzjtu.myweather.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class GetData {

    private static final String AppId = "94ae050db166d58e";
    private static final String Private_Key = "b10c6a_SmartWeatherAPI_3977792";
    private static final String url_head = "http://open.weather.com.cn/data/?";
//    private String areaid = "101160101";
    //指数常规接口
    private String type1 = "index_v";
    //指数基础接口
    private String type2 = "index_f";
    //3天预报基础接口
    private String type3 = "forecast_f";
    //3天预报常规接口
    private  String type4 = "forecast_v";
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private String weatherDatas;

    public GetData(){
//        createJson();
        getTodayWeatherData(getJSonData(getUrl(type2)));
        getThreeWeatherData(getJSonData(getUrl(type3)));
    }

    /**
     * 已经通过测试。得到正确的JSON数据。
     * @param url
     */

    public String getJSonData(String url)
    {
        InputStream inputStream;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        try
        {
            URL weatherUrl = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) weatherUrl.openConnection();
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                inputStream = httpConnection.getInputStream();
                while((len = inputStream.read(data)) != -1){
                    outputStream.write(data,0,len);
                }
                weatherDatas = new String(outputStream.toByteArray());
                inputStream.close();
                outputStream.close();
                return weatherDatas;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试数据
     */

    public void createJson()
    {
        try{
            //创建当天天气数据
            JSONObject weather = new JSONObject();
            JSONArray array = new JSONArray();
            JSONObject one = new JSONObject();
            one.put("i1","c1");
            one.put("i2","晨练指数");
            one.put("i3","");
            one.put("i4","适宜");
            one.put("i5","天气不错，空气清新，是您晨练的大好时机，建议不同年龄段的人们积极参加户外健身活动。");

            JSONObject two = new JSONObject();
            two.put("i1","co");
            two.put("i2","舒适度指数");
            two.put("i3","");
            two.put("i4","较不舒适");
            two.put("i5","白天天气晴好，明媚的阳光在给您带来好心情的同时，也会使您感到有些热，不很舒适。");

            JSONObject three = new JSONObject();
            three.put("i1","ct");
            three.put("i2","穿衣指数");
            three.put("i3","");
            three.put("i4","热");
            three.put("i5","天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。");

            array.put(one).put(two).put(three);
            weather.put("i",array);

            getTodayWeatherData(weather.toString());

            //创建3天天气数据
            JSONObject weatherThree = new JSONObject();
            JSONObject addC = new JSONObject();
            addC.put("c1","101160101");
            addC.put("c2","lanzhou");
            addC.put("c3","兰州");
            addC.put("c4","lanzhou");
            addC.put("c5","兰州");
            addC.put("c6","gansu");
            addC.put("c7","甘肃");
            addC.put("c8","china");
            addC.put("c9","中国");
            addC.put("c10","1");
            addC.put("c11","0931");
            addC.put("c12","730000");
            addC.put("c13",103.751000);
            addC.put("c14",36.068000);
            addC.put("c15","1518");
            addC.put("c16","AZ9931");
            addC.put("c17","+8");

            JSONObject addF = new JSONObject();
            JSONArray array3 = new JSONArray();
            JSONObject oneWeather = new JSONObject();
            oneWeather.put("fa","").put("fb","00").put("fc","").put("fd","13").put("fe","")
                    .put("ff","0").put("fg","").put("fh","0").put("fi","06:08|19:54");
            JSONObject twoWeather = new JSONObject();
            twoWeather.put("fa","00").put("fb","01").put("fc","25").put("fd","12").put("fe","0")
                    .put("ff","0").put("fg","0").put("fh","0").put("fi","06:07|19:55");
            JSONObject threeWeather = new JSONObject();
            threeWeather.put("fa","01").put("fb","03").put("fc","21").put("fd","11").put("fe","0")
                    .put("ff","0").put("fg","0").put("fh","0").put("fi","06:06|19:56");

            array3.put(oneWeather).put(twoWeather).put(threeWeather);
            addF.put("f1",array3);
            addF.put("f0","201605041100");

            weatherThree.put("c",addC);
            weatherThree.put("f",addF);

            getThreeWeatherData(weatherThree.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 解析3天天气数据
     * @param str  接口返回的JSON数据
     */

    public void getThreeWeatherData(String str)
    {
        WeatherData[] datas = new WeatherData[3];
        int i = 0;
        try
        {
            JSONObject json = new JSONObject(str);
            JSONObject getC = json.getJSONObject("c");
            (Utility.threeData).setBaseData(
                    getC.getString("c1"),getC.getString("c2"),getC.getString("c3"), getC.getString("c4"),
                    getC.getString("c5"),getC.getString("c6"),getC.getString("c7"),getC.getString("c8"),
                    getC.getString("c9"),getC.getString("c10"),getC.getString("c11"),getC.getString("c12"),
                    getC.getString("c13"),getC.getString("c14"), getC.getString("c15"),getC.getString("c16"),
                    getC.getString("c17"));
            JSONObject getf = json.getJSONObject("f");
            //3天天气数据
            JSONArray getWeatherThree = getf.getJSONArray("f1");
            while (i<3){
                JSONObject oneObj = getWeatherThree.getJSONObject(i);
                datas[i] = new WeatherData(oneObj.getString("fa"),oneObj.getString("fb"),oneObj.getString("fc"),
                        oneObj.getString("fd"),oneObj.getString("fe"),oneObj.getString("ff"),
                        oneObj.getString("fg"),oneObj.getString("fh"),oneObj.getString("fi"));
                i++;
            }

            Utility.threeData.setWeather(datas[0],datas[1],datas[2]);
            Utility.threeData.setTime(getf.getString("f0"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 解析当天天气数据
     * @param str  返回的JSON数据
     */

    public void getTodayWeatherData(String str)
    {
        try
        {
            JSONArray json = new JSONObject(str).getJSONArray("i");
            Utility.data = new TodayWeatherData((
                    json.getJSONObject(0)).getString("i2"),(json.getJSONObject(0)).getString("i4"),
                    (json.getJSONObject(0)).getString("i5"), (json.getJSONObject(1)).getString("i2"),
                    (json.getJSONObject(1)).getString("i4"),(json.getJSONObject(1)).getString("i5"),
                    (json.getJSONObject(2)).getString("i2"),(json.getJSONObject(2)).getString("i4"),
                    (json.getJSONObject(2)).getString("i5"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return  得到最终的URL
     */

    public String getUrl(String type)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String date = dateFormat.format(new Date());
        String url = "";
        try {
            //需要加密的数据
            String data = url_head + "areaid=" + Utility.cityNum + "&type=" + type + "&date=" + date + "&appid=";
            String key = getKey(data + AppId, Private_Key);
            String appid6 = AppId.substring(0,6);
            url = data + appid6 + "&key=" + key;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    private static byte[] HmacSHA1Encrypt(String url, String privatekey)
            throws Exception {
        byte[] data = privatekey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = url.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }

    /**
     * 获取加密后的key值
     * @param url  不完整的URL
     * @param privatekey  私钥
     * @return  key值
     * @throws Exception
     */

    private static String getKey(String url, String privatekey) throws Exception {
        byte[] key_bytes = HmacSHA1Encrypt(url, privatekey);
        String base64encoderStr = Base64.encodeToString(key_bytes, Base64.NO_WRAP);
        return URLEncoder.encode(base64encoderStr, ENCODING);
    }
}
