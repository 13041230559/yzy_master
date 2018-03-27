package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/11 15:59
 * 描述：
 */

public class AddressEntity{


    /**
     * msg : 收货地址查询成功
     * resultCode : 01
     * addressData : [{"city_name":"北京市YZY北京市YZY丰台区","update_time":"2018-01-04 17:55:17.831","consignee":"王盈堃","phone":"13041360559","detail_address":"北京丰台区马家堡西里","address_id":27,"virtualuser_id":4275,"area_id":110102,"is_default":0},{"city_name":"北京市YZY北京市YZY丰台区","update_time":"2018-01-04 19:34:47.178","consignee":"王盈堃","phone":"13041230559","detail_address":"丰台区马家堡搜宝商务中心","address_id":28,"virtualuser_id":4275,"area_id":110102,"is_default":1}]
     */

    private String msg;
    private int resultCode;
    private List<AddressDataBean> addressData;

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

    public List<AddressDataBean> getAddressData() {
        return addressData;
    }

    public void setAddressData(List<AddressDataBean> addressData) {
        this.addressData = addressData;
    }

    public static class AddressDataBean implements Parcelable {

        /**
         * city_name : 北京市YZY北京市YZY丰台区
         * update_time : 2018-01-04 17:55:17.831
         * consignee : 王盈堃
         * phone : 13041360559
         * detail_address : 北京丰台区马家堡西里
         * address_id : 27
         * virtualuser_id : 4275
         * area_id : 110102
         * is_default : 0
         */

        private String city_name;
        private String update_time;
        private String consignee;
        private String phone;
        private String detail_address;
        private int address_id;
        private int virtualuser_id;
        private int area_id;
        private int is_default;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(int virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.city_name);
            dest.writeString(this.update_time);
            dest.writeString(this.consignee);
            dest.writeString(this.phone);
            dest.writeString(this.detail_address);
            dest.writeInt(this.address_id);
            dest.writeInt(this.virtualuser_id);
            dest.writeInt(this.area_id);
            dest.writeInt(this.is_default);
        }

        public AddressDataBean() {
        }

        protected AddressDataBean(Parcel in) {
            this.city_name = in.readString();
            this.update_time = in.readString();
            this.consignee = in.readString();
            this.phone = in.readString();
            this.detail_address = in.readString();
            this.address_id = in.readInt();
            this.virtualuser_id = in.readInt();
            this.area_id = in.readInt();
            this.is_default = in.readInt();
        }

        public static final Parcelable.Creator<AddressDataBean> CREATOR = new Parcelable.Creator<AddressDataBean>() {
            @Override
            public AddressDataBean createFromParcel(Parcel source) {
                return new AddressDataBean(source);
            }

            @Override
            public AddressDataBean[] newArray(int size) {
                return new AddressDataBean[size];
            }
        };
    }
}
