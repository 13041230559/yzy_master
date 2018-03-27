package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/14 15:55
 * 描述：排行榜
 */

public class RankingEntity implements Parcelable {
    /**
     * msg : 查询成功
     * totalRow : 22
     * totalPage : 3
     * datas : [{"money":166,"uso_id":12,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":22,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":21,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":20,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":19,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":18,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":17,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":16,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":15,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"},{"money":166,"uso_id":14,"nick_name":"Joker.Wang","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg"}]
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
         * money : 166.0
         * uso_id : 12
         * nick_name : Joker.Wang
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/525847715ae34e02914e03de549f59bf.jpg
         */

        private double money;
        private int uso_id;
        private String nick_name;
        private String head_img;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getUso_id() {
            return uso_id;
        }

        public void setUso_id(int uso_id) {
            this.uso_id = uso_id;
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.totalRow);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.resultCode);
        dest.writeList(this.datas);
    }

    public RankingEntity() {
    }

    protected RankingEntity(Parcel in) {
        this.msg = in.readString();
        this.totalRow = in.readInt();
        this.totalPage = in.readInt();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Creator<RankingEntity> CREATOR = new Creator<RankingEntity>() {
        @Override
        public RankingEntity createFromParcel(Parcel source) {
            return new RankingEntity(source);
        }

        @Override
        public RankingEntity[] newArray(int size) {
            return new RankingEntity[size];
        }
    };
}
