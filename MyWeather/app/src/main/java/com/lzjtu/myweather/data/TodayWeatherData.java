package com.lzjtu.myweather.data;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class TodayWeatherData {
    private String i12 = "";
    private String i14 = "";
    private String i15 = "";
    private String i22 = "";
    private String i24 = "";
    private String i25 = "";
    private String i32 = "";
    private String i34 = "";
    private String i35 = "";
    String str = "网络慢，请重试！";

    public TodayWeatherData(){

    }

    public TodayWeatherData(String i12, String i14, String i15, String i22, String i24, String i25, String i32, String i34, String i35) {
        this.i12 = i12;
        this.i14 = i14;
        this.i15 = i15;
        this.i22 = i22;
        this.i24 = i24;
        this.i25 = i25;
        this.i32 = i32;
        this.i34 = i34;
        this.i35 = i35;
    }

    public String toString(){
        return this.i12+":"+i14+"---"+i15+"\n"
                +this.i22+":"+i24+"---"+i25+"\n"
                +this.i32+":"+i34+"---"+i35+"\n";
    }

    public String getI12() {
        if (i12 == null){
            return str;
        }
        return i12;
    }

    public void setI12(String i12) {
        this.i12 = i12;
    }

    public String getI14() {
        if (i14 == null){
            return str;
        }
        return i14;
    }

    public void setI14(String i14) {
        this.i14 = i14;
    }

    public String getI15() {
        if (i15 == null){
            return str;
        }
        return i15;
    }

    public void setI15(String i15) {
        this.i15 = i15;
    }

    public String getI22() {
        if (i22 == null){
            return str;
        }
        return i22;
    }

    public void setI22(String i22) {
        this.i22 = i22;
    }

    public String getI24() {
        if (i24 == null){
            return str;
        }
        return i24;
    }

    public void setI24(String i24) {
        this.i24 = i24;
    }

    public String getI25() {
        if (i25 == null){
            return str;
        }
        return i25;
    }

    public void setI25(String i25) {
        this.i25 = i25;
    }

    public String getI32() {
        if (i32 == null){
            return str;
        }
        return i32;
    }

    public void setI32(String i32) {
        this.i32 = i32;
    }

    public String getI34() {
        if (i34 == null){
            return str;
        }
        return i34;
    }

    public void setI34(String i34) {
        this.i34 = i34;
    }

    public String getI35() {
        if (i35 == null){
            return str;
        }
        return i35;
    }

    public void setI35(String i35) {
        this.i35 = i35;
    }
}
