package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/4 19:18
 * 描述：
 */

public class AddressDefaultEntity implements Parcelable {

    /**
     * msg : 获取默认收货地址成功
     * resultCode : 01
     * addressData : {"city_name":"北京市YZY北京市YZY丰台区","update_time":"2018-01-04 17:55:17.831","consignee":"王盈堃","phone":"13041360559","detail_address":"北京丰台区马家堡西里","address_id":27,"virtualuser_id":4275,"area_id":110102,"is_default":0}
     */

    private String msg;
    private int resultCode;
    private AddressDataBean addressData;

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

    public AddressDataBean getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressDataBean addressData) {
        this.addressData = addressData;
    }

    public static class AddressDataBean {
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.resultCode);
        dest.writeParcelable((Parcelable) this.addressData, flags);
    }

    public AddressDefaultEntity() {
    }

    protected AddressDefaultEntity(Parcel in) {
        this.msg = in.readString();
        this.resultCode = in.readInt();
        this.addressData = in.readParcelable(AddressDataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AddressDefaultEntity> CREATOR = new Parcelable.Creator<AddressDefaultEntity>() {
        @Override
        public AddressDefaultEntity createFromParcel(Parcel source) {
            return new AddressDefaultEntity(source);
        }

        @Override
        public AddressDefaultEntity[] newArray(int size) {
            return new AddressDefaultEntity[size];
        }
    };
}
