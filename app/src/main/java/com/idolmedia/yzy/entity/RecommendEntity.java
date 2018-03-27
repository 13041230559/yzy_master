package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/25 12:47
 * 描述：推荐
 */

public class RecommendEntity implements Parcelable {
    /**
     * msg : 查询成功
     * totalRow : 3
     * totalPage : 1
     * datas : [{"activity_type":2,"shop_label":"娱自营","shop_type":"ordinary","sale_no":0,"shopcommon_id":26,"current_price":120,"origin_price":124,"shop_name":"测试小卡EXO商品","pic_url":"http://59.110.67.175:80/treasureFile/find/cover/61700b51-c16d-4fba-956e-fd9857db9c71ImageViewerEShop.jpg"},{"activity_type":1,"shop_label":"娱自营","shop_type":"support","sale_no":0,"shopcommon_id":21,"current_price":12,"origin_price":18,"shop_name":"ceshi小卡应援","pic_url":"http://59.110.67.175:80/treasureFile/find/cover/70bb68c5-e1a4-48fa-a88e-230d032762252486613019960329555.jpg"},{"activity_type":1,"shop_label":"娱自营","shop_type":"discount","sale_no":0,"shopcommon_id":18,"current_price":12,"origin_price":12,"shop_name":"ceshi特惠","pic_url":"http://59.110.67.175:80/treasureFile/find/cover/d5228064-824e-4d03-89f6-8447847fb167jbj.jpg"}]
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
         * activity_type : 2
         * shop_label : 娱自营
         * shop_type : ordinary
         * sale_no : 0
         * shopcommon_id : 26
         * current_price : 120.0
         * origin_price : 124.0
         * shop_name : 测试小卡EXO商品
         * pic_url : http://59.110.67.175:80/treasureFile/find/cover/61700b51-c16d-4fba-956e-fd9857db9c71ImageViewerEShop.jpg
         */

        private int activity_type;
        private String shop_label;
        private String shop_type;
        private int sale_no;
        private int shopcommon_id;
        private double current_price;
        private double origin_price;
        private String shop_name;
        private String pic_url;

        public int getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(int activity_type) {
            this.activity_type = activity_type;
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

        public double getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(double current_price) {
            this.current_price = current_price;
        }

        public double getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(double origin_price) {
            this.origin_price = origin_price;
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

    public RecommendEntity() {
    }

    protected RecommendEntity(Parcel in) {
        this.msg = in.readString();
        this.totalRow = in.readInt();
        this.totalPage = in.readInt();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<RecommendEntity> CREATOR = new Parcelable.Creator<RecommendEntity>() {
        @Override
        public RecommendEntity createFromParcel(Parcel source) {
            return new RecommendEntity(source);
        }

        @Override
        public RecommendEntity[] newArray(int size) {
            return new RecommendEntity[size];
        }
    };
}
