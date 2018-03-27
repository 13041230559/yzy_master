package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/12 18:41
 * 描述：
 */

public class PayEntity {


    /**
     * msg : 订单生产成功
     * resultCode : 01
     * orderData : {"now_time":"2018-01-13 14:36:10","from_type":"cartOrder","create_time":"2018-01-13 14:36:09","totalMoney":92.32,"order_num":"yzy201801131436099640001"}
     */

    private String msg;
    private int resultCode;
    private OrderDataBean orderData;

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

    public OrderDataBean getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderDataBean orderData) {
        this.orderData = orderData;
    }

    public static class OrderDataBean {
        /**
         * now_time : 2018-01-13 14:36:10
         * from_type : cartOrder
         * create_time : 2018-01-13 14:36:09
         * totalMoney : 92.32
         * order_num : yzy201801131436099640001
         */

        private String now_time;
        private String from_type;
        private String create_time;
        private String totalMoney;
        private String order_num;

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public String getFrom_type() {
            return from_type;
        }

        public void setFrom_type(String from_type) {
            this.from_type = from_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }
    }
}
