package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 11:39
 * 描述：
 */

public class ProductclassEntity implements Parcelable {


    /**
     * msg : 查询成功
     * cata_pd : [{"surplus_no":286,"catalog_title":"测试优惠商品","original_price":"50.00","catalog_img":"abc","ssc_id":32,"sale_no":"0","current_price":"40.00","store":287,"limit_buy":"0"}]
     * resultCode : 01
     * totalCount : 0
     * selected : false
     */

    private String msg;
    private int resultCode;
    private int totalCount;
    private String selected;
    private List<CataPdBean> cata_pd;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public List<CataPdBean> getCata_pd() {
        return cata_pd;
    }

    public void setCata_pd(List<CataPdBean> cata_pd) {
        this.cata_pd = cata_pd;
    }

    public static class CataPdBean {
        /**
         * surplus_no : 286
         * catalog_title : 测试优惠商品
         * original_price : 50.00
         * catalog_img : abc
         * ssc_id : 32
         * sale_no : 0
         * current_price : 40.00
         * store : 287
         * limit_buy : 0
         */

        private int surplus_no;
        private String catalog_title;
        private String original_price;
        private String catalog_img;
        private int ssc_id;
        private String sale_no;
        private String current_price;
        private int store;
        private String limit_buy;

        public int getSurplus_no() {
            return surplus_no;
        }

        public void setSurplus_no(int surplus_no) {
            this.surplus_no = surplus_no;
        }

        public String getCatalog_title() {
            return catalog_title;
        }

        public void setCatalog_title(String catalog_title) {
            this.catalog_title = catalog_title;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getCatalog_img() {
            return catalog_img;
        }

        public void setCatalog_img(String catalog_img) {
            this.catalog_img = catalog_img;
        }

        public int getSsc_id() {
            return ssc_id;
        }

        public void setSsc_id(int ssc_id) {
            this.ssc_id = ssc_id;
        }

        public String getSale_no() {
            return sale_no;
        }

        public void setSale_no(String sale_no) {
            this.sale_no = sale_no;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public String getLimit_buy() {
            return limit_buy;
        }

        public void setLimit_buy(String limit_buy) {
            this.limit_buy = limit_buy;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.resultCode);
        dest.writeInt(this.totalCount);
        dest.writeString(this.selected);
        dest.writeList(this.cata_pd);
    }

    public ProductclassEntity() {
    }

    protected ProductclassEntity(Parcel in) {
        this.msg = in.readString();
        this.resultCode = in.readInt();
        this.totalCount = in.readInt();
        this.selected = in.readString();
        this.cata_pd = new ArrayList<CataPdBean>();
        in.readList(this.cata_pd, CataPdBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductclassEntity> CREATOR = new Parcelable.Creator<ProductclassEntity>() {
        @Override
        public ProductclassEntity createFromParcel(Parcel source) {
            return new ProductclassEntity(source);
        }

        @Override
        public ProductclassEntity[] newArray(int size) {
            return new ProductclassEntity[size];
        }
    };
}
