package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 12:06
 * 描述：资讯详情
 */

public class InformationDetalisEntity {

    /**
     * msg : 查询成功
     * datas : {"like_count":0,"create_time":"18-01-29","shop_from":"","shop_detail":"<p>不知不觉间，那些在舞台上唱着《Happiness》的少女们也渐渐展露出轻熟的女人味，迷离的眼神，轻启的唇间，仿佛即将唱出迷人的音符。很期待#RedVelvet#&nbsp;的全新单曲《Bad Boy》，期待遇见另一个\u201cRedVelvet\u201d<\/p><p>http://photo.weibo.com/6297598713/wbphotos/large/mid/4201585196019515/pid/006Sc56Nly1fnxmhkdxf7j30zk0owdzv<\/p><p>http://photo.weibo.com/6297598713/wbphotos/large/mid/4201585196019515/pid/006Sc56Nly1fnxmhi90fwj30zk0owdtg<\/p>","isPrised":"false","review_count":0,"shopcommon_id":89,"shop_name":"【画报】RedVevlet变身无罪 完颜团的美颜魅惑","popularity_count":29}
     * resultCode : 01
     */

    private String msg;
    private DatasBean datas;
    private int resultCode;

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

    public static class DatasBean {
        /**
         * like_count : 0
         * create_time : 18-01-29
         * shop_from :
         * shop_detail : <p>不知不觉间，那些在舞台上唱着《Happiness》的少女们也渐渐展露出轻熟的女人味，迷离的眼神，轻启的唇间，仿佛即将唱出迷人的音符。很期待#RedVelvet#&nbsp;的全新单曲《Bad Boy》，期待遇见另一个“RedVelvet”</p><p>http://photo.weibo.com/6297598713/wbphotos/large/mid/4201585196019515/pid/006Sc56Nly1fnxmhkdxf7j30zk0owdzv</p><p>http://photo.weibo.com/6297598713/wbphotos/large/mid/4201585196019515/pid/006Sc56Nly1fnxmhi90fwj30zk0owdtg</p>
         * isPrised : false
         * review_count : 0
         * shopcommon_id : 89
         * shop_name : 【画报】RedVevlet变身无罪 完颜团的美颜魅惑
         * popularity_count : 29
         */

        private String like_count;
        private String create_time;
        private String shop_from;
        private String shop_detail;
        private boolean isPrised;
        private int review_count;
        private int shopcommon_id;
        private String shop_name;
        private String popularity_count;

        public String getLike_count() {
            return like_count;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getShop_from() {
            return shop_from;
        }

        public void setShop_from(String shop_from) {
            this.shop_from = shop_from;
        }

        public String getShop_detail() {
            return shop_detail;
        }

        public void setShop_detail(String shop_detail) {
            this.shop_detail = shop_detail;
        }

        public boolean getIsPrised() {
            return isPrised;
        }

        public void setIsPrised(boolean isPrised) {
            this.isPrised = isPrised;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
        }

        public int getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(int shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getPopularity_count() {
            return popularity_count;
        }

        public void setPopularity_count(String popularity_count) {
            this.popularity_count = popularity_count;
        }
    }
}
