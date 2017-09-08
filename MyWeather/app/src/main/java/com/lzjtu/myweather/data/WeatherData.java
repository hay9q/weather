package com.lzjtu.myweather.data;

/**
 * Created by haydd on 2016/5/5 0005.
 */
public class WeatherData {
    private String fa = "";
    private String fb = "";
    private String fc = "";
    private String fd = "";
    private String fe = "";
    private String ff = "";
    private String fg = "";
    private String fh = "";
    private String fi = "";

    public WeatherData(String fa,String fb,String fc,String fd,String fe,String ff,String fg,String fh,String fi){
        this.fa = fa;
        this.fb = fb;
        this.fc = fc;
        this.fd = fd;
        this.fe = fe;
        this.ff = ff;
        this.fg = fg;
        this.fh = fh;
        this.fi = fi;
    }

    public String toString(){
        return fa + fb + fc + fd;
    }

    public String getFa() {
        if (fa == null){
            return "";
        }
        return fa;
    }

    public String getFb() {
        if (fb == null){
            return "";
        }
        return fb;
    }

    public String getFc() {
        if (fc == null){
            return "";
        }
        return fc;
    }

    public String getFd() {
        if (fd == null){
            return "";
        }
        return fd;
    }

    public String getFe() {
        if (fe == null){
            return "";
        }
        return fe;
    }

    public String getFf() {
        if (ff == null){
            return "";
        }
        return ff;
    }

    public String getFg() {
        if (fg == null){
            return "";
        }
        return fg;
    }

    public String getFh() {
        if (fh == null){
            return "";
        }
        return fh;
    }

    public String getFi() {
        if (fi == null){
            return "";
        }
        return fi;
    }
}
