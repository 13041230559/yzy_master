package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/2 17:43
 * 描述：
 */

public class SubmitOrderEntity {
    private  String from_id;
    private  List<CartItems> cartItems;
    private  List<DataBean> additionData;
    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public List<DataBean> getAdditionData() {
        return additionData;
    }

    public void setAdditionData(List<DataBean> additionData) {
        this.additionData = additionData;
    }


    public static  class CartItems{

        public String getCartitems_id() {
            return cartitems_id;
        }
        public void setCartitems_id(String cartitems_id) {
            this.cartitems_id = cartitems_id;
        }

        String cartitems_id;
    }

    public static  class DataBean{
        private String shopcommon_id;

        public String getShopcommon_id() {
            return shopcommon_id;
        }
        public void setShopcommon_id(String shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public List<DataBeans> getAdditionData() {
            return data;
        }

        public void setAdditionData(List<DataBeans> additionData) {
            this.data = additionData;
        }

        List<DataBeans>data;

        public  static class  DataBeans{
            String addition_key;
            String addition_value;
            int addition_id;

            public int getAddition_id() {
                return addition_id;
            }

            public void setAddition_id(int addition_id) {
                this.addition_id = addition_id;
            }

            public String getKey() {
                return addition_key;
            }

            public void setKey(String key) {
                this.addition_key = key;
            }

            public String getValue() {
                return addition_value;
            }

            public void setValue(String value) {
                this.addition_value = value;
            }
        }
    }



}
