package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/19 15:26
 * 描述： 娱
 */

public class CommodityEntity implements Parcelable {

    /**
     * msg : 查询成功
     * totalRow : 5
     * datas : [{"shop_label":"娱自营","shop_type":"discount","sale_no":0,"shopcommon_id":18,"current_price":12,"origin_price":12,"shop_name":"ceshi特惠","pic_url":"20171125/1853534771364ca8a1158ca257a76a1c.jpg"},{"shop_label":"娱自营","shop_type":"support","sale_no":0,"shopcommon_id":21,"current_price":12,"origin_price":18,"shop_name":"ceshi应援","pic_url":"20171125/5c7ce2efb4bb4ecdb8080d02430e3564.jpg"},{"shop_label":"娱自营","shop_type":"activity","sale_no":0,"shopcommon_id":23,"current_price":12,"origin_price":12,"shop_name":"CESHI活动","pic_url":"20171125/1853534771364ca8a1158ca257a76a1c.jpg"},{"shop_label":"娱自营","shop_type":"ordinary","sale_no":0,"shopcommon_id":26,"current_price":120,"origin_price":124,"shop_name":"测试EXO商品","pic_url":"20171125/30d89b22f6ac4a0f964639e54ebf7bce.jpg"},{"shop_label":"娱自营","shop_type":"reserve","sale_no":0,"shopcommon_id":31,"current_price":10,"origin_price":12,"shop_name":"预定商O品测试","pic_url":"20171125/ece676ccd7684d4f919bba4e8d1b5860.jpg"}]
     * totalPage : 1
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
         * shop_label : 娱自营
         * shop_type : discount
         * sale_no : 0
         * shopcommon_id : 18
         * current_price : 12.0
         * origin_price : 12.0
         * shop_name : ceshi特惠
         * pic_url : 20171125/1853534771364ca8a1158ca257a76a1c.jpg
         */

        private String shop_label;
        private int sale_no;
        private int shopcommon_id;
        private double current_price;
        private double original_price;
        private String shop_name;
        private String pic_url;
       private  String shop_type;
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
            return original_price;
        }

        public void setOrigin_price(double origin_price) {
            this.original_price = origin_price;
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

    public CommodityEntity() {
    }

    protected CommodityEntity(Parcel in) {
        this.msg = in.readString();
        this.totalRow = in.readInt();
        this.totalPage = in.readInt();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommodityEntity> CREATOR = new Parcelable.Creator<CommodityEntity>() {
        @Override
        public CommodityEntity createFromParcel(Parcel source) {
            return new CommodityEntity(source);
        }

        @Override
        public CommodityEntity[] newArray(int size) {
            return new CommodityEntity[size];
        }
    };
}
