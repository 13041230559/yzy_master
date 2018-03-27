package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/11 18:52
 * 描述：
 */

public class MyOrderDetalisEntity {


    /**
     * msg : 查询成功
     * datas : {"consignee_address":"北京市北京东城区北京丰台区马家堡西里","consignee":"王盈堃","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","total_pay_money":668.78,"businesser_vid":4267,"orderItems":[{"addition_id":74,"catalogItems":[{"catalog_id":34,"catalog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","original_price":200.11,"catalog_name":"188","reserve_status":"","is_reserve":0,"current_price":199.89,"shop_count":2,"pay_money":399.78}],"shop_label":"热门HOT","shop_type":"discount","shopcommon_id":48,"shop_name":"1803号普通商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\",\"addition_value\":\"111\"},{\"addition_id\":2,\"addition_key\":\"qq\",\"addition_value\":\"111\"}]"},{"addition_id":75,"catalogItems":[{"catalog_id":42,"catalog_img":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2938123505,618018376&fm=27&gp=0.jpg","original_price":299,"catalog_name":"A款","reserve_status":"","is_reserve":0,"current_price":199,"shop_count":1,"pay_money":199},{"catalog_id":43,"catalog_img":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3496132289,3783785834&fm=27&gp=0.jpg","original_price":39,"catalog_name":"B款","reserve_status":"","is_reserve":0,"current_price":30,"shop_count":1,"pay_money":30}],"shop_label":"快来","shop_type":"ordinary","shopcommon_id":53,"shop_name":"180105无附加信息普通商品EXO","content":"[]"},{"addition_id":76,"catalogItems":[{"catalog_id":32,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":50,"catalog_name":"测试优惠商品","reserve_status":"","is_reserve":0,"current_price":40,"shop_count":1,"pay_money":40}],"shop_label":"娱自营","shop_type":"discount","shopcommon_id":47,"shop_name":"测试优惠商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\",\"addition_value\":\"111\"}]"}],"total_express_fee":0,"pay_time":"","order_status":"toPay","creat_time":"2018-01-10 17:43:17","phone":"13041230559","nick_name":"11111111111","order_num":"yzy201801101743178310001","order_id":140}
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
         * consignee_address : 北京市北京东城区北京丰台区马家堡西里
         * consignee : 王盈堃
         * head_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * total_pay_money : 668.78
         * businesser_vid : 4267
         * orderItems : [{"addition_id":74,"catalogItems":[{"catalog_id":34,"catalog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","original_price":200.11,"catalog_name":"188","reserve_status":"","is_reserve":0,"current_price":199.89,"shop_count":2,"pay_money":399.78}],"shop_label":"热门HOT","shop_type":"discount","shopcommon_id":48,"shop_name":"1803号普通商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\",\"addition_value\":\"111\"},{\"addition_id\":2,\"addition_key\":\"qq\",\"addition_value\":\"111\"}]"},{"addition_id":75,"catalogItems":[{"catalog_id":42,"catalog_img":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2938123505,618018376&fm=27&gp=0.jpg","original_price":299,"catalog_name":"A款","reserve_status":"","is_reserve":0,"current_price":199,"shop_count":1,"pay_money":199},{"catalog_id":43,"catalog_img":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3496132289,3783785834&fm=27&gp=0.jpg","original_price":39,"catalog_name":"B款","reserve_status":"","is_reserve":0,"current_price":30,"shop_count":1,"pay_money":30}],"shop_label":"快来","shop_type":"ordinary","shopcommon_id":53,"shop_name":"180105无附加信息普通商品EXO","content":"[]"},{"addition_id":76,"catalogItems":[{"catalog_id":32,"catalog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","original_price":50,"catalog_name":"测试优惠商品","reserve_status":"","is_reserve":0,"current_price":40,"shop_count":1,"pay_money":40}],"shop_label":"娱自营","shop_type":"discount","shopcommon_id":47,"shop_name":"测试优惠商品EXO","content":"[{\"addition_id\":1,\"addition_key\":\"sfz\",\"addition_value\":\"111\"}]"}]
         * total_express_fee : 0.0
         * pay_time :
         * order_status : toPay
         * creat_time : 2018-01-10 17:43:17
         * phone : 13041230559
         * nick_name : 11111111111
         * order_num : yzy201801101743178310001
         * order_id : 140
         */
        private String nowTime;
        private  String final_end_time;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        private  String final_start_time;
        private String consignee_address;
        private String consignee;
        private String express_no;
        private String shippercode;

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getShippercode() {
            return shippercode;
        }

        public void setShippercode(String shippercode) {
            this.shippercode = shippercode;
        }

        private String head_img;
        private String total_pay_money;
        private String businesser_vid;
        private String total_express_fee;
        private String pay_time;
        private String order_status;
        private String create_time;
        private String phone;
        private String nick_name;
        private String order_num;
        private String order_id;
        private List<OrderItemsBean> orderItems;

        public String getConsignee_address() {
            return consignee_address;
        }

        public void setConsignee_address(String consignee_address) {
            this.consignee_address = consignee_address;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getTotal_pay_money() {
            return total_pay_money;
        }

        public void setTotal_pay_money(String total_pay_money) {
            this.total_pay_money = total_pay_money;
        }

        public String getBusinesser_vid() {
            return businesser_vid;
        }

        public void setBusinesser_vid(String businesser_vid) {
            this.businesser_vid = businesser_vid;
        }

        public String getTotal_express_fee() {
            return total_express_fee;
        }

        public void setTotal_express_fee(String total_express_fee) {
            this.total_express_fee = total_express_fee;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getCreat_time() {
            return create_time;
        }

        public void setCreat_time(String creat_time) {
            this.create_time = creat_time;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public List<OrderItemsBean> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemsBean> orderItems) {
            this.orderItems = orderItems;
        }

        public static class OrderItemsBean {
            /**
             * addition_id : 74
             * catalogItems : [{"catalog_id":34,"catalog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","original_price":200.11,"catalog_name":"188","reserve_status":"","is_reserve":0,"current_price":199.89,"shop_count":2,"pay_money":399.78}]
             * shop_label : 热门HOT
             * shop_type : discount
             * shopcommon_id : 48
             * shop_name : 1803号普通商品EXO
             * content : [{"addition_id":1,"addition_key":"sfz","addition_value":"111"},{"addition_id":2,"addition_key":"qq","addition_value":"111"}]
             */

            private String addition_id;
            private String shop_label;
            private String shop_type;
            private String shopcommon_id;
            private String shop_name;
            private String content;
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

            public String getShop_label() {
                return shop_label;
            }

            public void setShop_label(String shop_label) {
                this.shop_label = shop_label;
            }

            public String getShop_type() {
                return shop_type;
            }

            public void setShop_type(String shop_type) {
                this.shop_type = shop_type;
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
                public String getOrder_status() {
                    return order_status;
                }

                public void setOrder_status(String order_status) {
                    this.order_status = order_status;
                }

                /**
                 * catalog_id : 34
                 * catalog_img : http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg
                 * original_price : 200.11
                 * catalog_name : 188
                 * reserve_status :
                 * is_reserve : 0
                 * current_price : 199.89
                 * shop_count : 2
                 * pay_money : 399.78
                 */


                private String order_status;
                private String shop_label;

                public String getShop_label() {
                    return shop_label;
                }

                public void setShop_label(String shop_label) {
                    this.shop_label = shop_label;
                }

                public String getShop_name() {
                    return shop_name;
                }

                public void setShop_name(String shop_name) {
                    this.shop_name = shop_name;
                }

                private String shop_name;
                private String catalog_id;
                private String catalog_img;
                private String original_price;
                private String catalog_name;
                private String reserve_status;
                private String is_reserve;
                private String current_price;
                private String shop_count;
                private String pay_money;
                private String nowTime;
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

                private  String final_end_time;
                private  String final_start_time;
                public String getCatalog_id() {
                    return catalog_id;
                }

                public void setCatalog_id(String catalog_id) {
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

                public String getReserve_status() {
                    return reserve_status;
                }

                public void setReserve_status(String reserve_status) {
                    this.reserve_status = reserve_status;
                }

                public String getIs_reserve() {
                    return is_reserve;
                }

                public void setIs_reserve(String is_reserve) {
                    this.is_reserve = is_reserve;
                }

                public String getCurrent_price() {
                    return current_price;
                }

                public void setCurrent_price(String current_price) {
                    this.current_price = current_price;
                }

                public String getShop_count() {
                    return shop_count;
                }

                public void setShop_count(String shop_count) {
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
