package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */

public class CommodEntity {
    /**
     * msg : 查询成功
     * imgs : [{"shopcommon_id":65,"pic_url":"http://otkny7iog.bkt.clouddn.com/Fk-a9e4VYywDIpwL0g-xG9Gn0lAu","pic_type":2}]
     * commont_pd : {}
     * photo_text_detail : <p>测试测试</p><p><br/></p><p><br/></p>
     * datas : {"surplus_no":"100","original_price":100,"freePost":" 满 10.0件 包邮","like_count":0,"freight_id":2,"create_time":"2018-01-10 18:28:04","isForeign":"2","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","shop_label":"粉丝团购","end_time":"2018-01-31 00:00:00","virtualuser_id":4267,"review_count":0,"sale_no":0,"shop_name":"EXO专辑团购EXO专辑团购EXO专辑团购EXO专辑团购","start_time":"2018-01-01 00:00:00","between_price":"50-200","attestation_type":0,"isPrised":"false","nick_name":"11111111111","freight_type":1,"shopcommon_id":65,"current_price":100,"current_time":"2018-01-12 17:40:43","freight_order":10}
     * comment_type : [{"satis_name":"全部","count":0,"satisfaction":0}]
     * cata_pd : [{"surplus_no":100,"catalog_title":"全款","original_price":"100.00","catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","ssc_id":53,"sale_no":"0","current_price":"100.00","store":95,"limit_buy":"100"}]
     * resultCode : 01
     * record_pd : []
     */

    private String msg;
    private CommontPdBean commont_pd;
    private String photo_text_detail;
    private DatasBean datas;
    private int resultCode;
    private List<ImgsBean> imgs;
    private List<CommentTypeBean> comment_type;
    private List<CataPdBean> cata_pd;
    private List<RecordPdBean> record_pd;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommontPdBean getCommont_pd() {
        return commont_pd;
    }

    public void setCommont_pd(CommontPdBean commont_pd) {
        this.commont_pd = commont_pd;
    }

    public String getPhoto_text_detail() {
        return photo_text_detail;
    }

    public void setPhoto_text_detail(String photo_text_detail) {
        this.photo_text_detail = photo_text_detail;
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

    public List<CommentTypeBean> getComment_type() {
        return comment_type;
    }

    public void setComment_type(List<CommentTypeBean> comment_type) {
        this.comment_type = comment_type;
    }

    public List<CataPdBean> getCata_pd() {
        return cata_pd;
    }

    public void setCata_pd(List<CataPdBean> cata_pd) {
        this.cata_pd = cata_pd;
    }

    public List<RecordPdBean> getRecord_pd() {
        return record_pd;
    }

    public void setRecord_pd(List<RecordPdBean> record_pd) {
        this.record_pd = record_pd;
    }

    public static class CommontPdBean {
    }

    public static class DatasBean {
        /**
         * surplus_no : 100
         * original_price : 100.0
         * freePost :  满 10.0件 包邮
         * like_count : 0
         * freight_id : 2
         * create_time : 2018-01-10 18:28:04
         * isForeign : 2
         * head_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * shop_label : 粉丝团购
         * end_time : 2018-01-31 00:00:00
         * virtualuser_id : 4267
         * review_count : 0
         * sale_no : 0
         * shop_name : EXO专辑团购EXO专辑团购EXO专辑团购EXO专辑团购
         * start_time : 2018-01-01 00:00:00
         * between_price : 50-200
         * attestation_type : 0
         * isPrised : false
         * nick_name : 11111111111
         * freight_type : 1
         * shopcommon_id : 65
         * current_price : 100.0
         * current_time : 2018-01-12 17:40:43
         * freight_order : 10.0
         */

        private String surplus_no;
        private double original_price;
        private String freePost;
        private int like_count;
        private int freight_id;
        private String create_time;
        private int isForeign;
        private String head_img;
        private String shop_label;
        private String end_time;
        private int virtualuser_id;
        private int review_count;
        private int sale_no;
        private String shop_name;
        private String start_time;
        private String between_price;
        private int attestation_type;
        private boolean isPrised;
        private String nick_name;
        private int freight_type;
        private int shopcommon_id;
        private double current_price;
        private String current_time;
        private double freight_order;

        public String getSurplus_no() {
            return surplus_no;
        }

        public void setSurplus_no(String surplus_no) {
            this.surplus_no = surplus_no;
        }

        public double getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(double original_price) {
            this.original_price = original_price;
        }

        public String getFreePost() {
            return freePost;
        }

        public void setFreePost(String freePost) {
            this.freePost = freePost;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getFreight_id() {
            return freight_id;
        }

        public void setFreight_id(int freight_id) {
            this.freight_id = freight_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getIsForeign() {
            return isForeign;
        }

        public void setIsForeign(int isForeign) {
            this.isForeign = isForeign;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getShop_label() {
            return shop_label;
        }

        public void setShop_label(String shop_label) {
            this.shop_label = shop_label;
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

        public boolean getIsPrised() {
            return isPrised;
        }

        public void setIsPrised(boolean isPrised) {
            this.isPrised = isPrised;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getFreight_type() {
            return freight_type;
        }

        public void setFreight_type(int freight_type) {
            this.freight_type = freight_type;
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

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public double getFreight_order() {
            return freight_order;
        }

        public void setFreight_order(double freight_order) {
            this.freight_order = freight_order;
        }
    }

    public static class ImgsBean {
        /**
         * shopcommon_id : 65
         * pic_url : http://otkny7iog.bkt.clouddn.com/Fk-a9e4VYywDIpwL0g-xG9Gn0lAu
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

    public static class CommentTypeBean {
        /**
         * satis_name : 全部
         * count : 0
         * satisfaction : 0
         */

        private String satis_name;
        private int count;
        private int satisfaction;

        public String getSatis_name() {
            return satis_name;
        }

        public void setSatis_name(String satis_name) {
            this.satis_name = satis_name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(int satisfaction) {
            this.satisfaction = satisfaction;
        }
    }

    public static class CataPdBean {
        /**
         * surplus_no : 100
         * catalog_title : 全款
         * original_price : 100.00
         * catalog_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * ssc_id : 53
         * sale_no : 0
         * current_price : 100.00
         * store : 95
         * limit_buy : 100
         */

        private int surplus_no;
        private String catalog_title;
        private String original_price;
        private String catalog_img;
        private int ssc_id;
        private String sale_no;
        private String current_price;
        private int store;
        private int limit_buy;

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

        public int getLimit_buy() {
            return limit_buy;
        }

        public void setLimit_buy(int limit_buy) {
            this.limit_buy = limit_buy;
        }
    }

    public static class RecordPdBean {
        /**
                * money : 166.0
                * uso_id : 2
                * nick_name : Joker.Wang
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg
                */

        private double money;
        private int uso_id;
        private String nick_name;
        private String head_img;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getUso_id() {
            return uso_id;
        }

        public void setUso_id(int uso_id) {
            this.uso_id = uso_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }
    }










   /* *//**
     * msg : 查询成功
     * imgs : [{"shopcommon_id":26,"pic_url":"http://59.110.67.175:80/treasureFile/find/cover/61700b51-c16d-4fba-956e-fd9857db9c71ImageViewerEShop.jpg","pic_type":2}]
     * commont_pd : {"creat_time":"2017-12-13 14:23:09","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg","nick_name":"Joker.Wang","virtualuser_id":4275,"comment_id":8,"content":"hello22222"}
     * photo_text_detail : <p>HJDKSHAJKDHSAJK</p><p>手机号肯定撒环境肯定撒即可<br/></p>
     * datas : {"original_price":124,"freePost":"暂无","like_count":0,"freight_id":1,"create_time":"2017-12-05 16:57:36","isForeign":"1","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg","shop_label":"娱自营","end_time":"2017-11-28 00:00:00","virtualuser_id":4275,"review_count":0,"sale_no":0,"shop_name":"测试小卡EXO商品","percent":0,"start_time":"2017-11-21 00:00:00","attestation_type":0,"isPrised":"false","nick_name":"Joker.Wang","shopcommon_id":26,"current_price":120,"current_time":"2017-12-28 10:29:32"}
     * comment_type : [{"satis_name":"非常满意","count":2,"satisfaction":1},{"satis_name":"满意","count":1,"satisfaction":2},{"satis_name":"不满意","count":2,"satisfaction":3},{"satis_name":"全部","count":5,"satisfaction":0}]
     * cata_pd : [{"catalog_title":"12","original_price":124,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","ssc_id":10,"current_price":12,"store":30},{"catalog_title":"12","original_price":120,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","ssc_id":17,"current_price":12,"store":30}]
     * resultCode : 01
     * record_pd : [{"money":166,"uso_id":2,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg"},{"money":88,"uso_id":1,"nick_name":"11111111111","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100"}]
     *//*

    private String msg;
    private CommontPdBean commont_pd;
    private String photo_text_detail;
    private DatasBean datas;
    private int resultCode;
    private List<ImgsBean> imgs;
    private List<CommentTypeBean> comment_type;
    private List<CataPdBean> cata_pd;
    private List<RecordPdBean> record_pd;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommontPdBean getCommont_pd() {
        return commont_pd;
    }

    public void setCommont_pd(CommontPdBean commont_pd) {
        this.commont_pd = commont_pd;
    }

    public String getPhoto_text_detail() {
        return photo_text_detail;
    }

    public void setPhoto_text_detail(String photo_text_detail) {
        this.photo_text_detail = photo_text_detail;
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

    public List<CommentTypeBean> getComment_type() {
        return comment_type;
    }

    public void setComment_type(List<CommentTypeBean> comment_type) {
        this.comment_type = comment_type;
    }

    public List<CataPdBean> getCata_pd() {
        return cata_pd;
    }

    public void setCata_pd(List<CataPdBean> cata_pd) {
        this.cata_pd = cata_pd;
    }

    public List<RecordPdBean> getRecord_pd() {
        return record_pd;
    }

    public void setRecord_pd(List<RecordPdBean> record_pd) {
        this.record_pd = record_pd;
    }

    public static class CommontPdBean {
        *//**
         * creat_time : 2017-12-13 14:23:09
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg
         * nick_name : Joker.Wang
         * virtualuser_id : 4275
         * comment_id : 8
         * content : hello22222
         *//*

        private String creat_time;
        private String head_img;
        private String nick_name;
        private int virtualuser_id;
        private int comment_id;
        private String content;

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(int virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class DatasBean {
        *//**
         * original_price : 124.0
         * freePost : 暂无
         * like_count : 0
         * freight_id : 1
         * create_time : 2017-12-05 16:57:36
         * isForeign : 1
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg
         * shop_label : 娱自营
         * end_time : 2017-11-28 00:00:00
         * virtualuser_id : 4275
         * review_count : 0
         * sale_no : 0
         * shop_name : 测试小卡EXO商品
         * percent : 0
         * start_time : 2017-11-21 00:00:00
         * attestation_type : 0
         * isPrised : false
         * nick_name : Joker.Wang
         * shopcommon_id : 26
         * current_price : 120.0
         * current_time : 2017-12-28 10:29:32
         *//*

        private double original_price;
        private String freePost;
        private int like_count;
        private int freight_id;
        private String create_time;
        private int isForeign;
        private String head_img;
        private String shop_label;
        private String end_time;
        private int virtualuser_id;
        private int review_count;
        private int sale_no;
        private String shop_name;
        private int percent;
        private String start_time;
        private int attestation_type;
        private String isPrised;
        private String nick_name;
        private int shopcommon_id;
        private double current_price;
        private String current_time;

        public double getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(double original_price) {
            this.original_price = original_price;
        }

        public String getFreePost() {
            return freePost;
        }

        public void setFreePost(String freePost) {
            this.freePost = freePost;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getFreight_id() {
            return freight_id;
        }

        public void setFreight_id(int freight_id) {
            this.freight_id = freight_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getIsForeign() {
            return isForeign;
        }

        public void setIsForeign(int isForeign) {
            this.isForeign = isForeign;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getShop_label() {
            return shop_label;
        }

        public void setShop_label(String shop_label) {
            this.shop_label = shop_label;
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

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
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

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }
    }

    public static class ImgsBean {
        *//**
         * shopcommon_id : 26
         * pic_url : http://59.110.67.175:80/treasureFile/find/cover/61700b51-c16d-4fba-956e-fd9857db9c71ImageViewerEShop.jpg
         * pic_type : 2
         *//*

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

    public static class CommentTypeBean {
        *//**
         * satis_name : 非常满意
         * count : 2
         * satisfaction : 1
         *//*

        private String satis_name;
        private int count;
        private int satisfaction;

        public String getSatis_name() {
            return satis_name;
        }

        public void setSatis_name(String satis_name) {
            this.satis_name = satis_name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(int satisfaction) {
            this.satisfaction = satisfaction;
        }
    }

    public static class CataPdBean {
        *//**
         * catalog_title : 12
         * original_price : 124.0
         * catalog_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * ssc_id : 10
         * current_price : 12.0
         * store : 30
         *//*
        private String catalog_title;
        private double original_price;
        private String catalog_img;
        private int ssc_id;
        private double current_price;
        private int store;

        public String getCatalog_title() {
            return catalog_title;
        }

        public void setCatalog_title(String catalog_title) {
            this.catalog_title = catalog_title;
        }

        public double getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(double original_price) {
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

        public double getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(double current_price) {
            this.current_price = current_price;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }
    }

    public static class RecordPdBean {
        *//**
         * money : 166.0
         * uso_id : 2
         * nick_name : Joker.Wang
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/5d648c43e5de4f9db4b97d008c7ea4d4.jpg
         *//*

        private double money;
        private int uso_id;
        private String nick_name;
        private String head_img;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getUso_id() {
            return uso_id;
        }

        public void setUso_id(int uso_id) {
            this.uso_id = uso_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }
    }*/
}
