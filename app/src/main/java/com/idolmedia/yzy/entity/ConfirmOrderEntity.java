package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 21:06
 * 描述：
 */

public class ConfirmOrderEntity implements Parcelable {


    /**
     * msg : 查询成功
     * shopData : [{"from_id":4267,"head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","shops":[{"cartInfo":[{"original_price":200.11,"catalog_title":"188","cartitems_id":127,"unint_price":199.89,"caralog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","quantity_count":1},{"original_price":222,"catalog_title":"A款","cartitems_id":128,"unint_price":99,"caralog_img":"http://imgsrc.baidu.com/imgad/pic/item/42166d224f4a20a4ea0d8d139a529822730ed0d7.jpg","quantity_count":1}],"shop_label":"热门HOT","shopcommon_id":48,"shop_name":"1803号普通商品EXO","addition":"[{\"addition_id\":1,\"field\":\"身份证\",\"placehold\":\"请输入身份证信息，用于海关\",\"addition_key\":\"sfz\"},{\"addition_id\":2,\"field\":\"QQ号\",\"placehold\":\"请输入您的QQ号，方便后期与您沟通\",\"addition_key\":\"qq\"}]"},{"cartInfo":[{"original_price":50,"catalog_title":"测试优惠商品","cartitems_id":147,"unint_price":40,"caralog_img":"abc","quantity_count":1}],"shop_label":"娱自营","shopcommon_id":47,"shop_name":"测试优惠商品EXO","addition":"[{\"addition_id\":1,\"field\":\"身份证\",\"placehold\":\"请输入身份证信息，用于海关\",\"addition_key\":\"sfz\"}]"}],"from_name":"11111111111"},{"from_id":4275,"head_img":"http://otkny7iog.bkt.clouddn.com/headImg/333719088c9d47839d98d462f08ab581.jpg","shops":[{"cartInfo":[{"original_price":10,"catalog_title":"普通商品测试","cartitems_id":129,"unint_price":30,"caralog_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","quantity_count":2}],"shop_label":"娱自营","shopcommon_id":39,"shop_name":"测试普通商品EXO","addition":"[{\"addition_id\":1,\"field\":\"身份证\",\"placehold\":\"请输入身份证信息，用于海关\",\"addition_key\":\"sfz\"}]"}],"from_name":"Joker.Wang"}]
     * resultCode : 01
     */

    private String msg;
    private int resultCode;
    private List<ShopDataBean> shopData;

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

    public List<ShopDataBean> getShopData() {
        return shopData;
    }

    public void setShopData(List<ShopDataBean> shopData) {
        this.shopData = shopData;
    }

    public static class ShopDataBean implements Parcelable {
        /**
         * from_id : 4267
         * head_img : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * shops : [{"cartInfo":[{"original_price":200.11,"catalog_title":"188","cartitems_id":127,"unint_price":199.89,"caralog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","quantity_count":1},{"original_price":222,"catalog_title":"A款","cartitems_id":128,"unint_price":99,"caralog_img":"http://imgsrc.baidu.com/imgad/pic/item/42166d224f4a20a4ea0d8d139a529822730ed0d7.jpg","quantity_count":1}],"shop_label":"热门HOT","shopcommon_id":48,"shop_name":"1803号普通商品EXO","addition":"[{\"addition_id\":1,\"field\":\"身份证\",\"placehold\":\"请输入身份证信息，用于海关\",\"addition_key\":\"sfz\"},{\"addition_id\":2,\"field\":\"QQ号\",\"placehold\":\"请输入您的QQ号，方便后期与您沟通\",\"addition_key\":\"qq\"}]"},{"cartInfo":[{"original_price":50,"catalog_title":"测试优惠商品","cartitems_id":147,"unint_price":40,"caralog_img":"abc","quantity_count":1}],"shop_label":"娱自营","shopcommon_id":47,"shop_name":"测试优惠商品EXO","addition":"[{\"addition_id\":1,\"field\":\"身份证\",\"placehold\":\"请输入身份证信息，用于海关\",\"addition_key\":\"sfz\"}]"}]
         * from_name : 11111111111
         */

        private int from_id;
        private String head_img;
        private String from_name;
        private List<ShopsBean> shops;

        public int getFrom_id() {
            return from_id;
        }

        public void setFrom_id(int from_id) {
            this.from_id = from_id;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getFrom_name() {
            return from_name;
        }

        public void setFrom_name(String from_name) {
            this.from_name = from_name;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class ShopsBean implements Parcelable {
            /**
             * cartInfo : [{"original_price":200.11,"catalog_title":"188","cartitems_id":127,"unint_price":199.89,"caralog_img":"http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg","quantity_count":1},{"original_price":222,"catalog_title":"A款","cartitems_id":128,"unint_price":99,"caralog_img":"http://imgsrc.baidu.com/imgad/pic/item/42166d224f4a20a4ea0d8d139a529822730ed0d7.jpg","quantity_count":1}]
             * shop_label : 热门HOT
             * shopcommon_id : 48
             * shop_name : 1803号普通商品EXO
             * addition : [{"addition_id":1,"field":"身份证","placehold":"请输入身份证信息，用于海关","addition_key":"sfz"},{"addition_id":2,"field":"QQ号","placehold":"请输入您的QQ号，方便后期与您沟通","addition_key":"qq"}]
             */

            private int shopcommon_id;
            private String shop_label;
            private String shop_name;
            private String addition;
            private List<CartInfoBean> cartInfo;
            private List<AdditionBean> additionBeanList;

            public CartInfoBean getCartInfoBean() {
                return cartInfoBean;
            }

            public void setCartInfoBean(CartInfoBean cartInfoBean) {
                this.cartInfoBean = cartInfoBean;
            }

            private CartInfoBean cartInfoBean;

            public List<AdditionBean> getAdditionBeanList() {
                return additionBeanList;
            }

            public void setAdditionBeanList(List<AdditionBean> additionBeanList) {
                this.additionBeanList = additionBeanList;
            }

            public String getShop_label() {
                return shop_label;
            }
            public void setShop_label(String shop_label) {
                this.shop_label = shop_label;
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

            public String getAddition() {
                return addition;
            }

            public void setAddition(String addition) {
                this.addition = addition;
            }

            public List<CartInfoBean> getCartInfo() {
                return cartInfo;
            }

            public void setCartInfo(List<CartInfoBean> cartInfo) {
                this.cartInfo = cartInfo;
            }

            public static class CartInfoBean implements Parcelable {
                /**
                 * original_price : 200.11
                 * catalog_title : 188
                 * cartitems_id : 127
                 * unint_price : 199.89
                 * caralog_img : http://imgsrc.baidu.com/imgad/pic/item/bd315c6034a85edfa70da23143540923dd54751a.jpg
                 * quantity_count : 1
                 */

                private double original_price;
                private String catalog_title;
                private int cartitems_id;
                private double unint_price;
                private String caralog_img;
                private int quantity_count;
                private String shop_label;
                private String ssc_id;

                public String getSsc_id() {
                    return ssc_id;
                }

                public void setSsc_id(String ssc_id) {
                    this.ssc_id = ssc_id;
                }

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

                public double getOriginal_price() {
                    return original_price;
                }

                public void setOriginal_price(double original_price) {
                    this.original_price = original_price;
                }

                public String getCatalog_title() {
                    return catalog_title;
                }

                public void setCatalog_title(String catalog_title) {
                    this.catalog_title = catalog_title;
                }

                public int getCartitems_id() {
                    return cartitems_id;
                }

                public void setCartitems_id(int cartitems_id) {
                    this.cartitems_id = cartitems_id;
                }

                public double getUnint_price() {
                    return unint_price;
                }

                public void setUnint_price(double unint_price) {
                    this.unint_price = unint_price;
                }

                public String getCaralog_img() {
                    return caralog_img;
                }

                public void setCaralog_img(String caralog_img) {
                    this.caralog_img = caralog_img;
                }

                public int getQuantity_count() {
                    return quantity_count;
                }

                public void setQuantity_count(int quantity_count) {
                    this.quantity_count = quantity_count;
                }

                public CartInfoBean() {
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeDouble(this.original_price);
                    dest.writeString(this.catalog_title);
                    dest.writeInt(this.cartitems_id);
                    dest.writeDouble(this.unint_price);
                    dest.writeString(this.caralog_img);
                    dest.writeInt(this.quantity_count);
                    dest.writeString(this.shop_label);
                    dest.writeString(this.ssc_id);
                    dest.writeString(this.shop_name);
                }

                protected CartInfoBean(Parcel in) {
                    this.original_price = in.readDouble();
                    this.catalog_title = in.readString();
                    this.cartitems_id = in.readInt();
                    this.unint_price = in.readDouble();
                    this.caralog_img = in.readString();
                    this.quantity_count = in.readInt();
                    this.shop_label = in.readString();
                    this.ssc_id = in.readString();
                    this.shop_name = in.readString();
                }

                public static final Creator<CartInfoBean> CREATOR = new Creator<CartInfoBean>() {
                    @Override
                    public CartInfoBean createFromParcel(Parcel source) {
                        return new CartInfoBean(source);
                    }

                    @Override
                    public CartInfoBean[] newArray(int size) {
                        return new CartInfoBean[size];
                    }
                };
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.shopcommon_id);
                dest.writeString(this.shop_label);
                dest.writeString(this.shop_name);
                dest.writeString(this.addition);
                dest.writeList(this.cartInfo);
                dest.writeList(this.additionBeanList);
                dest.writeParcelable(this.cartInfoBean, flags);
            }

            public ShopsBean() {
            }

            protected ShopsBean(Parcel in) {
                this.shopcommon_id = in.readInt();
                this.shop_label = in.readString();
                this.shop_name = in.readString();
                this.addition = in.readString();
                this.cartInfo = new ArrayList<CartInfoBean>();
                in.readList(this.cartInfo, CartInfoBean.class.getClassLoader());
                this.additionBeanList = new ArrayList<AdditionBean>();
                in.readList(this.additionBeanList, AdditionBean.class.getClassLoader());
                this.cartInfoBean = in.readParcelable(CartInfoBean.class.getClassLoader());
            }

            public static final Creator<ShopsBean> CREATOR = new Creator<ShopsBean>() {
                @Override
                public ShopsBean createFromParcel(Parcel source) {
                    return new ShopsBean(source);
                }

                @Override
                public ShopsBean[] newArray(int size) {
                    return new ShopsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.from_id);
            dest.writeString(this.head_img);
            dest.writeString(this.from_name);
            dest.writeList(this.shops);
        }

        public ShopDataBean() {
        }

        protected ShopDataBean(Parcel in) {
            this.from_id = in.readInt();
            this.head_img = in.readString();
            this.from_name = in.readString();
            this.shops = new ArrayList<ShopsBean>();
            in.readList(this.shops, ShopsBean.class.getClassLoader());
        }

        public static final Creator<ShopDataBean> CREATOR = new Creator<ShopDataBean>() {
            @Override
            public ShopDataBean createFromParcel(Parcel source) {
                return new ShopDataBean(source);
            }

            @Override
            public ShopDataBean[] newArray(int size) {
                return new ShopDataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.resultCode);
        dest.writeList(this.shopData);
    }

    public ConfirmOrderEntity() {
    }

    protected ConfirmOrderEntity(Parcel in) {
        this.msg = in.readString();
        this.resultCode = in.readInt();
        this.shopData = new ArrayList<ShopDataBean>();
        in.readList(this.shopData, ShopDataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ConfirmOrderEntity> CREATOR = new Parcelable.Creator<ConfirmOrderEntity>() {
        @Override
        public ConfirmOrderEntity createFromParcel(Parcel source) {
            return new ConfirmOrderEntity(source);
        }

        @Override
        public ConfirmOrderEntity[] newArray(int size) {
            return new ConfirmOrderEntity[size];
        }
    };
}
