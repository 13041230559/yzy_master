package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/17 17:36
 * 描述：
 */

public class OldOrderEntity {

    /**
     * msg : 查询成功
     * totalRow : 2
     * totalPage : 1
     * datas : [{"img":"https://api.youdo-app.com/treasureFile/find/classify/2c0cddb8-a984-44d8-9263-95473fbe27e011111.jpg","find_type":"product","create_time":"Nov 22, 2017 9:56:19 PM","num":1,"title_img":"https://api.youdo-app.com/treasureFile/find/cover/14e208f4-ab8e-4c0b-a908-2d949ed5f917焦点图.jpg","express_money":0,"title":"一套透卡","express_company":"YD","product_title":"【E.L.F特别EVENT】《PLAY》透卡付邮免费送","order_status":"orderOver","totalAmount":10,"courier_number":"7700029843756","payMoney":10,"price":10,"order_num":"find20171122647"},{"img":"https://api.youdo-app.com/treasureFile/find/classify/6cdda81e-8d35-49eb-9ad6-bb921c1a5a289XC[~UP_(1QAI`WXFED({`B.png","find_type":"product","create_time":"Nov 24, 2017 8:07:32 PM","num":1,"title_img":"https://api.youdo-app.com/treasureFile/find/cover/5bc67b43-9460-4f1b-bd84-fbdc9563f27820171124151829.jpg","express_money":0,"title":"SJ八辑后续","express_company":"YD","product_title":"利特吧_TeukBar 全款预售SJ 八辑《#PLAY# PAUSE Ver.》韩国直邮送海报","order_status":"waitingGoods","totalAmount":168,"courier_number":"7700029844224","payMoney":168,"price":168,"order_num":"find20171124832"}]
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
         * img : https://api.youdo-app.com/treasureFile/find/classify/2c0cddb8-a984-44d8-9263-95473fbe27e011111.jpg
         * find_type : product
         * create_time : Nov 22, 2017 9:56:19 PM
         * num : 1
         * title_img : https://api.youdo-app.com/treasureFile/find/cover/14e208f4-ab8e-4c0b-a908-2d949ed5f917焦点图.jpg
         * express_money : 0
         * title : 一套透卡
         * express_company : YD
         * product_title : 【E.L.F特别EVENT】《PLAY》透卡付邮免费送
         * order_status : orderOver
         * totalAmount : 10
         * courier_number : 7700029843756
         * payMoney : 10
         * price : 10
         * order_num : find20171122647
         */

        private String img;
        private String find_type;
        private String create_time;
        private int num;
        private String title_img;
        private String express_money;
        private String title;
        private String express_company;
        private String product_title;
        private String order_status;
        private String totalAmount;
        private String courier_number;
        private String payMoney;
        private String price;
        private String order_num;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getFind_type() {
            return find_type;
        }

        public void setFind_type(String find_type) {
            this.find_type = find_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTitle_img() {
            return title_img;
        }

        public void setTitle_img(String title_img) {
            this.title_img = title_img;
        }

        public String getExpress_money() {
            return express_money;
        }

        public void setExpress_money(String express_money) {
            this.express_money = express_money;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExpress_company() {
            return express_company;
        }

        public void setExpress_company(String express_company) {
            this.express_company = express_company;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getCourier_number() {
            return courier_number;
        }

        public void setCourier_number(String courier_number) {
            this.courier_number = courier_number;
        }

        public String getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(String payMoney) {
            this.payMoney = payMoney;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }
    }
}
