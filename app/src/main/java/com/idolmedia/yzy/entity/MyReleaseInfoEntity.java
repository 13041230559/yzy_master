package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/6 21:52
 * 描述：
 */

public class MyReleaseInfoEntity  implements Parcelable {

    /**
     * msg : 查询成功
     * datas : {"activityCount":0,"head_img":"http://q.qlogo.cn/qqapp/1106213827/5E7AB1FE42A8C8B9D655226C4748EA13/100","nick_name":"JOKER.WANG","is_attestation":0,"communityCount":7,"productCount":0}
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
         * activityCount : 0
         * head_img : http://q.qlogo.cn/qqapp/1106213827/5E7AB1FE42A8C8B9D655226C4748EA13/100
         * nick_name : JOKER.WANG
         * is_attestation : 0
         * communityCount : 7
         * productCount : 0
         */

        private int activityCount;
        private String head_img;
        private String nick_name;
        private int is_attestation;
        private int communityCount;
        private int productCount;

        public int getActivityCount() {
            return activityCount;
        }

        public void setActivityCount(int activityCount) {
            this.activityCount = activityCount;
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

        public int getIs_attestation() {
            return is_attestation;
        }

        public void setIs_attestation(int is_attestation) {
            this.is_attestation = is_attestation;
        }

        public int getCommunityCount() {
            return communityCount;
        }

        public void setCommunityCount(int communityCount) {
            this.communityCount = communityCount;
        }

        public int getProductCount() {
            return productCount;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeParcelable((Parcelable) this.datas, flags);
        dest.writeInt(this.resultCode);
    }

    public MyReleaseInfoEntity() {
    }

    protected MyReleaseInfoEntity(Parcel in) {
        this.msg = in.readString();
        this.datas = in.readParcelable(DatasBean.class.getClassLoader());
        this.resultCode = in.readInt();
    }

    public static final Creator<MyReleaseInfoEntity> CREATOR = new Creator<MyReleaseInfoEntity>() {
        @Override
        public MyReleaseInfoEntity createFromParcel(Parcel source) {
            return new MyReleaseInfoEntity(source);
        }

        @Override
        public MyReleaseInfoEntity[] newArray(int size) {
            return new MyReleaseInfoEntity[size];
        }
    };
}
