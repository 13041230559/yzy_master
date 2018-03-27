package com.idolmedia.yzy.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/1.
 */

public class Countryentity implements Serializable {
    private String countryname;


    public Countryentity(int id, String countryname) {
        this.countrycode = id;
        this.countryname = countryname;
    }
    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public int getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(int countrycode) {
        this.countrycode = countrycode;
    }

    private int countrycode;

    @Override
    public String toString() {
        //重写该方法，作为选择器显示的名称
        return countryname;
    }
}
