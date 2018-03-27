package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/13 15:45
 * 描述：
 */

public class PayBuyEntity {


    /**
     * from_type : commonOrder
     * now_time : 2018-01-13 15:41:36
     * msg : 订单生产成功
     * create_time : 2018-01-13 15:41:35
     * totalMoney : 100.0
     * resultCode : 01
     * order_num : yzy201801131541356010001
     */

    private String from_type;
    private String now_time;
    private String msg;
    private String create_time;
    private String totalMoney;
    private int resultCode;
    private String order_num;

    public String getFrom_type() {
        return from_type;
    }

    public void setFrom_type(String from_type) {
        this.from_type = from_type;
    }

    public String getNow_time() {
        return now_time;
    }

    public void setNow_time(String now_time) {
        this.now_time = now_time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }
}
