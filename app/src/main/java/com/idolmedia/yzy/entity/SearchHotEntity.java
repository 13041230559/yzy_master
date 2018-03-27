package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 11:12
 * 描述：
 */

public class SearchHotEntity implements Parcelable {


    /**
     * msg : 查询成功
     * datas : {"idou":["安","张"],"product":["EXO"],"activity":["EX4","DDO4","来来来4","大酒店4"],"auth":[],"infomation":["EX3","DDO3"]}
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
        private List<String> idou;
        private List<String> product;
        private List<String> activity;
        private List<String> auth;
        private List<String> infomation;

        public List<String> getIdou() {
            return idou;
        }

        public void setIdou(List<String> idou) {
            this.idou = idou;
        }

        public List<String> getProduct() {
            return product;
        }

        public void setProduct(List<String> product) {
            this.product = product;
        }

        public List<String> getActivity() {
            return activity;
        }

        public void setActivity(List<String> activity) {
            this.activity = activity;
        }

        public List<String> getAuth() {
            return auth;
        }

        public void setAuth(List<String> auth) {
            this.auth = auth;
        }

        public List<String> getInfomation() {
            return infomation;
        }

        public void setInfomation(List<String> infomation) {
            this.infomation = infomation;
        }
    }

    public SearchHotEntity() {
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

    protected SearchHotEntity(Parcel in) {
        this.msg = in.readString();
        this.datas = in.readParcelable(DatasBean.class.getClassLoader());
        this.resultCode = in.readInt();
    }

    public static final Creator<SearchHotEntity> CREATOR = new Creator<SearchHotEntity>() {
        @Override
        public SearchHotEntity createFromParcel(Parcel source) {
            return new SearchHotEntity(source);
        }

        @Override
        public SearchHotEntity[] newArray(int size) {
            return new SearchHotEntity[size];
        }
    };
}
