package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/11 16:47
 * 描述：
 */

public class PreferentialHotEntity {


    /**
     * msg : 查询成功
     * datas : [{"original_price":10,"product_id":50,"shop_type":"discount","sale_no":0,"shopcommon_id":42,"current_price":10,"shop_name":"测试附加信息EXO","pic_url":"http://59.110.67.175:80/treasureFile/find/cover/0f60d2bb-ef70-4b0d-9314-4336ecc18381eees.jpg"}]
     * resultCode : 01
     */

    private String msg;
    private int resultCode;
    private List<DatasBean> datas;

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
         * original_price : 10.0
         * product_id : 50
         * shop_type : discount
         * sale_no : 0
         * shopcommon_id : 42
         * current_price : 10.0
         * shop_name : 测试附加信息EXO
         * pic_url : http://59.110.67.175:80/treasureFile/find/cover/0f60d2bb-ef70-4b0d-9314-4336ecc18381eees.jpg
         */

        private double original_price;
        private int product_id;
        private String shop_type;
        private int sale_no;
        private int shopcommon_id;
        private double current_price;
        private String shop_name;
        private String pic_url;

        public double getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(double original_price) {
            this.original_price = original_price;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
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
}
