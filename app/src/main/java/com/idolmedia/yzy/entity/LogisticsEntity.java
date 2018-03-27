package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/18 18:54
 * 描述：  物流
 */

public class LogisticsEntity implements Parcelable {


    /**
     * msg : 查询成功
     * expressInfo : 一直娱温馨提示：已经通知快递员打包发货，稍后便会有快递轨迹
     * datas : {"catalog_id":"139","catalog_img":"http://otkny7iog.bkt.clouddn.com/FlFlSLK7TwauScbVdCbm0Ad6Po4k","shippercode":"YD","express_time":"2018-03-09 17:38:38","shipperName":"韵达快递","order_num":"yzy2018030917361043301","express_no":"3101658429558"}
     * resultCode : 01
     */

    private String msg;
    private String expressInfo;
    private DatasBean datas;
    private int resultCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExpressInfo() {
        return expressInfo;
    }

    public void setExpressInfo(String expressInfo) {
        this.expressInfo = expressInfo;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public static class DatasBean {
        /**
         * catalog_id : 139
         * catalog_img : http://otkny7iog.bkt.clouddn.com/FlFlSLK7TwauScbVdCbm0Ad6Po4k
         * shippercode : YD
         * express_time : 2018-03-09 17:38:38
         * shipperName : 韵达快递
         * order_num : yzy2018030917361043301
         * express_no : 3101658429558
         */

        private String catalog_id;
        private String catalog_img;
        private String shippercode;
        private String express_time;
        private String shipperName;
        private String order_num;
        private String express_no;

        public String getCatalog_id() {
            return catalog_id;
        }

        public void setCatalog_id(String catalog_id) {
            this.catalog_id = catalog_id;
        }

        public String getCatalog_img() {
            return catalog_img;
        }

        public void setCatalog_img(String catalog_img) {
            this.catalog_img = catalog_img;
        }

        public String getShippercode() {
            return shippercode;
        }

        public void setShippercode(String shippercode) {
            this.shippercode = shippercode;
        }

        public String getExpress_time() {
            return express_time;
        }

        public void setExpress_time(String express_time) {
            this.express_time = express_time;
        }

        public String getShipperName() {
            return shipperName;
        }

        public void setShipperName(String shipperName) {
            this.shipperName = shipperName;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeString(this.expressInfo);
        dest.writeParcelable((Parcelable) this.datas, flags);
        dest.writeInt(this.resultCode);
    }

    public LogisticsEntity() {
    }

    protected LogisticsEntity(Parcel in) {
        this.msg = in.readString();
        this.expressInfo = in.readString();
        this.datas = in.readParcelable(DatasBean.class.getClassLoader());
        this.resultCode = in.readInt();
    }

    public static final Creator<LogisticsEntity> CREATOR = new Creator<LogisticsEntity>() {
        @Override
        public LogisticsEntity createFromParcel(Parcel source) {
            return new LogisticsEntity(source);
        }

        @Override
        public LogisticsEntity[] newArray(int size) {
            return new LogisticsEntity[size];
        }
    };
}
