package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 15:52
 * 描述：
 */

public class CartEntity {


    /**
     * msg : 购物车列表加载成功
     * invalidCartsData : [{"cart_id":37,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","cartitems_id":35,"creat_time":"2018-01-03 12:18:34","shop_label":"娱自营","shopcommon_id":26,"putaway":0,"shop_name":"测试小卡EXO商品"},{"cart_id":37,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","cartitems_id":36,"creat_time":"2018-01-03 12:18:38","shop_label":"娱自营","shopcommon_id":26,"putaway":0,"shop_name":"测试小卡EXO商品"}]
     * cartData : [{"cart_id":37,"from_id":4275,"creat_time":"2018-01-03 12:18:34","cartsItems":[{"surplus_no":72,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":124,"catalog_title":"12","cartitems_id":35,"quantity_count":1,"shop_label":"娱自营","shopcommon_id":26,"current_price":12,"is_check":1,"shop_name":"测试小卡EXO商品"},{"surplus_no":72,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":120,"catalog_title":"12","cartitems_id":36,"quantity_count":1,"shop_label":"娱自营","shopcommon_id":26,"current_price":12,"is_check":1,"shop_name":"测试小卡EXO商品"}],"from_name":"Joker.Wang"}]
     * resultCode : 01
     */

    private String msg;
    private int resultCode;
    private List<InvalidCartsDataBean> invalidCartsData;
    private List<CartDataBean> cartData;

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

    public List<InvalidCartsDataBean> getInvalidCartsData() {
        return invalidCartsData;
    }

    public void setInvalidCartsData(List<InvalidCartsDataBean> invalidCartsData) {
        this.invalidCartsData = invalidCartsData;
    }

    public List<CartDataBean> getCartData() {
        return cartData;
    }

    public void setCartData(List<CartDataBean> cartData) {
        this.cartData = cartData;
    }

    public static class InvalidCartsDataBean {
        /**
         * cart_id : 37
         * catalog_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * cartitems_id : 35
         * creat_time : 2018-01-03 12:18:34
         * shop_label : 娱自营
         * shopcommon_id : 26
         * putaway : 0
         * shop_name : 测试小卡EXO商品
         */

        private int cart_id;
        private String catalog_img;
        private int cartitems_id;
        private String creat_time;
        private String shop_label;
        private int shopcommon_id;
        private int putaway;
        private String shop_name;

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

        public String getCatalog_img() {
            return catalog_img;
        }

        public void setCatalog_img(String catalog_img) {
            this.catalog_img = catalog_img;
        }

        public int getCartitems_id() {
            return cartitems_id;
        }

        public void setCartitems_id(int cartitems_id) {
            this.cartitems_id = cartitems_id;
        }

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

        public String getShop_label() {
            return shop_label;
        }

        public void setShop_label(String shop_label) {
            this.shop_label = shop_label;
        }

        public int getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(int shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public int getPutaway() {
            return putaway;
        }

        public void setPutaway(int putaway) {
            this.putaway = putaway;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }
    }

    public static class CartDataBean {
        /**
         * cart_id : 37
         * from_id : 4275
         * creat_time : 2018-01-03 12:18:34
         * cartsItems : [{"surplus_no":72,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":124,"catalog_title":"12","cartitems_id":35,"quantity_count":1,"shop_label":"娱自营","shopcommon_id":26,"current_price":12,"is_check":1,"shop_name":"测试小卡EXO商品"},{"surplus_no":72,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":120,"catalog_title":"12","cartitems_id":36,"quantity_count":1,"shop_label":"娱自营","shopcommon_id":26,"current_price":12,"is_check":1,"shop_name":"测试小卡EXO商品"}]
         * from_name : Joker.Wang
         */
        private  boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        private int cart_id;
        private int from_id;
        private String creat_time;
        private String from_name;
        private List<CartsItemsBean> cartsItems;

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

        public int getFrom_id() {
            return from_id;
        }

        public void setFrom_id(int from_id) {
            this.from_id = from_id;
        }

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

        public String getFrom_name() {
            return from_name;
        }

        public void setFrom_name(String from_name) {
            this.from_name = from_name;
        }

        public List<CartsItemsBean> getCartsItems() {
            return cartsItems;
        }

        public void setCartsItems(List<CartsItemsBean> cartsItems) {
            this.cartsItems = cartsItems;
        }

        public static class CartsItemsBean {
            /**
             * surplus_no : 72
             * catalog_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
             * original_price : 124.0
             * catalog_title : 12
             * cartitems_id : 35
             * quantity_count : 1
             * shop_label : 娱自营
             * shopcommon_id : 26
             * current_price : 12.0
             * is_check : 1
             * shop_name : 测试小卡EXO商品
             */

            private int surplus_no;
            private String catalog_img;
            private double original_price;
            private String catalog_title;
            private int cartitems_id;
            private int quantity_count;
            private String shop_label;

            public String getShop_type() {
                return shop_type;
            }

            public void setShop_type(String shop_type) {
                this.shop_type = shop_type;
            }

            private String  shop_type;
            private int shopcommon_id;
            private double current_price;
           // private int is_check;
            private String shop_name;
            private boolean check;

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public int getSurplus_no() {
                return surplus_no;
            }

            public void setSurplus_no(int surplus_no) {
                this.surplus_no = surplus_no;
            }

            public String getCatalog_img() {
                return catalog_img;
            }

            public void setCatalog_img(String catalog_img) {
                this.catalog_img = catalog_img;
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

            public int getQuantity_count() {
                return quantity_count;
            }

            public void setQuantity_count(int quantity_count) {
                this.quantity_count = quantity_count;
            }

            public String getShop_label() {
                return shop_label;
            }

            public void setShop_label(String shop_label) {
                this.shop_label = shop_label;
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

          /*  public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }*/

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }
        }
    }
}
