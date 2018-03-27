package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/7 15:15
 * 描述：
 */

public class MessageClassEntity {

    /**
     * msg : 查询成功
     * totalRow : 1
     * totalPage : 1
     * datas : [{"click_type":1,"shippercode":"","create_time":"2018-03-07 14:49:44","outside_url":"","message_id":1,"have_read":0,"express_no":"","title":"商品名称","content":"商品介绍","message_virtualuser_id":12,"outside_title":"","shop_type":"ordinary","order_num":"","shopcommon_id":"82","pic_url":"http://otkny7iog.bkt.clouddn.com/headImg/87f4864cebd9414b95c3bb2ce677ea79.jpg"}]
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
         * click_type : 1
         * shippercode :
         * create_time : 2018-03-07 14:49:44
         * outside_url :
         * message_id : 1
         * have_read : 0
         * express_no :
         * title : 商品名称
         * content : 商品介绍
         * message_virtualuser_id : 12
         * outside_title :
         * shop_type : ordinary
         * order_num :
         * shopcommon_id : 82
         * pic_url : http://otkny7iog.bkt.clouddn.com/headImg/87f4864cebd9414b95c3bb2ce677ea79.jpg
         */

        private int click_type;
        private String shippercode;
        private String create_time;
        private String outside_url;
        private int message_id;
        private int have_read;
        private String express_no;
        private String title;
        private String content;
        private int message_virtualuser_id;
        private String outside_title;
        private String shop_type;
        private String order_num;
        private String shopcommon_id;
        private String pic_url;

        public int getClick_type() {
            return click_type;
        }

        public void setClick_type(int click_type) {
            this.click_type = click_type;
        }

        public String getShippercode() {
            return shippercode;
        }

        public void setShippercode(String shippercode) {
            this.shippercode = shippercode;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOutside_url() {
            return outside_url;
        }

        public void setOutside_url(String outside_url) {
            this.outside_url = outside_url;
        }

        public int getMessage_id() {
            return message_id;
        }

        public void setMessage_id(int message_id) {
            this.message_id = message_id;
        }

        public int getHave_read() {
            return have_read;
        }

        public void setHave_read(int have_read) {
            this.have_read = have_read;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getMessage_virtualuser_id() {
            return message_virtualuser_id;
        }

        public void setMessage_virtualuser_id(int message_virtualuser_id) {
            this.message_virtualuser_id = message_virtualuser_id;
        }

        public String getOutside_title() {
            return outside_title;
        }

        public void setOutside_title(String outside_title) {
            this.outside_title = outside_title;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getShopcommon_id() {
            return shopcommon_id;
        }

        public void setShopcommon_id(String shopcommon_id) {
            this.shopcommon_id = shopcommon_id;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
