package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/28 14:33
 * 描述：活动应援
 */

public class SupportEntity implements Parcelable {


    /**
     * msg : 查询成功
     * totalRow : 4
     * totalPage : 1
     * datas : [{"product_id":21,"shop_label":"生日开屏","shop_type":"support","sale_no":27,"shopcommon_id":100,"origin_price":0.01,"current_price":0.01,"shop_name":"【XIUMIN金珉锡吧 】0326生日开屏应援集占活动","pic_url":"http://otkny7iog.bkt.clouddn.com/FhwL8V32aTwikmf_Yw2gsXSFtFWc"},{"product_id":22,"shop_label":"生日应援","shop_type":"support","sale_no":0,"shopcommon_id":131,"origin_price":32.9,"current_price":1,"shop_name":"【百度Irene吧】0329生日公益开屏活动","pic_url":"http://otkny7iog.bkt.clouddn.com/Fv2JsPpefGr18714RVFHpBOAeTGk"},{"product_id":105,"shop_label":"活动","shop_type":"activity","sale_no":0,"shopcommon_id":123,"origin_price":0,"current_price":0,"shop_name":"丁程鑫签名照抢啊","pic_url":"http://otkny7iog.bkt.clouddn.com/FohoP-n-67DSBxmDvysa5ib906Z4"},{"product_id":90,"shop_label":"自制周边","shop_type":"activity","sale_no":8,"shopcommon_id":102,"origin_price":100,"current_price":100,"shop_name":"exo透卡一套","pic_url":"http://otkny7iog.bkt.clouddn.com/FrAXk40jnzkiNj60X5XAgsnh7ZeN"}]
     * resultCode : 01
     */

    private String msg;
    private int totalRow;
    private int totalPage;
    private int resultCode;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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
         * product_id : 21
         * shop_label : 生日开屏
         * shop_type : support
         * sale_no : 27
         * shopcommon_id : 100
         * origin_price : 0.01
         * current_price : 0.01
         * shop_name : 【XIUMIN金珉锡吧 】0326生日开屏应援集占活动
         * pic_url : http://otkny7iog.bkt.clouddn.com/FhwL8V32aTwikmf_Yw2gsXSFtFWc
         */

        private int product_id;
        private String shop_label;
        private String shop_type;
        private int sale_no;
        private int shopcommon_id;
        private double origin_price;
        private double current_price;
        private String shop_name;
        private String pic_url;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getShop_label() {
            return shop_label;
        }

        public void setShop_label(String shop_label) {
            this.shop_label = shop_label;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public int getSale_no() {
            return sale_no;
        }

        public void setSale_no(int sale_no) {
            this.sale_no = sale_no;
        }

        public int getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(int shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public double getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(double origin_price) {
            this.origin_price = origin_price;
        }

        public double getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(double current_price) {
            this.current_price = current_price;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.totalRow);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.resultCode);
        dest.writeList(this.datas);
    }

    public SupportEntity() {
    }

    protected SupportEntity(Parcel in) {
        this.msg = in.readString();
        this.totalRow = in.readInt();
        this.totalPage = in.readInt();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Creator<SupportEntity> CREATOR = new Creator<SupportEntity>() {
        @Override
        public SupportEntity createFromParcel(Parcel source) {
            return new SupportEntity(source);
        }

        @Override
        public SupportEntity[] newArray(int size) {
            return new SupportEntity[size];
        }
    };
}
