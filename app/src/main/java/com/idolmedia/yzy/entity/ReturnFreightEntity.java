package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/2 13:15
 * 描述：
 */

public class ReturnFreightEntity {

    /**
     * msg : 运费计算成功
     * freightData : [{"from_id":4275,"freight_money":35}]
     * resultCode : 01
     */

    private String msg;
    private int resultCode;
    private List<FreightDataBean> freightData;

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

    public List<FreightDataBean> getFreightData() {
        return freightData;
    }

    public void setFreightData(List<FreightDataBean> freightData) {
        this.freightData = freightData;
    }

    public static class FreightDataBean {
        /**
         * from_id : 4275
         * freight_money : 35.0
         */

        private int from_id;
        private double freight_money;

        public int getFrom_id() {
            return from_id;
        }

        public void setFrom_id(int from_id) {
            this.from_id = from_id;
        }

        public double getFreight_money() {
            return freight_money;
        }

        public void setFreight_money(double freight_money) {
            this.freight_money = freight_money;
        }
    }
}
