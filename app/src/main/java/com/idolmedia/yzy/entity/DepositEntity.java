package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 18:42
 * 描述：定金
 */

public class DepositEntity {


    /**
     * msg : 查询成功
     * imgs : [{"shopcommon_id":61,"pic_url":"http://otkny7iog.bkt.clouddn.com/FrrikkgrTmAzfEU1tWk5PAKzO3UM","pic_type":2}]
     * commont_pd : {}
     * photo_text_detail :
     * datas : {"surplus_no":999999,"canPay":"false","reserve_end_time":"2018-01-25 14:09:33","reserve_start_time":"2018-01-10 16:52:23","isForeign":"2","shop_label":"Universe","final_end_time":"2018-02-02 10:29:37","virtualuser_id":4267,"review_count":0,"between_price":"50","isPrised":"false","deduction_money":50,"current_time":"2018-01-15 16:05:18","payButton":1,"like_count":0,"create_time":"2018-01-10 16:52:23","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","final_start_time":"2018-01-27 16:16:50","sale_no":0,"store":"999999","shop_name":"exoEXO - 2017 Winter Special Album (First Press)","reserve_money":50,"attestation_type":0,"nick_name":"11111111111","shopcommon_id":61}
     * comment_type : [{"satis_name":"全部","count":0,"satisfaction":0}]
     * cata_pd : [{"surplus_no":999999,"catalog_title":"预定定金","original_price":50,"catalog_img":"http://otkny7iog.bkt.clouddn.com/FrrikkgrTmAzfEU1tWk5PAKzO3UM","ssc_id":"-1","current_price":50,"store":"999999","limit_buy":"0"}]
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
    private  int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

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
         private  String creat_time;
        private  String head_img;
        private  String nick_name;
        private  String virtualuser_id;
        private  String comment_id;

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

        public String getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(String virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private  String content;

    }

    public static class DatasBean {

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        /**
         * surplus_no : 999999
         * canPay : false
         * reserve_end_time : 2018-01-25 14:09:33
         * reserve_start_time : 2018-01-10 16:52:23
         * isForeign : 2
         * shop_label : Universe
         * final_end_time : 2018-02-02 10:29:37
         * virtualuser_id : 4267
         * review_count : 0
         * between_price : 50
         * isPrised : false
         * deduction_money : 50.0
         * current_time : 2018-01-15 16:05:18
         * payButton : 1
         * like_count : 0
         * create_time : 2018-01-10 16:52:23
         * head_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * final_start_time : 2018-01-27 16:16:50
         * sale_no : 0
         * store : 999999
         * shop_name : exoEXO - 2017 Winter Special Album (First Press)
         * reserve_money : 50.0
         * attestation_type : 0
         * nick_name : 11111111111
         * shopcommon_id : 61
         */

        private String percent;
        private int reserve_status;
        private String freePost;
        private int freight_id;
        public int getReserve_status() {
            return reserve_status;
        }

        public void setReserve_status(int reserve_status) {
            this.reserve_status = reserve_status;
        }
        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getFreePost() {
            return freePost;
        }

        public void setFreePost(String freePost) {
            this.freePost = freePost;
        }

        public int getFreight_id() {
            return freight_id;
        }

        public void setFreight_id(int freight_id) {
            this.freight_id = freight_id;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getFreight_type() {
            return freight_type;
        }

        public void setFreight_type(int freight_type) {
            this.freight_type = freight_type;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public double getFreight_order() {
            return freight_order;
        }

        public void setFreight_order(double freight_order) {
            this.freight_order = freight_order;
        }

        private int freight_type;
        private double freight_order;
        private String canPay;
        private String reserve_end_time;
        private String reserve_start_time;
        private String shop_label;
        private String final_end_time;
        private String deduction_money;
        private String current_time;
        private int payButton;
        private int like_count;
        private String create_time;
        private String head_img;
        private String final_start_time;
        private int sale_no;
        private String store;
        private String shop_name;
        private String reserve_money;
        private int attestation_type;
        private String nick_name;
        private int shopcommon_id;
        private int totalCount;
        private int surplus_no;
        private String support_money;
        private String original_price;
        private int isForeign;
        private String end_time;
        private String virtualuser_id;
        private String review_count;
        private String start_time;
        private String between_price;
        private boolean isPrised;
        private String sale_money;
        private String current_price;


        public String getSupport_money() {
            return support_money;
        }

        public void setSupport_money(String support_money) {
            this.support_money = support_money;
        }

        public String getSale_money() {
            return sale_money;
        }

        public void setSale_money(String sale_money) {
            this.sale_money = sale_money;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getSurplus_no() {
            return surplus_no;
        }

        public void setSurplus_no(int surplus_no) {
            this.surplus_no = surplus_no;
        }

        public String getCanPay() {
            return canPay;
        }

        public void setCanPay(String canPay) {
            this.canPay = canPay;
        }

        public String getReserve_end_time() {
            return reserve_end_time;
        }

        public void setReserve_end_time(String reserve_end_time) {
            this.reserve_end_time = reserve_end_time;
        }

        public String getReserve_start_time() {
            return reserve_start_time;
        }

        public void setReserve_start_time(String reserve_start_time) {
            this.reserve_start_time = reserve_start_time;
        }

        public int getIsForeign() {
            return isForeign;
        }

        public void setIsForeign(int isForeign) {
            this.isForeign = isForeign;
        }

        public String getShop_label() {
            return shop_label;
        }

        public void setShop_label(String shop_label) {
            this.shop_label = shop_label;
        }

        public String getFinal_end_time() {
            return final_end_time;
        }

        public void setFinal_end_time(String final_end_time) {
            this.final_end_time = final_end_time;
        }

        public String getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(String virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public String getReview_count() {
            return review_count;
        }

        public void setReview_count(String review_count) {
            this.review_count = review_count;
        }

        public String getBetween_price() {
            return between_price;
        }

        public void setBetween_price(String between_price) {
            this.between_price = between_price;
        }

        public boolean getIsPrised() {
            return isPrised;
        }

        public void setIsPrised(boolean isPrised) {
            this.isPrised = isPrised;
        }

        public String getDeduction_money() {
            return deduction_money;
        }

        public void setDeduction_money(String deduction_money) {
            this.deduction_money = deduction_money;
        }

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public int getPayButton() {
            return payButton;
        }

        public void setPayButton(int payButton) {
            this.payButton = payButton;
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

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getFinal_start_time() {
            return final_start_time;
        }

        public void setFinal_start_time(String final_start_time) {
            this.final_start_time = final_start_time;
        }

        public int getSale_no() {
            return sale_no;
        }

        public void setSale_no(int sale_no) {
            this.sale_no = sale_no;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getReserve_money() {
            return reserve_money;
        }

        public void setReserve_money(String reserve_money) {
            this.reserve_money = reserve_money;
        }

        public int getAttestation_type() {
            return attestation_type;
        }

        public void setAttestation_type(int attestation_type) {
            this.attestation_type = attestation_type;
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
    }

    public static class ImgsBean {
        /**
         * shopcommon_id : 61
         * pic_url : http://otkny7iog.bkt.clouddn.com/FrrikkgrTmAzfEU1tWk5PAKzO3UM
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
         * surplus_no : 999999
         * catalog_title : 预定定金
         * original_price : 50.0
         * catalog_img : http://otkny7iog.bkt.clouddn.com/FrrikkgrTmAzfEU1tWk5PAKzO3UM
         * ssc_id : -1
         * current_price : 50.0
         * store : 999999
         * limit_buy : 0
         */

        private int surplus_no;
        private boolean isCheck;
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private String catalog_title;
        private double original_price;
        private String catalog_img;
        private String ssc_id;
        private double current_price;
        private int store;
        private int limit_buy;
        private int buyNumber;
        private  Integer buyedCount;
        public Integer getBuyedCount() {
            return buyedCount;
        }

        public void setBuyedCount(Integer buyedCount) {
            this.buyedCount = buyedCount;
        }

        public int getBuyNumber() {
            return buyNumber;
        }

        public void setBuyNumber(int buyNumber) {
            this.buyNumber = buyNumber;
        }


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

        public String getSsc_id() {
            return ssc_id;
        }

        public void setSsc_id(String ssc_id) {
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

        public int getLimit_buy() {
            return limit_buy;
        }

        public void setLimit_buy(int limit_buy) {
            this.limit_buy = limit_buy;
        }
    }

    public static class RecordPdBean {
        /**
         * money : 0.1
         * uso_id : 36
         * nick_name : 娱甜
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/d6088fc1470d402c89cbbaa9ea305e0a.jpg
         */

        private String money;
        private String uso_id;
        private String nick_name;
        private String head_img;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getUso_id() {
            return uso_id;
        }

        public void setUso_id(String uso_id) {
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
}
