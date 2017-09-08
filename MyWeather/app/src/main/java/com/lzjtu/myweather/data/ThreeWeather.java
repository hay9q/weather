package com.lzjtu.myweather.data;

import com.lzjtu.myweather.data.WeatherData;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class ThreeWeather {

    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;
    private String c8;
    private String c9;
    private String c10;
    private String c11;
    private String c12;
    private String c13;
    private String c14;
    private String c15;
    private String c16;
    private String c17;

    private WeatherData one;
    private WeatherData two;
    private WeatherData three;

    public WeatherData getFour() {
        return four;
    }

    public WeatherData getFive() {
        return five;
    }

    public WeatherData getSix() {
        return six;
    }

    public WeatherData getSeven() {
        return seven;
    }

    private WeatherData four;
    private WeatherData five;
    private WeatherData six;
    private WeatherData seven;

    private String time;

    public ThreeWeather(){
        setNoWeather();

    }

    public void setBaseData(String c1,String c2,String c3,String c4,String c5,String c6,String c7,String c8,String c9,
    String c10,String c11,String c12,String c13,String c14,String c15,String c16,String c17)
    {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.c9 = c9;
        this.c10 = c10;
        this.c11 = c11;
        this.c12 = c12;
        this.c13 = c13;
        this.c14 = c14;
        this.c15 = c15;
        this.c16 = c16;
        this.c17 = c17;
    }

    public void setWeather(WeatherData one,WeatherData two,WeatherData three){
        this.one = one;
        this.two = two;
        this.three = three;
    }

    //本APP使用的接口只能得到3天的天气数据，为了界面的布局能更加丰富，这里创建了多4天的空数据。
    //可忽略，本人不建议使用该接口，数据太少。
    public void setNoWeather(){
        this.four = new WeatherData("","","","","","","","","");
        this.five = new WeatherData("","","","","","","","","");
        this.six = new WeatherData("","","","","","","","","");
        this.seven = new WeatherData("","","","","","","","","");
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC3() {
        return c3;
    }

    public String getC4() {
        return c4;
    }

    public String getC5() {
        return c5;
    }

    public String getC6() {
        return c6;
    }

    public String getC7() {
        return c7;
    }

    public String getC8() {
        return c8;
    }

    public String getC9() {
        return c9;
    }

    public String getC10() {
        return c10;
    }

    public String getC11() {
        return c11;
    }

    public String getC12() {
        return c12;
    }

    public String getC13() {
        return c13;
    }

    public String getC14() {
        return c14;
    }

    public String getC15() {
        return c15;
    }

    public String getC16() {
        return c16;
    }

    public String getC17() {
        return c17;
    }

    public WeatherData getOne() {
        return one;
    }

    public WeatherData getTwo() {
        return two;
    }

    public WeatherData getThree() {
        return three;
    }

    public String toString(){
        return c7 + c3 + "\n" + one.toString() + "\n"
                +two.toString() + "\n" +three.toString();
    }
}
