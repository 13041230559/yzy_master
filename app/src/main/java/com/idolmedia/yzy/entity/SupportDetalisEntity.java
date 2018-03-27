package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/13 13:56
 * 描述：
 */

public class SupportDetalisEntity {


    /**
     * msg : 查询成功
     * imgs : [{"shopcommon_id":100,"pic_url":"http://otkny7iog.bkt.clouddn.com/FhwL8V32aTwikmf_Yw2gsXSFtFWc","pic_type":2}]
     * datas : {"surplus_no":"10000","support_money":320,"original_price":0.01,"like_count":0,"create_time":"2018-03-01 17:34:23","isForeign":"1","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/d6088fc1470d402c89cbbaa9ea305e0a.jpg","end_time":"2018-03-25 17:34:37","virtualuser_id":4302,"review_count":0,"sale_no":0,"shop_name":"【XIUMIN金珉锡吧 】0326生日开屏应援集占活动","start_time":"2018-03-01 17:34:37","between_price":"","attestation_type":1,"isPrised":"false","nick_name":"娱甜","sale_money":0,"shopcommon_id":100,"current_price":0.01}
     * cata_pd : [{"surplus_no":10000,"catalog_title":"公益","original_price":"0.01","catalog_img":"http://otkny7iog.bkt.clouddn.com/FhwL8V32aTwikmf_Yw2gsXSFtFWc","ssc_id":92,"sale_no":"0","buyedCount":"0","current_price":"0.01","store":10000,"limit_buy":"5000"}]
     * resultCode : 01
     */

    private String msg;
    private DatasBean datas;
    private int resultCode;
    private List<ImgsBean> imgs;
    private List<CataPdBean> cata_pd;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<CataPdBean> getCata_pd() {
        return cata_pd;
    }

    public void setCata_pd(List<CataPdBean> cata_pd) {
        this.cata_pd = cata_pd;
    }

    public static class DatasBean {
        /**
         * surplus_no : 10000
         * support_money : 320.0
         * original_price : 0.01
         * like_count : 0
         * create_time : 2018-03-01 17:34:23
         * isForeign : 1
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/d6088fc1470d402c89cbbaa9ea305e0a.jpg
         * end_time : 2018-03-25 17:34:37
         * virtualuser_id : 4302
         * review_count : 0
         * sale_no : 0
         * shop_name : 【XIUMIN金珉锡吧 】0326生日开屏应援集占活动
         * start_time : 2018-03-01 17:34:37
         * between_price :
         * attestation_type : 1
         * isPrised : false
         * nick_name : 娱甜
         * sale_money : 0.0
         * shopcommon_id : 100
         * current_price : 0.01
         */

        private String surplus_no;
        private String support_money;
        private String original_price;
        private int like_count;
        private String create_time;
        private String isForeign;
        private String head_img;
        private String end_time;
        private int virtualuser_id;
        private int review_count;
        private int sale_no;
        private String shop_name;
        private String start_time;
        private String between_price;
        private int attestation_type;
        private String isPrised;
        private String nick_name;
        private String sale_money;
        private int shopcommon_id;
        private String current_price;

        public String getSurplus_no() {
            return surplus_no;
        }

        public void setSurplus_no(String surplus_no) {
            this.surplus_no = surplus_no;
        }

        public String getSupport_money() {
            return support_money;
        }

        public void setSupport_money(String support_money) {
            this.support_money = support_money;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIsForeign() {
            return isForeign;
        }

        public void setIsForeign(String isForeign) {
            this.isForeign = isForeign;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(int virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
        }

        public int getSale_no() {
            return sale_no;
        }

        public void setSale_no(int sale_no) {
            this.sale_no = sale_no;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getBetween_price() {
            return between_price;
        }

        public void setBetween_price(String between_price) {
            this.between_price = between_price;
        }

        public int getAttestation_type() {
            return attestation_type;
        }

        public void setAttestation_type(int attestation_type) {
            this.attestation_type = attestation_type;
        }

        public String getIsPrised() {
            return isPrised;
        }

        public void setIsPrised(String isPrised) {
            this.isPrised = isPrised;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getSale_money() {
            return sale_money;
        }

        public void setSale_money(String sale_money) {
            this.sale_money = sale_money;
        }

        public int getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(int shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }
    }

    public static class ImgsBean {
        /**
         * shopcommon_id : 100
         * pic_url : http://otkny7iog.bkt.clouddn.com/FhwL8V32aTwikmf_Yw2gsXSFtFWc
         * pic_type : 2
         */

        private int shopcommon_id;
        private String pic_url;
        private int pic_type;

        public int getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(int shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getPic_type() {
            return pic_type;
        }

        public void setPic_type(int pic_type) {
            this.pic_type = pic_type;
        }
    }

    public static class CataPdBean {
        /**
         * surplus_no : 10000
         * catalog_title : 公益
         * original_price : 0.01
         * catalog_img : http://otkny7iog.bkt.clouddn.com/FhwL8V32aTwikmf_Yw2gsXSFtFWc
         * ssc_id : 92
         * sale_no : 0
         * buyedCount : 0
         * current_price : 0.01
         * store : 10000
         * limit_buy : 5000
         */

        private int surplus_no;
        private String catalog_title;
        private String original_price;
        private String catalog_img;
        private int ssc_id;
        private String sale_no;
        private String buyedCount;
        private String current_price;
        private int store;
        private String limit_buy;

        public int getSurplus_no() {
            return surplus_no;
        }

        public void setSurplus_no(int surplus_no) {
            this.surplus_no = surplus_no;
        }

        public String getCatalog_title() {
            return catalog_title;
        }

        public void setCatalog_title(String catalog_title) {
            this.catalog_title = catalog_title;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getCatalog_img() {
            return catalog_img;
        }

        public void setCatalog_img(String catalog_img) {
            this.catalog_img = catalog_img;
        }

        public int getSsc_id() {
            return ssc_id;
        }

        public void setSsc_id(int ssc_id) {
            this.ssc_id = ssc_id;
        }

        public String getSale_no() {
            return sale_no;
        }

        public void setSale_no(String sale_no) {
            this.sale_no = sale_no;
        }

        public String getBuyedCount() {
            return buyedCount;
        }

        public void setBuyedCount(String buyedCount) {
            this.buyedCount = buyedCount;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public String getLimit_buy() {
            return limit_buy;
        }

        public void setLimit_buy(String limit_buy) {
            this.limit_buy = limit_buy;
        }
    }
}
