package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/13 14:49
 * 描述：
 */

public class AliPayEntity {


    /**
     * msg : 获取签名成功
     * order_no : yzy201801131445543810001
     * resultCode : 01
     * order_info : charset=utf-8&biz_content=%7B%22timeout_express%22%3A%2216m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%2285.0%22%2C%22subject%22%3A%22%E6%97%A0%E9%99%84%E5%8A%A0%E4%BF%A1%E6%81%AF%E4%BC%98%E6%83%A0%E5%95%86%E5%93%81%22%2C%22body%22%3A%22%E6%97%A0%E9%99%84%E5%8A%A0%E4%BF%A1%E6%81%AF%E4%BC%98%E6%83%A0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%22yzy201801131445543810001%22%2C%22passback_params%22%3A%22cartOrder%22%7D&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F59.110.67.175%3A1081%2FFHADMINM%2FappPay%2Falipay_notify_url&app_id=2017070307629190&sign_type=RSA2&version=1.0&timestamp=2018-01-13+14%3A45%3A58&sign=dMXrUHiFeQY86xRBvo7OBTuiDxwHgeZq4uHkDUaLb4%2FrHo5pH%2FpAIgN5SwqH0Ix75RWg36tnUdjAyPNL9Bx96IENF%2BEyWzfm6d%2FWrNQWyyoj41Z1lQKRM%2FkpwpQhOseIFqPCWFgc6MAvY9BPfqz56nVKvTNrJU8C7qD7kgaabBRiXhb64MVrxpEogtby%2F18tlpzRFBHJOc364rldGFIaBeQmztHeWYdqpKztflXOf6zJ6XD1Gd%2Bq27LtMHjS6r9Do%2FSrBmNauYEvJynwlZWVFG8Hz3z7RTP%2BaWHGAMSRlAczAeT3ynLbNWetsGkBxqp7J0uKbYxnBcJg2HZewf1sJg%3D%3D
     */

    private String msg;
    private String order_no;
    private int resultCode;
    private String order_info;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getOrder_info() {
        return order_info;
    }

    public void setOrder_info(String order_info) {
        this.order_info = order_info;
    }
}
