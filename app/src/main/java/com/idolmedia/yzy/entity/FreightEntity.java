package com.idolmedia.yzy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 20:55
 * 描述：
 */

public class FreightEntity implements Serializable {


    private List<DatasBean> shops;
    private  String from_id;

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public List<DatasBean> getShop() {
        return shops;
    }

    public void setShop(List<DatasBean> shop) {
        this.shops = shop;
    }



    public static class DatasBean {
      /*  DatasBean(String cartItems_id){
            this.cartItems_id=cartItems_id;
        }*/

        public String getCartItems_id() {
            return cartItems_id;
        }

        public void setCartItems_id(String cartItems_id) {
            this.cartItems_id = cartItems_id;
        }
        String cartItems_id;

    }
}
