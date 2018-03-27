package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 12:06
 * 描述：资讯
 */

public class InformationEntity {


    /**
     * msg : 查询成功
     * totalRow : 6
     * totalPage : 1
     * datas : [{"create_time":"2018-01-29 19:56:23","shopcommon_id":91,"shop_name":"dsadsa","pic_url":"http://otkny7iog.bkt.clouddn.com/Ft9Xkn8T1UVoRkwHhHhI6st03rNG","information_type":"新人","popularity_count":2},{"create_time":"2018-01-29 19:10:41","shopcommon_id":89,"shop_name":"【画报】RedVevlet变身无罪 完颜团的美颜魅惑","pic_url":"http://otkny7iog.bkt.clouddn.com/FmkwtLkW46VI8-IVs4Gd9JIGMDV-","information_type":"音乐","popularity_count":6},{"create_time":"2018-01-29 19:07:52","shopcommon_id":88,"shop_name":"【音乐】H.O.T.五人完整体再会！《无限挑战》又要搞事情啦","pic_url":"http://otkny7iog.bkt.clouddn.com/FjMlXa88lWUBK6wAFPjSpKqBPWhz","information_type":"新人","popularity_count":1},{"create_time":"2018-01-29 18:10:39","shopcommon_id":86,"shop_name":"dsadsa","pic_url":"http://otkny7iog.bkt.clouddn.com/FrL0Shu3AvvjYDHj4T40b9nBcTK0","information_type":"新人","popularity_count":0},{"create_time":"2018-01-22 11:10:51","shopcommon_id":78,"shop_name":"wewqew","pic_url":"http://otkny7iog.bkt.clouddn.com/FuT7PqUNcs-E1C_T8bCCOB94t69u","information_type":"新人","popularity_count":13},{"create_time":"2018-01-13 11:13:13","shopcommon_id":71,"shop_name":"资讯测试","pic_url":"http://otkny7iog.bkt.clouddn.com/FvOW97_V_wV08108OlnO2nYmuN5s","information_type":"公演","popularity_count":9}]
     * resultCode : 01
     */

    private String msg;
    private int totalRow;
    private int totalPage;
    private int resultCode;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * create_time : 2018-01-29 19:56:23
         * shopcommon_id : 91
         * shop_name : dsadsa
         * pic_url : http://otkny7iog.bkt.clouddn.com/Ft9Xkn8T1UVoRkwHhHhI6st03rNG
         * information_type : 新人
         * popularity_count : 2
         */

        private String create_time;
        private String shopcommon_id;
        private String shop_name;
        private String pic_url;
        private String information_type;
        private int popularity_count;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(String shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getInformation_type() {
            return information_type;
        }

        public void setInformation_type(String information_type) {
            this.information_type = information_type;
        }

        public int getPopularity_count() {
            return popularity_count;
        }

        public void setPopularity_count(int popularity_count) {
            this.popularity_count = popularity_count;
        }
    }
}
