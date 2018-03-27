package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/15 17:49
 * 描述：  Banner轮播
 */

public class BannerEntity implements Parcelable {

    /**
     * msg : 查询成功
     * datas : [{"outside_title":"微博","skip_type":"outsideUrl","banner_id":46,"banner_type":"yu,information,community,recommend","outside_url":"https://weibo.com/6297598713/G5upYnhIf?from=page_1006066297598713_profile&wvr=6&mod=weibotime&type=comment#_rnd1519964888063","shop_type":"","shopcommon_id":"0","pic_url":"http://otkny7iog.bkt.clouddn.com/FsvMtSs7vnL1r5EeyEA4KsuqHDa3","keyword":"","pic_url_iphonex":""},{"outside_title":"","skip_type":"","banner_id":52,"banner_type":"yu","outside_url":"","shop_type":"","shopcommon_id":"0","pic_url":"http://otkny7iog.bkt.clouddn.com/FtM3vh0tbZyV0smhm6lIcCeAy5el","keyword":"","pic_url_iphonex":""},{"outside_title":"","skip_type":"shop","banner_id":53,"banner_type":"yu","outside_url":"","shop_type":"ordinary","shopcommon_id":"103","pic_url":"http://otkny7iog.bkt.clouddn.com/FkiGeV9_4UozyjIcf4XiFwMtORG_","keyword":"","pic_url_iphonex":""}]
     * resultCode : 01
     */

    private String msg;
    private int resultCode;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Parcelable {
        /**
         * outside_title : 微博
         * skip_type : outsideUrl
         * banner_id : 46
         * banner_type : yu,information,community,recommend
         * outside_url : https://weibo.com/6297598713/G5upYnhIf?from=page_1006066297598713_profile&wvr=6&mod=weibotime&type=comment#_rnd1519964888063
         * shop_type :
         * shopcommon_id : 0
         * pic_url : http://otkny7iog.bkt.clouddn.com/FsvMtSs7vnL1r5EeyEA4KsuqHDa3
         * keyword :
         * pic_url_iphonex :
         */

        private String outside_title;
        private String skip_type;
        private int banner_id;
        private String banner_type;
        private String outside_url;
        private String shop_type;
        private int shopcommon_id;
        private String pic_url;
        private String keyword;
        private String pic_url_iphonex;

        public String getOutside_title() {
            return outside_title;
        }

        public void setOutside_title(String outside_title) {
            this.outside_title = outside_title;
        }

        public String getSkip_type() {
            return skip_type;
        }

        public void setSkip_type(String skip_type) {
            this.skip_type = skip_type;
        }

        public int getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(int banner_id) {
            this.banner_id = banner_id;
        }

        public String getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(String banner_type) {
            this.banner_type = banner_type;
        }

        public String getOutside_url() {
            return outside_url;
        }

        public void setOutside_url(String outside_url) {
            this.outside_url = outside_url;
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

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getPic_url_iphonex() {
            return pic_url_iphonex;
        }

        public void setPic_url_iphonex(String pic_url_iphonex) {
            this.pic_url_iphonex = pic_url_iphonex;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.outside_title);
            dest.writeString(this.skip_type);
            dest.writeInt(this.banner_id);
            dest.writeString(this.banner_type);
            dest.writeString(this.outside_url);
            dest.writeString(this.shop_type);
            dest.writeInt(this.shopcommon_id);
            dest.writeString(this.pic_url);
            dest.writeString(this.keyword);
            dest.writeString(this.pic_url_iphonex);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.outside_title = in.readString();
            this.skip_type = in.readString();
            this.banner_id = in.readInt();
            this.banner_type = in.readString();
            this.outside_url = in.readString();
            this.shop_type = in.readString();
            this.shopcommon_id = in.readInt();
            this.pic_url = in.readString();
            this.keyword = in.readString();
            this.pic_url_iphonex = in.readString();
        }

        public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel source) {
                return new DatasBean(source);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
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
        dest.writeList(this.datas);
    }

    public BannerEntity() {
    }

    protected BannerEntity(Parcel in) {
        this.msg = in.readString();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Creator<BannerEntity> CREATOR = new Creator<BannerEntity>() {
        @Override
        public BannerEntity createFromParcel(Parcel source) {
            return new BannerEntity(source);
        }

        @Override
        public BannerEntity[] newArray(int size) {
            return new BannerEntity[size];
        }
    };
}
