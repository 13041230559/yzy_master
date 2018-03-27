package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/11 19:23
 * 描述：
 */

public class OrderItemEntity {


    /**
     * addition_id : 74
     * catalogItems : [{"catalog_id":34,"catalog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","original_price":200.11,"catalog_name":"188","reserve_status":"","is_reserve":0,"current_price":199.89,"shop_count":2,"pay_money":399.78}]
     * shop_label : 热门HOT
     * shop_type : discount
     * shopcommon_id : 48
     * shop_name : 1803号普通商品EXO
     * content : [{"addition_id":1,"addition_key":"sfz","addition_value":"111"},{"addition_id":2,"addition_key":"qq","addition_value":"111"}]
     */

    private int addition_id;
    private String shop_label;
    private String shop_name;
    private String shop_type;
    private int shopcommon_id;

    private String content;
    public List<AdditionBean> getAdditionBeanList() {
        return additionBeanList;
    }

    public void setAdditionBeanList(List<AdditionBean> additionBeanList) {
        this.additionBeanList = additionBeanList;
    }

    private List<AdditionBean> additionBeanList;

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
    private List<CatalogItemsBean> catalogItems;

    public int getAddition_id() {
        return addition_id;
    }

    public void setAddition_id(int addition_id) {
        this.addition_id = addition_id;
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

    public int getShopcommon_id() {
        return shopcommon_id;
    }

    public void setShopcommon_id(int shopcommon_id) {
        this.shopcommon_id = shopcommon_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CatalogItemsBean> getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(List<CatalogItemsBean> catalogItems) {
        this.catalogItems = catalogItems;
    }

    public static class CatalogItemsBean {
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

        /**
         * catalog_id : 34
         * catalog_img : http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg
         * original_price : 200.11
         * catalog_name : 188
         * reserve_status :
         * is_reserve : 0
         * current_price : 199.89
         * shop_count : 2
         * pay_money : 399.78
         */

        private String shop_label;
        private String shop_name;
        private int catalog_id;
        private String catalog_img;
        private String original_price;
        private String catalog_name;
        private String reserve_status;
        private int is_reserve;
        private String current_price;
        private int shop_count;
        private String pay_money;

        public int getCatalog_id() {
            return catalog_id;
        }

        public void setCatalog_id(int catalog_id) {
            this.catalog_id = catalog_id;
        }

        public String getCatalog_img() {
            return catalog_img;
        }

        public void setCatalog_img(String catalog_img) {
            this.catalog_img = catalog_img;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getCatalog_name() {
            return catalog_name;
        }

        public void setCatalog_name(String catalog_name) {
            this.catalog_name = catalog_name;
        }

        public String getReserve_status() {
            return reserve_status;
        }

        public void setReserve_status(String reserve_status) {
            this.reserve_status = reserve_status;
        }

        public int getIs_reserve() {
            return is_reserve;
        }

        public void setIs_reserve(int is_reserve) {
            this.is_reserve = is_reserve;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public int getShop_count() {
            return shop_count;
        }

        public void setShop_count(int shop_count) {
            this.shop_count = shop_count;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }
    }
}
