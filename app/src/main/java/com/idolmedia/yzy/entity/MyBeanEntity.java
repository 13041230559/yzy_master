package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/5 18:49
 * 描述：  我的娱豆
 */

public class MyBeanEntity implements Parcelable {
    /**
     * money_no : 88.00
     * msg : 查询成功
     * datas : [{"money_no":2,"creat_time":"2017-11-25 21:08:57","virtualuser_id":4267,"description":"参与活动","moneyhistory_id":10,"money_type":"-"},{"money_no":1,"creat_time":"2017-11-20 21:08:57","virtualuser_id":4267,"description":"消费","moneyhistory_id":6,"money_type":"-"}]
     * resultCode : 01
     */

    private String money_no;
    private String msg;
    private int resultCode;
    private List<DatasBean> datas;

    public String getMoney_no() {
        return money_no;
    }

    public void setMoney_no(String money_no) {
        this.money_no = money_no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * money_no : 2.0
         * creat_time : 2017-11-25 21:08:57
         * virtualuser_id : 4267
         * description : 参与活动
         * moneyhistory_id : 10
         * money_type : -
         */

        private double money_no;
        private String creat_time;
        private int virtualuser_id;
        private String description;
        private int moneyhistory_id;
        private String money_type;

        public double getMoney_no() {
            return money_no;
        }

        public void setMoney_no(double money_no) {
            this.money_no = money_no;
        }

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

        public int getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(int virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getMoneyhistory_id() {
            return moneyhistory_id;
        }

        public void setMoneyhistory_id(int moneyhistory_id) {
            this.moneyhistory_id = moneyhistory_id;
        }

        public String getMoney_type() {
            return money_type;
        }

        public void setMoney_type(String money_type) {
            this.money_type = money_type;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.money_no);
        dest.writeString(this.msg);
        dest.writeInt(this.resultCode);
        dest.writeList(this.datas);
    }

    public MyBeanEntity() {
    }

    protected MyBeanEntity(Parcel in) {
        this.money_no = in.readString();
        this.msg = in.readString();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<MyBeanEntity> CREATOR = new Parcelable.Creator<MyBeanEntity>() {
        @Override
        public MyBeanEntity createFromParcel(Parcel source) {
            return new MyBeanEntity(source);
        }

        @Override
        public MyBeanEntity[] newArray(int size) {
            return new MyBeanEntity[size];
        }
    };
}
