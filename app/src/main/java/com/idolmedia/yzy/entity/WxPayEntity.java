package com.idolmedia.yzy.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/13 14:50
 * 描述：
 */

public class WxPayEntity {

    /**
     * order_no : yzy201801131445543810001
     * msg : 生成签名数据成功
     * package : Sign=WXPay
     * appid : wx2d80c6a71ca20ebc
     * sign : A81F4E826DE983B476D0EDCE3721FFF1
     * resultCode : 01
     * partnerid : 1486210522
     * prepayid : wx2018011314460716a1d5ae890730994212
     * noncestr : tR0czKZyaO7SBDQK6zpPGL83rxABTz
     * timestamp : 1515825967
     */

    private String order_no;
    private String msg;
    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private int resultCode;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
