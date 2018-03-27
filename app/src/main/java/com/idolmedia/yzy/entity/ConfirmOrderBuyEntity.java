package com.idolmedia.yzy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/11 11:54
 * 描述：
 */

public class ConfirmOrderBuyEntity implements Serializable{

    /**
     * original_price : 200.11
     * catalog_title : 188
     * cartitems_id : 127
     * unint_price : 199.89
     * caralog_img : http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg
     * quantity_count : 1
     */

    private double original_price;
    private String catalog_title;
    private int cartitems_id;
    private double unint_price;
    private String caralog_img;
    private int quantity_count;
    private String shop_label;
    private String shop_name;
    private int shopcommon_id;
    private int ssc_id;
    private List<AdditionBean> additionBeanList;
    public int getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(int ssc_id) {
        this.ssc_id = ssc_id;
    }

    public int getShopcommon_id() {
        return shopcommon_id;
    }

    public void setShopcommon_id(int shopcommon_id) {
        this.shopcommon_id = shopcommon_id;
    }

    private int from_id;
    private String head_img;

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    private String from_name;
    public String getShop_label() {
        return shop_label;
    }

    public void setShop_label(String shop_label) {
        this.shop_label = shop_label;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }



    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public String getCatalog_title() {
        return catalog_title;
    }

    public void setCatalog_title(String catalog_title) {
        this.catalog_title = catalog_title;
    }

    public int getCartitems_id() {
        return cartitems_id;
    }

    public void setCartitems_id(int cartitems_id) {
        this.cartitems_id = cartitems_id;
    }

    public double getUnint_price() {
        return unint_price;
    }

    public void setUnint_price(double unint_price) {
        this.unint_price = unint_price;
    }

    public String getCaralog_img() {
        return caralog_img;
    }

    public void setCaralog_img(String caralog_img) {
        this.caralog_img = caralog_img;
    }

    public int getQuantity_count() {
        return quantity_count;
    }

    public void setQuantity_count(int quantity_count) {
        this.quantity_count = quantity_count;
    }

    public List<AdditionBean> getAdditionBeanList() {
        return additionBeanList;
    }

    public void setAdditionBeanList(List<AdditionBean> additionBeanList) {
        this.additionBeanList = additionBeanList;
    }

    public static class AdditionBean{

        /**
         * addition_id : 1
         * field : 身份证
         * placehold : 请输入身份证信息，用于海关
         * addition_key : sfz
         */

        private int addition_id;
        private String field;
        private String placehold;
        private String addition_key;
        private String addition_value;

        public String getAddition_value() {
            return addition_value;
        }

        public void setAddition_value(String addition_value) {
            this.addition_value = addition_value;
        }

        public int getAddition_id() {
            return addition_id;
        }

        public void setAddition_id(int addition_id) {
            this.addition_id = addition_id;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getPlacehold() {
            return placehold;
        }

        public void setPlacehold(String placehold) {
            this.placehold = placehold;
        }

        public String getAddition_key() {
            return addition_key;
        }

        public void setAddition_key(String addition_key) {
            this.addition_key = addition_key;
        }
    }
}
