package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/8 13:40
 * 描述：  我的订单
 */

public class MyOrderEntity  {


    /**
     * msg : 查询成功
     * totalRow : 3
     * totalPage : 1
     * datas : [{"order_status":"toPay","nick_name":"11111111111","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","total_pay_money":80,"order_num":"yzy201801101411391920001","order_id":135,"businesser_vid":4267,"orderItems":[{"addition_id":"68","catalogItems":[{"catalog_id":32,"catalog_img":"abc","original_price":50,"catalog_name":"测试优惠商品","current_price":40,"shop_count":2,"pay_money":80}],"shop_type":"discount","shopcommon_id":47,"shop_name":"测试优惠商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\"}]"}],"total_express_fee":0},{"order_status":"toPay","nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/0b93fc1edd064d63b64aea06851021a0.jpg","total_pay_money":60,"order_num":"yzy201801101411399860001","order_id":136,"businesser_vid":4275,"orderItems":[{"addition_id":"69","catalogItems":[{"catalog_id":25,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":10,"catalog_name":"普通商品测试","current_price":30,"shop_count":2,"pay_money":60}],"shop_type":"ordinary","shopcommon_id":39,"shop_name":"测试普通商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\"}]"}],"total_express_fee":0},{"order_status":"toPay","nick_name":"11111111111","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","total_pay_money":668.78,"order_num":"yzy201801101743178310001","order_id":140,"businesser_vid":4267,"orderItems":[{"addition_id":"74","catalogItems":[{"catalog_id":34,"catalog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","original_price":200.11,"catalog_name":"188","current_price":199.89,"shop_count":2,"pay_money":399.78}],"shop_type":"discount","shopcommon_id":48,"shop_name":"1803号普通商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\",\"addition_value\":\"111\"},{\"addition_id\":2,\"addition_key\":\"qq\",\"addition_value\":\"111\"}]"},{"addition_id":"75","catalogItems":[{"catalog_id":42,"catalog_img":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2938123505,618018376&fm=27&gp=0.jpg","original_price":299,"catalog_name":"A款","current_price":199,"shop_count":1,"pay_money":199},{"catalog_id":43,"catalog_img":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3496132289,3783785834&fm=27&gp=0.jpg","original_price":39,"catalog_name":"B款","current_price":30,"shop_count":1,"pay_money":30}],"shop_type":"ordinary","shopcommon_id":53,"shop_name":"180105无附加信息普通商品EXO","content":"[]"},{"addition_id":"76","catalogItems":[{"catalog_id":32,"catalog_img":"abc","original_price":50,"catalog_name":"测试优惠商品","current_price":40,"shop_count":1,"pay_money":40}],"shop_type":"discount","shopcommon_id":47,"shop_name":"测试优惠商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\",\"addition_value\":\"111\"}]"}],"total_express_fee":0}]
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
         * order_status : toPay
         * nick_name : 11111111111
         * head_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * total_pay_money : 80.0
         * order_num : yzy201801101411391920001
         * order_id : 135
         * businesser_vid : 4267
         * orderItems : [{"addition_id":"68","catalogItems":[{"catalog_id":32,"catalog_img":"abc","original_price":50,"catalog_name":"测试优惠商品","current_price":40,"shop_count":2,"pay_money":80}],"shop_type":"discount","shopcommon_id":47,"shop_name":"测试优惠商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\"}]"}]
         * total_express_fee : 0.0
         */
        public String getNowTime() {
            return nowTime;
        }

        public void setNowTime(String nowTime) {
            this.nowTime = nowTime;
        }

        public String getFinal_end_time() {
            return final_end_time;
        }

        public void setFinal_end_time(String final_end_time) {
            this.final_end_time = final_end_time;
        }

        private  String create_time;

        public String getShippercode() {
            return shippercode;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        private String express_no;
        public void setShippercode(String shippercode) {
            this.shippercode = shippercode;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFinal_start_time() {
            return final_start_time;
        }

        public void setFinal_start_time(String final_start_time) {
            this.final_start_time = final_start_time;
        }
        private String shippercode;
        private String nowTime;
        private  String final_end_time;
        private  String final_start_time;
        private String order_status;
        private String nick_name;
        private String head_img;
        private double total_pay_money;
        private String order_num;
        private int order_id;
        private int businesser_vid;
        private double total_express_fee;
        private List<OrderItemsBean> orderItems;

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
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

        public double getTotal_pay_money() {
            return total_pay_money;
        }

        public void setTotal_pay_money(double total_pay_money) {
            this.total_pay_money = total_pay_money;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getBusinesser_vid() {
            return businesser_vid;
        }

        public void setBusinesser_vid(int businesser_vid) {
            this.businesser_vid = businesser_vid;
        }

        public double getTotal_express_fee() {
            return total_express_fee;
        }

        public void setTotal_express_fee(double total_express_fee) {
            this.total_express_fee = total_express_fee;
        }

        public List<OrderItemsBean> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemsBean> orderItems) {
            this.orderItems = orderItems;
        }

        public static class OrderItemsBean {
            /**
             * addition_id : 68
             * catalogItems : [{"catalog_id":32,"catalog_img":"abc","original_price":50,"catalog_name":"测试优惠商品","current_price":40,"shop_count":2,"pay_money":80}]
             * shop_type : discount
             * shopcommon_id : 47
             * shop_name : 测试优惠商品EXO
             * content : [{"addition_id":1,"addition_key":"sfz"}]
             */
            private String addition_id;
            private String shop_type;
            private int shopcommon_id;
            private String shop_name;

            public String getShop_label() {
                return shop_label;
            }

            public void setShop_label(String shop_label) {
                this.shop_label = shop_label;
            }

            private String content;
            private  String shop_label;
            private List<CatalogItemsBean> catalogItems;

            public List<AdditionBean> getAdditionBeanList() {
                return additionBeanList;
            }

            public void setAdditionBeanList(List<AdditionBean> additionBeanList) {
                this.additionBeanList = additionBeanList;
            }

            private List<AdditionBean> additionBeanList;
            public String getAddition_id() {
                return addition_id;
            }

            public void setAddition_id(String addition_id) {
                this.addition_id = addition_id;
            }

            public String getShop_type() {
                return shop_type;
            }

            public void setShop_type(String shop_type) {
                this.shop_type = shop_type;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<CatalogItemsBean> getCatalogItems() {
                return catalogItems;
            }

            public void setCatalogItems(List<CatalogItemsBean> catalogItems) {
                this.catalogItems = catalogItems;
            }

            public static class AdditionBean{

                /**
                 * addition_id : 1
                 * field : 身份证
                 * placehold : 请输入身份证信息，用于海关
                 * addition_key : sfz
                 */

                private int addition_id;
                private String field;
                private String placehold;
                private String addition_key;
                private String addition_value;

                public String getAddition_value() {
                    return addition_value;
                }

                public void setAddition_value(String addition_value) {
                    this.addition_value = addition_value;
                }

                public int getAddition_id() {
                    return addition_id;
                }

                public void setAddition_id(int addition_id) {
                    this.addition_id = addition_id;
                }

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getPlacehold() {
                    return placehold;
                }

                public void setPlacehold(String placehold) {
                    this.placehold = placehold;
                }

                public String getAddition_key() {
                    return addition_key;
                }

                public void setAddition_key(String addition_key) {
                    this.addition_key = addition_key;
                }
            }
            public static class CatalogItemsBean {
                public String getShop_name() {
                    return shop_name;
                }

                public void setShop_name(String shop_name) {
                    this.shop_name = shop_name;
                }

                public String getShop_label() {
                    return shop_label;
                }

                public void setShop_label(String shop_label) {
                    this.shop_label = shop_label;
                }

                /**
                 * catalog_id : 32
                 * catalog_img : abc
                 * original_price : 50.0
                 * catalog_name : 测试优惠商品
                 * current_price : 40.0
                 * shop_count : 2
                 * pay_money : 80.0

                 */

                private String shop_label;
                private String shop_name;
                private int catalog_id;
                private String catalog_img;
                private String original_price;
                private String catalog_name;
                private String current_price;
                private int shop_count;
                private String pay_money;
                private String order_status;
                private String nowTime;
                private  String final_end_time;
                private  String final_start_time;
                public String getOrder_status() {
                    return order_status;
                }

                public void setOrder_status(String order_status) {
                    this.order_status = order_status;
                }

                public String getNowTime() {
                    return nowTime;
                }

                public void setNowTime(String nowTime) {
                    this.nowTime = nowTime;
                }

                public String getFinal_end_time() {
                    return final_end_time;
                }

                public void setFinal_end_time(String final_end_time) {
                    this.final_end_time = final_end_time;
                }

                public String getFinal_start_time() {
                    return final_start_time;
                }

                public void setFinal_start_time(String final_start_time) {
                    this.final_start_time = final_start_time;
                }

                public int getCatalog_id() {
                    return catalog_id;
                }

                public void setCatalog_id(int catalog_id) {
                    this.catalog_id = catalog_id;
                }

                public String getCatalog_img() {
                    return catalog_img;
                }

                public void setCatalog_img(String catalog_img) {
                    this.catalog_img = catalog_img;
                }

                public String getOriginal_price() {
                    return original_price;
                }

                public void setOriginal_price(String original_price) {
                    this.original_price = original_price;
                }

                public String getCatalog_name() {
                    return catalog_name;
                }

                public void setCatalog_name(String catalog_name) {
                    this.catalog_name = catalog_name;
                }

                public String getCurrent_price() {
                    return current_price;
                }

                public void setCurrent_price(String current_price) {
                    this.current_price = current_price;
                }

                public int getShop_count() {
                    return shop_count;
                }

                public void setShop_count(int shop_count) {
                    this.shop_count = shop_count;
                }

                public String getPay_money() {
                    return pay_money;
                }

                public void setPay_money(String pay_money) {
                    this.pay_money = pay_money;
                }
            }
        }
    }
}
